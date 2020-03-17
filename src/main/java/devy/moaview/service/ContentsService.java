package devy.moaview.service;

import devy.moaview.controller.cache.Cache;
import devy.moaview.controller.request.RequestContents;
import devy.moaview.controller.request.RequestSite;
import devy.moaview.controller.resopnse.ResponseContents;
import devy.moaview.domain.Contents;
import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.service.mapper.ContentsMapper;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;
import devy.moaview.service.mapper.TargetContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 콘텐츠 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class ContentsService {

    @Autowired
    private ContentsTypeMapper contentsTypeMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private TargetContentsMapper targetContentsMapper;

    @Autowired
    private ContentsMapper contentsMapper;

    /**
     * 콘텐츠 목록을 요청한다.
     * @param requestContents
     *        콘텐츠 목록을 요청하기 위한 정보. 페이지 번호, 검색어, 콘텐츠 타입 번호 등등..
     * @return 콘텐츠 타입 목록, 사이트 정보, 조회된 콘텐츠 목록 등을 반환한다.
     */
    public ResponseContents listContents(RequestContents requestContents) {
        List<Contents> contentsList = Contents.list(requestContents, contentsMapper);

        ResponseContents responseContents = new ResponseContents(contentsTypeMapper, siteMapper);
        responseContents.setContentsList(contentsList);

        return responseContents;
    }

    /**
     * 콘텐츠를 등록한다.
     * @param contents
     *        등록할 콘텐츠 정보
     * @return 성공여부. 1 성공, 0 실패
     */
    public int registryContents(Contents contents) {
        Contents c = (Contents) Contents.get(contents, contentsMapper);
        if(c != null) {
            return 1;
        }
        return contents.registry(contentsMapper);
    }

}
