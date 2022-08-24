package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest03 {	

	public static void main(String[] args) {
		
		//회원번호의 내림차순으로 정렬될 수 있는 외부 정렬 기준을 작성하시오.
		
		ArrayList<Member2> memList = new ArrayList<Member2>();
		
		memList.add(new Member2(1, "홍길동", "010-1111-1111"));
		memList.add(new Member2(5, "이순신", "010-2222-1111"));
		memList.add(new Member2(9, "성춘향", "010-3333-1111"));
		memList.add(new Member2(3, "강감찬", "010-4444-1111"));
		memList.add(new Member2(6, "일지매", "010-5555-1111"));
		memList.add(new Member2(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬 전");
		for(Member2 mem : memList) {
			System.out.println(mem);
		}
		
		Comparator<Member2> comparator = new Comparator<Member2>() {
			@Override
			public int compare(Member2 memNo, Member2 memNo2) {
				
				int num1 = memNo.getNum();
				int num2 = memNo2.getNum();
				
				if(num1 == num2) return 0;
				else if(num1 > num2) return -1;
				else return 1;					
		}
		};
		
		
		
		Collections.sort(memList, comparator);
		System.out.println(memList);
		
		System.out.println();
		System.out.println("정렬 후");
		for(Member2 mem : memList) {
			System.out.println(mem);
		}
		

	}

}

class Member2 implements Comparator<Member2>{
	private int num;
	private String name;
	private String tel;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member2 [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	//생성자
	public Member2(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	@Override
	public int compare(Member2 memNo, Member2 memNo2) {
		
		int num1 = memNo.getNum();
		int num2 = memNo2.getNum();
		
		if(num1 == num2) return 0;
		else if(num1 > num2) return -1;
		else return 1;					
}

	
	
	
	
	
	
	
	
}