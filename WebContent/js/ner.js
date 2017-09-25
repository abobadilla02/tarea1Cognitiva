$(document).ready(function(){

	//var esClickeado=false;
	$(document).on("change","#form-ner div label input:checkbox",function(event){
		
		event.preventDefault();

		//esClickeado=!esClickeado;

		$.ajax({
			type:"POST",
			url:"ner",
			data: {entidad:$(this).attr("name")},
			success: function(respuesta){

				if(respuesta==""){

				}else{

					var arrayPalabras=respuesta.split(",");

					//si hay span de la clase, lo elimino, si no, lo pongo

					if($("."+arrayPalabras[0].split("_")[1]+"").length){

						$("."+arrayPalabras[0].split("_")[1]+"").contents().unwrap();
						/*
						for(var i=0;i<arrayPalabras.length;i++){
							$("#textoDocumento").html(function(index,html){
								return html.replace(arrayPalabras[i].split("_")[0],
									"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
							});
						}
						*/
					}else{

							for(var i=0;i<arrayPalabras.length;i++){
						

								$("#textoDocumento").html(function(index,html){

									//con esta expresión regular solo se tomarán las palabras completas, y no secciones de otras
									var changing_value = arrayPalabras[i].split("_")[0];
									var re = new RegExp("\\b" + changing_value + "\\b","g");

									return html.replace(re,
										"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
									
								});
							}					

					
						
					}
				}

				
			}



		});

	});
		

});