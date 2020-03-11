package devy.moaview.service;

import devy.moaview.domain.ContentsType;
import devy.moaview.service.mapper.ContentsTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * TODO
	 * @param contentsType
	 * @return
	 */
	public ContentsType getContentsType(ContentsType contentsType) {
		return null;
	}

}
