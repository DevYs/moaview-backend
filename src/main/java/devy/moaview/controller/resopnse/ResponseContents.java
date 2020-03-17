package devy.moaview.controller.resopnse;

import devy.moaview.domain.Contents;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.SiteMapper;

import java.util.List;

/**
 * 콘텐츠 정보 요청시 응답 정보
 */
public class ResponseContents extends Response {

	public ResponseContents(ContentsTypeMapper contentsTypeMapper, SiteMapper siteMapper) {
		super(contentsTypeMapper, siteMapper);
	}

	/** 콘텐츠 정보 목록 */
	private List<Contents> contentsList;

	public List<Contents> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<Contents> contentsList) {
		this.contentsList = contentsList;
	}

	@Override
	public String toString() {
		return "ResponseContents{" +
				"contentsList=" + contentsList +
				'}';
	}
}
