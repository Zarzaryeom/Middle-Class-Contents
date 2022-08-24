<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h2{
	color : red;
	fnt-weight : bold;
}
</style>
</head>
<body>
<h1>JSP : Java Server Page</h1>
<p>로그인 처리</p>
<p>게시판 리스트, 글쓰리, 수정삭제</p>
등등 서버내에서 처리하고 결과를 출력하는 페이지
<%
  String userId = request.getParameter("id");

  if(userId == null) userId = "guest";

  //자바로 처리하는 문장
  out.print("<h2>" + userId + "님 환영합니다.</h2>");

  String good = "즐거운 오후 되세요!";
%>
<h2><%= userId %>님 <%= good %></h2>

</body>
</html>