package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.ReplyVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class ReplyDaoImpl implements IReplyDao {

	private SqlMapClient sc;
	private static IReplyDao dao;
	// 생성자 = client객체 얻어오기
	private ReplyDaoImpl() {
		sc = SqlMapClientFactory.getSqlMapClient();
	}
	
	// getInstance()메서드 - dao객체 생성하고 리턴
	public static IReplyDao getInstance() {
		if(dao == null) dao = new ReplyDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public int updateReply(ReplyVO vo) throws SQLException {
		return (int) sc.update("reply.updateReply", vo);
	}

	@Override
	public int deleteReply(int reply) throws SQLException {
		return (int) sc.delete("reply.deleteReply", reply);
	}

	@Override
	public List<ReplyVO> replyList(int bonum) throws SQLException {
		return (List<ReplyVO>) sc.queryForList("reply.replyList", bonum);
	}

	@Override
	public int insertReply(ReplyVO vo) throws SQLException {
		return (int) sc.insert("reply.insertReply", vo);
	}

}
