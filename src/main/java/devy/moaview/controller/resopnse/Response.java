package devy.moaview.controller.resopnse;

import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;

import java.util.Map;

/**
 * API 요청시 응답해 줄 공통 정보
 *
 * @author devy
 */
public class Response {

	/** 키가 콘텐츠 타입 번호인 Map 타입의 콘텐츠 타입 정보 */
	private Map<Integer, ContentsType> contentsTypeMap;

	/** 키가 사이트 번호인 Map 타입의 사이트 정보 */
	private Map<Integer, Site> siteMap;

	public Map<Integer, ContentsType> getContentsTypeMap() {
		return contentsTypeMap;
	}

	public void setContentsTypeMap(Map<Integer, ContentsType> contentsTypeMap) {
		this.contentsTypeMap = contentsTypeMap;
	}

	public Map<Integer, Site> getSiteMap() {
		return siteMap;
	}

	public void setSiteMap(Map<Integer, Site> siteMap) {
		this.siteMap = siteMap;
	}

	@Override
	public String toString() {
		return "Response{" +
				"contentsTypeMap=" + contentsTypeMap +
				", siteMap=" + siteMap +
				'}';
	}
}
