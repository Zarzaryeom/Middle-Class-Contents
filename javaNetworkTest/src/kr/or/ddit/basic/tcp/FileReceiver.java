package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileReceiver extends Thread{
	private Socket socket;
	private DataInputStream dis;
	private FileOutputStream fost;
	
	// 생성자
	public FileReceiver(Socket socket) throws IOException {
		this.socket = socket;
		fost = new FileOutputStream("d:/d_other/down/펭귄_전송.jpg");
		try {
			dis = new DataInputStream(
					this.socket.getInputStream());
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void run() {
			try {
				int c;
				while((c = dis.read()) != -1) {
					fost.write(c);
				}
				System.out.println("전송 완료");
			} catch (IOException e) {
				// TODO: handle exception
			}
	}
}