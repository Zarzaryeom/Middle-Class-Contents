<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<style>
p{
	margin : 2px;
	padding : 2px;
}
.p1{
	float : left;
	width : 70%;
	
}
.p2{
	width : 25%;
	float : right;
	text-align: right;
}
hr{
	clear : both;
}
input[name=reply]{
	height : 55px;
	vertical-align: top;
}
</style>
</head>
<body>

<div class="container">
  <h2>Accordion 게시판</h2>
  <div id="accordion">
    <div class="card">
      <div class="card-header">
        <a class="card-link" data-toggle="collapse" href="#collapseOne">
		제목
        </a>
      </div>
      <div id="collapseOne" class="collapse show" data-parent="#accordion">
        <div class="card-body">
		  <p class="p1">
		    작성자 : 홍길동 &nbsp;&nbsp;&nbsp;
		    이메일: hong123@naver.com &nbsp;&nbsp;&nbsp;
		    날짜 : 2022-04-01 &nbsp;&nbsp;&nbsp;
		    조회수 : 0 &nbsp;&nbsp;&nbsp;
		  </p>
		  <p class="p2">
		    <input class="action" name="midofy" type="button" value="수정">
		    <input class="action" name="delete" type="button" value="삭제">
		  </p>
		  <hr>
		  <p class="p3">
		    내용출력<br>
		    내용출력<br>
		    내용출력<br>
		  </p>
		  <p class="p4">
		    <textarea row="100" cols="80"></textarea>
		    <input type="button" class="action" name="reply" value="등록">
		  <p>
        </div>
      </div>
    </div>
    <div class="card">
      <div class="card-header">
        <a class="collapsed card-link" data-toggle="collapse" href="#collapseTwo">
        Collapsible Group Item #2
      </a>
      </div>
      <div id="collapseTwo" class="collapse" data-parent="#accordion">
        <div class="card-body">
          Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        </div>
      </div>
    </div>
    <div class="card">
      <div class="card-header">
        <a class="collapsed card-link" data-toggle="collapse" href="#collapseThree">
          Collapsible Group Item #3
        </a>
      </div>
      <div id="collapseThree" class="collapse" data-parent="#accordion">
        <div class="card-body">
          Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        </div>
      </div>
    </div>
  </div>
</div>
    
</body>
</html>