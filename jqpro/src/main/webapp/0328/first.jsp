<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h1{
	color : red;
}

p{
	font-size : 2.0em;
	color : blue;
}
</style>
</head>
<body>
<h1>JSP : Java Server Page</h1>
<%

	request.setCharacterEncoding("UTF-8");

	String userId = request.getParameter("id");
	String userName = request.getParameter("name");
	
	//db연결 - select, insert, update,delete
	//crud처리

%>

<p><%= userId %>님 환영합니다.</p>
<p><%= userName %>님 즐거운 하로 되세요.</p>

</body>
</html>