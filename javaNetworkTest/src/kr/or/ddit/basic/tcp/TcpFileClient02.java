package kr.or.ddit.basic.tcp;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;

// Open Dialog를 이용하여 전송한 파일을 선택하여 서버로 전송한다.
// 이 때, 파일 이름도 전송한다. 

public class TcpFileClient02 {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
	private DataOutputStream dos;
	
	public void clientStart() {
		// 전송할 파일 선택
		File file = getSelectFile("OPEN");
		if(file==null) {
			System.out.println("전송할 파일을 선택하지 않았습니다.");
			System.out.println("파일 전송 작업을 중단합니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작");
			
			// 선택한 파일 이름 전송하기
			//  ==> 서버에 접속하면 제일 먼저 파일명을 전송한다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(file.getName()); // 파일명 전송
			
			bis = new BufferedInputStream(new FileInputStream(file));
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			while((len = bis.read(temp)) > 0) {
				bos.write(temp, 0, len);
			}
			
			bos.flush();
			System.out.println("파일 전송 완료");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!");
			e.printStackTrace();
		} finally {
			if(dos != null) try {dos.close();} catch(IOException e) {}
			if(bos != null) try {bos.close();} catch(IOException e) {}
			if(bis != null) try {bis.close();} catch(IOException e) {}
			if(socket != null) try {socket.close();} catch(IOException e) {}
		}
		
	}

	public static void main(String[] args) {		
		new TcpFileClient02().clientStart();
	}
	
	// 파일을 선택하여 선택한 파일을 반환하는 메서드
	public File getSelectFile(String option) {
		// SWING의 파일 '열기창', '저장창' 연습
		JFileChooser chooser = new JFileChooser();

		// 확장자 목록 중에 '모든 파일' 목록의 표시 여부를 설정하기
		// (true : 모든파일목록 보이기, false : 해제)
		chooser.setAcceptAllFileFilterUsed(true);

		// Dialog 창이 나타낼 기본 경로 설정
		// 'D:/d_other"폴더
		chooser.setCurrentDirectory(new File("D:/D_other"));

		int result;
		if ("OPEN".equals(option)) {
			// 열기용 창 보여주기
			result = chooser.showOpenDialog(new Panel());
		} else if ("SAVE".equals(option)) {
			// 저장용 창 보여주기
			result = chooser.showSaveDialog(new Panel());
		} else {
			System.out.println("option이 잘못되었습니다.");
			return null;
		}

		File selectedFile = null;
		// 창에서 '저장' 또는 '열기' 버튼을 클릭했을 때 처리
		if (result == JFileChooser.APPROVE_OPTION) {
			// 현재 선택한 파일 정보를 가져올 수 있다.
			selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		}

		return selectedFile;
	}

}
