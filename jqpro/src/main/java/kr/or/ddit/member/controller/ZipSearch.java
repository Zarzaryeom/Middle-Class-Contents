package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.ZipVO;

/**
 * Servlet implementation class ZipSearch
 */
@WebServlet("/ZipSearch.do")
public class ZipSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전송데이터 받기
			String dongValue = (String) request.getParameter("dongValue");
		
		// service객체 얻기
			IMemberService service = MemberServiceImpl.getInstance();
		
		// service메서드 호출하기 - 결과값 받기
			List<ZipVO> list = service.zipList(dongValue);
					
		// json데이터 만들기
			Gson gs = new Gson();
			String result = gs.toJson(list);
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.print(result);
			out.flush();
			
	}

}
