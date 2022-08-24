package kr.or.ddit.basic;

/*
 * wait(), notify()를 이용한 두 쓰레드에서 번갈아 한번씩 실행되는 예제(흐름을 알기 위해)
 * 
 * wait(), notify), notifyAll()은 동기화 영역에서만 사용 가능하다.
*/
public class ThreadTest18 {

	public static void main(String[] args) {
		WorkObject work = new WorkObject();
		
		ThreadA thA = new ThreadA(work);
		ThreadB thB = new ThreadB(work);
		
		thA.start();
		thB.start();
		
	}

}


// 공통으로 사용할 클래스
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()메서드 실행 중...");
		notify(); // 두 스레드 중에 어떤게 먼저 사용될지 모르기에 notify()를 먼저 쓴다
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodA()메서드 wait() 다음 실행 중...");
	}
	public synchronized void methodB() {
		System.out.println("methodB()메서드 작업 중...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("methodB()메서드 wait() 다음 작업 중...");
	}
}

// 공통으로 사용하는 객체의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread{
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <=10; i++) {
			workObj.methodA();
		}
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

//공통으로 사용하는 객체의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <=10; i++) {
			workObj.methodB();
		}
		// notify()는 동기화 작업이 완료된 객체에서만 사용이 가능하기 때문에 동기화를 해준다.
		synchronized (workObj) { 
			workObj.notify();
		}
	}
}