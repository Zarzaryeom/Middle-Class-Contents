package kr.or.ddit.buyer.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class BuyerDaoImpl implements IBuyerDao {
	
	private SqlMapClient smc;
	private static IBuyerDao dao;
	
	private BuyerDaoImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static IBuyerDao getInstance() {
		if(dao == null) dao = new BuyerDaoImpl();
		
		return dao;
	}

	@Override
	public List<BuyerVO> selectByName() {
		List<BuyerVO> list = null;
		try {
			list = smc.queryForList("buyer.selectByName");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public BuyerVO idByDetail(String buerId) {
		BuyerVO vo = null;
		try {
			vo = (BuyerVO)smc.queryForObject("buyer.idByDetail", buerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	
}
