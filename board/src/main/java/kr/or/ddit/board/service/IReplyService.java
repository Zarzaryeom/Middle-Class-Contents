package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.ReplyVO;

public interface IReplyService {

	// 댓글 수정 - cont, redate
	public int updateReply(ReplyVO vo);
	
	// 댓글 삭제
	public int deleteReply(int reply);
	
	// 댓글 리스트
	public List<ReplyVO> replyList(int bonum);
	
	// 댓글 저장
	public int insertReply(ReplyVO vo);
	
}
