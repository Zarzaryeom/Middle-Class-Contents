package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.vo.LprodVO;

public class LprodIbatisTest {

	// iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. iBatis의 환경설정파일을 읽어와서 실행한다.(sqlMapConfig.xml)
		try {
			// 1-1 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("utf-8");
			
			// 1-2 환경 설정 파일을 읽어올 스트림객체 생성.
			Reader rd = Resources.getResourceAsReader(
					"kr/or/ddit/ibatis/config/sqlMapconfig.xml");
			
			// 1-3 위에서 생성한 스트림 객체를 이용하여 환경설정 파일을 읽어서 실행한다.
			// iBatis를 처리할 객체가 반환된다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // 스트림 닫기
			
			//----------------------------------------------------------
			
			/*
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업 수행하기
			
			// 2-1. insert작업
			System.out.println("insert 작업 시작...");
			System.out.println("Lprod_id 입력 : ");
			int id = scan.nextInt();
			
			System.out.println("Lprod_gu 입력 : ");
			String gu = scan.next();
			
			System.out.println("Lprod_nm 입력 : ");
			String nm = scan.next();
			
			// 저장할 데이터를 VO에 담는다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(id);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			// sqlMapCliect객체변수를 이용해서 처리할 쿼리문을 호출하여 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스)
			// 		반환값 : insert 성공 : null, insert 실패 : 오류객체
			Object obj = smc.insert("Lprod.insertLprod", lvo);
			if(obj == null) {
				System.out.println("insert 성공");
			}else {
				System.out.println("insert 실패");
			}
			System.out.println("------------------------------------");
			*/
			//-------------------------------------------------
			
			/*
			// 2-2. update 작업
			System.out.println("update작업 시작...");
			System.out.print("수정할 Lprod_gu 입력 : ");
			String gu = scan.next();
			
			System.out.print("새로운 Lprod_id 입력 : ");
			int id = scan.nextInt();
			
			System.out.print("새로운 Lprod_nm 입력 : ");
			String nm = scan.next();
			
			// vo에 담기
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(id);
			lvo2.setLprod_gu(gu);
			lvo2.setLprod_nm(nm);
			
			// update형식) smc.update("namespace값.id값", 파라미터클래스)
			// 		==> 반환값 : 성공한 레코드 수
			int cnt = smc.update("Lprod.updateLprod", lvo2);
			if(cnt > 0) {
				System.out.println("update 성공!!");
			}else {
				System.out.println("update 실패..");
			}
			
			System.out.println("-------------------------------------");
			
			*/
			
			//-----------------------------------------------------------
			/*
			// 2-3. delete 작업
			System.out.println("delete작업 시작..");
			System.out.print("삭제할 Lprod_gu 입력 : ");
			String gu = scan.next();
			
			// delete형식) sm.delete("namespace값.id값", 파라미터클래스)
			//		==> 반환값 : 성공한 레코드 수
			int cnt2 = smc.delete("Lprod.deleteLprod", gu);
			
			if(cnt2 > 0) {
				System.out.println("delete 작업 성공!!");
			}else {
				System.out.println("delete 작업 실패..");
			}
			*/
			
			//-----------------------------------------------------------
			
			/*
			// 2-4. select 작업
			
			// 1) select한 결과가 여러개인 경우
			System.out.println("select 연습 (결과가 여러개의 레코드일 경우..)");
			
			// 결과가 여러개일 때 형식) smc.queryForList("namespace값.id값", 파라미터클래스);
			// 			==> 이 메서드는 여러개의 레코드 각각 VO에 담은 후 이 VO데이터를
			//				List에 추가해 주는 작업을 자동으로 수행한다.
			// 		반환값 ==> VO객체가 저장된 List객체
			
			List<LprodVO> lprodList = smc.queryForList("Lprod.getAllLprod");
			
			for(LprodVO lvo3 : lprodList) {
				System.out.println("ID : " + lvo3.getLprod_id());
				System.out.println("GU : " + lvo3.getLprod_gu());
				System.out.println("NM : " + lvo3.getLprod_nm());
				System.out.println("---------------------------------");
			}
			
			System.out.println("출력 끝");
			
			*/
			
			// 2) select 결과가 1개의 레코드일 경우
			System.out.println("select 작업 (결과가 1개의 레코드일 경우..)");
			System.out.print("검색할 Lprod_gu 입력 : ");
			String gu = scan.next();
			
			// 결과가 1개의 레코드일 경우 형식) 
			//			smc.queryForObject("namespade값.id값", 파라미터클래스);
			// 반환값 : - 처리결과가 여러개일 경우 ==> Exception객체 반환
			//		  - 처리결과가 1개일 경우 ==> 해당객체 반환(정상처리)
			//		  - 처리결과가 없을 경우 ==> null 반환
			LprodVO lvo4 = (LprodVO)smc.queryForObject("Lprod.getLprod", gu); // 형 변환이 필요
			
			if(lvo4 == null) {
				System.out.println("검색한 결과가 하나도 없습니다.");
			}else {
				System.out.println("ID : " + lvo4.getLprod_id());
				System.out.println("GU : " + lvo4.getLprod_gu());
				System.out.println("NM : " + lvo4.getLprod_nm());
				System.out.println("---------------------------------");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
