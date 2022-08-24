package kr.or.ddit.borad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDaoImpl implements IBoardDao {
	
	// 싱글톤 생성
	private static BoardDaoImpl dao;
	private BoardDaoImpl() { }
	public static BoardDaoImpl getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}

	// 게시글 수정
	@Override
	public int insertBoard(Connection con, Map<String, Object> mapBoard) throws SQLException {
		String sql = "insert into jdbc_board(board_title, board_writer)"
				+ "   values(?, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setObject(1, mapBoard.get("TITLE"));
		pstmt.setObject(2, mapBoard.get("CONTENT"));
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	// 게시글 삭제
	@Override
	public int deleteBoard(Connection con, Map<String, Object> mapBoard) throws SQLException {
		String sql = "delete from jdbc_board where board_no = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setObject(1, mapBoard.get("NO"));
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	// 전체 게시글 읽기
	@Override
	public List<Map<String, Object>> getAllBoard(Connection con) throws SQLException {
		String sql = "select board_no,"
				+ "          board_title,"
				+ "          board_writer,"
				+ "          board_date,"
				+ "          board_cnt,"
				+ "          BOARD_CONTENT"
				+ "     from jdbc_board"
				+ "    order by board_no desc";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		
		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("NO", rs.getObject(1));
			map.put("TITLE", rs.getString(2));
			map.put("WRITER", rs.getString(3));
			map.put("DATE", rs.getString(4));
			map.put("CNT", rs.getString(5));
			map.put("CONTENT", rs.getString(6));
			
			list.add(map);
		}
		
		return list;
	}

	// 하나의 게시글 읽기
	@Override
	public Map<String, Object> getReadBoard(Connection con, Map<String, Object> mapBoard) throws SQLException {
		String sql = "select board_no,"
				+ "          board_title,"
				+ "          board_writer,"
				+ "          board_date,"
				+ "          board_cnt,"
				+ "          BOARD_CONTENT"
				+ "     from jdbc_board"
				+ "    where board_no = ?";
		
		
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setObject(1, mapBoard.get("NO"));
		
		ResultSet rs = pstmt.executeQuery();
		
		Map<String, Object> map = null;
		
		if(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("TITLE", rs.getString(2));
			map.put("WRITER", rs.getString(3));
			map.put("DATE", rs.getString(4));
			map.put("CNT", rs.getString(5));
			map.put("CONTENT", rs.getString(6));
		}
//		for(int i = 1; i <= 6; i++) {
//			System.out.println(rs.getObject(i));
//			System.out.println(rs.getString(i));
//		}
		
		return map;
	}

	@Override
	public int creatBoard(Connection con, Map<String, Object> mapBoard) throws SQLException {
		String sql = "INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_WRITER, "
				+ "	  BOARD_DATE, BOARD_CNT, BOARD_CONTENT)"
				+ "   VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, 0, ?)";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setObject(1, mapBoard.get("TITLE"));
		pstmt.setObject(2, mapBoard.get("WRITER"));
		pstmt.setObject(3, mapBoard.get("CONTENT"));
		
		int cnt = pstmt.executeUpdate();		
		
		return cnt;
	}
	// 조회수 증가
	@Override
	public int cntBoard(Connection con, int num) throws SQLException {
		String sql = "UPDATE JDBC_BOARD"
				+ "   SET BOARD_CNT = ((SELECT BOARD_CNT FROM JDBC_BOARD WHERE BOARD_NO = ?) + 1)"
				+ " WHERE BOARD_NO = ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setObject(1, num);
		pstmt.setObject(2, num);
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}
	

	@Override
	public List<Map<String, Object>> searchBoard(Connection con, String boardName) throws SQLException {
		String sql = "select board_no,"
				+ "          board_title,"
				+ "          board_writer,"
				+ "          board_date,"
				+ "          board_cnt,"
				+ "          BOARD_CONTENT"
				+ "     from jdbc_board"
				+ "    where board_title like ?";
		
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "%" + boardName + "%");
		
		ResultSet rs = pstmt.executeQuery();

		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		
		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("NO", rs.getObject(1));
			map.put("TITLE", rs.getString(2));
			map.put("WRITER", rs.getString(3));
			map.put("DATE", rs.getString(4));
			map.put("CNT", rs.getString(5));
			map.put("CONTENT", rs.getString(6));
			
			list.add(map);
		}
		
		return list;
	}

}
