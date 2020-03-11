package devy.moaview.domain;

import devy.moaview.controller.cache.ICache;
import devy.moaview.service.mapper.Mapper;

/**
 * 사이트 정보를 나타내는 도메인
 *
 * @author devy
 */
public class Site extends Mapper implements ICache {

	/** 사이트 번호 */
	private int siteNo;

	/** 사이트 이름 */
	private String siteName;

	/** 사이트 URL */
	private String siteUrl;

	public int getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	@Override
	public int getKey() {
		return this.siteNo;
	}

	@Override
	public String toString() {
		return "Site{" +
				"siteNo=" + siteNo +
				", siteName='" + siteName + '\'' +
				", siteUrl='" + siteUrl + '\'' +
				'}';
	}

}
