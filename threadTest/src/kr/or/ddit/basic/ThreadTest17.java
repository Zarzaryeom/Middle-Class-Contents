package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * Vector, Hashtable(Map의 옛 버전) 등과 같이 예전부터 존재하던 Collection 객체들은 내부에 동기화 처리가 되어 있다.
 * 
 *  그런데 최근에 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다.
 *  그래서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다. 
*/
public class ThreadTest17 {
	private static Vector<Integer> vec = new Vector<Integer>();
	
	// 동기화 처리가 되지 않은 List
	private static List<Integer> list = new ArrayList<Integer>();
	
	// 동기화 처리를 한 List
	private static List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
		
	public static void main(String[] args) {
		
		// 익명구현체로 스레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 10000; i++) {
					//vec.add(i);
					//list.add(i);
					list2.add(i);
				}
			}
		}; 
		
		Thread[] ths = new Thread[] {
				new Thread(r), new Thread(r), new Thread(r),
				new Thread(r), new Thread(r)
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		//System.out.println("vec의 개수 : " + vec.size());	// 벡터는 동기화 처리가 자동으로 되어있기에 큰 문제없이 실행된다.
		//System.out.println("list1의 개수 : " + list.size());
		System.out.println("list2의 개수 : " + list2.size());
	}

}
