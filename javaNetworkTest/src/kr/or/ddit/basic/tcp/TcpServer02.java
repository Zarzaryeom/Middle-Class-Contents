package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) throws IOException {
		// 서버소켓을 만들고, 클라이언트가 접속해 오면 클라이언트와 연결된 소켓이 만들어지는데
		// 이 소켓을 메세지를 받는 쓰레드와 메세지를 보내는 쓰레들에 생성자의 인수로 넘겨 준다.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비준입니다..");
		System.out.println();
		
		Socket socket = server.accept(); // 반복문 형식으로 작성하면 1대 다 통신을 구현할 수 있다.
		
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
		
	}

}
