package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고, 사용자의 가위 바위 보는 showInputDialog()를 이용해서 입력 받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임에 진 것으로 처리한다.
 * 
 * 5초 안에 입력이 있으면 승패를 구해서 출력한다.
 * 
 * 결과 예시)
 * 1) 5초안에 입력이 없을 때
 * - 결 과 -
 * 시간 초과로 당신이 졌습니다.
 * 
 * 2) 5초안에 입력이 있을 때
 * - 결 과 -
 * 컴퓨터 : 가위
 * 당 신 : 바위
 * 결 과 : 당신이 이겼습니다.
*/
public class ThreadTest07 {

	public static void main(String[] args) {
		ThreadTime ti = new ThreadTime();
		DataInput2 di = new DataInput2();
		
		ti.start();
		di.start();
		
	}

}

//시간을 세는 쓰레드
class ThreadTime extends Thread{
	@Override
	public void run() {
		for(int i = 5; i >= 0; i--) {
			System.out.println(i + "초 남았습니다.");			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if(DataInput2.x == true) {
				// 가위바위보 진행
				int random = (int) Math.random() * 4;
				String com = null;

				if(random == 1) com = "가위";
				else if(random == 2) com = "바위";
				else com = "보";
			
				if (com.equals(DataInput2.str)) {
					System.out.println("  - 결 과 - ");
					System.out.println("컴퓨터 : " + com);
					System.out.println("당 신 : " + DataInput2.str);
					System.out.println("결 과 : 비겼습니다.");
					return;
				} else if (com.equals("가위") && DataInput2.str.equals("보")
						|| com.equals("바위") && DataInput2.str.equals("가위")
						|| com.equals("보") && DataInput2.str.equals("바위")) {
					System.out.println("  - 결 과 - ");
					System.out.println("컴퓨터 : " + com);
					System.out.println("당 신 : " + DataInput2.str);
					System.out.println("결 과 : 당신은 패배했습니다.");
					return;
				} else {
					System.out.println("  - 결 과 - ");
					System.out.println("컴퓨터 : " + com);
					System.out.println("당 신 : " + DataInput2.str);
					System.out.println("결 과 : 당신은 승리했습니다.");
					return;
				}
			} else if (DataInput2.x == false && i == 0) {
				System.out.println("  - 결 과 - ");
				System.out.println("시간 초과로 당신이 패배하였습니다.");
				System.exit(0);
			}
		}
	}
}

//사용자에게 정보를 받는 쓰레드
class DataInput2 extends Thread{
	public static boolean x = false; //카운트 다운 체크
	public static String str;
	@Override
	public void run() {
		str = JOptionPane.showInputDialog("가위/바위/보 중에 하나를 입력하세요.");
		x = true;
		if(!DataInput2.str.equals("가위") && !DataInput2.str.equals("바위") && !DataInput2.str.equals("보")) {
			System.out.println("잘못된 입력입니다.");
			System.exit(0);
		}
	}
}
