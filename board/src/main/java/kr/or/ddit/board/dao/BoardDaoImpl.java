package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao {

	private SqlMapClient sc;
	private static IBoardDao dao;
	// 생성자 = client객체 얻어오기
	private BoardDaoImpl() {
		sc = SqlMapClientFactory.getSqlMapClient();
	}
	
	// getInstance()메서드 - dao객체 생성하고 리턴
	public static IBoardDao getInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) throws SQLException {
		return sc.queryForList("board.selectList",map);
	}

	@Override
	public int totalCount(Map<String, String> map) throws SQLException {
		return (int) sc.queryForObject("board.totalCount", map);
	}

	@Override
	public int deleteBoard(int num) throws SQLException {
		return (int)sc.delete("board.deleteBoard", num);
	}

	@Override
	public int updateHit(int num) throws SQLException {
		return (int) sc.update("board.updateHit", num);
	}

}
