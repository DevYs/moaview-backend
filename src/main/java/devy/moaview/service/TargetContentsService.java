package devy.moaview.service;

import devy.moaview.controller.request.RequestTargetContents;
import devy.moaview.controller.resopnse.ResponseTargetContents;
import devy.moaview.domain.Contents;
import devy.moaview.domain.Site;
import devy.moaview.domain.TargetContents;
import devy.moaview.parser.TargetContentsParser;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;
import devy.moaview.service.mapper.TargetContentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 타겟 콘텐츠 관련 비즈니스 로직을 처리하는 서비스
 *
 * @author devy
 */
@Service
public class TargetContentsService {

    @Autowired
    private ContentsTypeMapper contentsTypeMapper;

    @Autowired
    private SiteMapper siteMapper;

    @Autowired
    private TargetContentsMapper targetContentsMapper;

    /**
     * 타겟 콘텐츠 1건을 조회한다.
     * @param targetContents
     *        조회하려는 타겟 콘텐츠 정보. 타켓 콘텐츠 번호 필수
     * @return 조회한 타겟 콘텐츠
     */
    public TargetContents getTargetContents(TargetContents targetContents) {
        return (TargetContents) TargetContents.get(targetContents.getTargetContentsNo(), targetContentsMapper);
    }

    /**
     * 타겟 콘텐츠 목록을 조회한다.
     * @param requestTargetContents
     *        검색어, 페이징 등 타겟 콘텐츠 목록을 조회하기 위한 정보
     * @return 콘텐츠 타입 목록, 사이트 정보, 조회한 타겟 콘텐츠 목록 등을 반환
     */
    public ResponseTargetContents listTargetContents(RequestTargetContents requestTargetContents) {
        List<TargetContents> targetContentsList = (List<TargetContents>) TargetContents.list(requestTargetContents, targetContentsMapper);

        ResponseTargetContents responseTargetContents = new ResponseTargetContents(contentsTypeMapper, siteMapper);
        responseTargetContents.setTargetContentsList(targetContentsList);

        return responseTargetContents;
    }

    /**
     * 타겟 콘텐츠 정보를 등록한다.
     * @param targetContents
     *        등록할 타겟 콘텐츠 정보
     * @return 성공여부. 1 성공, 0 실패
     */
    public int registryTargetContents(TargetContents targetContents) {
        return targetContents.registry(targetContentsMapper);
    }

    /**
     * 타겟 콘텐츠 정보를 수정한다.
     * @param targetContents
     *        수정할 타겟 콘텐츠 정보
     * @return 성공여부. 1 성공, 0 실패
     */
    public int modifyTargetContents(TargetContents targetContents) {
        return targetContents.modify(targetContentsMapper);
    }

    /**
     * 타겟 콘텐츠 정보를 삭제한다.
     * @param targetContents
     *        삭제할 타겟 콘텐츠 정보. 타겟 콘텐츠의 번호 필수
     * @return 성공여부. 1 성공, 0 실패
     */
    public int removeTargetContents(TargetContents targetContents) {
        return targetContents.remove(targetContentsMapper);
    }

    /**
     * 타겟 콘텐츠 정보가 유효하게 동작하는지 테스트한다.
     * @param targetContents
     *        테스트할 타겟 콘텐츠 정보
     * @return 테스트 후 생성된 콘텐츠 객체
     */
    public Contents testTargetContents(TargetContents targetContents) {
        TargetContentsParser targetContentsParser = new TargetContentsParser();
        String documentUrl = targetContentsParser.getDocumentUrl(targetContents);

        // 3. 실제 콘텐츠 URL 주소에 프로토콜 및 도메인 주소가 있는지 확인한다. 없다면 사이트 정보를 조회하여 프로토콜과 도메인 주소를 추가한다.
        if(!documentUrl.contains("http://") && !documentUrl.contains("https://")) {
            int siteNo = targetContents.getSiteNo();
            Site site = new Site();
            site.setSiteNo(siteNo);
            site = (Site) Site.get(site, siteMapper);

            if(documentUrl.indexOf("/") == 0) {
                documentUrl = documentUrl.replaceFirst("/", "");
            }

            documentUrl = site.getSiteUrl() + documentUrl;
        }

        return targetContentsParser.parseContents(targetContents, documentUrl);
    }

}
