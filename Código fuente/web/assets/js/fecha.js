	$(document).ready(function(){
		var d = new Date();
		var s = "/";
		var f = d.getDate()+s+(d.getMonth()+1)+s+d.getFullYear();
		$('#fecha').text(" "+" "+f);
	});