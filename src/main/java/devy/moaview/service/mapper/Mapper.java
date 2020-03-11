package devy.moaview.service.mapper;

import java.util.List;

/**
 * 도메인과 DB를 Mapping하는 공통 Mapper 클래스
 *
 * <p>도메인 클래스에서 DB Mapping을 위해 필수로 상속받아야 한다.</p>
 *
 * @author devy
 */
public abstract class Mapper {

	/**
	 * 매퍼 주입 후 1건을 등록한다.
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int registry(IMapper iMapper) {
		return iMapper.insert(this);
	}

	/**
	 * 등록할 정보와 매퍼를 직접 받아 1건 등록한다.
	 * @param obj
	 *        등록할 정보
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public static int registry(Object obj, IMapper iMapper) {
		return iMapper.insert(obj);
	}

	/**
	 * 매퍼 주입 후 1건을 삭제한다.
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int remove(IMapper iMapper) {
		return iMapper.delete(this);
	}

	/**
	 * 삭제할 정보와 매퍼를 직접받아 1건 삭제한다.
	 * @param obj
	 *        삭제할 정보
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public static int remove(Object obj, IMapper iMapper) {
		return iMapper.delete(obj);
	}

	/**
	 * 매퍼 주입 후 1건을 갱신한다.
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public int modify(IMapper iMapper) {
		return iMapper.update(this);
	}

	/**
	 * 갱신할 정보와 매퍼를 직접받아 1건 갱신한다.
	 * @param obj
	 *        갱신할 정보
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 성공여부. 1 성공, 0 실패
	 */
	public static int modify(Object obj, IMapper iMapper) {
		return iMapper.update(obj);
	}

	/**
	 * 매퍼와 정보를 받아 정보를 조회한다.
	 * @param obj
	 *        조회에 필요한 변수를 정의한 객체 또는 값
	 * @param iMapper
	 *        매핑에 사용될 매퍼
	 * @return 조회된 목록
	 */
	public static List list(Object obj, IMapper iMapper) {
		return iMapper.select(obj);
	}

	/**
	 * 매퍼와 정보를 받아 식별값으로 정보를 1건 조회한다.
	 * @param pk
	 *        조회하려는 정보의 식별값
	 * @param iMapper
	 *        매핑에 사용할 매퍼
	 * @return 조회된 정보 1건
	 */
	public static Object get(Object pk, IMapper iMapper) {
		return iMapper.selectOne(pk);
	}

	/**
	 * 매퍼를 통해 insert, remove, update 되는 메서드의 성공 여부에 대한 값을 정의한다.
	 *
	 * @author devy
	 */
	private enum Result {

		/** 성공 */
		SUCCESS(1),

		/** 실패 */
		FAILED(0);

		/** 값 */
		private int value;

		Result(int value) {
			this.value = value;
		}

		/** getter */
		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			return "Result{" +
					"value=" + value +
					'}';
		}
	}

}
