package devy.moaview.scheduler;

import devy.moaview.service.TargetContentsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
    private final long FIXED_RATE = 5 * 1000;

    /** 스케줄 실행 후 대기 시간 */
    private final long INITIAL_DELAY = 5 * 1000;

    /**
     * 크롤링을 실행한다.
     */
    @Scheduled(fixedRate = FIXED_RATE, initialDelay = INITIAL_DELAY)
    public void crawling() {

    }


}
