package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.IReplyDao;
import kr.or.ddit.board.dao.ReplyDaoImpl;
import kr.or.ddit.board.vo.ReplyVO;

public class ReplyServiceImpl implements IReplyService {

	private static IReplyService service;
	private IReplyDao dao;
	
	private ReplyServiceImpl() {
		dao = ReplyDaoImpl.getInstance();
	}
	
	public static IReplyService getInstance() {
		if(service == null) service = new ReplyServiceImpl();
		
		return service;
	}
	
	@Override
	public int updateReply(ReplyVO vo) {
		int num = 0;
		try {
			num = dao.updateReply(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	@Override
	public int deleteReply(int reply) {
		int num = 0;
		try {
			num = dao.deleteReply(reply);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return num;
	}

	@Override
	public List<ReplyVO> replyList(int bonum) {
		List<ReplyVO> list = null;
		try {
			list = dao.replyList(bonum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertReply(ReplyVO vo) {
		int num = 0;
		try {
			num = dao.insertReply(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
 