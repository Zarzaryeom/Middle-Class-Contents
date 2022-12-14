package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest071 {

	public static boolean inputCheck;
	
	public static void main(String[] args) {
		/*
		 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
		 * 
		 * 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고, 사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력 받는다.
		 * 
		 * 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
		 * 5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
		 * 
		 * 5초 안에 입력이 있으면 승패를 구해서 출력한다.
		 */

		
		// 교수님 풀이
		GameTimer gt = new GameTimer();
		
		// 난수를 이용해서 컴퓨터의 가위 바위 보 정하기
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3); // 0~2사이의 난수 만들기
		String com = data[index]; // 난수를 이용해서 컴퓨터의 가위, 바위, 보를 정한다.
		
		gt.start(); // 카운트다운 시작ㅁㄴㅇ
		
		// 사용자의 가위 바위 보 정하기
		String user = null;
		do {
			user = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
		}while(!user.equals("가위") && !user.equals("바위") && !user.equals("보"));
		
		inputCheck = true;
		
		// 결과 판정하기1
		String result ="";
		if(com.equals(user)) {
			result = "비겼습니다.";
		}else if(com.equals("가위") && user.equals("보") ||
				 com.equals("보") && user.equals("바위") ||
				 com.equals("바위") && user.equals("가위")) {
			result = "당신이 졌습니다.";			
		}else {
			result = "당신이 이겼습니다.";
		}
		
		// 결과 판정하기2
		/*
		  String temp = com + user;
		  switch(tem){
		  case "가위보":
		  case "바위가위":
		  case "보바위": result = "당신이 졌습니다.";
		  			break;
		  case "보가위"
		  case "가위바위"
		  case "바위보" : result = "당신이 이겼습니다";
		  			break;
		  default : result = "비겼습니다.";
		  }
		 */
		
		// 결과 출력하기
		System.out.println("   - 결 과 - ");
		System.out.println("컴퓨터 : " + com);
		System.out.println("사용자 : " + user);
		System.out.println("결 과 : " + result);
		
		
		
		
		
		}

}


class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트다운을 시작합니다.");
		for(int i = 5; i >= 1; i--) {
			
			// 입력완료 여부 검사
			if(ThreadTest071.inputCheck == true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("   - 결 과 - ");
		System.out.println("시간 초과로 당신이 졌습니다.");
		System.exit(0);		
	}
}