package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;

@WebServlet("/replyDelete.do")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int renum = Integer.parseInt(request.getParameter("renum"));
		
		IReplyService service = ReplyServiceImpl.getInstance();
		
		int res = service.deleteReply(renum);
		
		request.setAttribute("delete", res);
		
		request.getRequestDispatcher("work/result.jsp")
			   .forward(request, response);
	}

}
