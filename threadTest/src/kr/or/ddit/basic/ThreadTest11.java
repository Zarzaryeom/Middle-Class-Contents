package kr.or.ddit.basic;

/*
	Thread의 stop()메서드를 호출하면 해당 스레드가 바로 멈춘다.
	이 때 이 스레드가 사용하던 자원을 정리하지 못하고 프로그앰이 종료되어
	나중에 실행되는 프로그램에 영향을 줄 수 있다.
	그래서 stop()메서드는 비추천으로 되어 있다.
*/
public class ThreadTest11 {

	public static void main(String[] args) {
		/*
		ThreadStopTest01 th1 = new ThreadStopTest01();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		//th1.stop(); ==> 자원정리를 하지 않고 종료된다
		
		th1.setStop(true);
		*/
		
		ThreadStopTest02 th2 = new ThreadStopTest02();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		th2.interrupt();
		
	}

}

// 스레드를 멈추게하는 연습용 스레드
class ThreadStopTest01 extends Thread{
	private boolean stop;
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드를 실행 중...");
		}
		System.out.println("자원 정리....");
		System.out.println("스레드 종료....");
	}
}

// interrupt()메서드를 이용하여 스레드를 멈추게 하는 연습용 스레드
class ThreadStopTest02 extends Thread{
	@Override
	public void run() {
		/*
		// 방법 1 ==> interrupt()메서드와 sleep()메서드를 이용하는 방법
		// 작업의 속도가 딜레이될 수 있는 단점이 있다.
		try {
			while(true) {
				System.out.println("실행 중...");
				Thread.sleep(1);
			}
		}catch(InterruptedException e) {
			System.out.println("interrupt 발생");
		}
			System.out.println("자원 정리...");
			System.out.println("쓰레드 종료...");
		*/
		
		// 방법 2
		while(true) {
			System.out.println("Thread 실행 중...");
			
			// interrupt()메서드가 호출되었는지 여부를 검사한다.
			
			/*
			// 검사 방법 1 ==> 스레드의 인서턴스 메서드인 isInterrupted()메서드를 이용하기
			// isInterrupted()메서드는 interrupt()메서드가 호출되면 true를 반환단다.
			if(this.isInterrupted()) {
				break;
			}
		}
			 */
		
			// 검사 방법 2 ==> Thread의 정적 메서드인 interrupted()메서드를 이용하기
			// interrupted()메서드는 interrupt()메서드가 호출되면 true를 반환한다. 
			if(Thread.interrupted()) {
				break;
			}
		}
		System.out.println("자원 정리...");
		System.out.println("쓰레드 종료...");
		
	}
}

