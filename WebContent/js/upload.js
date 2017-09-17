$(document).ready(function(){
	var contenido= null;
	$(document).on("change","#upload",function(event){
		event.preventDefault();	
		var archivo = event.target.files[0];
 		var reader = new FileReader();
 		
  		reader.onload = function(event) {
  			contenido = event.target.result;
  			$("#texto").html(contenido);
  		}
  		reader.readAsText(archivo);
	});
});
