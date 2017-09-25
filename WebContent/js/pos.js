$(document).ready(function(){
	var pronounPintada=false;
	var nounPintada=false;
	//var esClickeado=false;


	$(document).on("change","input[name='noun']",function(event){
		event.preventDefault();
		nounPintada=false;
	});

	$(document).on("change","input[name='pronoun']",function(event){
		event.preventDefault();
		pronounPintada=false;
	});

	$(document).on("change","#form-pos div label input:checkbox",function(event){
		event.preventDefault();

		
		//esClickeado=!esClickeado;

		$.ajax({
			type:"POST",
			url:"pos",
			data: {categoria:$(this).attr("name")},
			success: function(respuesta){

				if(respuesta==""){

				}else{

					var arrayPalabras=respuesta.split("*");

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
							
							for(var i=0;i<arrayPalabras.length - 1;i++){

								$("#textoDocumento").html(function(index,html){

									//con esta expresión regular solo se tomarán las palabras completas, y no secciones de otras
									//var changing_value = arrayPalabras[i].split("_")[0];
									/*var tag_value=arrayPalabras[i].split("_")[1];
									var re;
									var esQuote=false; */
					
									var changing_value = arrayPalabras[i].split("_")[0];
									re = new RegExp("\\b" + changing_value + "\\b","g");
									
									//console.log(re);
									//console.log("<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
									return html.replace(re,
											"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
									//ya que POS categoriza las palabras anteriores al 's, y a este ultimo como dos distintas

									/*if(arrayPalabras[i].split("_")[2]=="'s"){
										//se considera la palabra anterior al 's como una distinta
										re = new RegExp("\\b" + changing_value,"g");
										if(tag_value=="pronoun"){
											pronounPintada=true;
										}else{
											if(tag_value=="noun"){
												nounPintada=true;
											}
										} 

										
										//return html.replace(re,
										//"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");

									}else{ */
									/*
										if(arrayPalabras[i].split("_")[2].charAt(0)=='\''){
											re = new RegExp("\\b" + changing_value,"g");
											
											if(tag_value=="pronoun"){
												pronounPintada=true;
											}else{
												if(tag_value=="noun"){
													nounPintada=true;
												}
											}

										}
									//}
								
									//re = new RegExp("\\b" + changing_value + "\\b","g");
									if(arrayPalabras[i].split("_")[0].charAt(0)=='\''){
									
									//solo las 's poseen un tercer elemento en el arreglo (la palabra que la precede)
									//investigar ->
										esQuote=true;
										alert(pronounPintada);
										if(pronounPintada && arrayPalabras[i].split("_")[3]=="pronoun"){

											re = new RegExp("<span class=\"pronoun\">"+arrayPalabras[i].split("_")[2]+"</span>"+changing_value + "\\b","g");

										}else{

											if(nounPintada && arrayPalabras[i].split("_")[3]=="noun" ){

												re = new RegExp("<span class=\"noun\">"+arrayPalabras[i].split("_")[2]+"</span>"+changing_value + "\\b","g");
											}else {

												re = new RegExp("\\b"+arrayPalabras[i].split("_")[2]+changing_value + "\\b","g");

											}
											
										}
										
										//return html.replace(re,arrayPalabras[i].split("_")[2]+
										//"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");

									}else{
										re = new RegExp("\\b" + changing_value + "\\b","g");
										//return html.replace(re,
										//"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");

									}


									if(esQuote){

										if(pronounPintada && arrayPalabras[i].split("_")[3]=="pronoun"){

											return html.replace(re,"<span class=\"pronoun\">"+arrayPalabras[i].split("_")[2]+"</span>"+
											"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
										}else{
											if(nounPintada && arrayPalabras[i].split("_")[3]=="noun" ){
												return html.replace(re,"<span class=\"noun\">"+arrayPalabras[i].split("_")[2]+"</span>"+
												"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
											}else{
												return html.replace(re,arrayPalabras[i].split("_")[2]+
												"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
											}
										}

										
									}else{
										if(esQuote==false){
											return html.replace(re,
											"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
										}
									} */

									

									//si la palabra es un 's, entonces se considera como una distinta
									/*
									if(arrayPalabras[i].split("_")[0]==" 's"){
										//solo las 's poseen un tercer elemento en el arreglo (la palabra que la precede)
										//investigar ->
										re = new RegExp("\\b"+arrayPalabras[i].split("_")[2]+changing_value + "\\b","g");
									}else{
										re = new RegExp("\\b" + changing_value + "\\b","g");
									}
									*/
									//sd

									//Buscar traer la palabra anterior, para realizar una distincion entre las distinas 's
									//COMPLETAR CON EL RESTO DE CATEGORÍAS-->POS TERMINADO
									/*
									return html.replace(re,
										"<span class='"+arrayPalabras[i].split("_")[1]+"'>"+arrayPalabras[i].split("_")[0]+"</span>");
										*/
								});
							}					

						/*
							if(esClickeado===false){
								//se eliminan todos los <span> con la clase correspondiente
								$("."+arrayPalabras[0].split("_")[1]+"").contents().unwrap();
						
							}
							
						*/	
						
					}
				}

				//se llamara al contenedor del documento y se buscarán
				//se pondra un <span> a las palabras para setearlas con la clase css correspondiente
			}



		});

	});
		

});