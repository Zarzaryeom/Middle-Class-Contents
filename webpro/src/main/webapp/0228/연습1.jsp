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
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String content = request.getParameter("area");
		
		content = content.replaceAll("/r","").replaceAll("\n", "<br>");
		
		out.print("<p> 이름 : " + name + "</p>");
		out.print("<p> 이메일 : " + email + "</p>");
		out.print("<p> 내용 : <br>" + content + "</p>");
	%>

</body>
</html>