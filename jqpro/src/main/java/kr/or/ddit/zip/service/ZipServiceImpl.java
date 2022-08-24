package kr.or.ddit.zip.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.ZipVO;
import kr.or.ddit.zip.dao.IZipDao;
import kr.or.ddit.zip.dao.ZipDaoImpl;

public class ZipServiceImpl implements IZipService {
	
	private IZipDao dao;
	private static IZipService service;
	private ZipServiceImpl() {
		dao = ZipDaoImpl.getInstance();
	}
	public static IZipService getInstance() {
		if(service == null) service = new ZipServiceImpl();
		return service;
	}

	@Override
	public List<String> selectSido() {
		return dao.selectSido();
	}

	@Override
	public List<String> selectGugun(String sido) {
		return dao.selectGugun(sido);
	}

	@Override
	public List<String> selectDong(Map<String, String> map) {
		return dao.selectDong(map);
	}

	@Override
	public List<ZipVO> selectResult(Map<String, String> map) {
		return dao.selectResult(map);
	}

}
