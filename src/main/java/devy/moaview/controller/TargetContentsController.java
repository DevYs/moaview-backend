package devy.moaview.controller;

import devy.moaview.controller.cache.Cache;
import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.controller.resopnse.ResponseTargetContents;
import devy.moaview.domain.Contents;
import devy.moaview.domain.Site;
import devy.moaview.domain.TargetContents;
import devy.moaview.parser.TargetContentsParser;
import devy.moaview.service.SiteService;
import devy.moaview.service.TargetContentsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 타겟 콘텐츠 정보의 요청 및 응답을 연결하는 컨트롤
 */
@Controller
public class TargetContentsController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private TargetContentsService targetContentsService;

    /**
     * 타겟 콘텐츠 목록 화면
     * @param requestTargetContents
     *        타겟 콘텐츠의 검색 및 페이징 정보를 담은 객체
     * @return 목록 화면 view
     */
    @GetMapping("/target_contents/list")
    public String listTargetContentsGet(RequestTargetContents requestTargetContents, Model model) {
        ResponseTargetContents responseTargetContents = targetContentsService.listTargetContents(requestTargetContents);
        model.addAttribute("responseTargetContents", responseTargetContents);
        return "target_contents/list";
    }

    /**
     * 타겟 콘텐츠 등록 화면
     * @return 등록 화면 view
     */
    @GetMapping("/target_contents/registry")
    public String registryTargetContentsGet(Model model) {
        model.addAttribute("contentsTypeList", Cache.CONTENTS_TYPE.list());
        model.addAttribute("siteList", Cache.SITE.list());
        return "target_contents/registry";
    }

    /**
     * 타겟 콘텐츠 등록 처리
     * @param targetContents
     *        등록할 타겟 콘텐츠 정보
     * @return 등록 처리 후 목록으로 이동
     */
    @PostMapping("/target_contents/registry")
    public String registryTargetContentsPost(TargetContents targetContents) {
        targetContentsService.registryTargetContents(targetContents);
        return "redirect:/target_contents/list";
    }

    /**
     * 타겟 콘텐츠 수정 화면
     * @param targetContents
     *        수정할 타겟 콘텐츠 번호를 담을 객체
     * @return 수정 화면 view
     */
    @GetMapping("/target_contents/modify")
    public String modifyTargetContentsGet(TargetContents targetContents, Model model) {
        targetContents = targetContentsService.getTargetContents(targetContents);
        model.addAttribute("contentsTypeList", Cache.CONTENTS_TYPE.list());
        model.addAttribute("siteList", Cache.SITE.list());
        model.addAttribute("targetContents", targetContents);
        return "target_contents/modify";
    }

    /**
     * 타겟 콘텐츠 수정 처리
     * @param targetContents
     *        수정할 타겟 콘텐츠 정보
     * @return 수정 처리 후 목록화면으로 이동
     */
    @PostMapping("/target_contents/modify")
    public String modifyTargetContentsPost(TargetContents targetContents) {
        targetContentsService.modifyTargetContents(targetContents);
        return "redirect:/target_contents/list";
    }

    /**
     * 타겟 콘텐츠 삭제 처리
     * @param targetContents
     *        삭제할 타겟 콘텐츠 번호를 받을 객체
     * @return 삭제 처리 후 목록화면으로 이동
     */
    @GetMapping("/target_contents/remove")
    public String removeTargetContentsGet(TargetContents targetContents) {
        targetContentsService.removeTargetContents(targetContents);
        return "redirect:/target_contents/list";
    }

    /**
     * 타겟 콘텐츠 정보 테스트 화면
     * @param model
     *        콘텐츠 타입 목록, 사이트 목록, 빈 타겟 콘텐츠 객체 등
     * @return 타겟 콘텐츠 테스트 화면
     */
    @GetMapping("/target_contents/test")
    public String testTargetContentsGet(Model model) {
        model.addAttribute("contentsTypeList", Cache.CONTENTS_TYPE.list());
        model.addAttribute("siteList", Cache.SITE.list());
        model.addAttribute("targetContents", new TargetContents());
        model.addAttribute("contents", null);
        return "target_contents/test";
    }

    /**
     * 타겟 콘텐츠 정보가 유효한지 테스트한다.
     * @param targetContents
     *        테스트하기 위해 입력한 타겟 콘텐츠 정보
     * @param model
     *        콘텐츠 타입 목록, 사이트 정보 목록, 입력한 타겟 콘텐츠 정보, 테스트 후 생성된 콘텐츠 정보
     * @return 타겟 콘텐츠 테스트 화면 및 결과
     */
    @PostMapping("/target_contents/test")
    public String testTargetContentsPost(TargetContents targetContents, Model model) {
        Contents contents = targetContentsService.testTargetContents(targetContents);
        model.addAttribute("contentsTypeList", Cache.CONTENTS_TYPE.list());
        model.addAttribute("siteList", Cache.SITE.list());
        model.addAttribute("targetContents", targetContents);
        model.addAttribute("contents", contents);
        return "target_contents/test";
    }

}
