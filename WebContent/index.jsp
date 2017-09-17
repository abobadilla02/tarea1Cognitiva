<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/estilos.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/upload.js"></script>
<title>Tarea 1 - Computación Cognitiva</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div id="columnaUpload" class="col-md-3">
				<legend>Subir archivo</legend>
				<input id="upload" type="file">
				
			</div>
			<div id="columnaTexto" class="col-md-6">
				<legend>Texto subido</legend>
				<div id="texto"></div>
			</div>
			<div id="columnaOpciones" class="col-md-3">
				<legend>POS</legend>
				<legend>NEN</legend>
			</div>
		</div>
		<div class="row">
			<div id="filaKeywords" class="col-md-12">
				<legend>Keywords</legend>		
			</div>
		</div>
	</div>
	
	
	
	
</body>
</html>