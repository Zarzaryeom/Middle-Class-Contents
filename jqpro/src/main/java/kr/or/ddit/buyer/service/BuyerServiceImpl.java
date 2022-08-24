package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.dao.BuyerDaoImpl;
import kr.or.ddit.buyer.dao.IBuyerDao;
import kr.or.ddit.buyer.vo.BuyerVO;

public class BuyerServiceImpl implements IBuyerService {
	
	private IBuyerDao dao;
	private static IBuyerService service;
	private BuyerServiceImpl() {
		dao = BuyerDaoImpl.getInstance();
	}
	
	public static IBuyerService getInstance() {
		if(service == null) service = new BuyerServiceImpl();
		
		return service;
	}
	
	@Override
	public List<BuyerVO> selectByName() {
		List<BuyerVO> list = dao.selectByName();
		return list;
	}

	@Override
	public BuyerVO idByDetail(String buyerId) {
		BuyerVO vo = dao.idByDetail(buyerId);
		return vo;
	}

}
