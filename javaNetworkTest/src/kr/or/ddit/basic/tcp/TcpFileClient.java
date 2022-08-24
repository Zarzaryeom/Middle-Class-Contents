package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
	public void clientStart() {
		File file =new File("d:/d_other/펭귄.jpg");
		if(!file.exists()) {
			System.out.println(file.getName() + " 파일이 없습니다.");
			System.out.println("파일 전송 작업을 중단합니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작...");
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 파일 내용을 읽어와 소켓으로 출력한다.
			while((len= bis.read(temp))>0) {
				bos.write(temp, 0, len);
			}
			bos.flush();
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!!");
			e.printStackTrace();
		} finally {
			if(bos!=null) try {bos.close();} catch(IOException e) {}
			if(bis!=null) try {bis.close();} catch(IOException e) {}
			if(socket!=null) try {socket.close();} catch(IOException e) {}
		}
			
	}

	public static void main(String[] args) {
		// 클라이언트가 d드라이브의 d_other 폴더에 있는 '펭귄.jpg'파일을 서버로 전송
//		try {
//			Socket socket = new Socket("localhost", 7778);
//			System.out.println("서버에 연결되었습니다.");
//			System.out.println();
//			
//			FileSender sender = new FileSender(socket);
//			
//			FileReceiver receiver = new FileReceiver(socket);
//			
//			sender.start();
//			receiver.start();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		new TcpFileClient().clientStart();
		
		
		
	}

}
