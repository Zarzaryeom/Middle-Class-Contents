package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.ScanUtil;

/*
 * 문제) Set을 활용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용해서 구한다.
 * 		(스트라이크는 S, 볼은 B로 나타낸다.)
 * 
 * 예시) 
 * 		컴퓨터 난수 ==> 9 5 7 
 * 		
 * 실행예시)
 * 	숫자입력 : 3 5 6
 *  3 5 6 => 1S 0B
 *  
 *  숫자입력 : 7 8 9
 *  7 8 9 => 0S 2B
 *  
 *  숫자입력 : 9 7 5
 *  9 7 5 => 1S 2B
 *  
 *  숫자입력 : 9 5 7
 *  9 6 7 => 3S 0B
 *  
 *  축하합니다...
 *  당신은 4번째만에 맞췄습니다.
 *  
 */

public class BaseballTest {

	public static void main(String[] args) {
		
		HashSet<Integer> hs = new HashSet<Integer>();		
		List<Integer> baseBall = new ArrayList<Integer>(); 
		
		
		// 야구 숫자 생성
		while(hs.size()<3) {
			int random = (int)(Math.random()*10);
			hs.add(random);
		}
		
		// Set의 자료를 List에 옮기기
		for(int num : hs) {
			int i = 0;
			baseBall.add(num);
		}
		
		// 자료 섞기
		Collections.shuffle(baseBall);
		
		System.out.println(baseBall);
		
				
		System.out.println("야구게임에 오신걸 환영합니다!");
		
		
		int num = 0;
		while (true) {
			List<Integer> number = new ArrayList<Integer>();
			int answerS = 0;
			int answerB = 0;

			System.out.println("첫번째 숫자를 입력하십시오.");
			int num1 = ScanUtil.nextInt();
			System.out.println("두번째 숫자를 입력하십시오.");
			int num2 = ScanUtil.nextInt();
			System.out.println("세번째 숫자를 입력하십시오.");
			int num3 = ScanUtil.nextInt();
			
			number.add(num1);
			number.add(num2);
			number.add(num3);
														
			if(baseBall.get(0) == num1) {
				answerS++;
			}else if(baseBall.get(0) == num2 || baseBall.get(0) == num3) {
				answerB++;
			}
		
			if(baseBall.get(1) == num2) {
				answerS++;
			}else if(baseBall.get(1) == num1 || baseBall.get(1) == num3) {
				answerB++;
			}
			if(baseBall.get(2) == num3) {
				answerS++;
			}else if(baseBall.get(2) == num2 || baseBall.get(2) == num3) {
				answerB++;
			}
		
		
		System.out.println("아쉽네요. 다시 도전해보세요!");
		System.out.println("입력된 숫자 : " + num1 + " " + num2 + " " + num3);
		System.out.println(num1 + " " + num2 + " " + num3 + " => " 
							+ answerS +"S" + " " + answerB + "B");
		System.out.println();
		
		num++;
		if(answerS == 3) break;		
		}
		
		System.out.println("축하합니다!!");
		System.out.println("당신은 " + num +"번째 만에 승리하셨습니다.");
		
		
		
		
		// 교수님 풀이
		new BaseballTest().gameStrat();
		
		
	}
	
	
	// 교수님 풀이
	private ArrayList<Integer> numList; // 난수가 저장될 List
	private ArrayList<Integer> userList; // 사용가자 입력한 값이 저장될 List
	
	private int strike;  // 스트라이크 개수
	private int ball; 	 // 볼 개수가 저장될 변수
	
	
	// 게임이 시작되는 메서드
	public void gameStrat() {
		
		// 난수 만드는 메서드 호출
		getNum();
		
		// 확인용
		System.out.println("컴퓨터 난수 : " + numList);
		
		int cnt = 0; // 몇번째만에 맞췄는지를 저장하는 변수
		
		do {
			cnt++;;
			
			// 사용자로부터 입력받는 메서드 호출
			inputNum();
			
			// 볼 카운트 구하기
			ballCount();
			
		}while(strike != 3);
		
		
		System.out.println();
		System.out.println("축하합니다.");
		System.out.println("당신은 " + cnt + "번째만에 맞췄습니다.");
	}
	
	
	// 1~9사이의 서로 다른 난수를 3개 만들어서 List에 저장하는 메서드
	// (Set이용)
	private void getNum() {
		Set<Integer> numSet = new HashSet<Integer>();
		
		// 1~9사이의 난수 만들기
		while(numSet.size() < 3) {
			numSet.add((int)(Math.random() * 9 + 1));
		}
		
		// 만들어진 난수를 List에 저장한다.
		numList = new ArrayList<Integer>();
		
		// List의 데이터를 섞어준다.
		Collections.shuffle(numList);
	}
	
	// 사용자로부터 3개의 정수를 입력받아서 List에 저장하는 메서드
	// 입력한 정수는 중복되지 않게 한다.
	private void inputNum() {
		int n1, n2, n3; //입력한 정수가 저장될 변수 선언
		
		do {
			System.out.print("숫자입력 : ");
			n1 = ScanUtil.nextInt();
			n2 = ScanUtil.nextInt();
			n3 = ScanUtil.nextInt();
			
			if(n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 숫자는 입력 할 수 없습니다.");
				System.out.println("다시 입력하세요.");
			}
					
		}while(n1 == n2 || n1 == n3 || n2 == n3);
		
		userList = new ArrayList<Integer>();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}
	
	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	private void ballCount() {
		strike = 0;
		ball = 0;		// 스트라이크와 볼의 개수를 0을로 초기화
		
		
		for(int i = 0; i < userList.size(); i++) {
			for(int j = 0; j < numList.size(); j++) {
				// 값이 같은지 검사
				if(userList.get(i) == numList.get(j)) {
					if(i == j ) {//위치가 같은지 검사
						strike++;
					}else {
						ball++;
					}
					
				}
			}
		}
			
		// 볼카운트 결과 출력하기
		System.out.printf("%d %d %d => %dS %dB\n",  numList.get(0), numList.get(2), strike, ball)
		
		;
		
		
	}

}
