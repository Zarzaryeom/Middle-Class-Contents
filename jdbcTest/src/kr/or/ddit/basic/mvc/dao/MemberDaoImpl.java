package kr.or.ddit.basic.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.basic.mvc.vo.MemberVo;

public class MemberDaoImpl implements IMemberDao { //인터페이스를 구현
	
	// 싱글톤
	private static MemberDaoImpl mdi;
	private MemberDaoImpl() { }
	public static MemberDaoImpl getInstance() {
		if(mdi == null) mdi = new MemberDaoImpl();
		
		return mdi;
	}
	
	

	@Override
	public int insertMember(Connection conn, MemberVo memVo) throws SQLException {
		String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
				+ "     values(?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_id());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_name());
		pstmt.setString(4, memVo.getMem_tel());
		pstmt.setString(5, memVo.getMem_addr());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		String sql = "delete from mymember where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateMember(Connection conn, MemberVo memVo) throws SQLException {
		String sql = "update mymember "
				+ "      set mem_pass = ?,"
				+ "          mem_name = ?,"
				+ "			 mem_tel = ?,"
				+ "			 mem_addr = ?"
				+ "    where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_pass());
		pstmt.setString(2, memVo.getMem_name());
		pstmt.setString(3, memVo.getMem_tel());
		pstmt.setString(4, memVo.getMem_addr());
		pstmt.setString(5, memVo.getMem_id());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public List<MemberVo> getAllMember(Connection conn) throws SQLException {
		String sql = "select * from mymember";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<MemberVo> list = new ArrayList<MemberVo>(); // 반환값이 저장될 변수

		while(rs.next()) {
			MemberVo memVo = new MemberVo();  // 1개의 레코드가 저장될 변수
			
			memVo.setMem_id(rs.getString("mem_id"));
			memVo.setMem_pass(rs.getString("mem_pass"));
			memVo.setMem_name(rs.getString("mem_name"));
			memVo.setMem_tel(rs.getString("mem_tel"));
			memVo.setMem_addr(rs.getString("mem_addr"));
			
			list.add(memVo);
		}
		
		return list;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "select count(*) cnt from mymember "
				+ "    where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		if(rs.next()) {
			count = rs.getInt("cnt");
		}
		
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		
		
		return count;
	}

	@Override
	public int updateMember2(Connection conn, String updateData, String updateField, String memId) throws SQLException {
		String sql = "update mymember"
				+ "      set  " + updateField + " = ?"
				+ "    where mem_id = ?";
		
		System.out.println(updateData + "\n" + updateField + "\n" + memId);
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, updateData);
		pstmt.setString(2, memId);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int updateMember3(Connection conn, Map<String, String> paramMap) throws SQLException {
		// key값 정보 ==> 회원ID(memid), 수정할 컬럼명(field), 수정할 데이터(data)
		String sql = "update mymember set "
				+ paramMap.get("field") + " = ?"
				+ " where mem_id = ?";
		
		PreparedStatement pstmt =conn.prepareStatement(sql);
		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memid"));
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt != null) pstmt.close();
		
		return cnt;
	}



}
