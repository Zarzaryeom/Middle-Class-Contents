package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

public interface IMemberService {
	
	public List<MemberVO> selectAll();
	
	// 중복검사 메서드
	public String checkId(String mem_id);
	// 우편번호 검색 메서드
	public List<ZipVO> zipList(String zipName);
	// 저장하기
	public String insertMember(MemberVO vo);
}
