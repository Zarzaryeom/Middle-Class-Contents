package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.jdt.internal.compiler.ast.MemberValuePair;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert.do")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // post형식일때는 반드시 추가해줘야 함.(get은 생략)
		
		// 1. 전송데이터 받기
		MemberVO vo = new MemberVO();
		/*
		vo.setMem_id(request.getParameter("mem_id"));
		vo.setMem_name(request.getParameter("mem_name"));
						...
		*/
		
		// 라이브러리를 사용하여 위에 처럼 변수를 모두 적을 필요없이 간단하게 처리한다.
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 2. service객체 얻기
		IMemberService service = MemberServiceImpl.getInstance(); 
				
		// 3. service메서드 호출하기
		String res = service.insertMember(vo);
		
		// 4. 결과값을 request에 저장하기
		request.setAttribute("servlet", res);
		//request.setAttribute("memId", vo.getMem_id());
		
		// 5. jsp로 forward
		request.getRequestDispatcher("member/insert.jsp").forward(request, response);
		
		
	}

}
