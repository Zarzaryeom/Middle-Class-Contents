package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVo;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceimpl implements IMemberService {
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
			cnt = dao.insertMember(conn, memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.deleteMember(conn, memId);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo memVo) {
		Connection conn = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			cnt = dao.updateMember(conn, memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember() {
		Connection conn = null;
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			conn = DBUtil3.getConnection();
			
			list = dao.getAllMember(conn);
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(conn != null) try {conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			count = dao.getMemberCount(conn, memId);
		} catch (Exception e) {
			count = 0;
			e.printStackTrace();
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
			cnt = dao.updateMember3(conn, paramMap);
				
					
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();} catch(SQLException e) {}
		}
		
		return cnt;
	}

}
