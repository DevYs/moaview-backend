package devy.moaview.domain;

import devy.moaview.controller.request.RequestContents;
import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.service.mapper.ContentsMapper;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 콘텐츠 관련 테스트
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentsTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SiteMapper siteMapper;

	@Autowired
	private ContentsTypeMapper contentsTypeMapper;

	@Autowired
	private TargetContentsMapper targetContentsMapper;

	@Autowired
	private ContentsMapper contentsMapper;

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

		TargetContents targetContents = new TargetContents();
		targetContents.setContentsTypeNo(humor.getContentsTypeNo());
		targetContents.setSiteNo(todayhumor.getSiteNo());
		targetContents.setTargetContentsName("베오베");
		targetContents.setTargetContentsUrl("http://www.todayhumor.co.kr/");
		targetContents.setTargetContentsCssSelector("div > ul > .test");
		targetContents.registry(targetContentsMapper);
	}

	/**
	 * 콘텐츠 등록 테스트
	 */
	@Test
	public void testRegistry() {
		List<TargetContents> targetContentsList = (List<TargetContents>) TargetContents.list(new RequestTargetContents(), targetContentsMapper);
		TargetContents targetContents = targetContentsList.get(0);

		Contents contents = new Contents();
		contents.setContentsTypeNo(targetContents.getContentsTypeNo());
		contents.setSiteNo(targetContents.getSiteNo());
		contents.setTargetContentsNo(targetContents.getTargetContentsNo());
		contents.setTargetContentsName(targetContents.getTargetContentsName());
		contents.setTitle("무궁화 꽃이 피었습니다.");
		contents.setDescription("동해 물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세");
		contents.setUrl("http://www.todayshumor.co.kr/");
		contents.setThumbnailUrl("http://www.todayshumor.co.kr/test.jpg");
		contents.setRegDate(LocalDateTime.now());
		int registry = contents.registry(contentsMapper);

		Assert.assertEquals(1, registry);
	}

	/**
	 * 콘텐츠 삭제 테스트
	 */
	@Test
	public void testRemove() {
		List<TargetContents> targetContentsList = (List<TargetContents>) TargetContents.list(new RequestTargetContents(), targetContentsMapper);
		TargetContents targetContents = targetContentsList.get(0);

		Contents contents = new Contents();
		contents.setContentsTypeNo(targetContents.getContentsTypeNo());
		contents.setSiteNo(targetContents.getSiteNo());
		contents.setTargetContentsNo(targetContents.getTargetContentsNo());
		contents.setTargetContentsName(targetContents.getTargetContentsName());
		contents.setTitle("무궁화 꽃이 피었습니다.");
		contents.setDescription("동해 물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세");
		contents.setUrl("http://www.todayshumor.co.kr/");
		contents.setThumbnailUrl("http://www.todayshumor.co.kr/test.jpg");
		contents.setRegDate(LocalDateTime.now());
		int registry = contents.registry(contentsMapper);
		Assert.assertEquals(1, registry);

		int remove = contents.remove(contentsMapper);
		Assert.assertEquals(1, remove);
	}

	/**
	 * 콘텐츠 삭제 테스트
	 */
	@Test
	public void testModify() {
		List<TargetContents> targetContentsList = (List<TargetContents>) TargetContents.list(new RequestTargetContents(), targetContentsMapper);
		TargetContents targetContents = targetContentsList.get(0);

		Contents contents = new Contents();
		contents.setContentsTypeNo(targetContents.getContentsTypeNo());
		contents.setSiteNo(targetContents.getSiteNo());
		contents.setTargetContentsNo(targetContents.getTargetContentsNo());
		contents.setTargetContentsName(targetContents.getTargetContentsName());
		contents.setTitle("무궁화 꽃이 피었습니다.");
		contents.setDescription("동해 물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세");
		contents.setUrl("http://www.todayshumor.co.kr/");
		contents.setThumbnailUrl("http://www.todayshumor.co.kr/test.jpg");
		contents.setRegDate(LocalDateTime.now());
		int registry = contents.registry(contentsMapper);
		Assert.assertEquals(1, registry);

		contents.setTitle("오늘 날씨가 참 좋다!");
		contents.setDescription("우리들의 막을 올렸던 그 여름.");
		int modify = contents.modify(contentsMapper);
		Assert.assertEquals(1, modify);
	}

	/**
	 * 콘텐츠 목록 조회
	 * <p>
	 *     콘텐츠 목록 조회시 콘텐츠 타입과 검색어, 페이지번호를 입력받아 조회한다. <br>
	 *     콘텐츠 타입은 여러 값을 받을 수 있다.
	 * </p>
	 */
	@Test
	public void testList() {
		List<TargetContents> targetContentsList = (List<TargetContents>) TargetContents.list(new RequestTargetContents(), targetContentsMapper);
		TargetContents targetContents = targetContentsList.get(0);

		Contents contents = new Contents();
		contents.setContentsTypeNo(targetContents.getContentsTypeNo());
		contents.setSiteNo(targetContents.getSiteNo());
		contents.setTargetContentsNo(targetContents.getTargetContentsNo());
		contents.setTargetContentsName(targetContents.getTargetContentsName());

		for(int i=0; i<100; i++) {
			contents.setTitle("무궁화 꽃이 피었습니다" + i);
			contents.setDescription("동해 물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세" + i);
			contents.setThumbnailUrl("http://www.todayshumor.co.kr/test.jpg");
			contents.setUrl("http://www.todayshumor.co.kr/" + i);
			contents.setRegDate(LocalDateTime.now());
			contents.registry(contentsMapper);
		}

		List<Integer> contentsTypeNoList = new ArrayList<>();
		contentsTypeNoList.add(1);

		RequestContents requestContents = new RequestContents();
		requestContents.setContentsTypeNoList(contentsTypeNoList);
		requestContents.setPageNo(1);
		requestContents.setTitle("피었습니다1");

		List<Contents> list = Contents.list(requestContents, contentsMapper);
		Assert.assertNotEquals(0, list.size());
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
