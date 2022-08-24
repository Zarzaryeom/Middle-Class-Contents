<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 서블릿에서 공유 데이터 꺼내기

  int res = (int) request.getAttribute("delete");
  if(res > 0){
%>
	{
		"sw" : "성공"
	}
	
  
<%  }else{  %>
	 
	{
		"sw : "실패"
	}
	  
<%
  }
%>