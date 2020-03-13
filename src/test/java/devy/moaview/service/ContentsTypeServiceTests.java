package devy.moaview.service;

import devy.moaview.domain.ContentsType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 콘텐츠 타입 서비스 테스트
 *
 * @author devy
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContentsTypeServiceTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ContentsTypeService contentsTypeService;

    /**
     * 콘텐츠 타입 목록 조회 테스트
     */
    @Test
    public void testListContentsType() {
        ContentsType contentsType = new ContentsType();

        contentsType.setContentsTypeName("커뮤니티");
        int i = contentsTypeService.registryContentsType(contentsType);
        Assert.assertEquals(1, i);

        contentsType.setContentsTypeName("유머");
        i = contentsTypeService.registryContentsType(contentsType);
        Assert.assertEquals(1, i);

        contentsType.setContentsTypeName("뉴스");
        i = contentsTypeService.registryContentsType(contentsType);
        Assert.assertEquals(1, i);

        List<ContentsType> contentsTypeList = contentsTypeService.listContentsType();
        Assert.assertEquals(3, contentsTypeList.size());
    }

    /**
     * 콘텐츠 타입을 1건 조회하는 테스트
     */
    @Test
    public void testGetContentsType() {
        ContentsType contentsType = new ContentsType();
        contentsType.setContentsTypeName("커뮤니티");
        int i = contentsTypeService.registryContentsType(contentsType);

        Assert.assertEquals(1, i);

        ContentsType result = contentsTypeService.getContentsType(contentsType);
        Assert.assertNotNull(result);
    }

    /**
     * 콘텐츠 타입 등록 테스트
     */
    @Test
    public void testRegistryContentsType() {
        ContentsType contentsType = new ContentsType();
        contentsType.setContentsTypeName("커뮤니티");
        int i = contentsTypeService.registryContentsType(contentsType);

        Assert.assertEquals(1, i);
    }

    /**
     * 콘텐츠 타입 수정 테스트
     */
    @Test
    public void testModifyContentsType() {
        ContentsType newContentsType = new ContentsType();
        newContentsType.setContentsTypeName("커뮤니티");
        int i = contentsTypeService.registryContentsType(newContentsType);

        Assert.assertEquals(1, i);

        ContentsType updateContentsType = contentsTypeService.getContentsType(newContentsType);
        updateContentsType.setContentsTypeName("커뮤니티 수정");
        int result = contentsTypeService.modifyContentsType(updateContentsType);

        Assert.assertEquals(1, result);
    }

    /**
     * 콘텐츠 타입 삭제 테스트
     */
    @Test
    public void testRemoveContentsType() {
        ContentsType newContentsType = new ContentsType();
        newContentsType.setContentsTypeName("커뮤니티");
        int i = contentsTypeService.registryContentsType(newContentsType);

        Assert.assertEquals(1, i);

        int remove = contentsTypeService.removeContentsType(newContentsType);
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
