package devy.moaview.controller.cache;

import devy.moaview.domain.ContentsType;
import devy.moaview.domain.Site;

import java.util.HashMap;
import java.util.Map;

/**
 * 정보 캐시
 *
 * <p>데이터베이스에 대한 요청을 줄이고 클라이언트에서 정보를 쉽게 사용할 수 있도록 미리 캐싱하기 위한 클래스</p>
 * <p>사이트 정보 및 콘텐츠 타입 정보만 캐싱한다.</p>
 *
 * @author devy
 */
public enum Cache {

	CONTENTS_TYPE(CacheType.CONTENTS_TYPE),
	SITE(CacheType.SITE);

	/** 콘텐츠 타입 정보 캐시 */
	private static final Map<Integer, ContentsType> CONTENTS_TYPE_MAP = new HashMap<>();

	/** 사이트 정보 캐시 */
	private static final Map<Integer, Site> SITE_MAP = new HashMap<>();

	/** 캐시 타입 */
	private	CacheType cacheType;

	Cache(CacheType cacheType) {
		this.cacheType = cacheType;
	}

	/**
	 * 정보를 1건 캐싱한다.
	 * @param obj 캐싱할 정보
	 * @return 성공 여부. 성공 1, 실패 0
	 */
	public int put(ICache obj) {
		// 1. 정보를 캐싱한다.
		cache().put(obj.getKey(), obj);

		// 2. 캐싱 여부를 확인한 후 결과를 반환
		return checkExist(obj.getKey());
	}

	/**
	 * 캐싱된 정보 중 1건을 갱신한다.
	 * @param obj 갱신할 정보
	 * @return 성공 여부. 성공 1, 실패 0
	 */
	public int modify(ICache obj) {

		// 1.정보를 갱신한다
		cache().replace(obj.getKey(), obj);

		// 2. 갱신 여부를 확인하고 결과를 반환한다.
		return checkExist(obj.getKey());

	}

	/**
	 * 정보 1건을 삭제한다.
	 * @param key 삭제할 key 값
	 * @return 성공 여부. 성공 1, 실패 0
	 */
	public int remove(int key) {

		// 1. 정보를 삭제한다
		cache().remove(key);

		// 2. 삭제 여부 확인
		int checkExistResult = checkExist(key);

		// 3. 삭제 여부 결과가 0일 경우 결과를 성공으로 반환한다.
		if(checkExistResult == CacheResult.FAILED.getValue()) {
			return CacheResult.SUCCESS.getValue();
		}

		// 4. 삭제 여부 결과가 1일 경우 결과를 실패로 반환한다.
		return CacheResult.FAILED.getValue();

	}

	/**
	 * 키 값으로 캐싱된 정보 1건을 가져온다.
	 * @param key 키 값
	 * @return 사이트 정보 또는 콘텐츠 타입
	 */
	public Object get(int key) {
		return cache().get(key);
	}

	/**
	 * 캐싱된 정보를 Map 형태로 반환한다.
	 * @return 캐싱된 Map 타입의 사이트 또는 콘텐츠 정보
	 */
	public Object getCacheMap() {
		return cache();
	}

	/**
	 * 캐싱, 삭제, 갱신 처리의 성공 여부를 확인한다
	 * @param no 성공 여부를 판별하려는 키 값
	 * @return 성공 여부. 성공 1, 실패 0
	 */
	private int checkExist(int no) {

		// 1. 키값으로 캐싱 정보를 1건 가져온다.
		Object obj = cache().get(no);

		// 2. 가져온 캐싱 정보가 null이 아니면 성공을 반환한다.
		if(obj != null) {
			return CacheResult.SUCCESS.getValue();
		}

		// 3. 가져온 캐싱 정보가 null이면 실패를 반환한다.
		return CacheResult.FAILED.getValue();

	}

	/**
	 * 조건에 맞는 캐시를 판단하여 반환한다.
	 * @return 캐시
	 */
	private Map cache() {

		// 1. 일치하는 캐시 타입의 캐시 판단하여 반환
		switch (this.cacheType) {
			case CONTENTS_TYPE : return CONTENTS_TYPE_MAP;
			case SITE : return SITE_MAP;
		}

		// 2. 일치하는 캐시 타입이 없으면 null을 반환한다
		return null;
	}

}
