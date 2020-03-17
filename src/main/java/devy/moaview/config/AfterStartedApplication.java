package devy.moaview.config;

import devy.moaview.controller.cache.Cache;
import devy.moaview.controller.cache.ICache;
import devy.moaview.controller.request.RequestSite;
import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.service.ContentsTypeService;
import devy.moaview.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.List;

/**
 * 웹 어플리케이션이 실행된 후 작업<br>
 * Configration, Bean, Component 등이 모두 생성된 상태이다.
 * @author devy
 */
@Configuration
public class AfterStartedApplication {

    private Logger logger = LoggerFactory.getLogger(AfterStartedApplication.class);

    @Autowired
    private ContentsTypeService contentsTypeService;

    @Autowired
    private SiteService siteService;

    /**
     * Spring boot가 시작되면 실행된다.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {
        logger.info("after started application");

        List<ContentsType> contentsTypeList = contentsTypeService.listContentsType();
        List<Site> siteList = siteService.listSite(new RequestSite());

        Cache.CONTENTS_TYPE.putAll(contentsTypeList);
        Cache.SITE.putAll(siteList);
    }

}
