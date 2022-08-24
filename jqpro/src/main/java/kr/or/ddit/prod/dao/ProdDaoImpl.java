package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdDaoImpl implements IProdDao {
	
	private static IProdDao dao;
	private SqlMapClient smc;
	private ProdDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	public static IProdDao getInstance() {
		if(dao == null) dao = new ProdDaoImpl();
		
		return dao;
	}

	@Override
	public List<ProdVO> selectByLgu(String lgu) {
		List<ProdVO> list = null;
		try {
			list = smc.queryForList("prod.selectByLgu", lgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public ProdVO selectById(String Id) {
		ProdVO vo = null;
		try {
			vo = (ProdVO) smc.queryForObject("prod.selectById", Id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

}
