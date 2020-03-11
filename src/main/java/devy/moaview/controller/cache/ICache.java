package devy.moaview.controller.cache;

/**
 * 캐싱할 객체가 구현해야하는 인터페이스
 *
 * @author devy
 */
public interface ICache {

	/**
	 * 캐싱하기 위해 키값을 정의한다
	 * @return 키 값은 int 형이다.
	 */
	int getKey();

}
