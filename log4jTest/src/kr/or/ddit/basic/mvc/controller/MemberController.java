package kr.or.ddit.basic.mvc.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import kr.or.ddit.basic.mvc.service.IMemberService;
import kr.or.ddit.basic.mvc.service.MemberServiceimpl;
import kr.or.ddit.basic.mvc.vo.MemberVo;
import kr.or.ddit.util.CryptoUtil;


/*
	1. 회원 정보 중에서 회원ID는 양방향 암호화로 변환하여 DB에 저장하고 화면에 보여줄 때는
   	   원래의 데이터로 복원하여 보여준다.
	2. 비밀번호는 단방향 알고리즘으로 암호화하여 DB에 저장한다.
*/

public class MemberController {
	private Scanner sc = new Scanner(System.in);
	private IMemberService service;
	private String key = "ab1q2w3e4r5t6y7u";

	// 생성자
	public MemberController() {
		service = MemberServiceimpl.getInstance();
	}

	// 시작 메서드
	public void startMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		while (true) {
			int choice = displayMenu();
			switch (choice) {
			case 1: insertMember();
				break; // 추가
			case 2: updateMember();
				break; // 수정
			case 3: deleteMember();
				break; // 삭제
			case 4: displayMember();
				break; // 전체 자료 출력
			case 5: updateMember2();
				break; // 수정2
			case 6: updateMember3();
				break; // 수정3(Map을 변수로 이용)
			case 0: // 작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			}
		}
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택하서 수정하기
	private void updateMember3() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("회원ID >> ");
		String memId = sc.next();
		
		// 아이디 암호화(양방향)
		String encryptedId = CryptoUtil.encryptoAES256(memId, key);
		
		int count = service.getMemberCount(encryptedId);
		if(count == 0) { // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원 ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		int num;
		String updateField = null;
		String updateTitle = null;
		
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println(" 1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
			System.out.println("---------------------------------------");
			System.out.print("수정항목 선택 >> ");
			num = sc.nextInt();
			
			switch(num) {
				case 1: updateField = "mem_pass";
						updateTitle = "비밀번호"; break;
				case 2: updateField = "mem_name";
						updateTitle = "회원이름"; break;
				case 3: updateField = "mem_tel";
						updateTitle = "전화번호"; break;
				case 4: updateField = "mem_addr";
						updateTitle = "회원주소"; break;
				default :
					System.out.println("수정 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
		}while(num < 1 || num > 5);
		
		System.out.println();
		sc.nextLine(); // 버퍼 비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = sc.nextLine();
		
		if(updateTitle.equals("비밀번호")) {
			updateData = CryptoUtil.sha512(updateData);
		}
		
		// 수정 작업에 필요한 정보를 Map객체에 셋팅한다.
		// key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
		Map<String, String> paramMap = new HashMap<String, String>();
		
		paramMap.put("memid", encryptedId);       // 회원ID
		paramMap.put("field", updateField); // 수정할 컬럼명
		paramMap.put("data", updateData);   // 수정할 데이터
		
		int cnt = service.updateMember3(paramMap);
		
		if(cnt > 0) {
			System.out.println("데이터 수정을 성공했습니다.");
		}else {
			System.out.println("데이터 수정에 실패했습니다.");
		}
		
	}
	


	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택하서 수정하기
		private void updateMember2() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원ID >> ");
			String memId = sc.next();
			
			// 아이디 암호화(양방향)
			String encryptedId = CryptoUtil.encryptoAES256(memId, key);
			
			int count = service.getMemberCount(encryptedId);
			if(count == 0) { // 없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원 ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			int num;
			String updateField = null;
			String updateTitle = null;
			
			do{
				System.out.println();
				System.out.println("수정할 항목을 선택하세요.");
				System.out.println(" 1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
				System.out.println("---------------------------------------");
				System.out.print("수정항목 선택 >> ");
				num = sc.nextInt();
				
				switch(num) {
					case 1: updateField = "mem_pass";
							updateTitle = "비밀번호"; break;
					case 2: updateField = "mem_name";
							updateTitle = "회원이름"; break;
					case 3: updateField = "mem_tel";
							updateTitle = "전화번호"; break;
					case 4: updateField = "mem_addr";
							updateTitle = "회원주소"; break;
					default :
						System.out.println("수정 항목을 잘못 선택했습니다.");
						System.out.println("다시 선택하세요.");
				}
			}while(num < 1 || num > 5);
			
			System.out.println();
			sc.nextLine(); // 버퍼 비우기
			System.out.print("새로운 " + updateTitle + " >> ");
			String updateData = sc.nextLine();
			
			if(updateTitle.equals("비밀번호")) {
				updateData = CryptoUtil.sha512(updateData);
			}
			
			int cnt = service.updateMember2(updateData, updateField, encryptedId);
			
			if(cnt > 0) {
				System.out.println("데이터 수정을 성공했습니다.");
			}else {
				System.out.println("데이터 수정에 실패했습니다.");
			}
			
		}
	
	
	// 전체 회원 정보를 출력하는 메서드
		private void displayMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			System.out.println();
			System.out.println("-----------------------------------");
			System.out.println(" ID  비밀번호   이름    전화번호     주소");
			System.out.println("-----------------------------------");
			
			List<MemberVo> list = new ArrayList<MemberVo>();
			
			list = service.getAllMember();
			
			if(list == null || list.size() == 0) {
				System.out.println("출력할 자료가 하나도 없습니다.");
			}else {
			
				for (int i = 0; i < list.size(); i++) {
					String decryptedId = CryptoUtil.decryptoAES256(list.get(i).getMem_id(), key);
					System.out.println(decryptedId + "\t" + 
										list.get(i).getMem_pass() + "\t" + 
										list.get(i).getMem_name() + "\t" + 
										list.get(i).getMem_tel() + "\t"  + 
										list.get(i).getMem_addr());
				}
			}
			
			System.out.println("-----------------------------------");
			System.out.println("출력 끝..");
			
		}
	
	// 회원 정보를 삭제하는 메서드
		private void deleteMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			System.out.println();
			System.out.println("삭제할 회원정보를 입력하세요.");
			System.out.print("삭제할 회원ID >> ");
			String memId = sc.next();
			
			// 아이디 암호화(양방향)
			String encryptedId = CryptoUtil.encryptoAES256(memId, key);
			
			int cnt = service.deleteMember(encryptedId);
			
			if(cnt > 0) {
				System.out.println("회원정보 삭제 성공!");
			}else {
				System.out.println("회원정보 삭제 실패..");
			}
		}
	
	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정하기
		private void updateMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요...");
			System.out.print("회원ID >> ");
			String memId = sc.next();
			
			// 아이디 암호화(양방향)
			String encryptedId = CryptoUtil.encryptoAES256(memId, key);
			
			int count = service.getMemberCount(encryptedId);
			if(count == 0) { // 없는 회원이면...
				System.out.println(memId + "은(는) 없는 회원 ID입니다.");
				System.out.println("수정 작업을 마칩니다.");
				return;
			}
			System.out.println();
			System.out.println("수정할 내용을 입력하세요.");
			System.out.print("새로운 비밀번호 >> ");
			String newMemPass = sc.next();
			// 비밀번호 암호화(단방향)
			String encryptedPass = CryptoUtil.sha512(newMemPass);
			
			System.out.print("새로운 회원이름 >> ");
			String newMemName = sc.next();
			
			System.out.print("새로운 전화번호 >> ");
			String newMemTel = sc.next();
			
			sc.nextLine();
			System.out.print("새로운 주소 >> ");
			String newMemAddr = sc.nextLine();
			
			MemberVo memVo = new MemberVo();
			memVo.setMem_pass(encryptedPass);
			memVo.setMem_name(newMemName);
			memVo.setMem_tel(newMemTel);
			memVo.setMem_addr(newMemAddr);
			memVo.setMem_id(encryptedId);
			
			int cnt = service.updateMember(memVo);
			
			if(cnt > 0) {
				System.out.println("회원정보 업데이트 성공!");
			}else {
				System.out.println("회원정보 업데이트 실패..");
			}
			
		}

	// 메뉴를 출력하고 선택한 작업번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println(" == 작 업 선 택 ==");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 수정");
		System.out.println(" 3. 자료 삭제");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 6. 자료 수정3");
		System.out.println(" 0. 작업 종료");
		System.out.println("-------------------------");
		System.out.print("원하는 작업번호 >> ");

		return sc.nextInt();
	}

