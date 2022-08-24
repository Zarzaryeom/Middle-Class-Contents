package kr.or.ddit.basic.singleton;

/*
 * singleton 패턴
 *   ==> 객체가 1개만 만들어지게 하는 방법(외부에서 new 명령을 사용하지 못하게 한다.)
 *   
 *   - 사용 이유
 *    1) 메모리 낭비 방지
 *    2) 데이터의 공유가 쉽다.
 *    
 *   
 *   - singleton클래스 만드는 방법(필수 구성 요소)
 *    1) 자신 class의 참조값이 저장될 변수를 private static으로 선언한다.
 *    2) 모든 생성자의 접근 제한자를 private으로 한다.
 *    3) 자신 class의 인스턴스를 생성하고 생성된 인스턴스를 반환하는 메서드를
 *    	 public static으로 작성한다.
 *       (이 메서드의 이름은 보통 getInstance로 한다.)
 */

public class Mysingleton {
	// 1번
	private static Mysingleton single;
	
	// 생성자를 만들지 않으면 자동으로 기본 생성자가 만들어지는데, 이 때 접근제한자가 public으로 
	// 만들어 지기 때문에 생성자를 따로 지정한다.
	// 2번 --> 외부에서 new 명령을 사용하지 못하게 막는다.
	private Mysingleton() {
		System.out.println("생성자 입니다.");
	}
	
	// 3번
	public static Mysingleton getInstance() {
		if(single == null) single = new Mysingleton();
		
		return single;
	}
	
	// 기타 이 클래스가 처리할 내용들을 기술한다.
	public void displayTest() {
		System.out.println("싱글톤 클래스의 메서드 호출입니다...");
	}

	
	
}
