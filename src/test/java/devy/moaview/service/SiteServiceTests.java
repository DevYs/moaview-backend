package devy.moaview.service;

import devy.moaview.controller.request.RequestSite;
import devy.moaview.domain.Site;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 사이트 서비스 테스트
 *
 * @author devy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteServiceTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SiteService siteService;

    /**
     * 사이트 정보 1건 조회 테스트
     */
    @Test
    public void testGetSite() {
        Site site = new Site();
        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        int i = siteService.registrySite(site);

        Assert.assertEquals(1, i);

        Site result = siteService.getSite(site);

        Assert.assertNotNull(result);
    }

    /**
     * TODO 사이트 정보 목록 조회 테스트
     */
    @Test
    public void testListSite() {
        Site site = new Site();

        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        int i = siteService.registrySite(site);

        Assert.assertEquals(1, i);

        site.setSiteName("클리앙");
        site.setSiteUrl("http://www.clien.co.kr/");
        i = siteService.registrySite(site);

        Assert.assertEquals(1, i);

        RequestSite requestSite = new RequestSite();
        requestSite.setPageNo(1);
        requestSite.setSiteName("클리앙");

        List<Site> list = siteService.listSite(requestSite);
        Assert.assertNotEquals(0, list.size());
    }

    /**
     * TODO 사이트 정보 등록 테스트
     */
    @Test
    public void testRegistrySite() {
        Site site = new Site();

        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        int i = siteService.registrySite(site);

        Assert.assertEquals(1, i);
    }

    /**
     * TODO 사이트 정보 수정 테스트
     */
    @Test
    public void testModifySite() {
        Site site = new Site();

        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        int i = siteService.registrySite(site);

        Assert.assertEquals(1, i);

        site.setSiteName("클리앙");
        site.setSiteUrl("http://www.clien.co.kr/");
        i = siteService.modifySite(site);

        Assert.assertEquals(1, i);
    }

    /**
     * TODO 사이트 정보 삭제 테스트
     */
    @Test
    public void testRemoveSite() {
        Site site = new Site();

        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        int i = siteService.registrySite(site);

        Assert.assertEquals(1, i);

        i = siteService.removeSite(site);
        Assert.assertEquals(1, i);
    }

    /**
     * 각 테스트 실행 후 데이터 삭제
     */
    @After
    public void clearTable() {
        String tableName = "site";
        jdbcTemplate.execute("delete from " + tableName);
        jdbcTemplate.execute("delete from sqlite_sequence");
    }
}
