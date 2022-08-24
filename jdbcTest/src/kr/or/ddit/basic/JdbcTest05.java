package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * Lprod테이블에 새로운 데이터를 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력받아서 처리하고,
 * lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 그리고 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 */

public class JdbcTest05 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//					"LBJ94", "JAVA");
			con = DBUtil.getConnection();
			
			
			System.out.println("새로운 데이터 추가하기");
			System.out.print("LPROD_GU : ");
			String lgu = sc.nextLine();
			
			if(lgu.length() > 4) {
				System.out.println("LPROD_GU의 값이 저장될 수 있는 값보다 큽니다.");
				System.out.println("자리수를 낮춰 주시길 바랍니다.");
				return;
			}
			
			System.out.print("LPROD_NM : ");
			String name = sc.nextLine();
			
			// LPROD_ID를 꺼내오기 위한 SQL
			String sql = "SELECT MAX(LPROD_ID) AS MAX"
					+ "  FROM LPROD";
			
			// LPROD에 자료를 넣기위한 SQL
			String sql2 = "INSERT INTO LPROD(LPROD_ID, LPROD_GU, LPROD_NM)"
					+ "    VALUES(?, ?, ?)";
			
			// LPROD_GU 중복 체크 SQL
			String sql3 = "SELECT *"
					+ "  FROM LPROD"
					+ " WHERE LPROD_GU = ?";
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			String idMax = null;
			//next()가 들어가지 않으면 포인터가 데이터를 읽지 못한다. 
			if(rs.next()) {
				// 컬럼의 alias가 없을 때
				// idMax = re.getString("max(lprod_id)");
				// idMax = re.getString(1);
				idMax = rs.getString("MAX");
			}
			
			int id = Integer.parseInt(idMax) + 1;
			
			// LPROD_GU 중복체크
			
			/*
				교수님 풀이
				String gu // 상품분류코드(lprod_gu)가 저장될 변수 선언
				int count = 0; // 입력한 상품분류코드의 개수가 저장될 변수
				do{
					System.out.print("상품분류코드 입력 : ");
					gu = caan.next();
					
					String sql2 = "select count(*) cnt from prod "
								+ " where lprod_gu = ? ";
					pstmt = con.prepareStatement(sql2);
					pstmt.setString(1, gu);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()){
						count = rs.getInt("cnt");
					}
					
					if(count>0) {
					System.out.println("입력한 상품분류코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				
				}while(count > 0); // 중복되면 반복처리 되도록 한다,
			
			*/
			pstmt = con.prepareStatement(sql3);
			
			pstmt.setString(1, lgu);
			
			if(pstmt.executeUpdate() > 0 ) {
				System.out.println("LPROD_GU가 중복되었습니다.");
				System.out.println("프로그램을 종료합니다.");
				return;
			}
			
			// LPROD 자료 업데이트
			pstmt = con.prepareStatement(sql2);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, lgu);
			pstmt.setString(3, name);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("데이터 추가가 성공적으로 진행되었습니다.");
			}else {
				System.out.println("등록 실패..");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) try { stmt.close(); } catch(SQLException e) { }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { }
			if(rs != null) try { rs.close(); } catch(SQLException e) { }
			if(con != null) try { con.close(); } catch(SQLException e) { }
		}
		
				
				
				
				
				
				
				
	}

}
