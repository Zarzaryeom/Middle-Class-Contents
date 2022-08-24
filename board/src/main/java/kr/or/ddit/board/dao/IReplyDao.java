package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.ReplyVO;

public interface IReplyDao {

	// 댓글 수정 - cont, redate
		public int updateReply(ReplyVO vo) throws SQLException;
		
		// 댓글 삭제
		public int deleteReply(int reply) throws SQLException;
		
		// 댓글 리스트
		public List<ReplyVO> replyList(int bonum) throws SQLException;
		
		// 댓글 저장
		public int insertReply(ReplyVO vo) throws SQLException;
		
}
