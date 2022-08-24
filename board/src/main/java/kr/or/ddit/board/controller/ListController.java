package kr.or.ddit.board.controller;

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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/listController.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 public ListController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		System.out.println("서블릿 시작");
		
		// 1. 요청시 전송데이터 받기 - page번호, stype, sword
		int rqpage = Integer.parseInt(request.getParameter("page"));
		String rqtype = request.getParameter("stype");
		String rqword = request.getParameter("sword");
		
//		System.out.println("page" + rqpage + " " + rqtype + " " + rqword);
		
		// 2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		//page관련 작업 - 전체 글 갯수, 총 페이지수, 한페이지당 출력할 글 갯수, 한 화면에 출력할 페이지 갯수
		
		Map<String, Object> pmap = service.getPageInfo(rqpage, rqtype, rqword);
		//pmap : start, end, startPage, endPage, totalPage가 들어 있다.
		
		
		// parameter Map 생성 - selectList 수행하기 위해서
		Map<String, Object> map =new HashMap<String, Object>();
		
		int startValue = (int)pmap.get("start");
		int endValue = (int)pmap.get("end");
		
//		System.out.println(startValue + " " + endValue);
		
		map.put("start", startValue);
		map.put("end", endValue);
		map.put("stype", rqtype);
		map.put("sword", rqword);
		
		// 3. service메서드 호출 하기 - 결과값 받기
		List<BoardVO> list = service.selectList(map);
		
//		System.out.println(list);
//		System.out.println(list.get(0).getContent());
		
		JsonObject obj = new JsonObject();
		obj.addProperty("totalp", (Integer) pmap.get("totalPage"));
		obj.addProperty("startp", (Integer) pmap.get("startPage"));
		obj.addProperty("endp", (Integer) pmap.get("endPage"));
		
		
		// 4. 결과값으로 응답데이터 생성 - html, text, xml, json데이터 가능
		Gson gs = new Gson();
		
		JsonElement ele = gs.toJsonTree(list);
		
		obj.add("datas", ele);
		
//		String result = gs.toJson(list);
		
		out.print(obj);
		out.flush();
//		
		System.out.println("서블릿 끝");
	}
	
}
