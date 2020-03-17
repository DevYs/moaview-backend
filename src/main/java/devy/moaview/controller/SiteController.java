package devy.moaview.controller;

import devy.moaview.controller.request.RequestSite;
import devy.moaview.domain.Site;
import devy.moaview.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 사이트 정보의 요청 및 응답을 연결하는 컨트롤러
 *
 * @author devy
 */
@Controller
public class SiteController {

    @Autowired
    private SiteService siteService;

    /**
     * 사이트 정보 목록 조회 화면
     * @param requestSite
     *        검색 및 페이징 정보
     * @return 조회 화면 view
     */
    @GetMapping("/site/list")
    public String listSiteGet(RequestSite requestSite, Model model) {
        List<Site> siteList = siteService.listSite(requestSite);
        model.addAttribute("siteList", siteList);
        return "site/list";
    }

    /**
     * 사이트 정보 등록 화면
     * @return 등록 화면 view
     */
    @GetMapping("/site/registry")
    public String registrySiteGet() {
        return "site/registry";
    }

    /**
     * 사이트 정보 등록 처리
     * @param site
     *        등록할 사이트 정보
     * @return 등록 처리 후 목록화면으로 이동
     */
    @PostMapping("/site/registry")
    public String registrySitePost(Site site) {
        siteService.registrySite(site);
        return "redirect:/site/list";
    }

    /**
     * 사이트 정보 수정 화면
     * @param site
     *        수정할 사이트 정보의 번호를 받을 객체
     * @return 수정화면 view
     */
    @GetMapping("/site/modify")
    public String modifySiteGet(Site site, Model model) {
        site = siteService.getSite(site);
        model.addAttribute("site", site);
        return "site/modify";
    }

    /**
     * 사이트 정보 수정 처리
     * @param site
     *        수정할 사이트 정보
     * @return 수정 처리 후 목록화면으로 이동
     */
    @PostMapping("/site/modify")
    public String modifySitePost(Site site) {
        siteService.modifySite(site);
       return "redirect:/site/list";
    }

    /**
     * 사이트 정보 삭제 처리
     * @param site
     *        삭제할 사이트 정보의 번호를 받을 객체
     * @return
     */
    @GetMapping("/site/remove")
    public String removeSiteGet(Site site) {
        siteService.removeSite(site);
        return "redirect:/site/list";
    }

}
