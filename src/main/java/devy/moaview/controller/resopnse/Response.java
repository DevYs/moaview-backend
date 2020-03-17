package devy.moaview.controller.resopnse;

import devy.moaview.controller.cache.Cache;
import devy.moaview.controller.request.RequestSite;
import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;

import java.util.List;
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

	public Response(ContentsTypeMapper contentsTypeMapper, SiteMapper siteMapper) {
		setContentsTypeMap(contentsTypeMapper);
		setSiteMap(siteMapper);
	}

	public Map<Integer, ContentsType> getContentsTypeMap() {
		return contentsTypeMap;
	}

	/**
	 * 콘텐츠 타입 목록을 조회하여 맵형태로 변환하여 저장
	 * @param contentsTypeMapper
	 * 		  콘텐츠 타입 목록을 조회할 매퍼
	 */
	private void setContentsTypeMap(ContentsTypeMapper contentsTypeMapper) {
		this.contentsTypeMap = (Map<Integer, ContentsType>) Cache.CONTENTS_TYPE.getCacheMap();
		if(contentsTypeMap.isEmpty()) {
			List<ContentsType> contentsTypeList = ContentsType.list(null, contentsTypeMapper);
			this.contentsTypeMap = (Map<Integer, ContentsType>) Cache.CONTENTS_TYPE.putAll(contentsTypeList);
		}
	}

	public Map<Integer, Site> getSiteMap() {
		return siteMap;
	}

	/**
	 * 사이트 정보 목록을 조회하여 맵형태로 변환하여 저장
	 * @param siteMapper
	 * 		  사이트 정보 목록을 조회할 매퍼
	 */
	private void setSiteMap(SiteMapper siteMapper) {
		this.siteMap = (Map<Integer, Site>) Cache.SITE.getCacheMap();
		if(siteMap.isEmpty()) {
			List<Site> siteList = Site.list(new RequestSite(), siteMapper);
			this.siteMap = (Map<Integer, Site>) Cache.SITE.putAll(siteList);
		}
	}

	@Override
	public String toString() {
		return "Response{" +
				"contentsTypeMap=" + contentsTypeMap +
				", siteMap=" + siteMap +
				'}';
	}
}
