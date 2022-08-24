package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdController
 */
@WebServlet("/ProdController.do")
public class ProdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//lprod를 선택시 prod id와 name을 select 한다.
		
		//전송시(요청시) 데이터 받기
		String lgu = request.getParameter("lgu");
		
		//service객체 얻기
		IProdService service = ProdServiceImpl.getInstance();
		//service메서드 호출 - 결과를 얻는다.
		List<ProdVO> list = service.selectByLgu(lgu);
		
		//gson 라이브러리 사용
		Gson gson = new Gson();
		String result = gson.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
		
//		request.setAttribute("prodValue", list);
//		
//		request.getRequestDispatcher("0330/prodList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		IProdService service = ProdServiceImpl.getInstance();
		
		ProdVO vo = service.selectById(id);
		
		//Gson 라이브러리 사용
		Gson gson = new Gson();
		String result = gson.toJson(vo);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
//		request.setAttribute("prodValue2", vo);
//		
//		request.getRequestDispatcher("0330/prodId.jsp").forward(request, response);
				
	}

}
