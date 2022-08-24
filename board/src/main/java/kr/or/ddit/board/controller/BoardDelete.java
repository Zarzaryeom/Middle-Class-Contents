package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/boardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("application/json; charset=utf-8");
//		PrintWriter out = response.getWriter();
		
		
		int rqnum = Integer.parseInt(request.getParameter("num"));
		int page = Integer.parseInt(request.getParameter("page"));
		String rqtype = request.getParameter("type");
		String rqword = request.getParameter("word");
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		int num = service.deleteBoard(rqnum);
		
		// totalPage를 다시 구해서 마지막 페이지가 남아 있는지 확인
		Map<String, Object> map = service.getPageInfo(page, rqtype, rqword);

		// request에 공유 데이터 저장
		request.setAttribute("num", num);
		request.setAttribute("totalPage", map.get("totalPage"));
		
		// jsp로 forword - joson 생성
		request.getRequestDispatcher("work/deleteboard.jsp").forward(request, response);
			
	}

}