	// 회원 정보를 추가(입력)하는 메서드
	private void insertMember() throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");

		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
		int count = 0; // 입력한 회원ID의 개수가 저장될 변수

		String memId; // 회원ID가 저장될 변수
		String encryptedId;
		do {
			System.out.print("회원ID >> ");
			memId = sc.next();
			
			// 아이디 암호화(양방향)
			encryptedId = CryptoUtil.encryptoAES256(memId, key);

			count = service.getMemberCount(encryptedId);

			if (count > 0) {
				System.out.println(memId + "는(은) 이미 등록된 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
		} while (count > 0);
		
		// 아이디 암호화(양방향)
		encryptedId = CryptoUtil.encryptoAES256(memId, key);

		System.out.print("비밀번호 >> ");
		String memPass = sc.next();
		
		// 비밀번호 암호화(단방향)
		String encryptedPass = CryptoUtil.sha512(memPass);

		System.out.print("회원이름 >> ");
		String memName = sc.next();

		System.out.print("전화번호 >> ");
		String memTel = sc.next();

		sc.nextLine(); // 입력버퍼 지우기
		System.out.print("회원 주소 >> ");
		String memAddr = sc.nextLine();
		
		// 입력한 데이터를 VO객체에 저장한다.
		MemberVo memVo = new MemberVo();
		memVo.setMem_id(encryptedId);
		memVo.setMem_pass(encryptedPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.insertMember(memVo);

		if(cnt > 0) {
			System.out.println("회원정보 추가 성공!");
		}else {
			System.out.println("회원정보 추가 실패..");
		}
		
	}

		
	// 메인 메서드
	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		new MemberController().startMember();
	}

}
