package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import util.ScanUtil;

public class HotelManager {

	public static void main(String[] args) {
		new HotelManager().hotelStart();
	}
	
	HashMap<Integer, Room> room = new HashMap<Integer, Room>();	
	List<Integer> roomList = new ArrayList<Integer>();
	
	// 실행 메서드
	void hotelStart() {
		System.out.println("****************************************");
		System.out.println("     호텔에 오신걸 환영합니다. 어서오십시요.");
		System.out.println("****************************************");
		
		// 방 번호 초기화
		int roomNum = 201;
		for(int i = 0; i < 9; i++) {
			room.put(roomNum, new Room(roomNum, "싱글룸", "-"));
			roomNum++;
		}
		roomNum = 301;
		for(int i = 0; i < 9; i++) {
			room.put(roomNum, new Room(roomNum, "더블룸", "-"));
			roomNum++;
		}
		roomNum = 401;
		for(int i = 0; i < 9; i++) {
			room.put(roomNum, new Room(roomNum, "스위트룸", "-"));
			roomNum++;
		}
		
		while(true) {
			System.out.println("----------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인  2. 체크아웃  3. 객실상태  4.업무종료");
			System.out.println("----------------------------------------");
			System.out.print("선택 >> ");
			int input = ScanUtil.nextInt();
		
			switch (input) {
			case 1: checkin(); break;
			case 2: checkout(); break;
			case 3: roomState(); break;
			case 4: 
				System.out.println("****************************************");
				System.out.println("    호텔 영업이 끝났습니다. 이용해주셔서 감사합니다.");
				System.out.println("****************************************");
				System.exit(0);
				break;
			default:
				System.out.println("잘못된 번호를 입력하셨습니다.");
				System.out.println("번호를 다시 한번 확인해주세요.");
				break;
			}
		}
		
	}

	// 객실 상태
	private void roomState() {		
		System.out.println("----------------------------------------");
		System.out.println("               현재 객실 상태");
		System.out.println("----------------------------------------");
		System.out.println("방 번호\t방 종류\t 투숙객 이름");
		System.out.println("----------------------------------------");
		
		Set<Integer> roomSet = room.keySet(); // key 뽑아내기
		for(int list : roomSet ) {
			roomList.add(list);
		}
		Collections.sort(roomList); // key 정렬
		
		for(int key : roomList) {			
			System.out.println(room.get(key).roomNo +"\t" + room.get(key).roomCate +
					"\t    " + room.get(key).getCustomer());
		}
		System.out.println("----------------------------------------");
	}

	//체크아웃
	private void checkout() {
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
		int input = ScanUtil.nextInt();
		
		if(!room.containsKey(input)) {
			System.out.println("선택하신 방 번호는 없는 방 번호입니다.");
		}else if(room.get(input).getCustomer().equals("-")) {
			System.out.println(input + "호 객실에는 체크인 한 사람이 없습니다.");
		}else {
			System.out.println(input + "호 객실의 " + room.get(input).getCustomer() + "님 체크아웃을 완료하였습니다.");
			room.get(input).setCustomer("-");
		}
	}

	// 체크인
	private void checkin() {		
		
		System.out.println("----------------------------------------");
		System.out.println("              체크인 작업");
		System.out.println("----------------------------------------");
		System.out.println("  * 201 ~ 209 : 싱글룸");
		System.out.println("  * 301 ~ 309 : 더블룸");
		System.out.println("  * 401 ~ 409 : 스위트룸");
		System.out.println("----------------------------------------");
		System.out.print("방 번호 입력 >> ");
		int input = ScanUtil.nextInt();		
		
		if(room.containsKey(input) && room.get(input).getCustomer().equals("-")) {
			System.out.println("누구를 체크인하시겠습니까?");
			System.out.print("이름 입력 >>");
			String name = ScanUtil.nextLine();
			
			room.get(input).setCustomer(name);			
			room.put(input, room.get(input));			
			System.out.println("체크인이 완료되었습니다!");
		}else if(room.containsKey(input)){
			System.out.println(input + "호 객실은 이미 손님이 있습니다.");
		}else {
			System.out.println(input + "호 객실은 존재하지 않습니다.");
		}
	}
	
	
}

class Room{
	int roomNo;
	String roomCate;
	String customer;
	
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomCate() {
		return roomCate;
	}

	public void setRoomCate(String roomCate) {
		this.roomCate = roomCate;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	
	public Room(int roomNo, String roomCate, String customer) {
		this.roomNo = roomNo;
		this.roomCate = roomCate;
		this.customer = customer;
	}	
}