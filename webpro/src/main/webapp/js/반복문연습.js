/**
 * 
 */
 
 function proc1(){
	// 1 ~ 10 합
}


function proc2(){
	// 1 ~ 100 짝수 합
	hap = 0;
	for(let i = 1; i <= 100; i++){
		//if(i % 2 == 0)	hap += i;
		if(i % 2 != 0) continue; // continue를 사용한 구조

		hap += i;		
	}
	
	document.querySelector("#result2").innerHTML = hap;
}

/*function proc3(){}*/
/*proc3 = function(){}*/

//람다함수 : '=>'를 이용하는 함수
// 예시 : proc3 = (매개변수) => {}
proc3 = () => {
	//사용자가 입력한 값을 계속 더하고 사용자가 0을 입력하면 누적한 값을 출력
	var hap = 0;
	var str = "";
	while(true){
		//입력
		su = parseInt(prompt("수를 입력"));
		
		//비교
		if(su == 0)	break;
				
		//더하고
		hap += su; 
		str += su + " ";
	}
	
	let res = "입력한 값들 : " + str + "<br>"
	res += "더한 합 : " + hap;
	document.querySelector("#result3").innerHTML = res;
	
}

proc4 = () => {
	
	str="";
	
	for(let i = 1; i <= 10; i++){
		for(let k = 1; k <= 10; k++){
			if((i+k)% 3 != 0) continue;			
			
			str += `${i}+${k}=${i+k}, &nbsp;&nbsp;&nbsp;`;
		}
		//document.querySelector("#result4").innerHTML = (i+k) + " ";
	}
	document.querySelector("#result4").innerHTML = str;
}

proc5 = () => {
	
	str = "";
	
	for(let i = 1; i <= 100; i++){
		if(i%2 == 0 && i%3 ==0){
			str += `${i}, &nbsp;`; 
		}
	}	
	document.querySelector("#result5").innerHTML = str;
}

proc6 = () =>{
	
	grth = "합이 100이상인 값들 : "; //두 수의 합이 100보다 큰 값들
	leth = "합이 100미만인 값들 : "; //두 수의 합이 100미안인 값들
	
	while(true){
	var num1 = parseInt(prompt("값1을 입력하세요."));
	var num2 = parseInt(prompt("값2를 입력하세요."));
	
		if(num1 == 0 && num2 == 0){
			break;
		}else if ((num1 + num2) >= 100) {
			grth += `${num1}+${num2}=${num1 + num2}, &nbsp;&nbsp;`;
		}else{
			leth += `${num1}+${num2}=${num1 + num2}, &nbsp;&nbsp;`;
		}
		document.querySelector("#result6").innerHTML = grth + "끝" + "<br>" + leth + "끝";
		
	}
}
	
