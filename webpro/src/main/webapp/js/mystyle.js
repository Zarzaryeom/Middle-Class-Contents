/**
 * 
 */
 
 //쇼핑카트 추가하기 값을 저장할 변수
 var check1; // 컴퓨터
 var check2; // 의류
 var check3; // 스포츠
 var check4; // 가구인테리어
 var check5; // 식품

 //쇼핑카트에 추가하기를 누르면 변수에 값 저장
 function check(page){
	
	if(page=='컴퓨터'){
		check1++;		
	}else if(page=='의류'){
		check2++;
	}else if(page=='스포츠'){
		check3++;
	}else if(page=='가구인테리어'){
		check4++;	
	}else if(page=='식품'){
		check5++;
	}
	document.getElementById("D:\A_TeachingMaterial\05_JQuery\webpro\src\main\webapp\task/shopcart/td1-1").setAttribute('placeholder', check1);
	document.getElementById("td2-1").setAttribute('placeholder', check2);
	document.getElementById("td3-1").setAttribute('placeholder', check3);
	document.getElementById("td4-1").setAttribute('placeholder', check4);
}
 //iframe에서 페이지를 변환하는 함수
 function page(name){
	//변수를 넣어서 href값을 받고
	let value =  name;
	var pageUrl;
	
	//다른 변수를 통해 그 정보를 넘겨준다.
	if(value == '식품'){
		pageUrl = "식품.html";
	}else if(value == '컴퓨터'){
		pageUrl = "computer.html";
	}else if(value == '의류'){
		pageUrl = "clothing.html";
	}else if(value == '스포츠/레져'){
		pageUrl = "스포츠레저.html"
	}else if(value == '가구인테리어'){
		pageUrl = "가구인테리어.html";
	}
	
	document.getElementById("mainpage").setAttribute('src', pageUrl);
}

 //장바구니에서 계산을 실행하는 함수
 function calc(){
	
	document.querySelector('#td1').value = check1;
	
	//var num1 = document.getElementById("#td1").value;
	let num1 = document.querySelector('#td1').value;
	//var num2 = document.getElementById('#td2').value;
	let num2 = document.querySelector('#td2').value;	
	//var num3 = document.getElementById('#td3').value;
	let num3 = document.querySelector('#td3').value;	
	//var num4 = document.getElementById('#td4').value;
	let num4 = document.querySelector('#td4').value;
	// 	let value2 = document.getElementById('su2').value
	//let num5 = document.querySelector('#td5').value;
			
	var cal1 = num1 * 2000000;
	var cal2 = num2 * 50000;
	var cal3 = num3 * 1000;	
	var cal4 = num4 * 50000;
	//var cal5 = (num5 + check5) * 50000;
	
	document.getElementById("td1-1").setAttribute('placeholder', cal1);
	document.getElementById("td2-1").setAttribute('placeholder', cal2);
	document.getElementById("td3-1").setAttribute('placeholder', cal3);
	document.getElementById("td4-1").setAttribute('placeholder', cal4);
	
//	check1 = 0;
// 	check2 = 0;
// 	check3 = 0;
// 	check4 = 0;
// 	check5 = 0;
	
}
