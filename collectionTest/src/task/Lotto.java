package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import util.ScanUtil;

public class Lotto {

	public static void main(String[] args) {

		Lotto lo = new Lotto();
		/*
		 * 로또 구매 프로그램
		 *  1. 사용자는 로또를 구매할 때 구매할 금액을 입력한다
		 *  2. 입력한 금액에 맞게 로또번호를 출력한다.
		 *  	(단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입 가능
		 *  		 거스름돈도 계산하여 출력)
		 * 
		*/
		// 로또 구매 메인 페이지
		while(true) {
			System.out.println("=========================");
			System.out.println("로또 프로그램");
			System.out.println("-------------------------");
			System.out.println(" 1. Lotto 구입");
			System.out.println(" 2. 프로그램 종료");
			System.out.println("=========================");
			System.out.print("메뉴 선택 : ");
			int input = ScanUtil.nextInt();
			System.out.println();

			switch(input) {
			case 1: lo.buy(); break;
			case 2: 
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);	
			}
		}
		
	}

	// 로또 구입 페이지
	public void buy() {
		System.out.println("Lotto 구입시작");
		System.out.println();
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.print("금액 입력 : ");
		int price = ScanUtil.nextInt();
		
		System.out.println();
		if(price > 100000) {
			System.out.println("입력 금액이 너무 많습니다.. 로또번호 구입 실패!!!");
			return;
		}else if(price < 1000) {
			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			return;
		}
		
		int lotto = price/1000;// 로또 갯수
		int result = price%1000;// 거스름돈
		
		System.out.println("행운의 로또번호는 아래와 같습니다.\n");		
		for(int i = 0; i < lotto; i++) {
		// 로또번호 나오는 set
			HashSet<Integer> lot = new HashSet<Integer>();
			ArrayList<Integer> lot2 = new ArrayList<Integer>();
		while(lot.size() < 6) {			
			int random = (int)(Math.random()*45) + 1;
			lot.add(random);
		}
		for(int number : lot) {
			lot2.add(number);
		}
			Collections.sort(lot2, new Desc2());
			System.out.println("로또번호" + (i+1) + ": " + lot2);
		}
		
		System.out.println("\n받은 금액은 " + price + "원이고"
							+ " 거스름돈은 " + result + "원입니다.\n");
	}
}


class Desc2 implements Comparator<Integer>{

	@Override
	public int compare(Integer num1, Integer num2) {
		return num1.compareTo(num2);
	}
	
}
