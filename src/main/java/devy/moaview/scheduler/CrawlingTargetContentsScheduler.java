package devy.moaview.scheduler;

import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.controller.resopnse.ResponseTargetContents;
import devy.moaview.domain.Contents;
import devy.moaview.domain.Site;
import devy.moaview.domain.TargetContents;
import devy.moaview.parser.TargetContentsParser;
import devy.moaview.service.ContentsService;
import devy.moaview.service.SiteService;
import devy.moaview.service.TargetContentsService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 주기적으로 타겟 콘텐츠를 크롤링한다.
 *
 * @author devy
 */
@EnableScheduling
@Configuration
public class CrawlingTargetContentsScheduler {

    private final Logger logger = LoggerFactory.getLogger(CrawlingTargetContentsScheduler.class);

    /** 스케줄을 실행시키기 전까지의 대기시간 */
    private final long FIXED_RATE = 10 * 60 * 1000;

    /** 애플리케이션 실행 후 대기 시간 */
    private final long INITIAL_DELAY = 2 * 60 * 1000;

    @Autowired
    private SiteService siteService;

    @Autowired
    private TargetContentsService targetContentsService;

    @Autowired
    private ContentsService contentsService;

    /**
     * 크롤링을 실행한다.
     */
    @Scheduled(fixedRate = FIXED_RATE, initialDelay = INITIAL_DELAY)
    public void crawling() {

        logger.info("Start Crawling !");

        // 1. 타겟 콘텐츠 파서 준비 및 타겟 콘텐츠의 모든 목록을 조회한다.
        TargetContentsParser targetContentsParser = new TargetContentsParser();
        List<TargetContents> targetContentsList = targetContentsService.listTargetContents(new RequestTargetContents()).getTargetContentsList();

        // 2. 모든 타겟 콘텐츠의 HTML을 크롤링하여 콘텐츠 정보를 파싱한다.
        for(TargetContents targetContents : targetContentsList) {
            String documentUrl = targetContentsParser.getDocumentUrl(targetContents);

            // 2.1 상태코드가 200(성공)이 아니라면 타겟 콘텐츠의 연결 가능 여부를 데이터베이스에 업데이트하고 null을 반환한다.
            if(documentUrl == null) {
                targetContentsService.modifyTargetContents(targetContents);
                continue;
            }

            // 2.2 실제 콘텐츠 URL 주소에 프로토콜 및 도메인 주소가 있는지 확인한다. 없다면 사이트 정보를 조회하여 프로토콜과 도메인 주소를 추가한다.
            if(!documentUrl.contains("http") && !documentUrl.contains("https")) {
                int siteNo = targetContents.getSiteNo();
                Site site = new Site();
                site.setSiteNo(siteNo);
                site = siteService.getSite(site);
                documentUrl = site.getSiteUrl() + documentUrl;
            }

            logger.info(documentUrl);

            // 2.3 실제 콘텐츠 URL에서 HTML을 크롤링하여 콘텐츠 정보를 파싱한다.
            Contents contents = targetContentsParser.parseContents(targetContents, documentUrl);

            // 2.4 콘텐츠 정보가 null이라면 파싱에 실패. 데이터베이스에 해당 타겟 콘텐츠의 오류 정보를 업데이트한다.
            if(contents == null) {
                targetContents.setResponseCode("999");
                targetContents.setResponseMessage("contents_is_null");
                targetContentsService.modifyTargetContents(targetContents);
                continue;
            }

            logger.info(contents.toString());

            // 2.5 콘텐츠 정보가 null이 아니라면 타겟 콘텐츠의 접속 및 파싱이 가능한 상태이므로 데이터베이스에 접속 여부를 업데이트한다.
            targetContents.setResponseCode("200");
            targetContents.setResponseMessage("성공");
            targetContentsService.modifyTargetContents(targetContents);

            // 2.6 콘텐츠를 등록한다.
            contentsService.registryContents(contents);

        }
    }

}
