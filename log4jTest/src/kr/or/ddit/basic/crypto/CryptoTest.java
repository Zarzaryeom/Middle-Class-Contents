package kr.or.ddit.basic.crypto;

import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		
		String plainText = "Hellow, World! 가나다라 1234 %^&*_+";
		// 암호화에 사용하는 키값 설정(16자리)
		String key = "ab1q2w3e4r5t6y7u";
		//String key = "가나다라마!";
		
		
		// 단방향으로 바꾼 암호화 결과는 복원시킬 수 없다.
		System.out.println("단방향 암호화 연습...");
		String result = CryptoUtil.sha512(plainText);
		System.out.println("원본 데이터 : " + plainText);
		System.out.println("SHA-512방식 : " + result);
		System.out.println();
//		System.out.println("---------------------------------------------");
		
//		System.out.println("양방향 암호화 연습(AES256방식)...");
//		System.out.println("원본 데이터 : " + plainText);
//		
//		String encryptedStr = CryptoUtil.encryptoAES256(plainText, key);
//		System.out.println("AES-256 암호화 : " + encryptedStr);
//		
//		String decryptedStr = CryptoUtil.decryptoAES256(encryptedStr, key);
//		System.out.println("AES-256 복호화 : " + decryptedStr);
		
	}

}
