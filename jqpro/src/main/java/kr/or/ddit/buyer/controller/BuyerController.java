package kr.or.ddit.buyer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;

/**
 * Servlet implementation class BuyerController
 */
@WebServlet("/BuyerController.do")
public class BuyerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// selectByName 실행
		
		// 1. 전송시 데이타 받기
		// 이 메서드에선 없음
		// 2. service 객체 얻기
		IBuyerService service = BuyerServiceImpl.getInstance();
		// 3. service 메서드 호출하기 - 결과값 반환
		List<BuyerVO> list = service.selectByName();
		
		// 4-1. request에 결과값을 저장(파라미터 : 이름, 값)
		request.setAttribute("buyerSelect", list);
		// 4-2. jsp로 위임(forward)하여 출력 또는 응답데이터 생성하기(출력 또는 json데이터)
		request.getRequestDispatcher("0330/buyerList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// idByDetail 실행
		
		// 1. 전송시 데이타 받기
		String buyerId = request.getParameter("id");
		// 2. service 객체 얻기
		IBuyerService service = BuyerServiceImpl.getInstance();
		// 3. service 메서드 호출하기 - 결과값 반환
		BuyerVO vo = service.idByDetail(buyerId);
		// 4-1. request에 결과값을 저장(파라미터 : 이름, 값)
		request.setAttribute("buyerDetail", vo);
		// 4-2. jsp로 위임(forward)하여 출력 또는 응답데이터 생성하기(출력 또는 json데이터)
		request.getRequestDispatcher("0330/detail.jsp").forward(request, response);
	}

}
