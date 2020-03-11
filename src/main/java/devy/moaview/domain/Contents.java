package devy.moaview.domain;

import devy.moaview.service.mapper.ContentsMapper;
import devy.moaview.service.mapper.Mapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 콘텐츠 정보
 *
 * <p>해당 도메인이 실제 사용자에게 보여지는 정보이다</p>
 *
 * @author devy
 */
public class Contents extends Mapper {

	/** 콘텐츠 번호 */
	private int contentsNo;

	/** 콘텐츠 타입 번호 */
	private int contentsTypeNo;

	/** 사이트 정보 번호 */
	private int siteNo;

	/** 타겟 콘텐츠 정보 번호 */
	private int targetContentsNo;

	/** 타겟 콘텐츠 이름 */
	private String targetContentsName;

	/** 콘텐츠 제목 */
	private String title;

	/** 콘텐츠 URL */
	private String url;

	/** 콘텐츠 정보 */
	private String description;

	/** 콘텐츠 썸네일 URL */
	private String thumbnailUrl;

	/** 등록일 */
	private LocalDateTime regDate;

	public int getContentsNo() {
		return contentsNo;
	}

	public void setContentsNo(int contentsNo) {
		this.contentsNo = contentsNo;
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

	public int getTargetContentsNo() {
		return targetContentsNo;
	}

	public void setTargetContentsNo(int targetContentsNo) {
		this.targetContentsNo = targetContentsNo;
	}

	public String getTargetContentsName() {
		return targetContentsName;
	}

	public void setTargetContentsName(String targetContentsName) {
		this.targetContentsName = targetContentsName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Contents{" +
				"contentsNo=" + contentsNo +
				", contentsTypeNo=" + contentsTypeNo +
				", siteNo=" + siteNo +
				", targetContentsNo=" + targetContentsNo +
				", targetContentsName='" + targetContentsName + '\'' +
				", title='" + title + '\'' +
				", url='" + url + '\'' +
				", description='" + description + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				", regDate=" + regDate +
				'}';
	}

}
