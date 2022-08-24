package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class FileSender extends Thread {
	private Socket socket;
	private DataOutputStream dos;
	
	private Scanner scan;
	private FileInputStream fout;
	private String name; // 채팅을 하는데 핑요한 Id
	
	// 생성자
	public FileSender(Socket socket) throws IOException {
		this.socket = socket;
		fout = new FileInputStream("d:/d_other/펭귄.jpg");
		
		System.out.println("파일 전송");
		
		try {
			dos = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	@Override
	public void run() {
			try {
				// 이름과 입력한 문자열을 연결하여 전송한다.
				dos.write(fout.read());
			} catch (IOException e) {
				// TODO: handle exception
		}
	}
}
