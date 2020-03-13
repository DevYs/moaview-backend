package devy.moaview.controller;

import devy.moaview.controller.request.RequestContents;
import devy.moaview.controller.resopnse.ResponseContents;
import devy.moaview.service.ContentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 콘텐츠 정보의 요청 및 응답을 연결하는 컨트롤러
 */
@Controller
public class ContentsAPIController {

    @Autowired
    private ContentsService contentsService;

    /**
     * TODO 콘텐츠 목록을 요청한다.
     * @param requestContents
     *        검색 및 페이징 정보와 콘텐츠 목록을 위한 정보를 담을 객체
     * @return ResponseContents 객체에 결과를 담아 반환한다.
     */
    public ResponseContents listContentsGet(RequestContents requestContents) {
        return null;
    }

}
