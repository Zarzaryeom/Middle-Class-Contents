<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <p>checkbox에서 선택한 여러개의 값을 가져온다</p>
  
  <%
  	request.setCharacterEncoding("utf-8");
    String[] foods = request.getParameterValues("food");
    
    out.print("<h1>선택한 음식</h1>");
    for(String food : foods){
  		out.print(food + "<br>");
    }
  %>
</body>
</html>