package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	 *	문제) 5명의 사람 이름을 입력받아 ArrayList에 저장한 후 이들 중 '김'씨 성의 이름을 모두 출력하시오. 입력은 Scanner객체를 이용한다. 
	 * 
	 */

public class ArrayListTest02 {
		
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("사람의 이름을 입력하세요.(1)");
		String name0 = sc.nextLine();
		
		System.out.println("사람의 이름을 입력하세요.(2)");
		String name1 = sc.nextLine();
		
		System.out.println("사람의 이름을 입력하세요.(3)");
		String name2 = sc.nextLine();
		
		System.out.println("사람의 이름을 입력하세요.(4)");
		String name3 = sc.nextLine();
		
		System.out.println("사람의 이름을 입력하세요.(5)");
		String name4 = sc.nextLine();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(name0);
		list.add(name1);
		list.add(name2);
		list.add(name3);
		list.add(name4);
		
		//1번
		System.out.println("1번 결과");	
		for(String str : list) {
			if(str.charAt(0) == '김') {				
			System.out.println("'김'씨의 성을 가진 사람 : " + str);			
			}
		}
		System.out.println("----------------------------------");


		//2번
		System.out.println("2번 결과");
		for(String str2 : list) {
			if(str2.indexOf("김") == 0)	{
			System.out.println("'김'씨의 성을 가진 사람 : " + str2);
			}
		}
		System.out.println("----------------------------------");
		
		//3번
		System.out.println("3번 결과");
		for(String str3 : list) {
			if(str3.substring(0,1).equals("김")) {
				System.out.println("'김'씨의 성을 가진 사람 : " + str3);
			}
		}
		System.out.println("----------------------------------");
		
		//4번
		System.out.println("4번 결과");
		String regex = "^김[\\D][\\D]";
		for(String str4 : list) {
			if(str4.matches(regex) == true) {
				System.out.println("'김'씨의 성을 가진 사람 : " + str4);
			}
		}
		
		//5번
		//startsWith : 시작문자를 찾는 메서드
		System.out.println("4번 결과");
		for(String str5 : list) {
			if(str5.startsWith("김") == true) {
				System.out.println("'김'씨의 성을 가진 사람 : " + str5);
			}
		}
	
	
		
		
		
	}
	
	
}
