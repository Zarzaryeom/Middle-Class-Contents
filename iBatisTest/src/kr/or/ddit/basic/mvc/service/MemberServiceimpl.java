package kr.or.ddit.basic.mvc.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.dao.IMemberDao;
import kr.or.ddit.basic.mvc.dao.MemberDaoImpl;
import kr.or.ddit.basic.mvc.vo.MemberVo;
import kr.or.ddit.util.SqlMapClientFactory;

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
	public Object insertMember(MemberVo memVo) {
		Object obj = null;
		SqlMapClient smc = null;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			obj = dao.insertMember(smc, memVo);
			
		} catch (SQLException e) {
			obj = null;
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		SqlMapClient smc = null;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.deleteMember(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVo memVo) {
		int cnt = 0;
		SqlMapClient smc = null;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.updateMember(smc, memVo);
			
		} catch (SQLException e) {
		}
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember() {
		SqlMapClient smc = null;
		List<MemberVo> list = new ArrayList<MemberVo>();
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			list = dao.getAllMember(smc);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public int getMemberCount(String memId) {
		SqlMapClient smc = null;
		int cnt = 0;
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.getMemberCount(smc, memId);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember2(String updateData, String updateField, String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember3(Map<String, String> paramMap) {
		int cnt = 0;
		try {
			SqlMapClient smc = SqlMapClientFactory.getSqlMapClient();
			cnt = dao.updateMember3(smc, paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	

}
