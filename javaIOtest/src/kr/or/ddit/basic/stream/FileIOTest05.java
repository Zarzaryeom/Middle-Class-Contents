package kr.or.ddit.basic.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		// 한글이 저장된 파일 읽어오기(한글의 인코딩을 지정해서 읽어오기)
		try {
//			FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
			
			// 안시 문서는 영어, 숫자 외 제대로 출력하지 못한다.
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			
			// InputStreamReader는 인코딩을 지정해서 처리할 수 있다.
//			FileInputStream fis = new FileInputStream("d:/d_other/test_utf8.txt");
			FileInputStream fis = new FileInputStream("d:/d_other/test_ansi.txt");
			
			// 인코딩 방식을 지정하지 않으면 기본 인코딩 방식(java에서 현재 지정된 방식)으로 읽어온다.
//			InputStreamReader isr = new InputStreamReader(fis);
			
			// 인코딩 방식 지정해서 읽어오기(보조 스트림)
			// 인코딩 방식 예시
			// - MS949 ==> 윈도우의 기본 한글 인코딩 방식(ANSI와 같다)
			// - UTF-8 ==> 유니코드의 UTF-8 인코딩 방식
			// - US-ASCII ==> 영문 전용 인코딩 방식
			InputStreamReader isr = new InputStreamReader(fis, "MS949");
//			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			
			int c;
//			while((c = fr.read()) != -1) {
			while((c = isr.read()) != -1) {
				System.out.print((char)c);
			}
			
//			fr.close();			
			isr.close(); // 보조 스트림을 닫으면 같이 사용된 기반 스트림도 같이 닫힌다,
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
