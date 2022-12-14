package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	
	// 접속한 클라이언트 정보를 저장할 Map객체 변수 선언
	//  - key값 : 접속한 사람 이름
	//  - value값 : 접속한 클라이언트와 연결된 Socket객체
	private Map<String, Socket> clientMap; 

	// 생성자
	public TcpMultiChatServer() {
		// clientMap을 동기화 처리가 가능하도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	// 시작하는 메서드
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다.");
			System.out.println();
			
			//서버가 계속 클라이언트를 받을 수 있도록 무한루프로 묶음
			while(true){
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + " : " +
										 socket.getPort() + "]에서 접속했습니다.");
			
				// 데이터를 받아서 전체 사용자에게 보내는 Thread 실행
				ServerReceiver serverTh = new ServerReceiver(socket);
				serverTh.start();
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(server != null) try {server.close();} catch(Exception e) {}
		}
	}
	
	// clientMap에 저장된 전체 사용자에게 메세지를 전송하는 메서드
	// 파라미터는 전송할 메세지(mag)
	private void sendToAll(String msg) {
		// clientMap의 데이터 갯수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				// client와 연결된 Socket객체를 구한다.
				Socket socket = clientMap.get(name);
				
				// 메세지를 보낼 수 있도록 OutputStream으로 데이터를 보낸다.
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				
				// 메세지를 보낸다.
				dos.writeUTF(msg);
				
			} catch (Exception e) {
			}
		}
	}
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}
	
	// 클라이언트로 메세지를 전송하는 Thread를 Inner Class로 작성한다.
	// 생성했던 변수들을 바로 사용하기 위해 Inner Class로 작성
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis; // 메세지를 받을 곳
		private DataOutputStream dos; // 중복된 이름이 들어왔을 때, 데이터가 서로 수정하는걸 막기 위해 설정
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				// 수신용
				dis = new DataInputStream(this.socket.getInputStream());
				
				// 송신용
				dos = new DataOutputStream(this.socket.getOutputStream());
				
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		
		@Override
		public void run() {
			String name = "";
			try {
				while(true) {
					// 클라이언트가 최초로 보낸 데이터는 사용자의 이름인데 이 이름이 중복되는지 여부를
					// feeedback으로 클라이언트에게 보내준다.
					
					name = dis.readUTF(); // 클라이언트가 보낸 이름을 받음
					
					// 이름이 중복되면..
					if(clientMap.containsKey(name)) {
						// 이름이 중복되면 '이름중복' 메세지를 클라이언트에게 보낸다.
						dos.writeUTF("이름중복");
					}else {
						// 중복되지 않으면 'OK'메세지를 보내고 반복문을 탈출
						dos.writeUTF("OK");
						break;
					}
				}
				
				// 다른 모든 클라이언트들에게 대화방 참여 메세지 전송
				sendToAll("[" + name + "]님이 입장했습니다.");
				
				// 대화명(이름)과 클라이언트의 Socket객체를 Map에 저장한다.
				clientMap.put(name, socket);
				
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
				
				// 한 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보내기
				while(dis != null) {
					sendToAll(dis.readUTF());
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// 이 finally 영익이 실행된다는 것은 클라이언트가 접속을 종료했을 때 이다.
				sendToAll("[" + name + "]님이 접속을 종료했습니다.");
				
				// 사용자 목록(Map)에서 삭제한다.
				clientMap.remove(name);
				
				System.out.println();
				System.out.println("[" + " : " + socket.getInetAddress() + 
												 socket.getPort() + "]에서 접속 종료했습니다.");
				
				System.out.println();
				System.out.println("현재 서버 접속자 수 : " + clientMap.size() + "명");
			}
		}
	}
	
	
	
	
	
	
	
}
