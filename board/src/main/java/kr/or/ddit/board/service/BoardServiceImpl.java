package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao dao;
	private static IBoardService service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	public static IBoardService getInstance() {
		if(service == null) service = new BoardServiceImpl();
		return service;
	} 
	
	

	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		List<BoardVO> list = null;
		
		try {
			list = dao.selectList(map);
			System.out.println("서비스 체크");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	@Override
	public int totalCount(Map<String, String> map) {
		int count = 0;
		
		try {
			count = dao.totalCount(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	@Override
	public Map<String, Object> getPageInfo(int page, String type, String word) {
		Map<String, Object> map =  new HashMap<String, Object>();
		
		// 한 페이지당 출력할 글 갯수
		int perlist = 3;
		
		// 한 화면에 출력할 페이지 갯수
		int perpage = 2;
		
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("stype", type);
		paraMap.put("sword", word);
		
		// 전체 글 갯수
		int count = this.totalCount(paraMap);
//		System.out.println(count);
		// 전체 페이지 수
		int totalPage = (int)Math.ceil((double)count / perlist);
		
		// start // end
		int start = (page - 1) * perlist + 1;
		int end = start + perlist - 1;
		if(end > count) end = count;
		
		//startPage, endPage 구하기
		//page = 1 => startPage = 1, endPage = 2
		//page = 2 => startPage = 1, endPage = 2
		//page = 3 => startPage = 3, endPage = 4
		//page = 4 => startPage = 3, endPage = 4
		// ... page = 7 => startPage = 7, endPage = 8
		int startPage = ((page-1) / perpage * perpage) + 1;
		int endPage = startPage + perpage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", totalPage);
		
		return map;
	}
	
	@Override
	public int deleteBoard(int num) {
		int res = 0;
		try {
			res = dao.deleteBoard(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public int updateHit(int num) {
		int res = 0;
		try {
			res = dao.updateHit(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


}
