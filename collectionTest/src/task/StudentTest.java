package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * 문제) 학번, 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student 클래스를 만든다.
 * 		이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 한다.
 * 		이 클래스는 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다.
 * 
 * 		이 Student객체는 List에 저장하여 관리한다.
 * 		
 * 		List에 저장된 Student객체를 총점의 역순으로 정렬하는데 총점이 같으면 이름이 오름차순으로 정렬이되는 외부 정렬 기준 클래스도 작성하시오.
 * 		
 * 		(단, 등수는 List에 전체 데이터가 모두 저장된 후에 구한다.)
 *
 * */

public class StudentTest {
	// 등수를 구하는 메서드
	public void setRankin(List<Student> stdList) {
		for(int i = 0; i < stdList.size(); i++) {
			int rank = 1;
			for(int j = 0; j < stdList.size(); j++) {
				if(stdList.get(i).getSum() < stdList.get(j).getSum()) {
					rank++;
				}
			}
			stdList.get(i).setRank(rank);	
		}
		
	/*
	 * for(Student std1
	 * 
	 */
		
		
	}

	public static void main(String[] args) {
		StudentTest test = new StudentTest();
		List<Student> stu = new ArrayList<Student>();
		
		stu.add(new Student(202201, "홍길동", 80, 66, 90));
		stu.add(new Student(202205, "강감찬", 50, 60, 87));
		stu.add(new Student(202204, "일지매", 68, 78, 90));
		stu.add(new Student(202203, "정청", 98, 15, 75));
		stu.add(new Student(202202, "이준수", 35, 75, 84));
		//등수를 구하는 메서드 호출
		test.setRankin(stu);
		
		
		

		System.out.println("학번 정렬 전");
		for(Student list : stu) {
			System.out.println(list);
		}
		
		
		System.out.println("--------------------------------");
		
		Collections.sort(stu);
		
		System.out.println("학번 정렬 후");
		for(Student list : stu) {
			System.out.println(list);
		}
		
		System.out.println("--------------------------------");
		
		Collections.sort(stu, new SortSum());
		System.out.println("총점 역순 정렬");
		for(Student list : stu) {
			System.out.println(list);
		}
		
		
	}

}


class Student implements Comparable<Student> {
	private int studentNum;
	private String name;
	private int hangulNum;
	private int englishNum;
	private int mathNum;	
	
	@Override
	public String toString() {
		return "Student [studentNum=" + studentNum + ", name=" + name + ", hangulNum=" + hangulNum + ", englishNum="
				+ englishNum + ", mathNum=" + mathNum + ", sum=" + sum + ", rank=" + rank + "]";
	}


	private int sum;
	public int getSum() {
		return sum;
	}


	public void setSum(int sum) {
		this.sum = sum;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	private int rank;
	
	
		public int getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getHangulNum() {
		return hangulNum;
	}


	public void setHangulNum(int hangulNum) {
		this.hangulNum = hangulNum;
	}


	public int getEnglishNum() {
		return englishNum;
	}


	public void setEnglishNum(int englishNum) {
		this.englishNum = englishNum;
	}


	public int getMathNum() {
		return mathNum;
	}


	public void setMathNum(int mathNum) {
		this.mathNum = mathNum;
	}



	//생성자
	public Student(int studentNum, String name, int hangulNum, int englishNum, int mathNum) {
		this.studentNum = studentNum;
		this.name = name;
		this.hangulNum = hangulNum;
		this.englishNum = englishNum;
		this.mathNum = mathNum;
		sum = hangulNum + englishNum + mathNum; 
	}

	//학번의 오름차순
	@Override
	public int compareTo(Student num1) {
		return new Integer(studentNum).compareTo(num1.getStudentNum());
		// return Integer.compare(studentNum, num1.getNum());
	}
	
}

class SortSum implements Comparator<Student>{

	@Override
	public int compare(Student num1, Student num2) {
		if(num1.getSum() > num2.getSum()) return -1;
		else if(num1.getSum() < num2.getSum()) return 1;
		else if(num1.getSum() == num2.getSum()) {
			return num1.getName().compareTo(num2.getName());
		}else return 0;
	}
}
