package kr.or.ddit.zip.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.ZipVO;

public interface IZipDao {

	// 시 데이터 뽑기 
	public List<String> selectSido();
	
	// 선택한 시에 해당하는 구,군 데이터 뽑기 
	public List<String> selectGugun(String sido);
	
	// 선택한 시, 구, 군에 해당하는 데이터 뽑기
	public List<String> selectDong(Map<String, String> map);
	
	// 선택한 데이터들에 해당하는 전체 지역 뽑기
	public List<ZipVO> selectResult(Map<String, String> map);

}
