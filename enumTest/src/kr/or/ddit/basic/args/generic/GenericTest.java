package kr.or.ddit.basic.args.generic;

class NonGenericClass{ // 제네릭을 사용하기 전에 사용했던 방법
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}

	/*
	 * Generic클래스 만드는 방법
	 * 형식)
	 *   class 클래스명<제네릭타입글자>{
	 *   	private 제네릭타입글자 변수명;  // 변수 선언에 제네릭을 사용할 경우
	 *   	...
	 *   
	 *   	제네릭타입글자 메서드명(){  // 반환값이 있는 메서드에서 사용할 경우
	 *   	  ...
	 *        return 값;
	 *   	}
	 *   
	 *   	void 메서드명(제네릭타입글자 변수명){  // 메서드의 매개변수에 제네릭을 사용할 경우
	 *   	  ...
	 *   	} 
	 *   }
	 *   
	 *    - 제네릭타입글자는 보통 대문자 한 글자를 사용한다.
	 *  -- 제네릭타입글자 --
	 *   T ==> Type
	 *   K ==> Key
	 *   V ==> Value
	 *   E ==> Element
	 *   N ==> Number
	 *    - 이외에도 사용자가 지정해서 사용할 수 있다.
	 */

class MyGeneric<T>{ // 제네릭 생성하기
	private T value;
	
	public T getValue() {
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}


public class GenericTest {

	public static void main(String[] args) {
		// 제네릭을 사용하지 않았을 경우
		// 데이터의 저장은 큰 무리없이 기존과 같은 방법으로 가능하다.
		// (Object가 부모쪽임으로 자동으로 형 변환)
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);
		
		// 데이터를 꺼내는 경우 자동으로 형 변환이 안된다
		String rtnNg1 = (String)ng1.getValue();
		System.out.println("문자열 반환값 rtnNg1 = " + rtnNg1);
		
		Integer irtnNg2 = (Integer)ng2.getValue();
		System.out.println("정수형 반환값 itrnNg2 = " + irtnNg2);
		System.out.println("--------------------------------------");
		
		MyGeneric<String> mg1 = new MyGeneric<>();
		mg1.setValue("안녕하세요");
		MyGeneric<Integer>mg2 = new MyGeneric<>();
		mg2.setValue(500);
		
		// 제네릭을 사용한 클래스에서 데이터를 꺼내올 때 형변환 없이 사용 가능하다.
		String rtnMg1 = mg1.getValue();
		int irtnMg2 = mg2.getValue();
		
		System.out.println("제네릭 문자열 반환값 : " + rtnMg1);
		System.out.println("제네릭 정수형 반환값 : " + irtnMg2);
		System.out.println("--------------------------------------");
		
		
		// 제네릭을 사용했을 때와 사용하지 않았을 때의 오류가 발생하는 시점이 서로 다른 경우의 예
		
		// 제네릭을 사용하지 않을 때 개발자가 흔히 할 수 있는 실수 예시
		// 문법적으로 오류는 없지만 실행할 때 오류가 발생한다.
//		NonGenericClass test = new NonGenericClass();
//		test.setValue("우리나라");
//		
//		Integer num = (Integer)test.getValue();
//		System.out.println("num = " + num);
		
		// 제네릭을 사용한 경우
		// 컴파일 단계에서 에러를 미리 찾을 수 있다.
//		MyGeneric<String> test2 = new MyGeneric<>();
//		test2.setValue("대한민국");
//		
//		String num2 = test2.getValue();
//		System.out.println("num2 = " + num2);
		
		
	}

}
