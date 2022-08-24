package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public class MemberServiceImpl implements IMemberService {

	//dao에 접근 - dao객체가 필요
	//service객체 생성 - controller가 사용
	
	private IMemberDao dao;
	private static IMemberService service;
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	public static IMemberService getInstance() {
		if(service == null) service = new MemberServiceImpl();
		
		return service;
	}
	
	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = null;
		try {
			list = dao.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public String checkId(String mem_id) {
		return dao.checkId(mem_id);
	}
	@Override
	public List<ZipVO> zipList(String zipName) {
		return dao.zipList(zipName);
	}
	@Override
	public String insertMember(MemberVO vo) {
		return dao.insertMember(vo);
	}

}
