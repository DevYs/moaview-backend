package devy.moaview.service;

import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;
import devy.moaview.service.mapper.TargetContentsMapper;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * 콘텐츠 타입 서비스 테스트
 *
 * @author devy
 */
public class TargetContentsServiceTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private ContentsTypeMapper contentsTypeMapper;

    @Autowired
    private TargetContentsMapper targetContentsMapper;

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
     * 각 테스트 실행 후 데이터 삭제
     */
    @After
    public void clearTable() {
        String tableName = "contents_type";
        jdbcTemplate.execute("delete from " + tableName);
        jdbcTemplate.execute("delete from sqlite_sequence");
    }

}
