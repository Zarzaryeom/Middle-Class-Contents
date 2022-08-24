package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

// mapper 접근 - SqlMapClient객체가필요
// dao클래스 객체 생성 - 리턴 - service에서 사용

public class MemberDaoImpl implements IMemberDao {

	private SqlMapClient smc;
	private static IMemberDao dao; // 다형성을 위해 인터페이스를 타입으로 지정
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IMemberDao getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<MemberVO> selectAll() throws SQLException {
		return smc.queryForList("member.selectAll");
	}

	// 아이디 중복검사
	@Override
	public String checkId(String mem_id) {
		String name = null;
		try {
			name = (String) smc.queryForObject("member.checkId", mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	// 우편번호 리스트
	@Override
	public List<ZipVO> zipList(String zipName) {
		List<ZipVO> zv = null;
		try {
			zv = smc.queryForList("zip.zipList", zipName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return zv;
	}

	// 새 멤버 추가
	@Override
	public String insertMember(MemberVO vo) {
		String memId = null;
		try {
			memId = (String) smc.insert("member.insertMember", vo); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memId;
	}

}
