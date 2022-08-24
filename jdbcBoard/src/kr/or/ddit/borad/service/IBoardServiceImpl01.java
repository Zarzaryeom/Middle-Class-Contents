package kr.or.ddit.borad.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.borad.dao.BoardDaoImpl;
import kr.or.ddit.borad.dao.BoardDaoImpl01;
import kr.or.ddit.borad.dao.IBoardDao;
import kr.or.ddit.borad.vo.BoardVo;
import kr.or.ddit.util.DBUtil3;

public class IBoardServiceImpl01 {
	private static IBoardServiceImpl01 service;
	private BoardDaoImpl01 dao;
	
	private IBoardServiceImpl01() {
		dao = BoardDaoImpl01.getInstance();
	}
	public static IBoardServiceImpl01 getInstance() {
		if(service == null) service = new IBoardServiceImpl01();
		
		return service;
	}
		
	
	
	
	public int insertBoard(BoardVo bv) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.insertBoard(conn, bv);
			
		} catch (SQLException e) {
			cnt= 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}
	
	
	public int deleteBoard(int boardNo){
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.deleteBoard(conn, boardNo);
			
		} catch (SQLException e) {
			cnt= 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}
	
	
	public int updateBoard(BoardVo bv) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateBoard(conn, bv);
			
		} catch (SQLException e) {
			cnt= 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}
	
	public BoardVo getBoard(int boardNo) {
		Connection conn = null;
		BoardVo bv = null;
		try {
			conn = DBUtil3.getConnection();
			// 조회수 증가
			int cnt = dao.setCountincrement(conn, boardNo);
			if(cnt == 0) { // 조회수 증가를 실패했을 때
				return null;
			}
			
			bv = dao.getBoard(conn, boardNo);
			
		} catch (SQLException e) {
			bv = null;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return bv;
	}
	
	
	public List<BoardVo> getAllBoard() {
		Connection conn = null;
		List<BoardVo> list = null;
		
		try {
			conn = DBUtil3.getConnection();
			list = dao.getAllBoard(conn);
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}
	
	
	public List<BoardVo> getSearchBoard(String boardTitle){
		Connection conn = null;
		List<BoardVo> list = null;
		
		try {
			conn = DBUtil3.getConnection();
			list = dao.getSearchBoard(conn, boardTitle);
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}
	
	
	public int setCountincrement(int boardNo)  {
	
		return 0;
	}
	
	
}
