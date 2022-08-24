package kr.or.ddit.basic.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
 * 
 * 		추가조건)
 * 		1) '6. 전화번호 저장' 메뉴를 추가하고 저장 기능을 구현한다.
 * 			(저장파일명은 'phoneData.dat'로 한다.)
 * 		2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
 * 		3) 프로그램을 종료할 때 Map의 데이터가 수정되거나 추가 또는 삭제되면 저장한 후 종료되도록 한다.
 * 		
 * 
 * 
 * 		메뉴 예시)
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  6. 전화번호 저장
 * 		  0. 프로그램 종료
 * 
 *   ---------------------------------------------
 *      실행 예시)
 * 		  1. 전화번호 등록
 * 		  2. 전화번호 수정  
 * 		  3. 전화번호 삭제	
 * 		  4. 전화번호 검색
 * 		  5. 전화번호 전체 출력
 * 		  6. 전화번호 저장
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

public class PhoneBookIOTest {

	public static void main(String[] args) {
		new PhoneBookIOTest().start();		
	}
	
	HashMap<String, String> map = new HashMap<String, String>();
	ArrayList<Phone> ph = new ArrayList<Phone>();
	
	
	// 시작
	public void start() {

		ObjectInputStream oin = null;
		//프로그램이 시작하면서 저장된 객체를 읽어와 넣기
		try {
			FileInputStream fis = new FileInputStream("d:/d_other/phoneData.dat");
			BufferedInputStream bis = new BufferedInputStream(fis);
			oin = new ObjectInputStream(bis);
			
			Object obj; // 읽어온 객체를 저장할 변수
			
			while((obj = oin.readObject()) != null) {
				Phone phn = (Phone)obj;
//				System.out.println("이름 : " + phn.getName());
//				System.out.println("주소 : " + phn.getAddr());
//				System.out.println("전화번호 : " + phn.getTel());
//				System.out.println("----------------------------");				
				map.put(phn.getName(), phn.getTel());
				ph.add(new Phone(phn.getName(), phn.getAddr(), phn.getTel()));
			}
			
		} catch(EOFException e) {
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(oin != null) try { oin.close(); } catch(IOException e) { }
		}
		
		while(true) {
			System.out.println();
			System.out.println("메뉴 화면");
			System.out.println("  1. 전화번호 등록");
			System.out.println("  2. 전화번호 수정");
			System.out.println("  3. 전화번호 삭제");
			System.out.println("  4. 전화번호 검색");
			System.out.println("  5. 전화번호 전체 출력");
			System.out.println("  6. 전화번호 저장");
			System.out.println("  0. 프로그램 종료");
			System.out.println("---------------------------");
			System.out.print(" 번호입력 >> ");
			System.out.println();
			System.out.println(ph.get(0).getName());
			int input = ScanUtil.nextInt();

			switch (input) {
			case 1:	inputTel(); break;
			case 2: insertTel(); break;
			case 3: deleteTel(); break;
			case 4: searchTel(); break;
			case 5: allTel(); break;
			case 6: phonenumSave(); break;
			case 0:
				exitSave();
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("입력한 값을 다시 확인해주세요!");
				break;
			}
		}
	}

	// 프로그램 종료전 저장되지 않은 자료들이 있다면 저장할 수 있도록 물어보는 메서드
	private void exitSave() {
		// 현재 존재하는 모든 인스턴스를 파일에 저장하는 함수
		// 이미 저장된 직렬화 데이터가 다시 저장되지 않게 하기 위해선?
		// 직렬화로 데이터를 한번 받아온 뒤 확인하는 과정 추가.
		// 파일 읽기 -> 저장된 map및 phone 데이터와 비교 -> 일치하지 않는 자료들만 저장

		// 현재 저장되어 있는 모든 map 값을 돌려보기
//		Set<String> keySet = map.keySet();

//		for (String key : keySet) {
			// 파일 읽어오기
//			ObjectInputStream ois = null;
//			try {
//				FileInputStream fis = new FileInputStream("d:/d_other/phoneData.dat");
//				BufferedInputStream bis = new BufferedInputStream(fis);
//				ois = new ObjectInputStream(bis);
//
//				Object obj;
//
//				while ((obj = ois.readObject()) != null) {
//					Phone phn = (Phone) obj;
//					if (phn.getName().equals(key)) { // 파일에 있는 map값들
//						map.remove(key); // map값에서 삭제
//					}
//				}
//
//			} catch (EOFException e) {
//				System.out.println("읽기 작업 끝");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {	if (ois != null) try {	ois.close();  } catch (IOException e) {	}
//			}
//		}
		
//		 남은 map값을 파일에 저장
//		if(map.size() != 0) {
//			try {
//				FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
//				BufferedOutputStream bout = new BufferedOutputStream(fout);
//				ObjectOutputStream oout = new ObjectOutputStream(bout);
//
//				Set<String> keySet2 = map.keySet();
//				for (String key : keySet2) {
//					for(int i = 0; i < ph.size(); i++) {
//						if(ph.get(i).getName().equals(key)){
//							oout.writeObject(new Phone(ph.get(i).getName(), ph.get(i).getAddr(), ph.get(i).getTel()));
//						}
//					}
//				}
//
//				oout.close();
//
//				System.out.println("파일에 저장 완료");
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
			try {
				FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
				BufferedOutputStream bout = new BufferedOutputStream(fout);
				ObjectOutputStream oout = new ObjectOutputStream(bout);

					for(int i = 0; i < ph.size(); i++) {
						oout.writeObject(new Phone(ph.get(i).getName(), ph.get(i).getAddr(), ph.get(i).getTel()));
					}

				oout.close();

				System.out.println("파일에 저장 완료");

			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	

	// 6.전화번호 저장
	private void phonenumSave() {
		//정보를 받고 Phoned의 인스턴스 생성
		System.out.println("저장할 전화번호의 사용자 정보를 입력하세요.");
		System.out.print(" ▶ 이름 : ");
		String name = ScanUtil.nextLine();
		if(map.containsKey(name)) {
			System.out.println("이미 등록된 사용자입니다.");
		}
				
		System.out.print(" ▶ 전화번호 :");
		String tel = ScanUtil.nextLine();
		
		System.out.print(" ▶ 주소 :");
		String addr = ScanUtil.nextLine();
				
		System.out.println("-----------------------------");
		System.out.println("    입력하신 정보를 확인해주세요 !");
		System.out.println("-----------------------------");
		System.out.println("   ▶ 이름 : " + name);
		System.out.println("   ▶ 전화번호 : " + tel);
		System.out.println("   ▶ 주소 : " + addr);
		System.out.println("-----------------------------");
		System.out.println("입력한 값이 정확합니까?(y/n)");
		String input = ScanUtil.nextLine();

		Phone ph1 = new Phone(name, addr,tel); // 인스턴트 객체 생성
		
		if(input.equals("y")) {
			ph.add(ph1);
			map.put(name, tel);
			
			System.out.println("성공적으로 저장되었습니다.");
		}else if(input.equals("n")) {
			System.out.println("등록과정을 취소하셨습니다.");
			System.out.println("메인메뉴로 돌아갑니다.");
			return;
		}
		
		// 객체를 파일로 저장
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/phoneData.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout = new ObjectOutputStream(bout);
			
			// 등록된 클래스를 저장
			System.out.println("등록 정보 저장");
			oout.writeObject(ph1);
			
			oout.close();
			
		} catch (IOException e) {
			e.printStackTrace();
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


class Phone implements Serializable{
	private static final long serialVersionUID = 4763075639327447999L;
	
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