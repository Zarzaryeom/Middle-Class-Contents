package kr.or.ddit.borad.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.borad.service.IBoardServiceImpl01;
import kr.or.ddit.borad.vo.BoardVo;

public class BoardController01 {
	
	private IBoardServiceImpl01 service;
	private Scanner scan;
	
	public BoardController01() {
		service = IBoardServiceImpl01.getInstance();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new BoardController01().boardStart();
	}
	
	// 시작 메서드
	public void boardStart() {
		String title = null;
		int choice = -1;
		while(true) {
			
			if(choice!=3) title = null;
			
			choice = displayMenu(title);
			
			switch(choice) {
				case 1: insertBoard(); break;
				case 2: viewBoard(); break;
				case 3: title = searchBoard(); break;
				case 0: 
					System.out.println("프로그램 종료..");
					return;
				default :
					System.out.println("작업번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요.");
				
			}
		}
	}
	
	// 게시글을 검색하는 메서드
	private String searchBoard() {
		scan.nextLine();
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String title = scan.nextLine();
		return title;
	}

	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int no = scan.nextInt();
		
		BoardVo bv = service.getBoard(no);
		
		if(bv == null) {
			System.out.println(no + "번 게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println();
		System.out.println(no + "번 글 내용");
		System.out.println("--------------------------------------------");
		System.out.println(" - 제 목 : " + bv.getTitle());
		System.out.println(" - 작성자 : " + bv.getWriter());
		System.out.println(" - 내 용 : " + bv.getContent());
		System.out.println(" - 작성일 : " + bv.getDate());
		System.out.println(" - 조회수 : " + bv.getCnt());
		System.out.println("--------------------------------------------");
		System.out.println(" 메뉴 : 1.수정    2.삭제    3.리스트로 가기");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		
		switch(num) {
			case 1 : updateBoard(no); break;
			case 2 : deleteBoard(no); break;
			case 3 : return; // 리스트로 가기
		
		}
		
	}

	private void deleteBoard(int no) {
		int cnt = service.deleteBoard(no);
		if(cnt > 0) {
			System.out.println(no + "번 글이 삭제되었습니다.");
		}else {
			System.out.println(no + "번 글 삭제에 실패하였습니다.");
		}
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int no) {
		scan.nextLine();
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("--------------------------------------------");
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		BoardVo bv = new BoardVo();
		bv.setBoard_no(no);
		bv.setTitle(title);
		bv.setContent(content);
		
		int cnt = service.updateBoard(bv);
		
		if(cnt > 0) {
			System.out.println(no + "번 글이 수정되었습니다.");
		}else {
			System.out.println(no + "번 글 수정에 실패하였습니다.");
		}
	}

	// 새글을 작성하는 메서드
	private void insertBoard() {
		scan.nextLine(); // 버퍼 비우기
		System.out.println();
		System.out.println("새 글 작성하기");
		System.out.println("--------------------------------------------");
		System.out.print(" - 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print(" - 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print(" - 내 용 : ");
		String content = scan.nextLine();
		
		// 입력받은 데이터를 VO에 저장한다.
		BoardVo bv = new BoardVo();
		bv.setTitle(title);
		bv.setWriter(writer);
		bv.setContent(content);
		
		int cnt = service.insertBoard(bv);
		
		if(cnt > 0) {
			System.out.println("새 글이 추가되었습니다!");
		}else {
			System.out.println("새 글 추가 실패..");
		}
		
	}

	// 게시글 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 반환하는 메서드
	public int displayMenu(String title) {
		List<BoardVo> boardList = null; 
		if(title == null || "".equals(title)) {
			boardList = service.getAllBoard();
		}else {
			boardList = service.getSearchBoard(title);
		}
		
		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println(" NO       제 목           작성자        조회수");
		System.out.println("--------------------------------------------");
		if(boardList == null || boardList.size() == 0) {
			System.out.println(" 출력할 게시글이 하나도 없습니다..");
		}else {
			for(BoardVo bv : boardList) {
				System.out.println(bv.getBoard_no() + "\t" + bv.getTitle() + "\t"
						+ bv.getWriter() + "\t" + bv.getCnt());
			}
		}
		System.out.println("--------------------------------------------");
		System.out.println("메뉴 ㅣ 1.새글작성 2.게시글보기 3.검색 0.작업 끝");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}
	

}
