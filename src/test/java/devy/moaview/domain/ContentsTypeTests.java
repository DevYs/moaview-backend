package devy.moaview.domain;

import devy.moaview.service.mapper.ContentsTypeMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentsTypeTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ContentsTypeMapper contentsTypeMapper;

	/**
	 * 콘텐츠 타입 등록 및 목록 조회 테스트
	 */
	@Test
	public void testRegistryAndList() {
		ContentsType contentsType = new ContentsType();

		contentsType.setContentsTypeName("커뮤니티");
		contentsType.registry(contentsTypeMapper);

		contentsType.setContentsTypeName("뉴스");
		contentsType.registry(contentsTypeMapper);

		contentsType.setContentsTypeName("IT");
		contentsType.registry(contentsTypeMapper);

		contentsType.setContentsTypeName("유머");
		contentsType.registry(contentsTypeMapper);

		List list = ContentsType.list(null, contentsTypeMapper);
		Assert.assertEquals(4, list.size());
	}

	/**
	 * 콘텐츠 타입 1건 조회 테스트
	 */
	@Test
	public void testGet() {
		ContentsType contentsType = new ContentsType();

		contentsType.setContentsTypeName("커뮤니티");
		int registry = contentsType.registry(contentsTypeMapper);
		Assert.assertEquals(1, registry);

		ContentsType o = (ContentsType) ContentsType.get(contentsType.getContentsTypeNo(), contentsTypeMapper);
		Assert.assertNotNull(o);
	}

	/**
	 * 콘텐츠 타입 삭제 테스트
	 */
	@Test
	public void testRemove() {
		ContentsType contentsType = new ContentsType();

		contentsType.setContentsTypeName("커뮤니티");
		int registry = contentsType.registry(contentsTypeMapper);
		Assert.assertEquals(1, registry);

		int remove = contentsType.remove(contentsTypeMapper);
		Assert.assertEquals(1, remove);
	}

	/**
	 * 콘텐츠 타입 수정 테스트
	 */
	@Test
	public void testModify() {
		ContentsType contentsType = new ContentsType();

		contentsType.setContentsTypeName("커뮤니티");
		int registry = contentsType.registry(contentsTypeMapper);
		Assert.assertEquals(1, registry);

		contentsType.setContentsTypeName("modify_test");
		int modify = contentsType.modify(contentsTypeMapper);
		Assert.assertEquals(1, modify);
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
