package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) LPROD_ID값을 2개를 입력 받아서 두 값들 중 작은 값부터 큰 값사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("(1)LPROD_ID 값 입력  : ");
		int num1 = sc.nextInt();
		
		System.out.print("(2)LPROD_ID 값 입력  : ");
		int num2 = sc.nextInt();
		
		/*
			int min = Math.min(num1, num2);
			int max = Math.max(num1, num2);
		
		*/
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"LBJ94", "JAVA");
			
			int temp = 0;
			if(num2 < num1) {
				temp = num1;
				num1 = num2;
				num2 = temp;
			}
			
			String sql = "SELECT LPROD_ID,"
					+ "    	     LPROD_GU,"
					+ "          LPROD_NM"
					+ "     FROM LPROD"
					+ "    WHERE LPROD_ID > " + num1
					+ "      AND LPROD_ID < " + num2;
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 쿼리문 결과 ==");
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
		}finally {
			if(rs != null) try {rs.close();} catch(SQLException e) {}
			if(stmt != null) try {stmt.close();} catch(SQLException e) {}
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		
		
	}

}
