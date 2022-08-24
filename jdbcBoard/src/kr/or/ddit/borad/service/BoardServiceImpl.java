package kr.or.ddit.borad.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.borad.dao.BoardDaoImpl;
import kr.or.ddit.borad.dao.IBoardDao;
import kr.or.ddit.util.DBUtil3;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	private static BoardServiceImpl service;
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	public static BoardServiceImpl getInstance() {
		if(service == null) service = new BoardServiceImpl();
		
		return service;
	}

	// 게시글 삽입
	@Override
	public int insertBoard(Map<String, Object> mapBoard) {
		Connection con = null;
		int cnt = 0;
		try {
			con = DBUtil3.getConnection();
			cnt = dao.insertBoard(con, mapBoard);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	// 게시글 삭제
	@Override
	public int deleteBoard(Map<String, Object> mapBoard) {
		Connection con = null;
		int cnt = 0;
		try {
			con = DBUtil3.getConnection();
			cnt = dao.deleteBoard(con, mapBoard);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	// 게시글 전체 읽어오기
	@Override
	public List<Map<String, Object>> getAllBoardService() {
		Connection con = null;
		List<Map<String, Object>> list = null;
		
		try {
			con = DBUtil3.getConnection();
			list = dao.getAllBoard(con);
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(con != null) try { con.close(); } catch(SQLException e) {}
		}
		
		return list;
	}

	// 게시글 하나 읽어오기
	@Override
	public Map<String, Object> getReadBoard(Map<String, Object> mapBoard) {
		Connection con = null;
		Map<String, Object> map = null;
		try {
			con = DBUtil3.getConnection();
			map = dao.getReadBoard(con, mapBoard);
			
		} catch (SQLException e) {
			map = null;
			e.printStackTrace();
		}finally {
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		return map;
	}

	@Override
	public int creatBoard(Map<String, Object> mapBoard) {
		Connection con = null;
		int cnt = 0;
		try {
			con = DBUtil3.getConnection();
			cnt = dao.creatBoard(con, mapBoard);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}
	@Override
	public int cntBoard(int num) {
		Connection con = null;
		int cnt = 0;
		try {
			con = DBUtil3.getConnection();
			cnt = dao.cntBoard(con, num);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
		return cnt;
	}
	
	@Override
	public List<Map<String, Object>> searchBoard(String boardName) {
		Connection con = null;
		List<Map<String, Object>> list = null;
		
		try {
			con = DBUtil3.getConnection();
			list = dao.searchBoard(con, boardName);
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(con != null) try {con.close();} catch(SQLException e) {}
		}
				
		return list;
	}

}
