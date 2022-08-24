package kr.or.ddit.borad.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.borad.service.BoardServiceImpl;
import kr.or.ddit.borad.service.IBoardService;

public class BoardController {
	private Scanner sc = new Scanner(System.in);
	private IBoardService service;
	private BoardController() {
		service = BoardServiceImpl.getInstance();
	}
	
	// 시작 페이지
	public void start() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		while(true) {
			list = service.getAllBoardService();
			System.out.println();
			System.out.println("=================== 대덕 게시판 ====================");
			System.out.println("-------------------------------------------------");
			System.out.println("No\t제목\t\t작성자\t\t조회수");
			System.out.println("-------------------------------------------------");
			// 게시글 출력
			if(list.isEmpty()) {
				System.out.println("더 이상 게시글이 없습니다.");
			}
			for(Map<String, Object> map : list) {
				System.out.println(map.get("NO") + "\t" + map.get("TITLE") + "\t\t" +
									map.get("WRITER") + "\t\t" + map.get("CNT"));
			}
			
			System.out.println("-------------------------------------------------");
			System.out.println("◇ 메뉴 : 1.새 글작성   2.게시글 보기   3.검색   0.작업 끝");
			System.out.print("☞ 작업 선택 >> ");
			int input = sc.nextInt();

			switch (input) {
			case 1:	creatBoard(); break; // 새 글작성
			case 2:	readBoard(); break;  // 게시글 보기
			case 3:	search(); break;     // 검색
			case 0:
				System.out.println("작업을 종료합니다.");
				return;
			default:
				System.out.println("잘못된 입력입니다.");
				System.out.println("다시 입력해주세요.");
				break;
			}
		}
		
	}

	
	
	
	
	// 검색하기
	private void search() {
		System.out.println();
		sc.nextLine(); // 버퍼 지우기
		System.out.println("검색 작업");
		System.out.println("-------------------------------------------------");
		System.out.print(" ☞ 검색할 제목 입력 : ");
		String boradName = sc.nextLine();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = service.searchBoard(boradName);
		System.out.println();
		System.out.println("=================== 대덕 게시판 ====================");
		System.out.println("-------------------------------------------------");
		System.out.println("No\t제목\t\t작성자\t\t조회수");
		System.out.println("-------------------------------------------------");
		// 게시글 출력
		if (list.isEmpty()) {
			System.out.println("더 이상 게시글이 없습니다.");
		}
		for (Map<String, Object> map : list) {
			System.out.println(map.get("NO") + "\t" + map.get("TITLE") + "\t\t" 
							 + map.get("WRITER") + "\t\t" + map.get("CNT"));
		}

		System.out.println("-------------------------------------------------");
		System.out.println("◇ 메뉴 : 1.새 글작성   2.게시글 보기   3.검색   0.작업 끝");
		System.out.print("☞ 작업 선택 >> ");
		int input = sc.nextInt();
		
		switch (input) {
		case 1:	creatBoard(); break; // 새 글작성
		case 2:	readBoard(); break;  // 게시글 보기
		case 3:	search(); break;     // 검색
		case 0:
			System.out.println("작업을 종료합니다.");
			System.exit(0);
		default:
			System.out.println("잘못된 입력입니다.");
			System.out.println("다시 입력해주세요.");
			break;
		}
	}

	// 작성글 읽기
	private void readBoard() {
		System.out.println();
		System.out.print("읽기 원하는 게시물 번호를 입력하세요. >> ");
		int input = sc.nextInt();
		
		// 조회수 세기
		int num = service.cntBoard(input);
		if(num < 1) {
			System.out.println("조회수 세기 오류");
		}
			
		// 입력된 번호에 맞는 작성글 출력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("NO", input);
		
		map = service.getReadBoard(map);
		
		if(map == null) {
			System.out.println("없는 게시글 번호입니다.");
			return;
		}
			
		
		// 출력된 화면
		System.out.println("-------------------------------------------------");
		System.out.println("   ▶ 제 목 : " + map.get("TITLE"));
		System.out.println("   ▶ 작성자 : " + map.get("WRITER"));
		System.out.println("   ▶ 내 용 : " + map.get("CONTENT"));
		System.out.println("   ▶ 작성일 : " + map.get("DATE"));
		System.out.println("   ▶ 조회수 : " + map.get("CNT"));
		System.out.println("-------------------------------------------------");
		System.out.println("◇ 메뉴 : 1.수정   2.삭제   3.목록으로 가기");
		System.out.print("☞ 작업 선택 >> ");
		int select = sc.nextInt();
		
		System.out.println();
		switch(select) {
		case 1: insertBoard(input); break; // 수정
		case 2: deledtBoard(input); break; // 삭제
		case 3: 
			System.out.println("목록으로 돌아갑니다.");
			break;
		default:
			System.out.println("잘못된 입력입니다. 목록으로 돌아갑니다!");
			break;
		}
		
	}

	// 글 삭제
	private void deledtBoard(int input) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println();
		
		map.put("NO", input);
		
		int cnt = service.deleteBoard(map);
		
		if(cnt > 0) {
			System.out.println("새 글이 성공적으로 삭제되었습니다.");
		}else {
			System.out.println("새 글 삭제를 실패하였습니다..");
		}
		
	}

	// 글 수정
	private void insertBoard(int input) {
		
	}

	// 새 글생성
	private void creatBoard() {
		Map<String, Object> map = new HashMap<String, Object>();
		sc.nextLine(); // 버퍼 잡기
		System.out.println();
		System.out.println("새 글 작성하기");
		System.out.println("-------------------------------------------------");
		System.out.print("  ▷ 제목 : ");
		String title = sc.nextLine();
		
		System.out.print("  ▷ 작성자 : ");
		String writer = sc.nextLine();
		
		System.out.print("  ▷ 내 용 : ");
		String content = sc.nextLine();
		
		map.put("TITLE", title);
		map.put("WRITER", writer);
		map.put("CONTENT", content);
		
		int cnt = service.creatBoard(map);
		
		if(cnt > 0) {
			System.out.println("새 글이 성공적으로 추가되었습니다.");
		}else {
			System.out.println("새 글 추가를 실패하였습니다..");
		}
	}

	// 메인 메서드
	public static void main(String[] args) {
		new BoardController().start();
	}

}
