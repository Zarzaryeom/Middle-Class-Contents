package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	// 리스트 출력
	public List<BoardVO> selectList (Map<String, Object> map);
	
	// 전체 글 갯수 가져오기
	public int totalCount(Map<String, String> map);
	
	// page정보 구하기
	public Map<String, Object> getPageInfo(int page, String type, String word);
	
	// 글 삭제
	public int deleteBoard(int num);
	
	// 조회수 증가
	public int updateHit(int num);
}
