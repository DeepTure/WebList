<%-- 
    Document   : Home
    Created on : 27/05/2020, 03:47:37 PM
    Author     : crist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
<head>
	<title>
		WebList
	</title>
	<link rel="shortcut icon" href="Img/DeepTureL.ico" />
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: no-preference)" href="CSS/RespL.css">
	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: no-preference)" href="CSS/FuenteL.css">

	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: dark)" href="CSS/RespD.css">
	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: light)" href="CSS/RespL.css">

	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: dark)" href="CSS/FuenteD.css">
	<link rel="stylesheet" type="text/css" media="screen and (prefers-color-scheme: light)" href="CSS/FuenteL.css">

	<style type="text/css">
		.bg-img{
			margin: 0;
			background-image: url('Img/BackgroundD.png');
			background-size: 100%;
			background-repeat: no-repeat;
			background-position: 0 0;
			background-attachment: fixed;
			background-size: cover;
			transition: background 1s;
		}
		.fg-img{
			background-image: url('Img/ForegroundD.png');
			background-size: 100%;
			background-repeat: no-repeat;
			background-position: 0 0;
			background-attachment: fixed;
			background-size: cover;
			transition: background 1s;
		}

		.logo{
			background-image: url('Img/DeepTureD.png');
			background-size: 100%;
			background-size: cover;
			transition: background 1s;
		}

		.logoHelp{
			background-image: url('Img/HelpL.png');
			background-size: 70%;
			background-repeat: no-repeat;
			background-position: 50% 0;
			transition: background 1s;
		}

		@media screen and (prefers-color-scheme: light) {
			.bg-img{
				background-image: url('Img/BackgroundL.png');
				transition: background 1s;
			}
			.fg-img{
				background-image: url('Img/ForegroundL.png');
				transition: background 1s;
			}
			.logo{
				background-image: url('Img/DeepTureL.png');
				transition: background 1s;
			}
			.logoHelp{
				background-image: url('Img/HelpD.png');
				transition: background 1s;
			}
		}
	</style>
	<script src="JS/Validate.js"></script>
</head>
<body link="Black" vlink="Black" alink="Black" class="bg-img">

	<nav>
		<p class="title">
			Bienvenido
		</p>
		<p class="space">
			<br>
			<br>
			<br>
		</p>
		<p class="subtitle">
			Mis grupos
		</p>
		<p class="space">
			<br>
			<br>
			<br>
		</p>
		<div class="atxt">
			<a href="#" onclick="camb4()">
				4IV7
			</a>

			<p class="space">
				<br>
			</p>

			<a href="#" onclick="camb4()">
				4IV8
			</a>

			<p class="space">
				<br>
			</p>
			
			<a href="#" onclick="camb6()">
				6IV1
			</a>

			<p class="space">
				<br>
			</p>
			
			<a href="#" onclick="camb6()">
				6IV2
			</a>

			<p class="space">
				<br>
			</p>
			
			<a href="#" onclick="camb2()">
				2IV2
			</a>
			
			<p class="space">
				<br>
			</p>

			<a href="#" onclick="camb2()">
				2IV4
			</a>
		</div>

		<!-- botones para salir de tu cuenta y para configurara tu cuenta-->
		<div class="padd">
			<input type="button" name="Perfil" value="Perfil" onclick="location.href='MyProfile.jsp'" class="inputbutn nav int ">
			<p class="space">
				<br>
			</p>
			<input type="button" name="LogOut" value="Cerrar sesión" onclick="location.href='index.jsp'" class="inputbutn nav int">
		</div>

	</nav>

	<div class="blockAn fg-img">
			<p class="space">
				<br>
				<br>
				<!--No quitar el br dinamico-->
			</p>

		<form name="acciones" action="#">
			<div class="pdf">
				<p class="title">
					Horario del grupo
				</p>
				<div id="pdf">
					<embed id="pdfs" src="Img/tec-Programacion-escolarizado.pdf" type="application/pdf" style="height: 35vw; width: 80%;"/>
				</div>
			</div>
			<p class="space">
				<br>
				<br>
			</p>

			<div name="lista">
				<p class="atxt">
					Lista del grupo 
					<input type="button" name="save" value="Pasar lista" id="btn" class="inputbutn int">
				</p>
				<p class="space">
					<br><br><br><br><br><br><br><br><br><br><br><br>
					<br><br><br><br><br><br><br><br><br><br><br><br>
				</p>
			</div>
			<!--botones para borrar o configurar las listas-->
			<div>
				<p class="atxt">
					Lista actual: 
				</p>
				<p class="space">
					<br>
				</p>
				<input type="button" name="save" value="Guardar" id="btn" class="inputbutn int">
				<p class="space">
					<br>
				</p>
				<input type="button" name="delete" value="Borrar" id="btn" class="inputbutn int">
				<p class="space">
					<br>
				</p>
			</div>
		</form>
	</div>

	<!-- aqui apareceran las listas de los profesores
		°se extraera los nombres de cada alumno de la base de datos y luego se ordenaran en lista en este lugar
		°el maestro seleccionara si vinieron atraves de un checkbutton y luego seleccionará una se las opciones que esán en el div superiror
	-->
	<script type="text/javascript">
		//definimos los arreglos de las materias
			var mat1= new Array ("Geometria y Tri...", "Filosofia II", "Computacion basi...", "ingles II", "expresion oral...", "biologia basica", "historia de mex...","orientacion juvenil...","tic's");

			var mat2 =new Array ("Calculo diferencial","Fisica II","Quimica II","ingles IV","Dibujo tecnico II","PWS","Bases de datos","LPTI II","TPPCC");

			var mat3 = new Array ("Probabilidad y est...","Fisica IV","Quimica IV","Ingles IV","orientacion juvenil...","Metodos agiles...","Soporte de software","Ingenieria de soft...","LPTI IV","Proyecto integrador");

			//funciones que hacn dinamica la lista
		function camb2(){
			//obtenermos el tamaño total del arreglo
			opc=mat1.length;
			//ponemos el tamaño del arreglo en el select
			document.getElementById("materias").length= opc;
			document.getElementById("pdfs").src="Img/tec-computacion-escolarizado.pdf";
			for(var i=0;i<opc;i++){
				//metemos una por una cada materia a el select
				document.getElementById("materias").options[i].text=mat1[i];
				//metemeos elnombtre de la materia como valor del select
				document.getElementById("materias").options[i].value=mat1[i];
			}
		}
		//los mismo se repite en todas las funciones
		function camb4(){
			opc=mat2.length;
			document.getElementById("materias").length = opc;
			document.getElementById("pdfs").src="Img/tec-Programacion-escolarizado.pdf";
			for(var i=0;i<opc;i++){
				document.acciones.opt.options[i].text=mat2[i];
				document.acciones.opt.options[i].value=mat2[i];
			}
		}
		function camb6(){
			opc=mat3.length;
			document.getElementById("materias").length = opc;
			document.getElementById("pdfs").src="Img/ESFM-Licenciatura-en-Física-y-Matemáticas.pdf";
			for(var i=0;i<opc;i++){
				document.acciones.opt.options[i].text=mat3[i];
				document.acciones.opt.options[i].value=mat3[i];
			}
		}
	</script>

</body>
</html>
