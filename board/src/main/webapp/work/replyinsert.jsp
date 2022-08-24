<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	// 공유 데이터
    	
    	int renum = (Integer) request.getAttribute("renum");
    
    	if(renum > 0){
    %>
    	{
    		"sw" : "저장성공"
    	}
    
    <%}else { %>
    	{
    		"sw" : "저장실패"
    	}
    
    <%
    }
    %>
    
    