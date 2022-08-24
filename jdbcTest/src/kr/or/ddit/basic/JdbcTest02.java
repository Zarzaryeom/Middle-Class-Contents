package kr.or.ddit.basic;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력 받아서 입력한 값보다 LPROD_ID가 큰 자료들을 출력하세오.

public class JdbcTest02 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("LPROD_ID 값 입력 : ");
		int num = sc.nextInt();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
												"LBJ94", "JAVA");
			
			String sql = "SELECT LPROD_ID,"
					+ "    	     LPROD_GU,"
					+ "          LPROD_NM"
					+ "     FROM LPROD"
					+ "    WHERE LPROD_ID > " + num;
			
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 쿼리문 처리 결과 ==");
			while(rs.next()) {
				System.out.println("LPROD_Id : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_NM : " + rs.getString(3));
				System.out.println("---------------------------------------");
				
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		
		
		
		
		
		
		
		
		
		
	}

}
