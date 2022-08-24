<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BuyerVO> list = (List<BuyerVO>)request.getAttribute("buyerSelect");
	//BuyerVO vo1 = (BuyerVO)request.getAttribute("buyerDetail");
%>

<%-- jsp주석은 %을 활용해서 넣어야 한다. --%>
<%-- buyer 테이블의 전체 이름을 받아오는 jsp --%>
[
<%
	for(int i = 0; i < list.size(); i++){
		BuyerVO vo = list.get(i);
		if(i > 0) out.print(",");
	%>	
		{
		"id" : "<%= vo.getBuyer_id() %>",
		"name" : "<%= vo.getBuyer_name() %>"
		}
	<%
	}
%>
]

<%-- 상세 정보를 얻는 jsp --%>
<%--
[
	{
		"id" : "<%= vo1.getBuyer_id() %>",
		"name" : "<%= vo1.getBuyer_name() %>"
		"lgu" : "<%= vo1.getBuyer_lgu() %>",
		"bank" : "<%= vo1.getBuyer_bank() %>",
		"bankno" : "<%= vo1.getBuyer_bankno() %>",
		"bankname" : "<%= vo1.getBuyer_bankname() %>",
		"zip" : "<%= vo1.getBuyer_zip() %>",
		"add1" : "<%= vo1.getBuyer_add1() %>",
		"add2" : "<%= vo1.getBuyer_add2() %>",
		"mail" : "<%= vo1.getBuyer_mail() %>"
	}
]
--%>
