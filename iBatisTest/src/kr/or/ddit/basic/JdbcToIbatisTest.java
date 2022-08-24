package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.util.SqlMapClientFactory;
import kr.or.ddit.vo.LprodVO;

/*
 * Lprod테이블에 새로운 데이터를 추가하기
 * 
 * lprod_gu와 lprod_nm은 직접 입력받아서 처리하고,
 * lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1크게 한다.
 * 
 * 그리고 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 * 
 * (SQL문이 저장되는 xml문서의 파일명 : jdbc.xml)
 */

public class JdbcToIbatisTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// iBatis 환경설정 읽어오기
		SqlMapClient smc = null;
		try {
//			Charset charset = Charset.forName("utf-8");
//			Resources.setCharset(charset);
//			
//			Reader rd = Resources.getResourceAsReader(
//					"kr/or/ddit/ibatis/config/sqlMapconfig.xml");
//			
//			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
//			
//			rd.close();
			
			smc = SqlMapClientFactory.getSqlMapClient();
			
			// lprod_id 제일 큰 값 받아오기
			int num = (int) smc.queryForObject("Test.selectMaxLprodId");
								
			int lprodId = num++;
			
			// Lprod insert 실행하기
			System.out.println("Lprod insert 작업을 시작합니다");
			System.out.println();
			System.out.print("입력할 Lprod_gu : ");
			String lprod_gu = sc.next();

			// lprod_gu 체크하기
			String lv = (String) smc.queryForObject("Test.checkLprodGu", lprod_gu);
			if(lv != null) {
				System.out.println(lprod_gu + "값이 이미 존재합니다.");
				System.out.println("작업을 종료합니다.");
				return;
			}
			/*
			 * String gu;
			 * int count = 0;
			 * do{
			 * 	  System.out.print("입력할 Lprod_gu : ");
			 *    gu = scan.next();
			 *    count = (int) smc.queryForObject("Test.checkLprodGu", lprod_gu);
			 *    if(count > 0){
			 *    		System.out.print("입력한 Lprod_gu : " + gu + "는 이미 등록된 코드입니다.");
			 *    }
			 * }while(count > 0);
			 */
			
			System.out.println("입력할 Lprod_nm : ");
			String lprod_nm = sc.next();
			
			LprodVO lvo1 = new LprodVO();
			lvo1.setLprod_id(lprodId);
			lvo1.setLprod_gu(lprod_gu);
			lvo1.setLprod_nm(lprod_nm);
			
			Object obj = smc.insert("Test.insertLprod", lvo1);
			
			if(obj == null) {
				System.out.println("insert 성공");
			}else {
				System.out.println("insert 실패");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		
	}
}
