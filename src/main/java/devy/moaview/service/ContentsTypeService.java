package devy.moaview.service;

import devy.moaview.domain.ContentsType;
import devy.moaview.service.mapper.ContentsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 콘텐츠 타입 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class ContentsTypeService {

	@Autowired
	private ContentsTypeMapper contentsTypeMapper;

	/**
	 * TODO 콘텐츠 타입 1건을 조회힌다.
	 * @param contentsType
	 * 		  조회하려는 콘텐츠 타입의 번호를 담을 객체
	 * @return 조회한 콘텐츠 타입
	 */
	public ContentsType getContentsType(ContentsType contentsType) {
		return (ContentsType) ContentsType.get(contentsType, contentsTypeMapper);
	}

	/**
	 * TODO 모든 콘텐츠 타입을 조회한다.
	 * @return 모든 콘텐츠 타입
	 */
	public List<ContentsType> listContentsType() {
		return ContentsType.list(null, contentsTypeMapper);
	}

	/**
	 * TODO 콘텐츠 타입을 등록한다.
	 * @param contentsType
	 * 		  등록할 콘텐츠 타입 정보
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int registryContentsType(ContentsType contentsType) {
		return contentsType.registry(contentsTypeMapper);
	}

	/**
	 * TODO 콘텐츠 타입을 수정한다.
	 * @param contentsType
	 * 		  수정할 콘텐츠 타입 정보
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int modifyContentsType(ContentsType contentsType) {
		return contentsType.modify(contentsTypeMapper);
	}

	/**
	 * TODO 콘텐츠 타입을 삭제한다.
	 * @param contentsType
	 * 		  삭제할 콘텐츠 타입의 번호를 담을 객체
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int removeContentsType(ContentsType contentsType) {
		return contentsType.remove(contentsTypeMapper);
	}

}
