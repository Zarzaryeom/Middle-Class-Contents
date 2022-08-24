package kr.or.ddit.basic.stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class bufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력 향상을 위하여 buffered스트림을 사용한다.(byte 기반)
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);
			
			for(char i = '1'; i <= '9'; i++) {
				bout.write(i);
			}
			bout.flush(); // 버퍼에 남겨져 출력되지 않은 데이터를 강제 출력
						  // close()만 써도 남은 데이터를 출력할 수 있지만 중간에 다른 프로그램이나 계산이 들어갈 수 있으므로 항상 작성하는 편이 좋다.
						
			
			// 버퍼스트림의 close()는 버퍼의 내용을 모두 flush한 후에 닫아준다.
			bout.close();
			
			
			System.out.println("작업 끝...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
