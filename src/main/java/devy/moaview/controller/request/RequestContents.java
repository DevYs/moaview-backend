package devy.moaview.controller.request;

import java.util.List;

/**
 * 콘텐츠 정보를 요청시 필요한 정보
 */
public class RequestContents {

	/** 조회할 페이지 번호 */
	private int pageNo;

	/** 페이지당 컬럼 수 */
	private int limit = 20;

	/** 검색 위치 */
	private int offset = 0;

	/** 콘텐트 타입 번호 목록 */
	private List<Integer> contentsTypeNoList;

	/** 콘텐츠 제목 */
	private String title;

	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 페이지 번호를 세팅하면서 DB에서 검색할 offset 위치를 함께 계산한다.
	 * @param pageNo
	 *        페이지 번호
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		this.offset = (pageNo - 1) * this.limit;
	}

	public int getLimit() {
		return limit;
	}

	public int getOffset() {
		return offset;
	}

	public List<Integer> getContentsTypeNoList() {
		return contentsTypeNoList;
	}

	public void setContentsTypeNoList(List<Integer> contentsTypeNoList) {
		this.contentsTypeNoList = contentsTypeNoList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "RequestContents{" +
				"pageNo=" + pageNo +
				", contentsTypeNoList=" + contentsTypeNoList +
				", title='" + title + '\'' +
				'}';
	}
}
