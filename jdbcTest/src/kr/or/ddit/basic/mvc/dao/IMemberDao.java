package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVo;

/**
 * 실제 DB와 연결해서 SQL문을 수행하고 결과를 작성해서 Service에 전달하는 Dao의 interface
 * 
 * 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.
 * 
 * @author PC-09
 *
 */
public interface IMemberDao {
	/**
	 * MemberVo에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param conn java.sql.Connerction 객체
	 * @param memVo DB에 insert할 자료가 저장된 MemberVo객체
	 * @return insert작업 성공 : 1, insert 작업 실패 : 0
	 * @throws SQLException
	 */
	public int insertMember(Connection conn, MemberVo memVo) throws SQLException;
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param conn Connection객체
	 * @param memId 삭제할 회원ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int deleteMember(Connection conn, String memId) throws SQLException;
	
	/**
	 * MemberVo자료를 이용하여 DB에 updqte하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param memVo update할 회원 정보가 저장된 MemberVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember(Connection conn, MemberVo memVo) throws SQLException;
	
	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아 반환하는 메서드
	 * @param conn Connection객체
	 * @return MemberVo객체가 저장된 List
	 * @throws SQLException
	 */
	public List<MemberVo> getAllMember(Connection conn) throws SQLException;
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param conn Connection 객체
	 * @param memId 검색할 회 ID
	 * @return 검색된 회원ID의 개수
	 * @throws SQLException
	 */
	public int getMemberCount(Connection conn, String memId) throws SQLException;
	
	/**	 * 
	 * updateData, updataField, memId를 이용하여 DB에 원하는 정보만 update하는 메서드
	 * @param conn Connection객체
	 * @param updateData update할 회원 정보가 저장된 String
	 * @param updateField update할 컬럼 정보가 저장된 String
	 * @param memId update할 회원의 ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember2(Connection conn, String updateData, String updateField, String memId) throws SQLException;
	
	// Map으로 변수를 넘기는 방법
	/**
	 * Map의 정보를 이용하여 회원정보 중 원하는 컬럼을 수정하는 메서드
	 * 		key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data) 
	 * @param Connection Connection객체
	 * @param paramMap 수정할 회원ID, 수정할 컬럼명, 수정할 데이터가 저장될 Map
	 * @return
	 */
	public int updateMember3(Connection conn, Map<String, String> paramMap) throws SQLException;
	
	
	
	
	
	
	
}
