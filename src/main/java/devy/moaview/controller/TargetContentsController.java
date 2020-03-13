package devy.moaview.controller;

import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.domain.TargetContents;
import devy.moaview.service.TargetContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 타겟 콘텐츠 정보의 요청 및 응답을 연결하는 컨트롤
 */
@Controller
public class TargetContentsController {

    @Autowired
    private TargetContentsService targetContentsService;

    /**
     * TODO 타겟 콘텐츠 목록 화면
     * @param requestTargetContents
     *        타겟 콘텐츠의 검색 및 페이징 정보를 담은 객체
     * @return 목록 화면 view
     */
    public String listTargetContentsGet(RequestTargetContents requestTargetContents) {
        return null;
    }

    /**
     * TODO 타겟 콘텐츠 등록 화면
     * @return 등록 화면 view
     */
    public String registryTargetContentsGet() {
        return null;
    }

    /**
     * TODO 타겟 콘텐츠 등록 처리
     * @param targetContents
     *        등록할 타겟 콘텐츠 정보
     * @return 등록 처리 후 목록으로 이동
     */
    public String registryTargetContentsPost(TargetContents targetContents) {
        return null;
    }

    /**
     * TODO 타겟 콘텐츠 수정 화면
     * @param targetContents
     *        수정할 타겟 콘텐츠 번호를 담을 객체
     * @return 수정 화면 view
     */
    public String modifyTargetContentsGet(TargetContents targetContents) {
        return null;
    }

    /**
     * TODO 타겟 콘텐츠 수정 처리
     * @param targetContents
     *        수정할 타겟 콘텐츠 정보
     * @return 수정 처리 후 목록화면으로 이동
     */
    public String modifyTargetContentsPost(TargetContents targetContents) {
        return null;
    }

    /**
     * TODO 타겟 콘텐츠 삭제 처리
     * @param targetcontents
     *        삭제할 타겟 콘텐츠 번호를 받을 객체
     * @return 삭제 처리 후 목록화면으로 이동
     */
    public String removeTargetContentsGet(TargetContents targetcontents) {
        return null;
    }

}
