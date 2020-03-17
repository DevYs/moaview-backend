package devy.moaview.controller;

import devy.moaview.domain.ContentsType;
import devy.moaview.service.ContentsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 콘텐트 타입 정보의 요청 및 응답을 연결하는 컨트롤러
 *
 * @author devy
 */
@Controller
public class ContentsTypeController {

    @Autowired
    private ContentsTypeService contentsTypeService;

    /**
     * 콘텐츠 타입 목록 화면
     * @return 콘텐츠 타입 목록 view
     */
    @GetMapping("/contents_type/list")
    public String listContentsTypeGet(Model model) {
        List<ContentsType> contentsTypeList = contentsTypeService.listContentsType();
        model.addAttribute("contentsTypeList", contentsTypeList);
        return "contents_type/list";
    }

    /**
     * 콘텐츠 타입 등록 화면
     * @return 콘텐츠 타입 등록 view
     */
    @GetMapping("/contents_type/registry")
    public String registryContentsTypeGet() {
        return "contents_type/registry";
    }

    /**
     * 콘텐츠 타입 등록 처리
     * @param contentsType
     *        등록할 콘텐츠 타입 정보
     * @return 등록 처리 후 목록화면으로 redirect
     */
    @PostMapping("/contents_type/registry")
    public String registryContentsTypePost(ContentsType contentsType) {
        contentsTypeService.registryContentsType(contentsType);
        return "redirect:/contents_type/list";
    }

    /**
     * 콘텐츠 타입 수정 화면
     * @param contentsType
     *        수정할 콘텐츠 타입의 번호를 받을 객체
     * @return 콘텐츠 타입 수정 view
     */
    @GetMapping("/contents_type/modify")
    public String modifyContentsTypeGet(ContentsType contentsType, Model model) {
        contentsType = contentsTypeService.getContentsType(contentsType);
        model.addAttribute("contentsType", contentsType);
        return "contents_type/modify";
    }

    /**
     * 콘텐츠 타입 수정 처리
     * @param contentsType
     *        수정할 콘텐츠 타입 정보
     * @return 수정 처리 후 목록화면으로 redirect
     */
    @PostMapping("/contents_type/modify")
    public String modifyContentsTypePost(ContentsType contentsType) {
        contentsTypeService.modifyContentsType(contentsType);
        return "redirect:/contents_type/list";
    }

    /**
     * 콘텐츠 타입 삭제 처리
     * @param contentsType
     *        삭제할 콘텐츠 타입의 번호를 받을 객체
     * @return 삭제 처리 후 목록 화면으로 redirect
     */
    @GetMapping("/contents_type/remove")
    public String removeContentsTypeGet(ContentsType contentsType) {
        contentsTypeService.removeContentsType(contentsType);
        return "redirect:/contents_type/list";
    }

}
