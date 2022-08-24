<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String checkId = (String) request.getAttribute("check");
	
	if(checkId == null){
		// 사용 가능
%>
	{
		"flag" : "사용가능한 아이디입니다."	
	}
<% 
	}else {
		// 사용 불가
%>
	{
		"flag" : "사용이 불가능한 아이디입니다."
	}
<%
	}
%>

