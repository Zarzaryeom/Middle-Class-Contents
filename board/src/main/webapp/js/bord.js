/**
 * 
 */
 var boardWrite = function(){
//	data : $('#ff').serializeJSON(),
//	success : function(){
//		성공하면 listServer(1) // 첫 페이지로 자동 이동
//	}
}
 
 var boardUpdate = function(){
	//date : board, // content, mail, subject, num
}
 
 
 var readHit = function(target){
		$.ajax({
		url : '/board/hitUpdate.do',
		type : 'get',
		data : {"num" : actionIdx}, 
		success : function(res){
			//db성공하면 화면 수정
			if(res.sw == "성공"){
				hit = $(target).parents('.card').find('.bhit');
				vhit = parseInt($(hit).text()) + 1;
				
				$(hit).text(vhit);
			}
		},
		error : function(xhr){
			alert("replydUpdate 상태 : " + xhr.status)
		},
		dataType : 'json'
	})
}
 
 
 var replyUpdate = function(){
	$.ajax({
		url : '/board/replyUpdate.do',
		type : 'post',
		data : reply, //cont, renum 변수가 객체에 들어있다.
		success : function(res){
			alert("업데이트 " + res.sw)
			if(res.sw == "성공"){
				// 성공했으면 화면에서 삭제
				vp3.html(modishow)
			}
		},
		error : function(xhr){
			alert("replydUpdate 상태 : " + xhr.status)
		},
		dataType : 'json'
	})
}
 
 var replyDelete = function(target){
	$.ajax({
		url : '/board/replyDelete.do',
		type : 'get',
		data :{
			"renum" : actionIdx
		},
		success : function(res){
			alert("삭제" + res.sw)
			// 화면에서 지우기
			$(target).parents('.rcode').remove();
			
		},
		error : function(xhr){
			alert("replyDelete 상태 : " + xhr.status)
		},
		dataType : 'json'
	})
}
 
 var replyList = function(target){
	// target변수는 등록 버튼 또는 제목의 a태그
	$.ajax({
		url : '/board/replyList.do',
		type : 'get',
		data : {"bonum" : actionIdx},
		success : function(res){
			rcode = "";
			
			$.each(res, function(i, v){
				rcode += '<div class="rcode">';
				rcode += '	  <p class="p1">';
				rcode += '	    작성자 : ' + v.name + ' &nbsp;&nbsp;&nbsp;';
				rcode += '	    날짜 : ' + v.redate + '&nbsp;&nbsp;&nbsp;';
				rcode += '	  </p>';
				rcode += '	  <p class="p2">';
				rcode += '	    <input idx="' + v.renum + '" class="action" name="r_modify" type="button" value="댓글수정">';
				rcode += '	    <input idx="' + v.renum + '" class="action" name="r_delete" type="button" value="댓글삭제">';
				rcode += '	  </p>';
				rcode += '	  <hr>';
				rcode += '	  <p class="p3">';
				rcode += 	  v.cont.replace(/\r/g,"").replace(/\n/g,"<br>");
				rcode += '	  </p>';
				rcode += '    </div>';
				cardBody = $(target).parents('.card')
						  			.find('.card-body')						  
				cardBody.find('.rcode').remove()
				cardBody.append(rcode);
			})
		},
		error : function(xhr){
			alert("상태 : " + xhr.status)
		},
		dataType : 'json'
	})
	
	 
}
 
 var replyInsert = function(target){
	$.ajax({
		url : '/board/replyInsert.do',
		type : 'post',
		data : reply, // reply객체 - bonum, name, cont
		success : function(res){
			alert(res.sw);
			//댓글 출력			
			replyList(target);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status)
		},
		dataType : 'json'
	})
}
 
 
 var boardDlete = function(){
	typevalue = $('#stype option:selected').val();
	wordvalue = $('#sword').val().trim();
	
	$.ajax({
		url : '/board/boardDelete.do',
		type : 'post',
		data : {"num" : actionIdx,
				"type" : typevalue,
				"word" : wordvalue,
				"page" : currentPage
				},
	    success : function(res){
			if(res.sw == "ok"){
				if(res.totalp < currentPage){
					currentPage = res.totalp;
				}
				listServer();
			}else{
				alert("삭제 실패");
			}
		},
		error : function(xhr){
			alert("상태 : " + xhr.status + " 체크");
		},
		dataType : 'json'
	})
}
 
 
 var listServer = function(){
	$.ajax({
		url : '/board/listController.do',
		type : 'post',
		data : {'page' : currentPage,
		        'stype' : typevalue,
		        'sword' : wordvalue},
		success : function(res){
			code = "<div id='accordion'>";			
			$.each(res.datas, function(i, v){
			code += '<div class="card">';
		    code += '  <div class="card-header action" name="title" idx="'+ v.num +'">';
		    code += '    <a class="card-link" data-toggle="collapse" href="#collapse'+ v.num +'">';
			code += v.subject +'</a>';
		    code += '  </div>';
		    code += '  <div id="collapse'+ v.num +'" class="collapse" data-parent="#accordion">';
		    code += '    <div class="card-body">';
			code += '	  <p class="p1">';
			code += '	    작성자 : <span class="bwr">' + v.writer + '</span> &nbsp;&nbsp;&nbsp;';
			code += '	    이메일: <span class="bma"> ' + v.mail +'</span>&nbsp;&nbsp;&nbsp;';
			code += '	    날짜 : <span class="bda">' + v.wdate + '</span>&nbsp;&nbsp;&nbsp;';
			code += '	    조회수 : <span class="bhit">' + v.hit + '</span>&nbsp;&nbsp;&nbsp;';
			code += '	  </p>';
			code += '	  <p class="p2">';
			code += '	    <input idx="'+ v.num +'" class="action" name="modify" data-toggle="modal" data-target="#modiModal" type="button" value="수정">';
			code += '	    <input idx="'+ v.num +'" class="action" name="delete" type="button" value="삭제">';
			code += '	  </p>';
			code += '	  <hr>';
			code += '	  <p class="p3">';
			code += v.content;
			code += '	  </p>';
			code += '	  <p class="p4">';
			code += '	    <textarea row="" cols="80"></textarea>';
			code += '	    <input idx="'+ v.num +'" type="button" class="action" name="reply" value="등록">';
			code += '	  </p>';
		    code += '    </div>';
		    code += '  </div>';
		    code += '</div>';
			}) // 반복문
			code += "</div>";
			
			$('.container').html(code);
			
			pcode ="";
			// 이번 버튼 출력
			if(res.startp > 1){
				pcode += '<ul class="pagination">';
  				pcode += '<li class="page-item">';
  				pcode += '<a class="page-link prev" href="#">Previous</a></li></ul>';
  				
			}
			
			pcode += '<ul class="pagination pager">';
  			
			// 페이지 목록 출력
			for(i = res.startp; i <= res.endp; i++){
				if(currentPage == i){
					pcode += '<li class="page-item active">';
					pcode += '<a class="page-link pnum" href="#">'+ i +'</a>'
					pcode += '</li>';
				}else{
					pcode += '<li class="page-item">';
					pcode += '<a class="page-link pnum" href="#">'+ i +'</a>'
					pcode += '</li>';
				}
			}
			pcode += '</ul>'
			
			// 다음 버튼 출력
			if(res.endp < res.totalp){
				pcode += '<ul class="pagination">';
  				pcode += '<li class="page-item">';
  				pcode += '<a class="page-link next" href="#">next</a></li></ul>';
			}
			$('#pagelist').html(pcode);
		},
		error : function(xhr){
			alert("상태 : " + xhr.status);
		},
		dataType : 'json'
	})
} //listServer 종료