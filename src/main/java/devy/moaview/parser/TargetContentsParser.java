package devy.moaview.parser;

import devy.moaview.domain.Contents;
import devy.moaview.domain.TargetContents;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class TargetContentsParser {

    private final Logger logger = LoggerFactory.getLogger(TargetContentsParser.class);

    private final int TIMEOUT_MILLIS = 5 * 1000;

    /**
     * 타켓 콘텐츠 URL과 타겟 콘텐츠의 CSS 셀렉터로 실제 콘텐츠의 URL을 파싱한다.
     * @param targetContents
     *        타겟 콘텐츠 URL과 CSS 셀렉터 정보를 담고 있는 타겟 콘텐츠 객체
     * @return 실제 콘텐츠의 URL 주소
     */
    public String getDocumentUrl(TargetContents targetContents) {

        String targetContentsUrl = targetContents.getTargetContentsUrl();
        String targetContentsCssSelector = targetContents.getTargetContentsCssSelector();

        try {

            // 1. 타겟 콘텐츠 URL에 연결 객체를 만들어 상태 코드 및 메세지를 받아온다
            Connection.Response response = Jsoup.connect(targetContentsUrl).execute();
            int statusCode = response.statusCode();
            String statusMessage = response.statusMessage();

            // 2. 상태코드가 200(성공)이 아니라면 타겟 콘텐츠의 연결 가능 여부를 데이터베이스에 업데이트하고 null을 반환한다.
            if(statusCode != HttpStatus.OK.value()) {
                targetContents.setResponseCode(String.valueOf(statusCode));
                targetContents.setResponseMessage(statusMessage);
                return null;
            }

            // 3. 실제 콘텐츠의 URL 주소를 반환한다.
            return response.parse().select(targetContentsCssSelector).attr("href");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 실제 콘텐츠 URL의 HTML을 파싱하여 필요한 메타데이터(title, description, url, image 등)를 파싱하여 콘텐츠 정보를 가져온다.
     * @param documentUrl
     *        실제 콘텐츠의 URL 주소
     * @return 성공 여부. 1 성공, 0 실패
     */
    public Contents parseContents(TargetContents targetContents, String documentUrl) {

        // 1. 가져올 값을 선언한다.
        String title = "", description = "", image = "", url = "";

        // 2. parsing
        try {
            Document contentsDocument = Jsoup.parse(new URL(documentUrl), TIMEOUT_MILLIS);
            title = getMetaValue(contentsDocument, targetContents.getTitleCssSelector(), targetContents.getTitleAttr());
            description = getMetaValue(contentsDocument, targetContents.getDescriptionCssSelector(), targetContents.getDescriptionAttr());
            image = getMetaValue(contentsDocument, targetContents.getImageCssSelector(), targetContents.getImageAttr());
            url = documentUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. URL이 존재하지 않는다면 콘텐츠를 저장하지 않는다.
        if(check(title) || check(url)) {
            return null;
        }

        // 4. 가져온 값들을 바탕으로 콘텐츠 생성
        Contents contents = new Contents();
        contents.setContentsTypeNo(targetContents.getContentsTypeNo());
        contents.setSiteNo(targetContents.getSiteNo());
        contents.setTargetContentsNo(targetContents.getTargetContentsNo());
        contents.setTargetContentsName(targetContents.getTargetContentsName());
        contents.setTitle(title);
        contents.setDescription(description);
        contents.setUrl(url);
        contents.setThumbnailUrl(image);
        contents.setRegDate(LocalDateTime.now());

        return contents;
    }

    /**
     * HTML 문서에서 cssSelector에 해당하는 태그를 찾고 속성이 값을 갖고 있다면 attr 값을 반환한다.
     * @param contentsDocument
     *        콘텐츠 HTML 문서
     * @param cssSelector
     *        값을 얻고자하는 태그의 css selector 문자열
     * @param attr
     *        값이 속성에 있을 경우에 attr은 빈 값이 아니다.
     * @return title, description, image url, content url 등의 문자열
     */
    private String getMetaValue(Document contentsDocument, String cssSelector, String attr) {
        if(attr == null || attr.length() == 0) {
            return contentsDocument.select(cssSelector).text();
        }

        return contentsDocument.select(cssSelector).attr(attr);
    }

    private boolean check(String str) {
        return str == null || str.length() == 0;
    }

}
