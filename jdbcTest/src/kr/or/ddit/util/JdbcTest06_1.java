package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest06_1 {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner sc = new Scanner(System.in);

	// 시작 메서드
	public void startMember() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
			case 1: insertMember(); break; // 추가
			case 2: updateMember(); break; // 수정
			case 3: deleteMember(); break; // 삭제
			case 4: displayMember(); break; // 전체 자료 출력
			case 5: updateMember2(); break; // 수정
			case 0:  // 작업 끝
				System.out.println("작업을 마칩니다.");
				return;
			default :
				System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
			
			}
		}
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 선택하서 수정하기
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID >> ");
		String memId = sc.next();
		
		int count = getMemberCount(memId);
		if(count == 0) { // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원 ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		
		System.out.println("수정하기 원하는 항목을 선택하세요.");
		System.out.println("------------------------------");
		System.out.println(" 1. 회원 비밀번호");
		System.out.println(" 2. 회원 이름");
		System.out.println(" 3. 회원 전화번호");
		System.out.println(" 4. 회원 주소");
		System.out.println("------------------------------");
		System.out.print(" 번호 선택 >> ");
		int input = sc.nextInt();
		
		switch(input) {
			case 1: updateMemPass(memId); break;
			case 2: updateMemName(memId); break;
			case 3: updateMemTel(memId); break;
			case 4: updateMemaddr(memId); break;
			default :
				System.out.println("잘못된 번호 선택입니다.");
				System.out.println("메인 메뉴로 돌아갑니다.");
				break;
		}
		
		// 교수님 코딩
//		int num;
//		String updateField = null;
//		String updateTitle = null;
//		
//		do{
//			System.out.println();
//			System.out.println("수정할 항목을 선택하세요.");
//			System.out.println(" 1.비밀번호  2.회원이름  3.전화번호  4.회원주소");
//			System.out.println("---------------------------------------");
//			System.out.println("수정항목 선택 >> ");
//			num = sc.nextInt();
//			
//			switch(num) {
//				case 1: updateField = "mem_pass";
//						updateTitle = "비밀번호"; break;
//				case 2: updateField = "mem_name";
//						updateTitle = "회원이름"; break;
//				case 3: updateField = "mem_tel";
//						updateTitle = "전화번호"; break;
//				case 4: updateField = "mem_addr";
//						updateTitle = "회원주소"; break;
//				default :
//					System.out.println("수정 항목을 잘못 선택했습니다.");
//					System.out.println("다시 선택하세요.");
//			}
//		}while(num < 1 || num > 5);
//		
//		System.out.println();
//		sc.nextLine(); // 버퍼 비우기
//		System.out.print("새로운 " + updateTitle + " >> ");
//		String updateData = sc.nextLine();
//		
//		try {
//			con = DBUtil.getConnection();
//			
//			String sql = "update mymember set " + 
//					updateField + " = ? where mem_id = ?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, updateData);
//			pstmt.setString(2, memId);
//			
//			int cnt = pstmt.executeUpdate();
//			
//			if(cnt > 0) {
//				System.out.println("수정작업 성공!");
//			}else {
//				System.out.println("수정작업 실패"..);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disConnect();
//		}
		
		
	}


	private void updateMemaddr(String memId) {
		sc.nextLine();
		System.out.println("변경할 주소를 입력하세요.");
		String newAddr = sc.nextLine();
		try {
			con = DBUtil.getConnection();
			
			String sql = "update mymember"
					+ "      set mem_addr = ?"
					+ "    where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newAddr);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 주소 수정 완료!!");
			}else {
				System.out.println(memId + "회원 주소 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	private void updateMemTel(String memId) {
		System.out.println("변경할 전화번호를 입력하세요.");
		String newTel = sc.next();
		try {
			con = DBUtil.getConnection();
			
			String sql = "update mymember"
					+ "      set mem_tel = ?"
					+ "    where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newTel);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 전화번호 수정 완료!!");
			}else {
				System.out.println(memId + "회원 전화번호 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	private void updateMemName(String memId) {
		System.out.println("변경할 회원 이름을 입력하세요.");
		String newName = sc.next();
		try {
			con = DBUtil.getConnection();
			
			String sql = "update mymember"
					+ "      set mem_name = ?"
					+ "    where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newName);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 이름 수정 완료!!");
			}else {
				System.out.println(memId + "회원 이름 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	private void updateMemPass(String memId) {
		System.out.println("변경할 비밀번호를 입력하세요.");
		String newPass = sc.next();
		try {
			con = DBUtil.getConnection();
			
			String sql = "update mymember"
					+ "      set mem_pass = ?"
					+ "    where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPass);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 비밀번호 수정 완료!!");
			}else {
				System.out.println(memId + "회원 비밀번호 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	// 회원 정보를 삭제하는 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원정보를 입력하세요.");
		System.out.println("삭제할 회원ID >> ");
		String memId = sc.next();
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "delete from mymember where mem_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 삭제 성공");
			}else {
				System.out.println(memId + "회원은 없는 회원이어간 삭제에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}



	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정하기
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.println("회원ID >> ");
		String memId = sc.next();
		
		int count = getMemberCount(memId);
		if(count == 0) { // 없는 회원이면...
			System.out.println(memId + "은(는) 없는 회원 ID입니다.");
			System.out.println("수정 작업을 마칩니다.");
			return;
		}
		System.out.println();
		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("새로운 비밀번호 >> ");
		String newMemPass = sc.next();
		
		System.out.println("새로운 회원이름 >> ");
		String newMemName = sc.next();
		
		System.out.println("새로운 전화번호 >> ");
		String newMemTel = sc.next();
		
		sc.nextLine();
		System.out.println("새로운 주소 >> ");
		String newMemAddr = sc.nextLine();
		
		try {
			con = DBUtil.getConnection();
			String sql = "update mymember "
					+ "      set mem_pass = ?, "
					+ "          mem_name = ?, "
					+ "          mem_tel = ?, "
					+ "          mem_addr = ? "
					+ "    where mem_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newMemPass);
			pstmt.setString(2, newMemName);
			pstmt.setString(3, newMemTel);
			pstmt.setString(4, newMemAddr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 정보 수정 완료!!");
			}else {
				System.out.println(memId + "회원 정보 수정 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}




	// 전체 회원 정보를 출력하는 메서드
	private void displayMember() {
		System.out.println();
		System.out.println("-----------------------------------");
		System.out.println(" ID  비밀번호   이름    전화번호     주소");
		System.out.println("-----------------------------------");
		
		try {
//			con = DBUtil.getConnection();
			con = DBUtil3.getConnection();
			String sql = "select * from mymember";
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memPass = rs.getString("mem_pass");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "\t" + memPass + "\t" +
									memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("-----------------------------------");
			System.out.println("출력 끝...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
	}

	// 회원 정보를 추가(입력)하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		
		// 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
		int count = 0; // 입력한 회원ID의 개수가 저장될 변수
		
		String memId; // 회원ID가 저장될 변수
		do {
			System.out.print("회원ID >> ");
			memId = sc.next();
			
			count = getMemberCount(memId);
			
			if(count > 0) {
				System.out.println(memId + "는(은) 이미 등록된 ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
			}
		}while(count > 0);
		
		System.out.println("비밀번호 >> ");
		String memPass = sc.next();
		
		System.out.println("회원이름 >> ");
		String memName = sc.next();
		
		System.out.println("전화번호 >> ");
		String memTel = sc.next();
		
		sc.nextLine(); // 입력버퍼 지우기
		System.out.println("회원 주소 >> ");
		String memAddr = sc.nextLine();
		
		try {
			con = DBUtil.getConnection();
			String sql = "insert into mymember"
					+ "   (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "  values(?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0){
				System.out.println("회원 정보 추가 성공!");
			}else {
				System.out.println("회원 정보 추가 실패..");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}
	
	// 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember"
					+ "	   where mem_id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		
		return count;
	}

	// 사용했던 자원을 반납하는 메서드
	private void disConnect() {
		if(rs != null) try {rs.close();} catch(SQLException e) {}
		if(stmt != null) try {stmt.close();} catch(SQLException e) {}
		if(pstmt != null) try {pstmt.close();} catch(SQLException e) {}
		if(con != null) try {con.close();} catch(SQLException e) {}
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
		System.out.println(" 0. 작업 종료");
		System.out.println("-------------------------");
		System.out.print("원하는 작업번호 >> ");
		
		return sc.nextInt();
	}
	
	public static void main(String[] args) {
		new JdbcTest06_1().startMember();
	}

}
