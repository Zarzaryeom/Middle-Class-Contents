/**
 * 
 */
 
 function proc(){
	//div id="d1"를 검색
	var vd = document.getElementById("d1");
	
	cr = parseInt(Math.random() * 256);
	cg = parseInt(Math.random() * 256);
	cb = parseInt(Math.random() * 256);
	
	cr = cr.toString(16);
	cg = cg.toString(16);
	cb = cb.toString(16);
	
	vd.style.background = "#" + cr + cg + cb;
	
	
}
