package devy.moaview.domain;

import devy.moaview.service.mapper.IMapper;
import devy.moaview.service.mapper.Mapper;
import devy.moaview.service.mapper.TargetContentsMapper;

import java.util.List;

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
				", responseCode='" + responseCode + '\'' +
				", responseMessage='" + responseMessage + '\'' +
				'}';
	}

}
