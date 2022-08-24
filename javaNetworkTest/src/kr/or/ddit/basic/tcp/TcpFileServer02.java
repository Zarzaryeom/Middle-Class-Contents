package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 전송되어온 파일을 보내온 파일이름으로 저장한다.
// 저장위치 ('d:/d_other/down' 폴더)
public class TcpFileServer02 {
	private ServerSocket server;
	private Socket socket;
	
	private DataInputStream dis;
	
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
	private String saveDir = "d:/d_other/down";
	private String filename;
	
	public void serverStrat() {
		File save = new File(saveDir);
		if(!save.exists()) {
			save.mkdirs();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료");
			
			socket = server.accept();
			
			// 파일 이름 받아오기
			dis = new DataInputStream(socket.getInputStream());
			filename = dis.readUTF();
			if(filename == null) {
				filename = "noname.jpg";
			}
			
			bis = new BufferedInputStream(socket.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(saveDir + File.separator + filename));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			while((len = bis.read(temp)) > 0){
				bos.write(temp, 0, len);
			}
			bos.flush();
			
			System.out.println("파일 다운로드 완료");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(dis != null) try { dis.close(); } catch(IOException e) {}
			if(bos != null) try { bos.close(); } catch(IOException e) {}
			if(bis != null) try { bis.close(); } catch(IOException e) {}
			if(socket != null) try { socket.close(); } catch(IOException e) {}
			if(server != null) try { server.close(); } catch(IOException e) {}
		}
	}

	public static void main(String[] args) {
		new TcpFileServer02().serverStrat();
	}

}
