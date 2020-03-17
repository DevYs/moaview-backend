package devy.moaview.controller.request;

/**
 * 타겟 콘텐츠 요청 객체
 */
public class RequestTargetContents {

	/** 페이지 번호 */
	private int pageNo;

	/** 페이지당 목록 아이템 갯수 */
	private int limit = 20;

	/** DB에서의 위치 */
	private int offset = 0;

	/** 사이트 번호 */
	private int siteNo;

	/** 콘텐츠 타입 번호 */
	private int contentsTypeNo;

	/** 타겟 콘텐츠명 검색어 */
	private String searchWord;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.offset = (this.pageNo - 1) * 20;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getSiteNo() {
		return siteNo;
	}

	public void setSiteNo(int siteNo) {
		this.siteNo = siteNo;
	}

	public int getContentsTypeNo() {
		return contentsTypeNo;
	}

	public void setContentsTypeNo(int contentsTypeNo) {
		this.contentsTypeNo = contentsTypeNo;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "RequestTargetContents{" +
				"pageNo=" + pageNo +
				", siteNo=" + siteNo +
				", contentsTypeNo=" + contentsTypeNo +
				", searchWord='" + searchWord + '\'' +
				'}';
	}
}
