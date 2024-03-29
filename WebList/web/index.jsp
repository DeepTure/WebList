<%-- 
    Document   : index
    Created on : 27/05/2020, 03:35:40 PM
    Author     : DeepTure
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    background-image: url('Img/ForegroundLHelp.png');
                    transition: background 1s;
                }
                @media screen and (orientation: landscape){
                    .fg-img{
                        background-image: url('Img/ForegroundL.png');
                        transition: background 1s;
                    }
                }
                .logo{
                    background-image: url('Img/DeepTureL.png');
                    transition: background 1s;
                }
            }
        </style>
        <script src="JS/Validate.js"></script>
    </head>
    <body link="Black" vlink="Black" alink="Black" class="bg-img">
        <nav>
            <article class="ImgicoLog logo">

            </article>
        </nav>

        <div class="block start fg-img">
            <div class="space">
                <br>
            </div>	

            <a href="Help.jsp" class="InitHelp">
                <aside class="logoHelp white">
                    <div class="space">
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                    </div>
                    <div class="atxt white">
                        Ayuda
                    </div>
                </aside>
            </a>

            <div class="title white">
                Iniciar sesi&oacute;n
            </div>

            <div class="space">
                <br>
            </div>

            <div>
                <form method="post" id="LogIn" action="InicioSesionController">
                    <p class="subtitle white">
                        Usuario
                    </p>
                    <div class="space">
                        <br>
                    </div>
                    <input type="text" id="Account" class="inputtxt white" name="user">
                    <div class="space">
                        <br>
                        <br>
                        <br>
                    </div>
                    <p class="subtitle white">
                        Contrase&ntilde;a
                    </p>
                    <div class="space">
                        <br>
                    </div>
                    <input type="password" id="Password" class="inputtxt white" name="pass">
                    <div class="space">
                        <br>
                        <br>
                        <br>
                        <br>
                    </div><!-- Cuando tenga funcion este boton se convierte en submit-->
                    <input type="submit" id="Login" value="Inicia sesi&oacute;n" class="inputbutn int white" >
                    <div class="space">
                        <br>
                    </div>
                    <input type="button" id="Recover" value="Recuperar cuenta" class="inputbutn int white" onclick="location.href = 'Recover.jsp'">
                </form>
            </div>	
        </div>
    </body>
    <%=request.getAttribute("code")%>
</html>

