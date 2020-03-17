package devy.moaview.domain;

import devy.moaview.service.mapper.Mapper;

/**
 * 주기적으로 크롤링할 타겟 콘텐츠
 *
 * <p>타겟 콘텐츠는 특정 사이트의 특정 페이지에서 CSS 셀렉터를 기준으로 크롤링된다.</p>
 *
 * @author devy
 */
public class TargetContents extends Mapper {

	/** 타겟 콘텐츠 번호 */
	private int targetContentsNo;

	/** 콘텐츠 타입 번호 */
	private int contentsTypeNo;

	/** 사이트 정보 번호 */
	private int siteNo;

	/** 타겟 콘텐츠명 */
	private String targetContentsName;

	/** 타겟 콘텐츠 URL */
	private String targetContentsUrl;

	/** 타겟 콘텐츠 CSS 셀렉터 */
	private String targetContentsCssSelector;

	/** HTML 문서상에서 콘텐츠의 제목 태그 css 셀렉터 */
	private String titleCssSelector;

	/** HTML 문서상에서 콘텐츠의 제목 태그의 속성에 제목 텍스트가 있을 경우 그 속성의 이름 */
	private String titleAttr;

	/** HTML 문서상에서 콘텐츠의 설명 태그 css 셀렉터 */
	private String descriptionCssSelector;

	/** HTML 문서상에서 콘텐츠의 설명 태그의 속성에 설명 텍스트가 있을 경우 그 속성의 이름 */
	private String descriptionAttr;

	/** HTML 문서상에서 콘텐츠의 썸네일 이미지 태그 css 셀렉터 */
	private String imageCssSelector;

	/** HTML 문서상에서 콘텐츠의 썸네일 이미지 태그의 속성에 썸네일 이미지 URL이 있을 경우 그 속성의 이름 */
	private String imageAttr;

	/** HTML 문서상에서 콘텐츠의 실제 URL css 셀렉터 */
	private String urlCssSelector;

	/** HTML 문서상에서 콘텐츠의 실제 URL 문자열이 속성에 있을 경우 css 셀렉터 */
	private String urlAttr;

	/** HTTP 요청시 응답 코드. 해당 코드로 타겟 콘텐츠의 크롤링 가능 여부를 확인한다. */
	private String responseCode;

	/** HTTP 요청시 응답 메세지 */
	private String responseMessage;

	public int getTargetContentsNo() {
		return targetContentsNo;
	}

	public void setTargetContentsNo(int targetContentsNo) {
		this.targetContentsNo = targetContentsNo;
	}

	public int getContentsTypeNo() {
		return contentsTypeNo;
	}

	public void setContentsTypeNo(int contentsTypeNo) {
		this.contentsTypeNo = contentsTypeNo;
	}

	public int getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
	}

	public String getTargetContentsName() {
		return targetContentsName;
	}

	public void setTargetContentsName(String targetContentsName) {
		this.targetContentsName = targetContentsName;
	}

	public String getTargetContentsUrl() {
		return targetContentsUrl;
	}

	public void setTargetContentsUrl(String targetContentsUrl) {
		this.targetContentsUrl = targetContentsUrl;
	}

	public String getTargetContentsCssSelector() {
		return targetContentsCssSelector;
	}

	public void setTargetContentsCssSelector(String targetContentsCssSelector) {
		this.targetContentsCssSelector = targetContentsCssSelector;
	}

	public String getTitleCssSelector() {
		return titleCssSelector;
	}

	public void setTitleCssSelector(String titleCssSelector) {
		this.titleCssSelector = titleCssSelector;
	}

	public String getTitleAttr() {
		return titleAttr;
	}

	public void setTitleAttr(String titleAttr) {
		this.titleAttr = titleAttr;
	}

	public String getDescriptionCssSelector() {
		return descriptionCssSelector;
	}

	public void setDescriptionCssSelector(String descriptionCssSelector) {
		this.descriptionCssSelector = descriptionCssSelector;
	}

	public String getDescriptionAttr() {
		return descriptionAttr;
	}

	public void setDescriptionAttr(String descriptionAttr) {
		this.descriptionAttr = descriptionAttr;
	}

	public String getImageCssSelector() {
		return imageCssSelector;
	}

	public void setImageCssSelector(String imageCssSelector) {
		this.imageCssSelector = imageCssSelector;
	}

	public String getImageAttr() {
		return imageAttr;
	}

	public void setImageAttr(String imageAttr) {
		this.imageAttr = imageAttr;
	}

	public String getUrlCssSelector() {
		return urlCssSelector;
	}

	public void setUrlCssSelector(String urlCssSelector) {
		this.urlCssSelector = urlCssSelector;
	}

	public String getUrlAttr() {
		return urlAttr;
	}

	public void setUrlAttr(String urlAttr) {
		this.urlAttr = urlAttr;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	@Override
	public String toString() {
		return "TargetContents{" +
				"targetContentsNo=" + targetContentsNo +
				", contentsTypeNo=" + contentsTypeNo +
				", siteNo=" + siteNo +
				", targetContentsName='" + targetContentsName + '\'' +
				", targetContentsUrl='" + targetContentsUrl + '\'' +
				", targetContentsCssSelector='" + targetContentsCssSelector + '\'' +
				", titleCssSelector='" + titleCssSelector + '\'' +
				", titleAttr='" + titleAttr + '\'' +
				", descriptionCssSelector='" + descriptionCssSelector + '\'' +
				", descriptionAttr='" + descriptionAttr + '\'' +
				", imageCssSelector='" + imageCssSelector + '\'' +
				", imageAttr='" + imageAttr + '\'' +
				", urlCssSelector='" + urlCssSelector + '\'' +
				", urlAttr='" + urlAttr + '\'' +
				", responseCode='" + responseCode + '\'' +
				", responseMessage='" + responseMessage + '\'' +
				'}';
	}
}
