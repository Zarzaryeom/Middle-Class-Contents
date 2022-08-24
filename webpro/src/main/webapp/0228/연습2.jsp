<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../CSS/public.css">
<style>
  p{
  	font-size : 2.0em
  }
</style>
</head>
<body>
   <%
		request.setCharacterEncoding("UTF-8");
	
		String name = request.getParameter("name2");
		String addr = request.getParameter("addr");
		String email = request.getParameter("email2");
		String gender = request.getParameter("gender");
		
		out.print("<p> 이름 : " + name + "</p>");
		out.print("<p> 이메일 : " + addr + "</p>");
		out.print("<p> 내용 : " + email + "</p>");
		out.print("<p> 성별 : " + gender + "</p>");
	%>

</body>
</html>