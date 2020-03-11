package devy.moaview.domain;

import devy.moaview.controller.request.RequestSite;
import devy.moaview.service.mapper.SiteMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SiteTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SiteMapper siteMapper;

	/**
	 * 등록 테스트
	 */
	@Test
	public void testRegistry() {
		Site site = new Site();
		site.setSiteName("test");
		site.setSiteUrl("test");

		int registry = site.registry(siteMapper);

		Assert.assertEquals(1, registry);
	}

	/**
	 * 삭제 테스트
	 */
	@Test
	public void testRemove() {
		Site site = new Site();
		site.setSiteName("test");
		site.setSiteUrl("test");

		int registry = site.registry(siteMapper);
		Assert.assertEquals(1, registry);

		int remove = site.remove(siteMapper);
		Assert.assertEquals(1, remove);
	}

	/**
	 * 갱신 테스트
	 */
	@Test
	public void testUpdate() {
		Site site = new Site();
		site.setSiteName("test");
		site.setSiteUrl("test");

		int registry = site.registry(siteMapper);
		Assert.assertEquals(1, registry);

		site.setSiteName("modify");
		site.setSiteUrl("modify");
		int modify = site.modify(siteMapper);
		Assert.assertEquals(1, modify);
	}

	/**
	 * 목록 조회 테스트
	 */
	@Test
	public void testList() {
		Site site = new Site();

		for(int i=0; i<20; i++) {
			site.setSiteName("test" + i);
			site.setSiteUrl("test" + i);
			site.registry(siteMapper);
		}

		RequestSite requestSite = new RequestSite();
		requestSite.setPageNo(1);
		requestSite.setSiteName("test");

		List<Site> list = (List<Site>) Site.list(requestSite, siteMapper);

		Assert.assertEquals(20, list.size());
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
