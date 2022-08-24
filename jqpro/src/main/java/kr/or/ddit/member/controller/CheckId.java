package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.member.vo.ZipVO;

/**
 * Servlet implementation class CheckId
 */
@WebServlet("/CheckId.do")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckId() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 전송 데이터 받기
		String memId = request.getParameter("id");
		
		// 2. service객체 생성
		IMemberService service = MemberServiceImpl.getInstance();
		
		// 3. service메서드 호출 - 결과값 받기(mem_id)
		String id = service.checkId(memId);
		// 결과값은 a001 -> a001, k123 -> null
		
		// 4. request에 저장
		request.setAttribute("check", id);
		
		// 5. jsp로 forward
		request.getRequestDispatcher("member/idcheck.jsp").forward(request, response);
		
		
	}

}
