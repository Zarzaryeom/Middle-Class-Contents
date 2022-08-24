package kr.or.ddit.borad.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBoardDao {
	
	/**
	 * 게시글을 수정하는 메서드
	 * @param con
	 * @param mapBoard
	 * @return
	 * @throws SQLException
	 */
	public int insertBoard(Connection con, Map<String, Object> mapBoard) throws SQLException;
	
	/**
	 * 게시글을 삭제하는 메서드
	 * @param con
	 * @param mapBoard
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(Connection con, Map<String, Object> mapBoard) throws SQLException;
	
	/**
	 * 전체 게시글을 불러오는 메서드
	 * @param con
	 * @param mapBoard
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getAllBoard(Connection con) throws SQLException;
	
	/**
	 * 게시글 하나를 읽어오는 메서드
	 * @param con
	 * @param mapBoard
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getReadBoard(Connection con, Map<String, Object> mapBoard) throws SQLException;
	
	/**
	 * 게시글을 생성하는 메서드
	 * @param con
	 * @param mapBoard
	 * @return
	 * @throws SQLException
	 */
	public int creatBoard(Connection con, Map<String, Object> mapBoard) throws SQLException;
	
	/**
	 * 조회수 증가 메서드
	 * @param con
	 * @param num
	 * @return
	 * @throws SQLException
	 */
	public int cntBoard(Connection con, int num) throws SQLException;
	
	/**
	 * 검색 메서드
	 * @param con
	 * @param boardName
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> searchBoard(Connection con, String boardName) throws SQLException;
}
