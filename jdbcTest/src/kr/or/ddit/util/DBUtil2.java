package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class
// (dbinfo.properties파일의 내용을 읽어와 설정하기)
public class DBUtil2 {
	static Properties prop; // Properties객체 변수 선언

	// static 초기화 블럭
	static {
		
		prop = new Properties(); // Properties객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f); // 스트림 객체 생성
			
			prop.load(fin); // prop객체에 text파일에서 저장한 값이 들어있는 상태
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패..");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("입출력 오류... 드라이버 로딩 실패~");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
//					"LBJ94", "JAVA");
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("user"),
					prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!");
			return null;
		}
	}
	
}
