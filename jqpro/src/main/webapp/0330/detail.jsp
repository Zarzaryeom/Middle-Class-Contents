<%@page import="kr.or.ddit.buyer.vo.BuyerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BuyerVO vo1 = (BuyerVO)request.getAttribute("buyerDetail");
%>


	{
		"id" : "<%= vo1.getBuyer_id() %>",
		"name" : "<%= vo1.getBuyer_name() %>",
		"lgu" : "<%= vo1.getBuyer_lgu() %>",
		"bank" : "<%= vo1.getBuyer_bank() %>",
		"bankno" : "<%= vo1.getBuyer_bankno() %>",
		"bankname" : "<%= vo1.getBuyer_bankname() %>",
		"zip" : "<%= vo1.getBuyer_zip() %>",
		"add1" : "<%= vo1.getBuyer_add1() %>",
		"add2" : "<%= vo1.getBuyer_add2() %>",
		"mail" : "<%= vo1.getBuyer_mail() %>"
	}
