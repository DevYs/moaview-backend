package devy.moaview.domain;

import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;
import devy.moaview.service.mapper.TargetContentsMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetContentsTests {

	private final Logger logger = LoggerFactory.getLogger(TargetContentsTests.class);

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

		logger.info(todayhumor.toString());
		logger.info(clien.toString());

		ContentsType contentsType = new ContentsType();

		contentsType.setContentsTypeName("유머");
		contentsType.registry(contentsTypeMapper);

		contentsType.setContentsTypeName("커뮤니티");
		contentsType.registry(contentsTypeMapper);

		List<ContentsType> contentsTypeList = (List<ContentsType>) ContentsType.list(null, contentsTypeMapper);
		humor = contentsTypeList.get(0);
		community = contentsTypeList.get(1);

		logger.info(humor.toString());
		logger.info(community.toString());
	}

	/**
	 * 등록 테스트
	 */
	@Test
	public void testRegistry() {
		TargetContents targetContents = new TargetContents();
		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsName("베오베");
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");
		int registry = targetContents.registry(targetContentsMapper);

		Assert.assertEquals(1, registry);
	}

	/**
	 * 1건 조회 테스트
	 */
	@Test
	public void testGet() {
		TargetContents targetContents = new TargetContents();
		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsName("베오베");
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");
		int registry = targetContents.registry(targetContentsMapper);
		Assert.assertEquals(1, registry);

		TargetContents result = (TargetContents) TargetContents.get(targetContents.getTargetContentsNo(), targetContentsMapper);
		Assert.assertNotNull(result);
	}

	/**
	 * 검색 및 페이징 테스트
	 */
	@Test
	public void testSearchAndPagination() {
		int checkSize = 20;

		TargetContents targetContents = new TargetContents();

		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");

		for(int i=0; i<checkSize; i++) {
			targetContents.setTargetContentsName("베오베 " + i);
			targetContents.registry(targetContentsMapper);
		}

		targetContents.setContentsTypeNo(community.getContentsTypeNo());
		targetContents.setSiteNo(clien.getSiteNo());
		targetContents.setTargetContentsUrl("https://www.clien.net/service/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");

		for(int i=0; i<100; i++) {
			targetContents.setTargetContentsName("추천 " + i);
			targetContents.registry(targetContentsMapper);
		}

		RequestTargetContents requestTargetContents = new RequestTargetContents();
		requestTargetContents.setPageNo(1);
		requestTargetContents.setContentsTypeNo(1);
		requestTargetContents.setSiteNo(1);
		requestTargetContents.setSearchWord("베오베");

		List<TargetContents> list = (List<TargetContents>) TargetContents.list(requestTargetContents, targetContentsMapper);
		Assert.assertEquals(checkSize, list.size());
	}

	/**
	 * 수정 테스트
	 */
	@Test
	public void testModify() {
		TargetContents targetContents = new TargetContents();
		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsName("베오베");
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");
		int registry = targetContents.registry(targetContentsMapper);
		Assert.assertEquals(1, registry);

		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/test");
		int modify = targetContents.modify(targetContentsMapper);
		Assert.assertEquals(1, modify);
	}

	/**
	 * 삭제 테스트
	 */
	@Test
	public void testRemove() {
		TargetContents targetContents = new TargetContents();
		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsName("베오베");
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");
		int registry = targetContents.registry(targetContentsMapper);
		Assert.assertEquals(1, registry);

		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/test");
		int remove = targetContents.remove(targetContentsMapper);
		Assert.assertEquals(1, remove);
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
