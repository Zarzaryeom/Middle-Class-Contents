<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/jquery.serializejson.min.js"></script>
<script>
$(function(){
	
	//변수
	SidoData = "";
	GugunData = "";
	DongData = "";
	
	// 시 입력하기
	//$('#btn1').on('click', function(){
		$.ajax({
			url : '/jqpro/ZipControllerSum.do',
			type : 'get',
			data : {"check" : 0},
			success : function(res){
				console.log(res)
				str ="";
				$.each(res, function(i, v){
					str += "<option value= '"+ v +"'>" + v + "</option>" 
				})
				$('#sido').html(str);
			},
			error : function(xhr){
				alert("시 구하기 상태 : " + xhr.status)
			},
			dataType : 'json'
		})
	//})
	
	// 구, 군 선택하기
	$('#sido').on('change', function(){
		
		SidoData = $('option:selected', this).val();
		SidoData2 = $('option:selected', this).text();
		
		console.log(SidoData);
		console.log(SidoData2);
		
		$.ajax({
			url : '/jqpro/ZipControllerSum.do',
			type : 'get',
			data : {"dataS" : SidoData,
					"check" : 1},
			success : function(res) {
				str ="";
				$.each(res, function(i, v){
					str += "<option>" + v + "</option>" 
				})
				$('#gugun').html(str);
			},
			error : function(xhr) {
				alert("군 구하기 상태 : " + xhr.status);
			},
			dataType : 'json'
			})
		})
	
	// 동 선택하기
	$('#gugun').on('change', function(){
		GugunData = $('option:selected', this).text();
		
		$.ajax({
			url : '/jqpro/ZipControllerSum.do',
			type : 'get',
			data : {"dataS" : SidoData,
					"dataG" : GugunData,
					"check" : 2},
			success : function(res){
				str="";
				$.each(res, function(i, v){
					str += "<option>" + v + "</option>"
				})
				$('#dong').html(str);				
				$('#dong').trigger('change');
			},
			error : function(xhr){
				alert("동 구하기 상태 : " + xhr.status)
			},
			dataType : 'json'
			
		})
	})
	
	// 남은 상세주소 모두 출력하기
	$('#dong').on('change', function(){
		DongData = $('option:selected', this).text();
		
		$.ajax({
			url : '/jqpro/ZipControllerSum.do',
			type : 'get',
			data : {"dataS" : SidoData,
					"dataG" : GugunData,
					"dataD" : DongData,
					"check" : 3},
			success : function(res){
				str = "<table border='1' class='table'>";
				str += "<thead class='thead-dark'>";
				str += "<tr><th>시/도</th>"
				str += "<th>구/군</th>"
				str += "<th>동</th>"
				str += "<th>번지</th></tr></thead>"
				$.each(res, function(i, v){
					if(typeof v.bunji == "undefined") {
						bunji = "-"
					}else bunji = v.bunji
					str += "<tr class='trHover'><td>" + v.sido + "</td>"
					str += "<td>" + v.gugun + "</td>"
					str += "<td>" + v.dong + "</td>"
					str += "<td>" + bunji + "</td></tr>"
				})
				str += "</table>"
				$('#result').html(str)
			},
			error : function(xhr){
				alert("상태 : " + xhr);
			},
			dataType : 'json'
		})
	})

})
</script>
<style>
table{
	text-align: center;
}
.trHover:hover{
	background : yellow;
}
</style>
</head>
<body>

  <button type="button" class="btn btn-outline-info mb-2 mr-sm-2" data-toggle="modal" data-target="#myModal">우편번호검색madal</button>
  <br><br>
  <select id="sido"></select>
  <select id="gugun"></select>
  <select id="dong"></select>
  
  <div id="result"></div>
  
  
<!-- The Modal -->
<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">우편번호 찾기</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      <input type="text" id="dong">
      <input type="button" value="확인" id="btn1">
  	  <div id="result1"></div>
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
      </div>

    </div>
  </div>
</div>
</body>
</html>