package kr.or.ddit.basic.stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  문제) D드라이브의 d_other 폴더에 있는 '펭귄.jpg'파일을 D드라이브의 d_other폴더에 있는 '연습용'폴더에 '펭귄_복사본.jpg'파일로
 *  	 복사하는 프로그램을 작성하시오. 
 */

public class FileCopy {

	public static void main(String[] args) {
		try {
			
			FileInputStream fout = new FileInputStream("d:/d_other/펭귄.jpg");
			FileOutputStream fost = new FileOutputStream("d:/d_other/연습용/펭귄_복사.jpg");
			
						
			System.out.println("출력 완료");
			
			int c;
			while((c = fout.read()) != -1) {
				fost.write(c);
			}
			
			fout.close();
			fost.close();
			
		} catch (IOException e) {
			System.out.println("실행 중 오류 발생");
		}
		
		/*
		 * //교수님 풀이
		 * File file = new File("d:/d_other/펭귄.jpg");
		 * 
		 * //원본 파일이 있는지 확인하는 과정
		 * if(!file.exists()){
		 * 	  System.out.println(file.getName() + "파일이 없습니다.");
		 *    System.out.println("복사작업을 중단합니다.);
		 *    return;
		 * }
		 *  
		 * try {
		 * 		//복사할 파일 스트림 객체 생성
				FileInputStream fin = new FileInputStream(file);
				BufferedInputStream bin = new BufferedInputStream(fin)
							
				//복사될 파일 스트림 객체 생성
				FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사.jpg");
				BufferedOutputStream bout = new BufferedOutputStream(fout)
			
			System.out.println("복사 시작..");
			
			int data;
			
			//버퍼 사용시
			while((data = bin.read()) != -1){
				bout.write(data);
			}
			bout.flush();
			
			//스트림 닫기
			bin.close();
			bout.close();
			
			System.out.println("복사 작업 끝...");
			
			//버퍼 미 사용시
			while((data = fin.read()) != -1){
				fout.write(data);
			}
			fout.flush();
			
			System.out.println("복사 작업 끝...");
			
			//스트림 닫기
			fin.close();
			fout.close();
		
			} catch (Exception e) {
			// TODO: handle exception
			}
		 * 
		 */
		
	}

}
