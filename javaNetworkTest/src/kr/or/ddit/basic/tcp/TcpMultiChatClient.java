package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	public static void main(String[] args) {

		new TcpMultiChatClient().clientStart();
	}

	// 시작 메서드
	public void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "192.168.34.41";
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다.");
			System.out.println();
			
			// 메시지 전송용 스레드와 수신용 스레드를 실행시킨다.
			ClientSender sender = new ClientSender(socket);
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
				
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		}
		
	}
	

	// 메세지 전송용 스레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis; // 피드백을 받기 위해
		private DataOutputStream dos; // 이름이나 메세지를 보내기 위해
		
		private String name;
		private Scanner scan;
		
		// 생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				dis = new DataInputStream(this.socket.getInputStream());
				dos = new DataOutputStream(this.socket.getOutputStream());
				
				if(dis != null) {
					// 처음 클라이언트가 실행되면 자신의 대화명(이름)을 서버로 전송하고 대화명의 중복 여부를
					// feedback으로 받아서 확인한다.
					
					// 대화명이 중복이 되면 계속 반복이 되어야 함으로 do-while문 사용
					do {
					System.out.println("대화명 : ");
					String name = scan.nextLine();
					
					dos.writeUTF(name); // 대화명 전송
					
					// 대화명 중복여부(feedback)를 받는다.
					String feedback = dis.readUTF();
					
					if("이름중복".equals(feedback)) {
						// 대화명이 중복될 때..
						System.out.println(name + "은 이름이 중복됩니다.");
						System.out.println("다른 대화명을 입력하세요.");
					}else {
						// 중복되지 않을 때
						this.name = name;
						System.out.println(name + "이름으로 대화방에 입장했습니다.");
						break; // 반복문 탈출
					}
					}while(true);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		@Override
		public void run() {
			try {
				while(dos != null) {
					// 키보드로 입력한 내용을 서버로 전송한다.
					dos.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
	// 메세지 수신용 스레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		// 생성자
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		@Override
		public void run() {
			try {
				while(dis != null) {
					// 서버가 보내준 메시지를 받아서 화면에 출력한다.
					System.out.println(dis.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	
}
