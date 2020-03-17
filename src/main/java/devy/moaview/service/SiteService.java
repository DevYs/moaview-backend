package devy.moaview.service;

import devy.moaview.controller.cache.Cache;
import devy.moaview.controller.request.RequestSite;
import devy.moaview.domain.Site;
import devy.moaview.service.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 사이트 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class SiteService {

    @Autowired
    private SiteMapper siteMapper;

    /**
     * 사이트 정보를 1건 조회한다.
     * @param site
     *        조회하려는 사이트 정보의 번호를 담을 객체로 필수적으로 사이트 번호가 있어야한다.
     * @return 조회된 사이트 정보
     */
    public Site getSite(Site site) {
        return (Site) Site.get(site.getSiteNo(), siteMapper);
    }

    /**
     * 사이트 정보 목록을 조회한다.
     * @param requestSite
     *        검색어, 페이징 정보 등
     * @return 조회된 사이트 정보 목록
     */
    public List<Site> listSite(RequestSite requestSite) {
        return (List<Site>) Site.list(requestSite, siteMapper);
    }

    /**
     * 사이트 정보를 등록한다.
     * @param site
     *        등록할 사이트 정보
     * @return 성공여부. 1 성공, 0 실패
     */
    public int registrySite(Site site) {
        site.registry(siteMapper);
        return Cache.SITE.put(site);
    }

    /**
     * 사이트 정보를 수정한다.
     * @param site
     *        수정할 사이트 정보
     * @return 성공여부. 1 성공, 0 실패
     */
    public int modifySite(Site site) {
        site.modify(siteMapper);
        return Cache.SITE.modify(site);
    }

    /**
     * 사이트 정보를 삭제한다.
     * @param site
     *        삭제를 위해서는 사이트 번호가 필요하다.
     * @return 성공여부. 1 성공, 0 실패
     */
    public int removeSite(Site site) {
        site.remove(siteMapper);
        return Cache.SITE.remove(site.getSiteNo());
    }

}
