<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <%
    request.setCharacterEncoding("UTF-8");
  	String name = request.getParameter("name");
    String id = request.getParameter("id");
    
    out.print("<p>이름 : " + name + "</p>");
    out.print("<p>아이디 : " + id + "</p>");
  %>
</body>
</html>