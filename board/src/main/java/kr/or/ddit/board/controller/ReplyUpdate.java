package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.board.vo.ReplyVO;

@WebServlet("/replyUpdate.do")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int renum = Integer.parseInt(request.getParameter("renum"));
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setRenum(renum);
		vo.setCont(cont);
		
		IReplyService service = ReplyServiceImpl.getInstance();
		
		int res = service.updateReply(vo);
		
		request.setAttribute("delete", res);
		
		request.getRequestDispatcher("work/result.jsp").forward(request, response);
				
	}

}
