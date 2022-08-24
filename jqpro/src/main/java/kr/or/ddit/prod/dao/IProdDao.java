package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.ProdVO;

public interface IProdDao {
	
	public List<ProdVO> selectByLgu(String lgu);
	
	public ProdVO selectById(String Id);
}
