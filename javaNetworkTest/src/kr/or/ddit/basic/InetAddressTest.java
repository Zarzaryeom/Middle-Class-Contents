package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress :" + ip.getHostAddress());
		System.out.println("toString : " + ip.toString()); // 도메인명과 ip주소를 같이 보여준다.
		System.out.println();
		
		// 자신의 컴퓨터 IP정보 가져오기
		InetAddress localIP = InetAddress.getLocalHost();
		
		System.out.println("내 컴의 HostName : " + localIP.getHostName());
		System.out.println("내 컴의 HostAddress : " + localIP.getHostAddress());
		System.out.println();

		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] multiIp = InetAddress.getAllByName("www.goggle.com");
		for(InetAddress ips : multiIp) {
			System.out.println(ips.toString());
		}
	}

}
