<%-- 
    Document   : HomeAdmin
    Created on : 27/05/2020, 03:46:01 PM
    Author     : crist
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>
		WebList
	</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
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
	<!-- Aquí esta todo el navegador -->
	<nav>
		<p class="title">
			Bienvenido
		</p>
		<p class="space">
			<br>
			<br>
			<br>
		</p>
		<p class="atxt">
			Grupos registrados
		</p>
		<p class="space">
			<br>
		</p>
		<!-- De momento los grupos se vana a quedar estaticos solo para los cuartos -->
		<a href="#" class="g7">4IV7</a>
		<p class="space">
			<br>
		</p>
		<a href="#"  class="g8">4IV8</a>
		<p class="space">
			<br>
		</p>
		<a href="#" class="g9">4IV9</a>
		<p class="space">
			<br>
		</p>
		<div class="padd">
			<button class="inputbutn int nav">
				Perfil
			</button>
			<p class="space">
				<br>
			</p>
			<button class="inputbutn int nav" onclick="location.href='index.jsp'">
				Cerrar sesion
			</button>
		</div>
	</nav>
        <%=request.getAttribute("code")%>
	<div class="blockAn fg-img">
		<p class="space">
			<br>
			<br>
			<!--No quitar el br dinamico-->
		</p>

		<!-- Iniciamos con la parte conde el administrador puede modificar los datos -->
		<p class="title">
			Alumnos
		</p>
		<p class="space">
			<br>
		</p>
		<!-- este es el formulario para los alumnos -->
        <form id="alumno" method="post" action="CRUDalumno">
			<select class="inputtxt">
				<option>Alumno 1</option>
				<option>Alumno 2</option>
				<option>Alumno 3</option>
			</select>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Boleta: <input type="number" name="boleta" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			
			<!-- el año de ingreso lo omití porque viene en la boleta y creo que no es necesario -->
			
			<p class="atxt">
				Nombres: <input type="text" name="nombres" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Apellido paterno: <input type="text" name="app" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Apellido materno: <input type="text" name="apm" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>

			<!-- Botones del  formulariio-->
						<input type="hidden" id="gr" name="grupo" value="null">
                        <input type="hidden" id="command" name="instruction" value="null">
                        <input type="radio" name="election" class="new" value="new">Guardar como nuevo<br>
                        <input type="radio" name="election" class="save" value="save" onclick="">Guardar cambios<br>
                        <input type="radio" name="election" class="delete" value="delete" onclick="">Eliminar<br>
            <p class="space">
				<br>
			</p>
            <input type="submit" value="Ejecutar acciones" class="inputbutn int">
		</form>
		<!-- Este script se encarga de cambiar la instruccion del hidden -->
		<script type="text/javascript">
			$(document).ready(function(){
				$('.new').click(function(){
					document.getElementById('command').value='new';
				});
				$('.save').click(function(){
					document.getElementById('command').value='save';
				});
				$('.delete').click(function(){
					document.getElementById('command').value='delete';
				});
				
				//esto es de los grupos
				$('.g7').click(function(){
					document.getElementById('gr').value="4IV7";
				});
				$('.g8').click(function(){
					document.getElementById('gr').value="4IV8";
				});
				$('.g9').click(function(){
					document.getElementById('gr').value="4IV9";
				});
			});
		</script>


		<p class="space">
			<br>
		</p>

		<!-- Formulario de para los profesores -->
		<p class="title">
			Profesores
		</p>
		<p class="space">
			<br>
		</p>
		<form>
			<select class="inputtxt">
				<option>Profesor 1</option>
				<option>Profesor 2</option>
				<option>Profesor 3</option>
			</select>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Numero de empleado: <input type="number" name="" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Nombres: <input type="text" name="" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Apellido paterno: <input type="text" name="" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>
			<p class="atxt">
				Apellido materno: <input type="text" name="" class="inputtxt">
			</p>
			<p class="space">
				<br>
			</p>

			<!-- Botones del formulario profesor -->
			<input type="submit" name="" value="Guardar" class="inputbutn int">
			<p class="space">
				<br>
			</p>
			<input type="submit" name="" value="Eliminar" class="inputbutn int">
		</form>
	</div>
</body>
</html>
