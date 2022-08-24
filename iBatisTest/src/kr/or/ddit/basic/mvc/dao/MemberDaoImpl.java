package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.mvc.vo.MemberVo;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao { //인터페이스를 구현
	
	// 싱글톤
	private static MemberDaoImpl mdi;
	private MemberDaoImpl() { }
	public static MemberDaoImpl getInstance() {
		if(mdi == null) mdi = new MemberDaoImpl();
		
		return mdi;
	}
	@Override
	public Object insertMember(SqlMapClient smc, MemberVo memVo) throws SQLException {
		
		Object obj = smc.insert("member.insertMember", memVo);
		
		return obj;
	}
	
	// 반환값을 int로 유지할 시 insert문
//	public int insertMember2(SqlMapClient smc, MemberVo memVo) throws SQLException {
//		int cnt = 0;
//		Object obj = smc.insert("member.insertMember", memVo);
//		
//		if(obj == null) cnt = 1;
//		
//		return cnt;
//	}
	
	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
		int cnt = smc.delete("member.deleteMember", memId);
		return cnt;
	}
	
	@Override
	public int updateMember(SqlMapClient smc, MemberVo memVo) throws SQLException {
		int cnt = smc.update("member.updateMember", memVo);
		return cnt;
	}
	
	@Override
	public List<MemberVo> getAllMember(SqlMapClient smc) throws SQLException {
		List<MemberVo> list = smc.queryForList("member.getAllMember");
		
		return list;
	}
	@Override
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException {
		int cnt = (int) smc.queryForObject("member.getMemberCount", memId);
		
		return cnt;
	}
	@Override
	public int updateMember2(SqlMapClient smc, String updateData, String updateField, String memId)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updateMember3(SqlMapClient smc, Map<String, String> paramMap) throws SQLException {
		return smc.update("member.updateMember3", paramMap);
	}
	
	

	

}
