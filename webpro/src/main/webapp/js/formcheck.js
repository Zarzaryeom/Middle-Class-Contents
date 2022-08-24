/**
 * 
 */
  function datapro(){
  		
  		f = document.ff;
  		
  		//입력한 값을 가져온다.
		namevalue = f.name.value.trim();
		  
		//이름 공백 검증
		if(namevalue.length < 1){
			alert("이름을 입력하세요");
			return false;
		}
		  
		//이름 길이 검증
		if(namevalue.length < 2 || namevalue.length >5){
			alert("이름은 2 ~ 5사이입니다.");
			return false;
		}
		//이름 정규식
		namereg = /^[가-힣]{2,5}$/;
		
		if(!(namereg.test(namevalue))){
			alert("이름 형식 오류");
			return false;
		}
		
		//-------------------------------
		//아이디 검증
		idvalue = f.userid.value.trim();
		
		if(idvalue.length < 1){
			alert("아이디를 입력하세요");
			return false;
		}
		
		if(idvalue.length < 4 || idvalue.length > 10){
			alert("아이디는 4 ~ 10 사이입니다.");
			return false;
		}
		
		//아이디 정규식
		idreg = /^[a-zA-z][a-zA-z0-9]{3,9}$/;
		
		if(!(idreg.test(idvalue))){
			alert("아이디 형식 오류...")
			return false;
		}
		
		//-------------------------------
		//이메일 검증
		emailvalue = f.email.value.trim();
		
		//공백
		if(emailvalue.length < 1){
			alert("이메일 입력하세요");
			return false;
		}
		
		//정규식 
		// * 첫글자는 영문자 1글자 -> 영문자나 숫자가 올 수 있다.
		// -> @
		emailreg = /^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[a-z]+){1,2}$/;
		
		if(!(emailreg.test(emailvalue))){
			alert("이메일 형식오류..");
			return false;
		}
		//-------------------------------
		//휴대폰 전화번호 검증
		phvalue = f.phone.value.trim();
		
		//공백
		if(phvalue.length < 1){
			alert("전화번호를 입력하세요.");
			return false;
		}
		
		//길이 -> 11자리 일치
		if(phvalue.length != 11){
			alert("전화번호 길이가 맞지 않습니다.");
			return false;
		}
		
		//정규식
		phreg = /^\d{3}\d{4}\d{4}$/;
		if(!(phreg.test(phvalue))){
			alert("전화번호 형식 오류")
			return false;
		}
		//--------------------------------------
		//비밀번호
		pvalue = f.pass.value.trim();
		
		//공백
		if(pvalue.length < 1){
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		//길이
		if(pvalue.length < 4 || pvalue.length > 12){
			alert("비밀번호는 4 ~ 12사이입니다.")
			return false;
		}
		
		//정규식
		passreg = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*]).{4,12}$/;
		
		 if(!(passreg.test(pvalue))){
		  	alert("비밀번호 형식 오류");
		  	return false;
		 }
		
		
		alert("올바른 데이터 성공");
		return true;
  }
	  
// 	  f.name.value;
// 	  f.addr.value;
// 	  f.birthday.value;
// 	  f.emil.value;
// 	  f.userif.value;
// 	  f.pass.value;
// 	  f.phone.value;
	  