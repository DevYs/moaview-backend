package devy.moaview.service;

import devy.moaview.controller.request.RequestSite;
import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.controller.resopnse.ResponseTargetContents;
import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.domain.TargetContents;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;
import devy.moaview.service.mapper.TargetContentsMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 콘텐츠 타입 서비스 테스트
 *
 * @author devy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetContentsServiceTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private ContentsTypeMapper contentsTypeMapper;

    @Autowired
    private TargetContentsService targetContentsService;

    private Site todayhumor;
    private Site clien;

    private ContentsType humor;
    private ContentsType community;

    @Before
    public void setDataSiteAndContentsType() {
        Site site = new Site();

        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://todayhumor.co.kr/");
        site.registry(siteMapper);

        site.setSiteName("클리앙");
        site.setSiteUrl("https://www.clien.net/service/");
        site.registry(siteMapper);

        List<Site> siteList = (List<Site>) Site.list(null, siteMapper);
        todayhumor = siteList.get(0);
        clien = siteList.get(1);

        ContentsType contentsType = new ContentsType();

        contentsType.setContentsTypeName("유머");
        contentsType.registry(contentsTypeMapper);

        contentsType.setContentsTypeName("커뮤니티");
        contentsType.registry(contentsTypeMapper);

        List<ContentsType> contentsTypeList = (List<ContentsType>) ContentsType.list(null, contentsTypeMapper);
        humor = contentsTypeList.get(0);
        community = contentsTypeList.get(1);
    }

    /**
     * TODO 타겟 콘텐츠 1건 조회 테스트
     */
    @Test
    public void testGetTargetContents() {
        ContentsType contentsType = (ContentsType) ContentsType.list(null, contentsTypeMapper).get(0);
        Site site = (Site) Site.list(new RequestSite(), siteMapper).get(0);

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        targetContents.setSiteNo(site.getSiteNo());
        targetContents.setTargetContentsName("테스트");
        targetContents.setTargetContentsUrl("http://www.test.co.kr/");
        targetContents.setTargetContentsCssSelector("div > ul > li");

        int i = targetContentsService.registryTargetContents(targetContents);

        Assert.assertEquals(1, i);

        targetContents = targetContentsService.getTargetContents(targetContents);

        Assert.assertNotNull(targetContents);
    }

    /**
     * TODO 타겟 콘텐츠 목록 조회 테스트
     */
    @Test
    public void testListTargetContents() {
        ContentsType contentsType = (ContentsType) ContentsType.list(null, contentsTypeMapper).get(0);
        Site site = (Site) Site.list(new RequestSite(), siteMapper).get(0);

        for(int i=0; i<100; i++) {
            TargetContents targetContents = new TargetContents();
            targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
            targetContents.setSiteNo(site.getSiteNo());
            targetContents.setTargetContentsName("테스트");
            targetContents.setTargetContentsUrl("http://www.test.co.kr/");
            targetContents.setTargetContentsCssSelector("div > ul > li");
            targetContentsService.registryTargetContents(targetContents);
        }

        RequestTargetContents requestTargetContents = new RequestTargetContents();
        requestTargetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        requestTargetContents.setSiteNo(site.getSiteNo());
        requestTargetContents.setPageNo(1);
        requestTargetContents.setSearchWord("테스트");

        ResponseTargetContents responseTargetContents = targetContentsService.listTargetContents(requestTargetContents);
        Assert.assertNotEquals(0, responseTargetContents.getTargetContentsList().size());
    }

    /**
     * TODO 타겟 콘텐츠 등록 테스트
     */
    @Test
    public void testRegistryTargetContents() {
        ContentsType contentsType = (ContentsType) ContentsType.list(null, contentsTypeMapper).get(0);
        Site site = (Site) Site.list(new RequestSite(), siteMapper).get(0);

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        targetContents.setSiteNo(site.getSiteNo());
        targetContents.setTargetContentsName("테스트");
        targetContents.setTargetContentsUrl("http://www.test.co.kr/");
        targetContents.setTargetContentsCssSelector("div > ul > li");

        int i = targetContentsService.registryTargetContents(targetContents);

        Assert.assertEquals(1, i);
    }

    /**
     * TODO 타겟 콘텐츠 수정 테스트
     */
    @Test
    public void testModifyTargetContents() {
        ContentsType contentsType = (ContentsType) ContentsType.list(null, contentsTypeMapper).get(0);
        Site site = (Site) Site.list(new RequestSite(), siteMapper).get(0);

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        targetContents.setSiteNo(site.getSiteNo());
        targetContents.setTargetContentsName("테스트");
        targetContents.setTargetContentsUrl("http://www.test.co.kr/");
        targetContents.setTargetContentsCssSelector("div > ul > li");

        int i = targetContentsService.registryTargetContents(targetContents);
        Assert.assertEquals(1, i);

        targetContents.setTargetContentsName("수정 테스트");
        i = targetContentsService.modifyTargetContents(targetContents);
        Assert.assertEquals(1, i);
    }

    /**
     * TODO 타겟 콘텐츠 삭제 테스트
     */
    @Test
    public void testRemoveTargetContents() {
        ContentsType contentsType = (ContentsType) ContentsType.list(null, contentsTypeMapper).get(0);
        Site site = (Site) Site.list(new RequestSite(), siteMapper).get(0);

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        targetContents.setSiteNo(site.getSiteNo());
        targetContents.setTargetContentsName("테스트");
        targetContents.setTargetContentsUrl("http://www.test.co.kr/");
        targetContents.setTargetContentsCssSelector("div > ul > li");

        int i = targetContentsService.registryTargetContents(targetContents);
        Assert.assertEquals(1, i);

        i = targetContentsService.removeTargetContents(targetContents);
        Assert.assertEquals(1, i);
    }

    /**
     * 각 테스트 실행 후 데이터 삭제
     */
    @After
    public void clearTable() {
        jdbcTemplate.execute("delete from contents_type");
        jdbcTemplate.execute("delete from site");
        jdbcTemplate.execute("delete from target_contents");
        jdbcTemplate.execute("delete from sqlite_sequence");
    }

}
