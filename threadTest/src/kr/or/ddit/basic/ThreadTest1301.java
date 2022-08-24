package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
	10마리의 말들이 경주하는 경마 프로그램 작성하기
	말은 Horse라는 이름의 쓰레드 클래스로 작성하는데
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
	그리고 이 클래스에는 등수를 오름차순으로 처리할 수 있는 내부 정렬기준이 있다.
	(Comparer인터페이스 구현)
	
	경기 구간은 1 ~ 50 구간으로 되어 있다.
	
	경기가 끝나면 등수 순으로 출력한다.
	
	경기가 진행 중일때는 중간 중간에 말들의 위치를 아래와 같이 나타낸다.(하나의 스레드로 구성)
	예)
	01번말 : --->--------------------- 
	02번말 : -------->----------------
	...
	10번말 : ----->-------------------
	
	 
*/
public class ThreadTest1301 {
	// 말 이름
	public static String[] horseName = {"스페셜스토리", "슈퍼파크", "로드로즈", "올드블레이드",
			"해피플랜", "은혜파도", "프라우들리", "안양호랑이", "오피서커맨딩", "대지"};

	public static void main(String[] args) {
		List<Map<String, Object>> rankList = new ArrayList<Map<String, Object>>();
		HashMap<String, Object> map = new HashMap<>();
		
		RacePosition2 rp = new RacePosition2(null);
		
		long gameStart = System.currentTimeMillis() / 1000; // 스레드가 시작 시간
		
		System.out.println("                - 대덕 경마 스타디움 -");
		System.out.println("==========================================================");
		System.out.println("                      Track");		
		System.out.println("==========================================================");
		
		// 경주마 입력 및 스레드 출력
		for (int i = 0; i < horseName.length; i++) {
			rp = new RacePosition2(horseName[i]);
			rp.start();
			
			System.out.println(rp.gethorsename());

			map = new HashMap<String, Object>();

			map.put("HNAME", horseName[i]);
			map.put("LOCATION", rp.getNum());
			map.put("ENDTIME", rp.getEndTimeCheck());

			rankList.add(map);
		}
		
//		System.out.println(map.get("HNAME").toString());
//		System.out.println(rankList);
//		System.out.println(rp.gethorsename());

			// 경기 내용을 출력
			do {
				long chekTime = System.currentTimeMillis() / 1000;
				if (0 < (chekTime - gameStart) && (chekTime - gameStart) < 4) {
					for(int i = 0; i < rankList.size(); i ++) {
						if (rankList.get(i).get("HNAME").equals(rp.gethorsename())) {
							map = new HashMap<String, Object>();

							map.put("LOCATION", rp.getNum());

							ShowRace2 sr = new ShowRace2(rp.gethorsename(), rp.getNum());

							sr.start();

							try {
								sr.join();
							} catch (InterruptedException e) {
								// TODO: handle exception
							}

						}
					}
				}
				// 경주가 끝나면 값을 저장한다.
//				if (rp.isRaceEnd() == false) {
//						map = new HashMap<String, Object>();
//
//						map.put("LOCATION", rp.getNum());
//						map.put("ENDTIME", rp.getEndTimeCheck());
//
//						rankList.set(rankList.indexOf(rp.getName()), map);
//					}
				
			} while (rp.isRaceEnd() == true);
			
		System.out.println(rankList);

		// 모든 경주마가 들어올 때까지 기다린다.
		try {
			rp.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		

		
		
		// rankList를 활용하여 그 값을 통해 배열 활용.
//		List<Integer> rank = new ArrayList<Integer>(); // 랭크를 저장할 리스트
//		
//		for(int i = 0; i < rankList.size(); i++ ) {
//			int rankNum = 1;
//			for(int j = 0; j < rankList.size(); j++) {
//				if(Long.parseLong(rankList.get(i).get("ENDTIME").toString()) > Long.parseLong(rankList.get(j).get("ENDTIME").toString())) {
//					rankNum++;
//				}
//			}
//			System.out.println(rankList.get(i).get("ENDTIME"));
//			rank.add(rankNum);
//		}		
		
		
		// 최종 등수를 출력
//		System.out.println("*********** 최종 등수 ***********");
//		for(int i = 0; i < rankList.size(); i++) {
//			System.out.println(rankList.get(i).get("HNAME") + " : " + rank.get(i));
//		}
		
		
		
		
		
		
		
		
		
	}

}

// 말의 현재 위치를 구하는 스레드
class RacePosition2 extends Thread{
	public String horsename; // 말 이름
	public long endRaceTime; // 쓰레드가 끝날 때 시간
	public long getEndRaceTime() {
		return endRaceTime;
	}

	public void setEndRaceTime(long endRaceTime) {
		this.endRaceTime = endRaceTime;
	}

	public String gethorsename() {
		return horsename;
	}



	public long time; // 스레드가 시작할때 시간
	public int num; // 말의 위치
	public boolean raceEnd = true;

	public boolean isRaceEnd() {
		return raceEnd;
	}

	public long getTime() {
		return time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public long getEndTimeCheck() {
		return endRaceTime;
	}

	// 생성자
	public RacePosition2(String horsename) {
		this.horsename = horsename;
	}
	

	
	@Override
	public void run() { 
		for(int i = 1; i <= 50; i++) {
			num = i;
			try {
				// 일시정지값 : 101 ~ 1000
				Thread.sleep((int)Math.random() * 900 + 101);
			} catch (InterruptedException e) {
			}			
			}
			
		endRaceTime = System.currentTimeMillis() / 1000;
		System.out.println(horsename + " 골인!!");
		raceEnd = false;
	}
}

// 전체 경주의 모습을 보여줄 스레드(경주를 한번 출력하면 쓰레드가 끝나도록 구성)
class ShowRace2 extends Thread{
	String[] track = {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
			"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
			"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
			"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
			"-", "-", "-", "-", "-", "-", "-", "-", "-", "-",};
	Integer racePosition;
	String name;
	
	public ShowRace2(String name, Integer racePosition) {
		this.name = name;
		this.racePosition = racePosition;
	}

	@Override
	public void run() {
			track[racePosition] = ">"; // 현재 위치를 나타내는 함수	
			System.out.print(name + " : " );
			for(int k = 0; k < track.length; k++) { // 경기장 출력
				System.out.print(track[k]);
			}
			track[racePosition] = "-";	
			System.out.println();
	}
}

//class raceDesc implements Comparable<List<Map<String, Object>>>{
//
//	@Override
//	public int compareTo(List<Map<String, Object>> time1, List<Map<String, Object>> time2) {
//		
//		String t1 = time1.get("ENDTIME").toString();
//		String t2 = time1.get("ENDTIME").toString();
//		
//		if(t1.compareTo(t2) < 0) {
//			return 1;
//		}else if(t1.compareTo(t2) > 0) {
//			return -1;
//		}else return 0;
//		
//	}
//	
//}




