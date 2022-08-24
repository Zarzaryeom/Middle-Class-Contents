package kr.or.ddit.lprod.dao;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	
	private SqlMapClient smc;
	private static ILprodDao dao;
	
	private LprodDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static ILprodDao getInstance() {
		if(dao == null) dao = new LprodDaoImpl();
		
		return dao;
	}

	@Override
	public List<LprodVO> selectAll() {
		
		List<LprodVO> list = null;
		try {
			list = smc.queryForList("lprod.selectAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
