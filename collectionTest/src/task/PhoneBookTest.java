package task;

import java.util.ArrayList;
import java.util.HashMap;

import util.ScanUtil;

/*
 * 문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고,
 * 		Map을 이용하여 전화번호 정보를 관리하는 프그램을 작성하시오.
 * 
 * 		전화번호 정보는 Map에 저장하여 관리한다.
 * 		(key값은 입력받은 사람의 '이름'으로 하고, value값은 'Phone클래스의 인스턴스'로 한다.) 
 * 
 * 		아래 메뉴의 기능을 모두 작성하시오.
 * 		(삭제, 검색 기능은 '이름'을 이용해서 처리한다.)
 * 		메뉴 예시)
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  0. 프로그램 종료
 * 
 *   ---------------------------------------------
 *      실행 예시)
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  0. 프로그램 종료
 *    ---------------------
 *     번호입력 >> 1
 *     
 *     새롭게 등록할 전화번호 정보를 입력하세요.
 *     이름 >> 홍길동
 *     전화번호 >> 010-1111-1111
 *     주소 >> 대전시 중구 오류동
 *     
 *     '홍길동'전화번호 등록 완료!!
 *     
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  0. 프로그램 종료
 *    ---------------------
 *     번호입력 >> 1
 *     
 *     새롭게 등록할 전화번호 정보를 입력하세요.
 *     이름 >> 홍길동
 *     
 *     '홍길동'은 이미 등록된 사람입니다.
 *     
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  0. 프로그램 종료
 *    ---------------------
 *     번호입력 >> 5
 *     
 *   -------------------------------------
 *     번호    이름      전화번호        주 소
 *      1    홍길동   010-1111-1111  대전시 중구 오류동
 *      ~~~
 *      ~~~
 *   -------------------------------------
 *    출력완료
 *    
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  0. 프로그램 종료
 *    ---------------------
 *     번호입력 >> 0
 *     
 *     프로그램을 종료합니다.
 */

public class PhoneBookTest {

	public static void main(String[] args) {
		new PhoneBookTest().start();		
	}
	
	HashMap<String, String> map = new HashMap<String, String>();
	ArrayList<Phone> ph = new ArrayList<Phone>();
		
	// 시작
	public void start() {
		while(true) {
			System.out.println();
			System.out.println("메뉴 화면");
			System.out.println("  1. 전화번호 등록");
			System.out.println("  2. 전화번호 수정");
			System.out.println("  3. 전화번호 삭제");
			System.out.println("  4. 전화번호 검색");
			System.out.println("  5. 전화번호 전체 출력");
			System.out.println("  0. 프로그램 종료");
			System.out.println("---------------------------");
			System.out.print(" 번호입력 >> ");
			int input = ScanUtil.nextInt();

			switch (input) {
			case 1:	inputTel(); break;
			case 2: insertTel(); break;
			case 3: deleteTel(); break;
			case 4: searchTel(); break;
			case 5: allTel(); break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("입력한 값을 다시 확인해주세요!");
				break;
			}
		}
	}

	private void allTel() {		
		System.out.println("현재 저장되어 있는 모든 정보입니다.");
		System.out.println("---------------------------");
		System.out.println("번호  이름\t   전화번호\t주소");
		System.out.println("---------------------------");
		
		for(int i = 0; i < ph.size(); i++) {
			System.out.println((i+1) + "  " + ph.get(i).getName() + "  " + 
					ph.get(i).tel + "  " + ph.get(i).getAddr());
		}
		System.out.println("---------------------------");
	}

	private void searchTel() {
		System.out.println("찾을 전화번호의 사용자를 입력해주세요.");
		String name = ScanUtil.nextLine();
		
		if(map.containsKey(name)) {
			System.out.println(name + " 사용자의 번호 : " + map.get(name) + "입니다.");
		}else {
			System.out.println("입력하신 사용자에 대한 정보를 찾을 수 없습니다.");
		}
	}

	private void deleteTel() {
		System.out.println("삭제할 전화번호의 사용자를 입력해주세요.");
		String name = ScanUtil.nextLine();
		
		if(map.containsKey(name)) {
			map.remove(name);
			System.out.println("삭제가 성공적으로 진행되었습니다.");		
		}else {
			System.out.println("입력하신 사용자에 대한 정보를 찾을 수 없습니다.");
		}
	}

	private void insertTel() {
		System.out.println("전화번호 수정 절차를 시작합니다.");
		System.out.println("수정할 정보의 이름을 입력하세요.");
		String name = ScanUtil.nextLine();
		
		if(map.containsKey(name)) {		
			System.out.println("수정할 번호를 입력하세요.");
			String tel = ScanUtil.nextLine();
		
			map.put(name, tel);
		
			System.out.println("정보가 다음과 같이 수정되었습니다.");
			System.out.println(" ▷ 이름 : " + name + "  ▷ 수정된 번호 : " + tel);
		}else {
			System.out.println("입력하신 사용자에 대한 정보를 찾을 수 없습니다.");
		}
		
	}

	private void inputTel() {
		input:while(true) {
			System.out.println("번호 등록을 시작합니다.");
			System.out.println("아래의 설명에 맞는 정보를 입력해주세요");
			System.out.print(" ▶ 이름 : ");
			String name = ScanUtil.nextLine();
			if(map.containsKey(name)) {
				System.out.println("'" + name + "'은 이미 등록된 사람입니다.");
				return;
			}
			System.out.print(" ▶ 전화번호 : ");
			String tel = ScanUtil.nextLine();
			System.out.print(" ▶ 주소 : ");
			String addr = ScanUtil.nextLine();
		
			while(true) {
				System.out.println("-----------------------------");
				System.out.println("입력하신 정보를 확인해주세요 !");
				System.out.println("-----------------------------");
				System.out.println(" ▶ 이름 : " + name);
				System.out.println(" ▶ 전화번호 : " + tel);
				System.out.println(" ▶ 주소 : " + addr);
				System.out.println("-----------------------------");
				System.out.println("정보가 맞다면 'y' 틀리면 'n'을 눌러주세요");
				String input = ScanUtil.nextLine();
		
				if(input.equals("y")) {
					ph.add(new Phone(name, addr, tel));
					map.put(name, tel);
					
					System.out.println("정보가 성공적으로 등록되었습니다.");
					return;
				}else if(input.equals("n")) {
					System.out.println("정보를 다시 입력합니다.");
					continue input; 
				}else {
					System.out.println("잘못된 입력입니다. 입력정보를 확인해주세요.");
				}		
			}
		}
		
	}
	

}


class Phone{
	String name; // 이름
	String addr; // 주소
	String tel;  // 전화번호
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	
	public Phone(String name, String addr, String tel) {
		super();
		this.name = name;
		this.addr = addr;
		this.tel = tel;
	}
}