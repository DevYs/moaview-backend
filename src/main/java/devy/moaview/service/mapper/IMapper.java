package devy.moaview.service.mapper;

import java.util.List;

/**
 * 공통 Mapper 기능 정의
 *
 * @author devy
 */
public interface IMapper {

	/**
	 * 1건의 데이터를 저장한다.
	 * @param obj
	 *        저장할 데이터
	 * @return 성공여부. 1 성공, 0 실패
	 */
	int insert(Object obj);

	/**
	 * 1건의 데이터를 삭제한다.
	 * @param obj
	 *        삭제할 데이터
	 * @return 성공여부. 1 성공, 0 실패
	 */
	int delete(Object obj);

	/**
	 * 1건의 데이터를 갱신한다.
	 * @param obj
	 *        갱신할 데이터
	 * @return 성공여부. 1 성공, 0 실패
	 */
	int update(Object obj);

	/**
	 * DB에 질의한 결과를 반환한다.
	 * <p>질의 쿼리는 XML에 정의되며 해당 XML의 변수는 obj 매개변수를 통해 전달된다.</p>
	 * <p>전달되는 매개변수의 타입은 XML 질의 구문 정의시 parameterType으로 정의한다.</p>
	 * <p>질의한 결과를 반환하는 정보의 타입 역시 XML 질의 구문 정의시 resultType으로 정의한다.</p>
	 * @param obj
	 *        질의문의 매개변수를 정의한 객체
	 * @return 질의 결과 객체. 정의하는 타입에 따라 List, Map, Value 등 여러가지가 될 수 있다.
	 */
	List select(Object obj);

	/**
	 * DB에 질의한 결과를 1건 반환한다.
	 * <p>질의 쿼리는 XML에 정의되며 해당 XML의 변수는 pk 매개변수를 통해 전달된다.</p>
	 * <p>전달되는 매개변수의 타입은 XML 질의 구문 정의시 parameterType으로 정의한다.</p>
	 * <p>질의한 결과를 반환하는 정보의 타입 역시 XML 질의 구문 정의시 resultType으로 정의한다.</p>
	 * @param pk
	 *        질의문의 매개변수를 정의한 객체
	 * @return 질의 결과 객체. 정의하는 타입에 따라 List, Map, Value 등 여러가지가 될 수 있다.
	 */
	Object selectOne(Object pk);

}
