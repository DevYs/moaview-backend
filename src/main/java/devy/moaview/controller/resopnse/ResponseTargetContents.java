package devy.moaview.controller.resopnse;

import devy.moaview.domain.TargetContents;

import java.util.List;

/**
 * 타겟 콘텐츠 정보 요청시 응답 정보
 *
 * @author devy
 */
public class ResponseTargetContents extends Response {

	/** 타겟 콘텐츠 정보 목록 */
	private List<TargetContents> targetContentsList;

	public List<TargetContents> getTargetContentsList() {
		return targetContentsList;
	}

	public void setTargetContentsList(List<TargetContents> targetContentsList) {
		this.targetContentsList = targetContentsList;
	}

	@Override
	public String toString() {
		return "ResponseTargetContents{" +
				"targetContentsList=" + targetContentsList +
				'}';
	}
}
