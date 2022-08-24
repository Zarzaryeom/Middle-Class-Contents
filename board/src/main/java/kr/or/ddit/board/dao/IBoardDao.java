package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	// 리스트 출력
	public List<BoardVO> selectList (Map<String, Object> map) throws SQLException;
	
	// 전체 글 갯수 가져오기
	public int totalCount(Map<String, String> map) throws SQLException;
	
	// 글 삭제
	public int deleteBoard(int num) throws SQLException;
		
	// 조회수 증가
	public int updateHit(int num) throws SQLException;;
	
}
