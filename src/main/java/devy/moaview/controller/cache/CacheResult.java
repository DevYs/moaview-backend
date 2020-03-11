package devy.moaview.controller.cache;

/**
 * 캐싱 처리시 결과를 표현하는 enum 클래스
 *
 * @author devy
 */
public enum CacheResult {

	/** 성공 */
	SUCCESS(1),

	/** 실패 */
	FAILED(0);

	/** 결과 값 */
	private int value;

	CacheResult(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Result{" +
				"value=" + value +
				'}';
	}
}
