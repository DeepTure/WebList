<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Home
    Created on : 27/05/2020, 03:47:37 PM
    Author     : crist
--%>

<%@page import="com.deepture.utils.classdata.Inasistencias"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.deepture.utils.classdata.alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
﻿<!DOCTYPE html>
<html>
    <head>  <%
        //obtiene los productos del controlador
        List<alumno> alumnos = (List<alumno>) request.getAttribute("alumnosD");
        if (alumnos == null) {
            alumnos = (List<alumno>) session.getAttribute("als");
        } else {
            session.setAttribute("als", alumnos);
        }
        //esto es para tener el id del profesor
        String id = String.valueOf(request.getAttribute("id_profe"));
        if (id == null) {
            id = String.valueOf(session.getAttribute("id_p"));
        } else {
            session.setAttribute("id_p", id);
        }
        List<alumno> faltados = (List<alumno>) request.getAttribute("faltados");
        List<Inasistencias> faltas = (List<Inasistencias>) request.getAttribute("faltas");
        %>
        <title>
            WebList
        </title>
        <link rel="shortcut icon" href="Img/DeepTureL.ico" />
        <meta charset="utf-8">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
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
                <form action="CRUDalumno" method="post">
                    <input type="hidden" name="idpr" value="<%=id%>">
                    <input type="hidden" name="instruction" value="4IV7">
                    <input type="submit" name="" value="4IV7">
                </form>

                <p class="space">
                    <br>
                </p>

                <form action="CRUDalumno" method="post">
                    <input type="hidden" name="idpr" value="<%=id%>">
                    <input type="hidden" name="instruction" value="4IV8">
                    <input type="submit" name="" value="4IV8">
                </form>

                <p class="space">
                    <br>
                </p>

                <form action="CRUDalumno" method="post">
                    <input type="hidden" name="idpr" value="<%=id%>">
                    <input type="hidden" name="instruction" value="4IV9">
                    <input type="submit" name="" value="4IV9">
                </form>
            </div>

            <!-- botones para salir de tu cuenta y para configurara tu cuenta-->
            <div class="padd">
                <input type="button" name="Perfil" value="Perfil" onclick="location.href = 'MyProfile.jsp'" class="inputbutn nav int ">
                <p class="space">
                    <br>
                </p>
                <input type="button" name="LogOut" value="Cerrar sesión" onclick="location.href = 'index.jsp'" class="inputbutn nav int">
            </div>

        </nav>

        <div class="blockAn fg-img">
            <p class="space">
                <br>
                <br>
                <!--No quitar el br dinamico-->
            </p>

            <form action="CRUDalumno" method="post">
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
                        Materia<br>
                        <input type="radio" name="rd" id="mat1" value="PSW"><label for="mat1">Programacion y servicios web</label><br>
                        <input type="radio" name="rd" id="mat2" value="TPPC"><label for="mat2">Tecnicas de programacion con calidad</label><br>
                        <input type="radio" name="rd" id="mat3" value="BBDD"><label for="mat3">Base de datos</label><br>
                        <input type="radio" name="rd" id="mat4" value="LPTI"><label for="mat"4>Laboratorio de proyectos de tecnologias de la informacion</label>
                    </p>
                    <p class="space">
                        <br><br>
                    </p>
                    <script type="text/javascript">
                        $(document).ready(function () {
                            $('#mat1').click(function () {
                                document.getElementById('command').value = 'PSW';
                                document.getElementById('command2').value = 'PSW';
                            });
                            $('#mat2').click(function () {
                                document.getElementById('command').value = 'TPPC';
                                document.getElementById('command2').value = 'TPPC';
                            });
                            $('#mat3').click(function () {
                                document.getElementById('command').value = 'BBDD';
                                document.getElementById('command2').value = 'BBDD';
                            });
                            $('#mat4').click(function () {
                                document.getElementById('command').value = 'LPTI';
                                document.getElementById('command2').value = 'LPTI';
                            });
                        });
                    </script>
                </div>
                <!--botones para borrar o configurar las listas-->
                <div>
                    <p class="atxt">
                        Lista actual: 
                    </p>

                    <div>
                        <table border="2">
                            <tr>
                                <td>Boleta</td><td>Nombre</td><td>Asistencia</td>
                            </tr>
                            <%  boolean entra;
                                entra = false;
                                if (alumnos != null) {%>
                            <%for (alumno a : alumnos) {%>
                            <tr>
                                <td><%=a.getBoleta()%></td><td><%=a.getNombre()%> <%=a.getApp()%></td><td><input type="checkbox" name="asistencia" value="<%=a.getBoleta()%>"></td>
                            </tr>
                            <%}
                                entra = true;
                            } else {%>
                            <tr>
                                <td>215</td><td>juan</td><td><input type="checkbox"></td>
                            </tr>
                            <%}%>
                        </table>


                        <p class="space">
                            <br>
                        </p>

                        <input type="hidden" name="instruction" value="saveAs">
                        <!-- Este id command es el que lleva la materia -->
                        <input type="hidden" name="materia" id="command" value="null">
                        <!--Este hidden tiene el id del profesor-->
                        <input type="hidden" name="idP" value="<%=id%>">
                        <!-- Este es el hidden donde tiene el grupo -->
                        <input type="hidden" name="gr" value="<%=request.getAttribute("gr")%>">
                        <input type="submit" name="save" value="Guardar" id="btn" class="inputbutn int">
                        <p class="space">
                            <br>
                        </p>
                        </form>
                        <%
                            if (faltas == null) {
                        %>
                        <form action="CRUDalumno" method="post">
                            <input type="hidden" name="gr" value="<%=request.getAttribute("gr")%>">
                            <input type="hidden" name="idpr" value="<%=id%>">
                            <input type="hidden" name="materia" id="command2" value="null">
                            <input type="hidden" name="instruction" value="getFalt">
                            <input type="submit" name="delete" value="Revisar ultimo registro" id="btn" class="inputbutn int">
                        </form>
                    </div>
                    <%
                    } else if (faltas.isEmpty()) {
                    %>
                    <p class="atxt">
                        No hay faltas actualmente.
                    </p>
                    <%
                    } else {
                    %>
                    <p class="atxt">
                        Faltas del dia de hoy.
                    </p>
                    <table border="2">
                        <tr>
                            <td>Boleta</td><td>Nombre</td><td>Accion</td>
                        </tr>
                        <%
                        %>
                        <%for (Inasistencias i : faltas) {
                                for (alumno a : faltados) {
                                    if (i.getBoleta() == a.getBoleta()) {
                        %>
                        <tr>
                        <form action="CRUDalumno" method="post">
                            <input type="hidden" name="gr" value="<%=i.getGrupo()%>">
                            <input type="hidden" name="idpr" value="<%=i.getId_maestro()%>">
                            <input type="hidden" name="materia" value="<%=i.getId_materia()%>">
                            <input type="hidden" name="boleta" value="<%=i.getBoleta()%>">
                            <input type="hidden" name="dia" value="<%=i.getDia()%>">
                            <input type="hidden" name="instruction" value="eliminarFal">
                            <td><%=a.getBoleta()%></td><td><%=a.getNombre()%> <%=a.getApp()%></td><td><input type="submit" name="" value="Borrar" id="btn" class="inputbutn int"></td>
                        </form>
                        </tr>
                        <%}
                                }
                            }
                            Inasistencias i = faltas.get(0);
                        %>
                    </table>
                    <br>
                    <p class="atxt">
                        Insertar nueva falta.
                    </p>
                    <form action="CRUDalumno" method="post">
                        <input type="hidden" name="gr" value="<%=i.getGrupo()%>">
                        <input type="hidden" name="idpr" value="<%=i.getId_maestro()%>">
                        <input type="hidden" name="materia" value="<%=i.getId_materia()%>">
                        <input type="hidden" name="dia" value="<%=i.getDia()%>">
                        <label for="bol">Numero de Boleta</label>
                        <input type="text" id="bol" name="boleta" class="inputtxt"/>
                        <input type="hidden" name="instruction" value="añadirFal">
                        <br>
                        <input type="submit" name="" value="Guardar" id="btn" class="inputbutn int"/>
                    </form>
                    <%
                        }%>

                    <p class="space">
                        <br>
                    </p>
                </div>
        </div>

        <!-- aqui apareceran las listas de los profesores
                °se extraera los nombres de cada alumno de la base de datos y luego se ordenaran en lista en este lugar
                °el maestro seleccionara si vinieron atraves de un checkbutton y luego seleccionará una se las opciones que esán en el div superiror
        -->
        <script type="text/javascript">
            //definimos los arreglos de las materias
            var mat1 = new Array("Geometria y Tri...", "Filosofia II", "Computacion basi...", "ingles II", "expresion oral...", "biologia basica", "historia de mex...", "orientacion juvenil...", "tic's");

            var mat2 = new Array("Calculo diferencial", "Fisica II", "Quimica II", "ingles IV", "Dibujo tecnico II", "PWS", "Bases de datos", "LPTI II", "TPPCC");

            var mat3 = new Array("Probabilidad y est...", "Fisica IV", "Quimica IV", "Ingles IV", "orientacion juvenil...", "Metodos agiles...", "Soporte de software", "Ingenieria de soft...", "LPTI IV", "Proyecto integrador");

            //funciones que hacn dinamica la lista
            function camb2() {
                //obtenermos el tamaño total del arreglo
                opc = mat1.length;
                //ponemos el tamaño del arreglo en el select
                document.getElementById("materias").length = opc;
                document.getElementById("pdfs").src = "Img/tec-computacion-escolarizado.pdf";
                for (var i = 0; i < opc; i++) {
                    //metemos una por una cada materia a el select
                    document.getElementById("materias").options[i].text = mat1[i];
                    //metemeos elnombtre de la materia como valor del select
                    document.getElementById("materias").options[i].value = mat1[i];
                }
            }
            //los mismo se repite en todas las funciones
            function camb4() {
                getElementById('ins').value = "4IV7";
            }
            function camb6() {
                opc = mat3.length;
                document.getElementById("materias").length = opc;
                document.getElementById("pdfs").src = "Img/ESFM-Licenciatura-en-Física-y-Matemáticas.pdf";
                for (var i = 0; i < opc; i++) {
                    document.acciones.opt.options[i].text = mat3[i];
                    document.acciones.opt.options[i].value = mat3[i];
                }
            }
        </script>

    </body>
    <%
        if (request.getAttribute("code") != null) {
    %>
    <%=request.getAttribute("code")%>
    <%
        }
    %>
</html>
