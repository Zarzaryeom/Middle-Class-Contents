package kr.or.ddit.borad.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IBoardService {
		

	public int insertBoard(Map<String, Object> mapBoard);
	

	public int deleteBoard(Map<String, Object> mapBoard);
	

	public List<Map<String, Object>> getAllBoardService();
	

	public Map<String, Object> getReadBoard(Map<String, Object> mapBoard);
	
	
	public int creatBoard(Map<String, Object> mapBoard);
	
	public int cntBoard(int num);
	
	public List<Map<String, Object>> searchBoard(String boardName);
}
