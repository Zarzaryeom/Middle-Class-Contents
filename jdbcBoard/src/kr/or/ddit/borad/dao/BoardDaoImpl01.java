package kr.or.ddit.borad.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.borad.vo.BoardVo;

public class BoardDaoImpl01 {
	
	private static BoardDaoImpl01 dao;
	
	private BoardDaoImpl01() { }
	
	public static BoardDaoImpl01 getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl01();
		}
		return dao;
	}
	
	public int insertBoard(Connection conn, BoardVo bv) throws SQLException {
		String sql = "insert into jdbc_board (board_no, board_title, "
				+ "   board_writer, board_date, board_cnt, board_content)"
				+ " values(board_seq.nextval, ?, ?, sysdate, 0, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getWriter());
		pstmt.setString(3, bv.getContent());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}
	
	
	public int deleteBoard(Connection conn, int boardNo) throws SQLException{
		String sql = "delete from jdbc_board where board_no = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}
	
	
	public int updateBoard(Connection conn, BoardVo bv) throws SQLException{
		String sql = "update jdbc_board set"
				+ "   board_title = ? , board_date = sysdate, board_content = ?"
				+ "   where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getTitle());
		pstmt.setString(2, bv.getContent());
		pstmt.setInt(3, bv.getBoard_no());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}
	
	public BoardVo getBoard(Connection conn, int boardNo) throws SQLException{
		String sql = "select board_no, board_title, board_writer,"
				+ " to_char(board_date, 'YYYY-MM-DD') as board_date, board_cnt,"
				+ " board_content"
				+ " from jdbc_board where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		BoardVo bv = null;
		if(rs.next()) {
			bv = new BoardVo();
			bv.setBoard_no(rs.getInt("board_no"));
			bv.setTitle(rs.getString("board_title"));
			bv.setContent(rs.getString("board_content"));
			bv.setWriter(rs.getString("board_writer"));
			bv.setCnt(rs.getInt("board_cnt"));
			bv.setDate(rs.getString("board_date"));
		}
		if(pstmt != null) pstmt.close();
		if(rs != null) rs.close();
		return bv;
	}
	
	
	public List<BoardVo> getAllBoard(Connection conn) throws SQLException{
		String sql = "select board_no, board_title, board_writer,"
				+ " to_char(board_date, 'YYYY-MM-DD') as board_date, board_cnt,"
				+ " board_content"
				+ " from jdbc_board order by board_no desc";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		while(rs.next()) {
			BoardVo bv = new BoardVo();
			bv = new BoardVo();
			bv.setBoard_no(rs.getInt("board_no"));
			bv.setTitle(rs.getString("board_title"));
			bv.setContent(rs.getString("board_content"));
			bv.setWriter(rs.getString("board_writer"));
			bv.setCnt(rs.getInt("board_cnt"));
			bv.setDate(rs.getString("board_date"));
			
			list.add(bv);
		}
		
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		
		return list;
	}
	
	
	public List<BoardVo> getSearchBoard(Connection conn, String boardTitle) throws SQLException{
		String sql = "select board_no, board_title, board_writer,"
				+ " to_char(board_date, 'YYYY-MM-DD') as board_date, board_cnt,"
				+ " board_content"
				+ " from jdbc_board where board_title like '%' || ? || '%' "
				+ " order by board_no desc";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardTitle);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<BoardVo> list = new ArrayList<BoardVo>();
		while(rs.next()) {
			BoardVo bv = new BoardVo();
			bv = new BoardVo();
			bv.setBoard_no(rs.getInt("board_no"));
			bv.setTitle(rs.getString("board_title"));
			bv.setContent(rs.getString("board_content"));
			bv.setWriter(rs.getString("board_writer"));
			bv.setCnt(rs.getInt("board_cnt"));
			bv.setDate(rs.getString("board_date"));
			
			list.add(bv);
		}
		
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		return list;
	}
	
	
	public int setCountincrement(Connection conn, int boardNo) throws SQLException {
		String sql = "update jdbc_board set"
				+ " board_cnt = board_cnt + 1"
				+ " where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
			
		return cnt;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
