package task;

import java.util.HashMap;
import java.util.Set;

import util.ScanUtil;
/*
 * Scanner객체 입력 메서드의 특징
 *  - next(), nextInt(), nextDouble(),.. 등
 *    ==> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
 *  - nextLine()
 *    ==> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
 *  - Scanner는 입력한 값이 입력버퍼에 먼저 저장된 후에 차례로 꺼내와서 처리된다.
 */

public class PhoneBookT {
	private HashMap<String, Phone2> phoneBookMap;

	// 생성자
	public PhoneBookT() {
		phoneBookMap = new HashMap<String, Phone2>();		
	}
	public static void main(String[] args) {
		new PhoneBookT().phoneStart();
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneStart() {
		System.out.println();
		System.out.println("**************************");
		System.out.println("    전화번호 관리 프로그램");
		System.out.println("**************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1: insert(); break; // 등록
			case 2: update(); break; // 수정
			case 3: delete(); break; // 삭제
			case 4: search(); break; // 검색
			case 5: displayAll(); break; // 전체출력
			case 0: 
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 선택하세요.");
			}
		}
	}
	
	// 전화번호 정보를 검색하는 메서드
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		Phone2 p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("=============================");
		System.out.println(" 이름 : " + p.getName());
		System.out.println(" 전화번호 : " + p.getTel());
		System.out.println(" 주손 : " + p.getAddr());
		System.out.println("=============================");
	}
	
	//전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();
		
		// 입력한 사람의 이름이 전화번호에 없으면
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("삭제 작업을 마칩니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨 전화번호 정보 삭제 완료");
	}
	
	// 전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();
		
		// 입력한 사람의 이름이 전화번호정보에 없으면..
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}		
		System.out.println("새로운 전화번호 >>");
		String newTel = ScanUtil.nextLine();
		
		System.out.println("새로운 주소 >>");
		String newAddr = ScanUtil.nextLine();
		
		phoneBookMap.put(name, new Phone2(name, newAddr, newTel));
		System.out.println(name + "씨의 전화번호 벙보 수정완료!!");
	}
	
	// 전화번호 정보 전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		// 모든 key값 가져오기
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("-----------------------------------");
		System.out.println(" 번호     이름     전화번호      주소");
		if(phoneKeySet.size() == 0) {
			System.out.println("등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			int cnt = 0; //번호 출력용 변수
			for(String name : phoneKeySet) {
				cnt++;
				Phone2 p = phoneBookMap.get(name);
				System.out.println(cnt + "  " + name + "  "  + p.getTel() + "  " + p.getAddr());
			}
		}
		System.out.println("-----------------------------------");
		System.out.println("출력 끝...");
	}
	
	// 새로운 전화번호 정보를 등록하는 메서드 
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.println("이름 >>");
		String name = ScanUtil.nextLine();
		
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.println("전화번호 >>");
		String tel = ScanUtil.nextLine();
		
		System.out.println("주소 >>");
		String addr = ScanUtil.nextLine();
		
		//Map에 추가 방법1
		Phone2 p = new Phone2(name, addr, tel);
		phoneBookMap.put(name, p);
		
		//추가 방법2
		//phoneBookMap.put(name, new Phone2(name, addr, tel));
	}
	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu() {
		System.out.println("--------------------------");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("0. 프로그램 종료");
		System.out.println("--------------------------");
		System.out.println("번호 입력 >>");
		int num = ScanUtil.nextInt();
		return num;		
	}

}


// 하나의 전화번호 정보가 저장될 class 작성
class Phone2{
	private String name;
	private String addr;
	private String tel;
	
	// 생성자
	public Phone2(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
}
