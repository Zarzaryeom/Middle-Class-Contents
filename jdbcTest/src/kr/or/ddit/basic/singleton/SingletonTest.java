package kr.or.ddit.basic.singleton;

public class SingletonTest {

	public static void main(String[] args) {
		//Mysingleton test1 = new Mysingleton(); // 외부에서 new 명령으로 생성 불가
		
		Mysingleton test2 = Mysingleton.getInstance();
		Mysingleton test3 = Mysingleton.getInstance();
		
		System.out.println("test2 => " + test2);
		System.out.println("test3 => " + test3);
		
		System.out.println(test2 == test3);
		System.out.println(test2.equals(test3));
		
		
		test2.displayTest();
		
		
		
	}

}
