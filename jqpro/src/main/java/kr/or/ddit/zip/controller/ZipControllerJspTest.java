package kr.or.ddit.zip.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.zip.service.IZipService;
import kr.or.ddit.zip.service.ZipServiceImpl;

/**
 * Servlet implementation class ZipControllerJspTest
 */
@WebServlet("/ZipControllerJspTest.do")
public class ZipControllerJspTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipControllerJspTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int input = Integer.parseInt(request.getParameter("check"));
		
		IZipService service = ZipServiceImpl.getInstance();
		
		switch(input) {
		case 0: proc(request, response, service); break;
		case 1: proc1(request, response, service); break;
		case 2: proc2(request, response, service); break;
		case 3: proc3(request, response, service); break;
		}
		
	}

	private void proc3(HttpServletRequest request, HttpServletResponse response, IZipService service) {
		
	}

	private void proc2(HttpServletRequest request, HttpServletResponse response, IZipService service) {
		
	}

	private void proc1(HttpServletRequest request, HttpServletResponse response, IZipService service) {
		
	}

	private void proc(HttpServletRequest request, HttpServletResponse response, IZipService service) {
		
		List<String> list = service.selectSido();
		
		
		
	}

}
