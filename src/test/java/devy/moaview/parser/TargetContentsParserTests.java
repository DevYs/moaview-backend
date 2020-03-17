package devy.moaview.parser;

import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.domain.TargetContents;
import devy.moaview.service.ContentsService;
import devy.moaview.service.ContentsTypeService;
import devy.moaview.service.SiteService;
import devy.moaview.service.TargetContentsService;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetContentsParserTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ContentsTypeService contentsTypeService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private TargetContentsService targetContentsService;

    @Autowired
    private ContentsService contentsService;

    @Before
    public void before() {
        ContentsType contentsType = new ContentsType();
        contentsType.setContentsTypeName("유머");
        contentsTypeService.registryContentsType(contentsType);

        Site site = new Site();
        site.setSiteName("오늘의 유머");
        site.setSiteUrl("http://www.todayshumor.co.kr/");
        siteService.registrySite(site);

        TargetContents targetContents = new TargetContents();
        targetContents.setContentsTypeNo(contentsType.getContentsTypeNo());
        targetContents.setSiteNo(site.getSiteNo());
        targetContents.setTargetContentsName("베스트 오브 베스트");
        targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/board/list.php?table=bestofbest");
        targetContents.setTargetContentsCssSelector(".list_tr_humordata td.subject a:first-child");
        targetContentsService.registryTargetContents(targetContents);
    }

    @Test
    public void testGetDocumentUrl() {
        TargetContentsParser targetContentsParser = new TargetContentsParser();

        List<TargetContents> targetContentsList = targetContentsService.listTargetContents(new RequestTargetContents()).getTargetContentsList();
        for(TargetContents targetContents : targetContentsList) {
            String documentUrl = targetContentsParser.getDocumentUrl(targetContents);
            Assert.assertNotNull(documentUrl);
        }

    }

    @Test
    public void testParseContents() {
        TargetContentsParser targetContentsParser = new TargetContentsParser();

        List<TargetContents> targetContentsList = targetContentsService.listTargetContents(new RequestTargetContents()).getTargetContentsList();
        for(TargetContents targetContents : targetContentsList) {
            String documentUrl = targetContentsParser.getDocumentUrl(targetContents);
            targetContentsParser.parseContents(targetContents, documentUrl);
        }
    }

    /**
     * 각 테스트 실행 후 데이터 삭제
     */
    @After
    public void clearTable() {
        jdbcTemplate.execute("delete from contents_type");
        jdbcTemplate.execute("delete from site");
        jdbcTemplate.execute("delete from target_contents");
        jdbcTemplate.execute("delete from contents");
        jdbcTemplate.execute("delete from sqlite_sequence");
    }

}
