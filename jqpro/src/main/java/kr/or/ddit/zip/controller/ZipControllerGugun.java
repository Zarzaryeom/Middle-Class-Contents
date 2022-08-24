package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.member.vo.ZipVO;
import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.service.ZipServiceImpl;

/**
 * Servlet implementation class ZipControllerGugun
 */
@WebServlet("/ZipControllerGugun.do")
public class ZipControllerGugun extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipControllerGugun() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String gugun = request.getParameter("dataS");
		
		IZipService service = ZipServiceImpl.getInstance();
		
		List<String> list = service.selectGugun(gugun);
		
		Gson gs = new Gson();
		String result = gs.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // post형식일때는 반드시 추가해줘야 함.(get은 생략)
		
		String sido = request.getParameter("dataS");
		String gugun = request.getParameter("dataG");
		String dong = request.getParameter("dataD");
		
		IZipService service = ZipServiceImpl.getInstance();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		map.put("dong", dong);
		
		
		List<ZipVO> vo = service.selectResult(map);
		
		System.out.println(vo);
		
		Gson gs = new Gson();
		String result = gs.toJson(vo);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
		
	}

}
