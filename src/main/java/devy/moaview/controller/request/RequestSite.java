package devy.moaview.controller.request;

/**
 * 사이트 목록 조회를 위한 변수 정의 클래스
 *
 * @author devy
 */
public class RequestSite {

	/** 페이지 번호 */
	private int pageNo;

	/** 페이지당 목록 아이템 갯수 */
	private int limit = 20;

	/** DB 조회시 시작 위치 */
	private int offset = 0;

	/** 검색할 사이트명 */
	private String siteName;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.offset = (this.pageNo - 1) * this.limit;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Override
	public String toString() {
		return "RequestSite{" +
				"pageNo=" + pageNo +
				", limit=" + limit +
				", offset=" + offset +
				", siteName='" + siteName + '\'' +
				'}';
	}
}
