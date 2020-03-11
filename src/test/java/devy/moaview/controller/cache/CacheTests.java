package devy.moaview.controller.cache;

import devy.moaview.domain.Site;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 콘텐츠 타입 및 사이트 정보의 캐시 테스트
 *
 * @author devy
 */
@RunWith(SpringRunner.class)
public class CacheTests {

	/**
	 * 사이트 정보 캐싱 테스트
	 */
	@Test
	public void testPutSite() {
		Site site = new Site();
		site.setSiteNo(1);
		site.setSiteName("test");
		site.setSiteUrl("test");

		Cache.SITE.put(site);

		Site s = (Site) Cache.SITE.get(site.getSiteNo());

		Assert.assertNotNull(s);
		Assert.assertEquals(s.getSiteName(), site.getSiteName());
	}

}
