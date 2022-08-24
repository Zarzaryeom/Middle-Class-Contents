package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", 
					"LBJ94", "JAVA");
			
			System.out.println("계좌번호 정보 추가하기");
			
			System.out.print("계좌번호 : ");
			String bankNo = sc.next();
			
			System.out.print("은행 명 : ");
			String bankName = sc.next();
			
			System.out.print("예금주 명 :");
			String bankUser = sc.next();
			
			
			// Statement객체를 이용하여 데이터 추가하기
			String sql = "INSERT INTO BANKINFO(BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					+ " VALUES('" + bankNo + "', '" + bankName + "', '" + bankUser + "', SYSDATE)";
			
//			System.out.println(sql);
			
			stmt = con.createStatement();
			
			// select문을 실행할 때는 excuteQuery()메서드를 사용하고
			// insert, update, delete등과 같이 select문이 아닌 쿼리문을 실행할때는
			// excuteUpdate()메서드를 사용한다.
			// excuteUpdate()메서드의 반환값 ==> 작업에 성공한 레코드 수
//			int cnt = stmt.executeUpdate(sql);
			
//			System.out.println("반환값 : " + cnt);
			
			// 1. PreperedStatement객체를 이용하여 추가하기
			// 쿼리문을 작성할 때 데이터가 들어갈 자리를 물음표(?)로 표시한다
			String sql2 = "INSERT INTO BANKINFO(BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					+ " VALUES(?, ?, ?, SYSDATE)";
			
			// 2. PreparedStatement객체를 생성한다.
			// 		==> 이 때 실행할 쿼리문을 인수값으로 넘겨준다.
			pstmt = con.prepareStatement(sql2);
			
			// 3. 쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표번호, 데이터) --> 물음표 번호는 1번부터 시작한다.
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// 4. 데이터 셋팅이 완료되면 쿼리문을 실행한다.
			//		=> select문 : exeuteQuery()메서드 사용
			//		=> select문 이외 : exeuteUpdate()메서드 사용
			// pstmt를 생성하면서 쿼리문을 넣었기 때문에 빈 괄호를 적는다.
			int cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null) try { stmt.close(); } catch(SQLException e) { }
			if(pstmt != null) try { pstmt.close(); } catch(SQLException e) { }
			if(con != null) try { con.close(); } catch(SQLException e) { }
		}
		
		
		
		
	}

}
