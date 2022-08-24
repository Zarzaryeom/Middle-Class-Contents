<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String res = (String) request.getAttribute("servlet");
	// String memId = (String) request.getAttribute("memId");
	// memId를 사용한다면 --> if(res.equals(memId))
	
	if(res != null){
%>
	{
		"flag" : "<%= res %>님 가입을 축하합니다!"
	}		

<%	}else{ %>

	{
		"flag" : "가입에 실패하셨습니다..."	
	}
	
<%	}
%>
