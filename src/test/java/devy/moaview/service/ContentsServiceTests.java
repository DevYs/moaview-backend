package devy.moaview.service;

import devy.moaview.controller.request.RequestContents;
import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.controller.resopnse.ResponseContents;
import devy.moaview.domain.Contents;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 콘텐츠 서비스 테스트
 *
 * @author DevY
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentsServiceTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private ContentsTypeMapper contentsTypeMapper;

    @Autowired
    private TargetContentsMapper targetContentsMapper;

    @Autowired
    private ContentsService contentsService;

    private Site todayhumor;
    private Site clien;

    private ContentsType humor;
    private ContentsType community;

    /**
     * 테스트 데이터
     */
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

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(humor.getContentsTypeNo());
        targetContents.setSiteNo(todayhumor.getSiteNo());
        targetContents.setTargetContentsName("베오베");
        targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
        targetContents.setTargetContentsCssSelector("div > ul > .test");
        targetContents.registry(targetContentsMapper);
    }

    /**
     * TODO 콘텐츠 등록 테스트
     */
    @Test
    public void testRegistryContents() {
        TargetContents targetContents = (TargetContents) TargetContents.list(new RequestTargetContents(), targetContentsMapper).get(0);

        Contents contents = new Contents();
        contents.setContentsTypeNo(targetContents.getContentsTypeNo());
        contents.setSiteNo(targetContents.getSiteNo());
        contents.setTargetContentsNo(targetContents.getTargetContentsNo());
        contents.setTargetContentsName(targetContents.getTargetContentsName());
        contents.setTitle("테스트");
        contents.setDescription("테스트");
        contents.setUrl("http://www.test.com/");
        contents.setThumbnailUrl("http://www.test.com/test.jpg");
        contents.setRegDate(LocalDateTime.now());

        int i = contentsService.registryContents(contents);
        Assert.assertEquals(1, i);
    }

    /**
     * TODO 콘텐츠 목록 조회 테스트
     */
    @Test
    public void testListContents() {
        TargetContents targetContents = (TargetContents) TargetContents.list(new RequestTargetContents(), targetContentsMapper).get(0);

        Contents contents = new Contents();

        for(int i=0; i<100; i++) {
            contents.setContentsTypeNo(targetContents.getContentsTypeNo());
            contents.setSiteNo(targetContents.getSiteNo());
            contents.setTargetContentsNo(targetContents.getTargetContentsNo());
            contents.setTargetContentsName(targetContents.getTargetContentsName());
            contents.setTitle("테스트" + i);
            contents.setDescription("테스트" + i);
            contents.setUrl("http://www.test.com/" + i);
            contents.setThumbnailUrl("http://www.test.com/test.jpg");
            contents.setRegDate(LocalDateTime.now());
            contentsService.registryContents(contents);
        }

        List<Integer> contentsTypeNoList = new ArrayList<>();
        contentsTypeNoList.add(targetContents.getContentsTypeNo());

        RequestContents requestContents = new RequestContents();
        requestContents.setPageNo(1);
        requestContents.setContentsTypeNoList(contentsTypeNoList);
        requestContents.setTitle("테스트");

        ResponseContents responseContents = contentsService.listContents(requestContents);

        Assert.assertNotEquals(0, responseContents.getContentsTypeMap().size());
        Assert.assertNotEquals(0, responseContents.getContentsTypeMap().size());
        Assert.assertNotEquals(0, responseContents.getContentsList().size());
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
