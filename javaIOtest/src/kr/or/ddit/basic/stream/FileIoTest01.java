package kr.or.ddit.basic.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIoTest01 {

	public static void main(String[] args) {
		// 파일의 내용을 읽어와 화면에 출력하기 ==> FileInputstream객체 이용(File을 다루는 메서드)
		try {
			// 읽어올 파일을 인수값으로 지정해서 FileInputStream객체 생성
			
			// 방법1
//			FileInputStream fin = new  FileInputStream("d:/d_other/test.txt");
			
			// 방법2
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c; // 읽어온 데이터를 저장할 변수
			
			while((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char)c);
			}
			
			fin.close();
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다...");
		}
		
	}

}
