package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * 회원을 관리하는 프로그램을 작성하시오.
 * (MYMEMBER테이블 이용)
 * 
 * 아래 메뉴의 기능을 모두 구현하시오(CRUD기능 구현하기)
 * 
 * 메뉴예시)
 * -----------------------
 * 		== 작업 선택 ==
 *   1. 자료 추가        --> insert (C)
 *   2. 자료 수정        --> update (U)
 *   3. 자료 삭제        --> delete (D)
 *   4. 전체 자료 출력     --> select (R)
 *   0. 작업 끝.
 * -----------------------
 * 
 * 조건)
 *  1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
 *  2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
 *  3) 자료 수정에서 '회원ID'는 변경되지 않는다. 
 * 
 */

public class JdbcTest06 {
	
	Scanner sc = new Scanner(System.in);
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest06().start();

	}
	
	public void start() {
		
		while(true) {
			System.out.println("************** 회원 관리 프로그램 **************");
			System.out.println("-------------------------------------------");
			System.out.println("                == 작업 선택 ==");
			System.out.println("   1. 자료 추가");
			System.out.println("   2. 자료 수정");
			System.out.println("   3. 자료 삭제");
			System.out.println("   4. 전체 자료 출력");
			System.out.println("   0. 작업 끝");
			System.out.println("-------------------------------------------");
			int input = sc.nextInt();
		
			switch(input) {
			case 1: insert(); break;
			case 2: update(); break;
			case 3: delete(); break;
			case 4: show(); break;
			case 0: break;
			default:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);			
			}
		}
		
		
		
	}

	// 전체 자료 출력
	private void show() {
		sc = new Scanner(System.in);
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "SELECT * FROM MYMEMBER";
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("-------------------------------------------");
			System.out.println("회원 아이디    성 명        전화번호        주소   ");
			System.out.println("-------------------------------------------");
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t  " + rs.getString(3) + "\t" 
									+ rs.getString(4) + "\t\t" + rs.getString(5));
			} 
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
	}

	// 자료 삭제
	// 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다
	private void delete() {
		sc = new Scanner(System.in);
		try {
			con = DBUtil.getConnection();
			
			String sql = "DELETE FROM MYMEMBER"
					+ "    WHERE MEM_ID = ?";
			
			String sql2 = "SELECT * "
					+ "      FROM MYMEMBER"
					+ "     WHERE MEM_ID = ?";
			
			System.out.print("삭제할 아이디를 입력하세요 : ");
			String memId = sc.nextLine();
			
			while(true) {
				// 아이디 체크
				pstmt = con.prepareStatement(sql2);

				pstmt.setString(1, memId);

				if (pstmt.executeUpdate() < 1) {
					System.out.println("해당하는 아이디가 없습니다. 다시 입력해주세요.");
					memId = sc.nextLine();
				}else break;
			}
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("성공적으로 삭제가 완료되었습니다.");
				System.out.println();
			}else {
				System.out.println("삭제 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(con != null) try { con.close(); } catch(SQLException e) {}
		}
	}

	// 자료 업데이트
	// 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
	private void update() {
		sc = new Scanner(System.in);
		try {
			con = DBUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER"
					+ "      SET MEM_PASS = ?,"
					+ "          MEM_NAME = ?,"
					+ "          MEM_TEL = ?,"
					+ "          MEM_ADDR = ?"
					+ "    WHERE MEM_ID = ?";
			
			String sql2 = "SELECT * "
					+ "      FROM MYMEMBER"
					+ "     WHERE MEM_ID = ?";
			
			System.out.print("아이디를 입력하세요 : ");
			String memId = sc.nextLine();
			
			while(true) {
				// 아이디 체크
				pstmt = con.prepareStatement(sql2);

				pstmt.setString(1, memId);

				if (pstmt.executeUpdate() < 1) {
					System.out.println("해당하는 아이디가 없습니다. 다시 입력해주세요.");
					memId = sc.nextLine();
				}else break;
			}
			
			System.out.print("수정된 비밀번호를 입력하세요 : ");
			String memPass = sc.nextLine();
			
			System.out.print("수정된 이름을 입력하세요 :");
			String name = sc.nextLine();
			
			System.out.print("수정된 전화번호를 입력하세요 : ");
			String tel = sc.nextLine();
			
			System.out.println("수정된 주소를 입력하세요 : ");
			String addr = sc.nextLine();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memPass);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("성공적으로 정보가 등록되었습니다.");
				System.out.println();
			}else {
				System.out.println("정보 등록 실패");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(con != null) try { con.close(); } catch(SQLException e) {}
		}
		
		
	}

	// 자료 추가
	// 1) 자료 추가에서 '회원ID'는 중복되지 않는다.(중복되면 다시 입력받는다.)
	private void insert() {
		sc = new Scanner(System.in);
		try {
			con = DBUtil.getConnection();
			
			String sql = "INSERT INTO MYMEMBER(MEM_ID, MEM_PASS, MEM_NAME, MEM_TEL, MEM_ADDR)"
					+ "    VALUES(?, ?, ?, ?, ?)"; 
			
			String sql2 = "SELECT * "
					+ "      FROM MYMEMBER"
					+ "     WHERE MEM_ID = ?";
			
			System.out.print("아이디를 입력하세요 : ");
			String memId = sc.nextLine();
			
			while(true) {

				// 아이디 중복 체크
				pstmt = con.prepareStatement(sql2);

				pstmt.setString(1, memId);

				if (pstmt.executeUpdate() > 0) {
					System.out.println("이미 존재하는 아이디입니다. 다시 입력해주세요.");
					memId = sc.nextLine();
				}else break;
			}
			
			System.out.print("비밀번호를 입력하세요 : ");
			String memPass = sc.nextLine();
			
			System.out.print("이름을 입력하세요 :");
			String name = sc.nextLine();
			
			System.out.print("전화번호를 입력하세요 : ");
			String tel = sc.nextLine();
			
			System.out.println("주소를 입력하세요 : ");
			String addr = sc.nextLine();
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("성공적으로 정보가 등록되었습니다.");
				System.out.println();
			}else {
				System.out.println("정보 등록 실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) {}
			if(con != null) try { con.close(); } catch(SQLException e) {}
		}
	}

}
