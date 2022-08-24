package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVo;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceimpl implements IMemberService {
	private static final Logger logger = Logger.getLogger(MemberServiceimpl.class);
	private IMemberDao dao;
	// 싱글톤
	private static MemberServiceimpl msi;
	
	// 생성자
	private MemberServiceimpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceimpl getInstance() {
		if(msi == null) msi = new MemberServiceimpl();
		
		return msi;
	}
	
	@Override
	public int insertMember(MemberVo memVo) {
		Connection conn = null;
		int cnt = 0; // 반환값 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성...");
			cnt = dao.insertMember(conn, memVo);
			logger.info("insert작업 성공!!");
		} catch (SQLException e) {
			logger.error("insert작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납...");
			} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			cnt = dao.deleteMember(conn, memId);
			logger.info("delete작업 성공!!");
			
		} catch (SQLException e) {
			logger.error("delete작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납..");
			} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo memVo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성");
			cnt = dao.updateMember(conn, memVo);
			logger.info("update작업 성공!");
		} catch (SQLException e) {
			logger.error("update작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납..");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember() {
		Connection conn = null;
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			conn = DBUtil3.getConnection();
			logger.trace("Connect 객체 생성");			
			list = dao.getAllMember(conn);
			logger.info("전체 출력 작업 성공!");
			
		} catch (SQLException e) {
			logger.error("전체 출력 작업 오류.. : " + e);
			list = null;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납..");
			} catch(SQLException e) {}
		}
		
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			logger.trace("Connection객체 생성");
			count = dao.getMemberCount(conn, memId);
			logger.info("회원 숫자 세기 작업 성공!");
		} catch (Exception e) {
			logger.error("회원 숫자 세기 작업 오류 : " + e);
			count = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납..");
			} catch(SQLException e) {}
		}
		return count;
	}

	@Override
	public int updateMember2(String updateData, String updateField, String memId) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			count = dao.updateMember2(conn, updateData, updateField, memId);
			
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateMember3(Map<String, String> paramMap) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			logger.trace("Connection객체 생성");
			cnt = dao.updateMember3(conn, paramMap);
			logger.info("부분 수정 작업 성공!");
				
					
		} catch (SQLException e) {
			logger.error("부분 수정 작업 오류 : " + e);
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {
				conn.close();
				logger.info("Connection객체 반납..");
			} catch(SQLException e) {}
		}
		
		return cnt;
	}

}
