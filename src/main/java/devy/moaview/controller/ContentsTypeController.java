package devy.moaview.controller;

import devy.moaview.domain.ContentsType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 콘텐트 타입 정보의 요청 및 응답을 연결하는 컨트롤러
 *
 * @author devy
 */
@Controller
public class ContentsTypeController {

    /**
     * TODO 콘텐츠 타입 목록 화면
     * @return 콘텐츠 타입 목록 view
     */
    public String listContentsTypeGet() {
        return null;
    }

    /**
     * TODO 콘텐츠 타입 등록 화면
     * @return 콘텐츠 타입 등록 view
     */
    public String registryContentsTypeGet() {
        return null;
    }

    /**
     * TODO 콘텐츠 타입 등록 처리
     * @param contentsType
     *        등록할 콘텐츠 타입 정보
     * @return 등록 처리 후 목록화면으로 redirect
     */
    public String registryContentsTypePost(ContentsType contentsType) {
        return null;
    }

    /**
     * TODO 콘텐츠 타입 수정 화면
     * @param contentsType
     *        수정할 콘텐츠 타입의 번호를 받을 객체
     * @return 콘텐츠 타입 수정 view
     */
    public String modifyContentsTypeGet(ContentsType contentsType) {
        return null;
    }

    /**
     * TODO 콘텐츠 타입 수정 처리
     * @param contentsType
     *        수정할 콘텐츠 타입 정보
     * @return 수정 처리 후 목록화면으로 redirect
     */
    public String modifyContentsTypePost(ContentsType contentsType) {
        return null;
    }

    /**
     * TODO 콘텐츠 타입 삭제 처리
     * @param contentsType
     *        삭제할 콘텐츠 타입의 번호를 받을 객체
     * @return 삭제 처리 후 목록 화면으로 redirect
     */
    public String removeContentsTypeGet(ContentsType contentsType) {
        return null;
    }

}
