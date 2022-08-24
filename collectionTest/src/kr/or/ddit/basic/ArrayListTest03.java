package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import util.ScanUtil;

/*
 * 문제2) 5명 별명을 입력받아 ArrayList에 저장하고 이 별명 중에 제일 긴 별명을 출력하시오.
 * 		(단, 별명의 길이는 모두 다르게 입력한다.)
 * 
 * 문제1) 5명 별명을 입력받아 ArrayList에 저장하고이 별명 중에 제일 긴 별명을 출력하시오.
 * 		(단, 별명의 길이가 같은 것을 입력할 수 있다.)

*/
public class ArrayListTest03 {

	public static void main(String[] args) {

		
		// 문1
		List<String> nickName = new ArrayList<String>();
		
		System.out.println("별명 5개를 입력하세요.");
		for(int i = 0; i < 5; i++) {
			System.out.println((i+1) + "번째 별명을 입력하세요." );
			nickName.add(ScanUtil.nextLine());
		}
		
		for(int i = 0; i < nickName.size(); i++) {
			for(int j = i; j < nickName.size(); j++) {
				if(nickName.get(i).length() < nickName.get(j).length()) {
					String temp = null;
					temp = nickName.get(i);
					nickName.set(i, nickName.get(j));
					nickName.set(j, temp);					
				}
			}
		}
		System.out.println("1번");
		System.out.println(nickName.get(0));
		
		
		
		System.out.println("-------------------------------------------");
		
		// 문2
		System.out.println("2번");
		for(int i = 0; i < nickName.size(); i++) {
			if(nickName.get(0).length() == nickName.get(i).length()) {
				System.out.println(nickName.get(i));
			}
		}
		
		System.out.println("-------------------------------------------");
		
		
		// 강사님 풀이
		// 문1
		// 제일 긴 별명이 저장될 변수 선언
		//		==> List의 첫번째 자료로 초기화 한다.
		String maxAlias = nickName.get(0);
		
		for(int i = 1; i < nickName.size(); i++) {
			if(maxAlias.length() < nickName.get(i).length()) {
				maxAlias = nickName.get(i);
			}
		}		
		System.out.println("제일 긴 별명 : " + maxAlias);
		
		System.out.println("-------------------------------------------");
		// 문2
		// 제일 긴 별명의 길이가 저장될 변수 선언
		//		==> 첫번째 별명의 길이로 초기화한다.
		int maxLength = nickName.get(0).length();
		for(int i = 1; i < nickName.size(); i++) {
			if(maxLength < nickName.get(i).length()) {
				maxLength = nickName.get(i).length();						
			}
		}
		
		System.out.println("제일 긴 별명들...");
		for(String alias : nickName) {
			if(maxLength == alias.length()) {
				System.out.println(alias);
			}
		}
		
		
	}

}
