package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
 * Servlet implementation class ZipControllerSum
 */
@WebServlet("/ZipControllerSum.do")
public class ZipControllerSum extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipControllerSum() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int check = Integer.parseInt(request.getParameter("check"));
		
		IZipService service = ZipServiceImpl.getInstance();
		
		switch(check) {
		case 0: proc(request, response, service); break;
		case 1: proc1(request, response, service); break;
		case 2: proc2(request, response, service); break;
		case 3: proc3(request, response, service); break;		
		}
		
	}

	private void proc3(HttpServletRequest request, HttpServletResponse response, IZipService service) throws ServletException, IOException {
		
		String sido = request.getParameter("dataS");
		String gugun = request.getParameter("dataG");
		String dong = request.getParameter("dataD");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		map.put("dong", dong);
		
		
		List<ZipVO> vo = service.selectResult(map);
		
		Gson gs = new Gson();
		String result = gs.toJson(vo);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
	}

	private void proc2(HttpServletRequest request, HttpServletResponse response, IZipService service) throws ServletException, IOException {
		String sido = request.getParameter("dataS");
		String gugun = request.getParameter("dataG");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sido", sido);
		map.put("gugun", gugun);
		
		List<String> list = service.selectDong(map);
		
		Set<String> list2 = new HashSet<String>();		
		for(int i = 0; i < list.size(); i++) {
			String dv = list.get(i);
			String[] arr = dv.split(" ");
			list2.add(arr[0]);
		}
		
		Gson gs = new Gson();
		String result = gs.toJson(list2);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
	}

	private void proc1(HttpServletRequest request, HttpServletResponse response, IZipService service) throws ServletException, IOException {
		String gugun = request.getParameter("dataS");
		
		List<String> list = service.selectGugun(gugun);
		
		Gson gs = new Gson();
		String result = gs.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
	}

	private void proc(HttpServletRequest request, HttpServletResponse response, IZipService service) throws ServletException, IOException {
		List<String> list = service.selectSido();
		
		Gson gs = new Gson();
		String result = gs.toJson(list);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
	}
}


