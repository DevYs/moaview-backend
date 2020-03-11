package devy.moaview.domain;

import devy.moaview.controller.cache.ICache;
import devy.moaview.service.mapper.ContentsTypeMapper;
import devy.moaview.service.mapper.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 콘텐츠 타입
 * <p>커뮤니티, 유머, 뉴스 등의 콘텐츠의 타입을 나타낸다.</p>
 * @author devy
 */
public class ContentsType extends Mapper implements ICache {

	/** 콘텐츠 타입 번호 */
	private int contentsTypeNo;

	/** 콘텐츠 타입의 이름 */
	private String contentsTypeName;

	/** 콘텐츠 타입이 화면에 보여질 순서 */
	private int sequence;

	public int getContentsTypeNo() {
		return contentsTypeNo;
	}

	public void setContentsTypeNo(int contentsTypeNo) {
		this.contentsTypeNo = contentsTypeNo;
	}

	public String getContentsTypeName() {
		return contentsTypeName;
	}

	public void setContentsTypeName(String contentsTypeName) {
		this.contentsTypeName = contentsTypeName;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public int getKey() {
		return this.contentsTypeNo;
	}

	@Override
	public String toString() {
		return "ContentsType{" +
				"contentsTypeNo=" + contentsTypeNo +
				", contentsTypeName='" + contentsTypeName + '\'' +
				", sequence=" + sequence +
				'}';
	}

}
