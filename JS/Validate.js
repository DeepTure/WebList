//Patrones 100% intelektuales osi osi Uwu
var patron_pw_min=/[a-z]+/
var patron_pw_may=/[A-Z]+/
var patron_pw_num=/[0-9]+/
var patron_pw_sib=/[\W]+/
var patron_pw_inval_car=/[\s]+/
var patron_email=/^[\w+.]+@{1}[a-z]+[.]{1}com|web|org{1}$/
//No copiar marca registrada MarckDJ©

function Log_In() {
    let usr = document.getElementById("LogIn").Account.value
    let passwd = document.getElementById("LogIn").Password.value
    if((usr == "Kevin_Jafet" && passwd == "MoranDT01") || (usr == "Leo_Yeudiel" && passwd =="PiñaDT02") || (usr == "Daniel_Josias" && passwd == "CuellarDT03") || (usr =="Cristopher_De_La_O" && passwd == "FloresDT04")  || (usr =="Marco_Antonio" && passwd == "MotaDT05") || (usr =="Carlos_Saúl" && passwd == "AlferezDT06")){
        alert("Hola "+ usr)
    }else{
        alert("No se encontro el usuario")
    }
}

function changePassword() {
    let pass1 = document.getElementById("password").pass1.value
    let pass2 = document.getElementById("password").pass2.value
    let fuerza=0;
    if(pass1!=pass2 || pass1==null || pass2==null || pass1=="" || pass2==""){
        alert("Las contraseñas no coinciden")
    }else if(patron_pw_inval_car.test(pass1)){
        alert("la contraseña tiene caracteres no validos")
    }else{
        if(pass1.length<8){
            alert("el texto no es lo suficientemente largo")
        }else{
            fuerza+=1
        }
        if(!patron_pw_may.test(pass1)){
            alert("el texto no tiene almenos una mayuscula")
        }else{
            fuerza+=1
        }
        if(!patron_pw_min.test(pass1)){
            alert("el texto no tiene almenos una minuscula")
        }else{
            fuerza+=1
        }
        if(!patron_pw_num.test(pass1)){
            alert("el texto no tiene almenos un numero")
        }else{
            fuerza+=1
        }
        if(!patron_pw_sib.test(pass1)){
            alert("el texto no tiene almenos un simbolo")
        }else{
            fuerza+=1
        }
        alert("Tu contraseña tiene una fuerza de nivel: "+fuerza)
    }
}

function verEmail() {
    let email = document.getElementById("password").email.value
    if(patron_email.test(email)){
        alert("Bien")
    }else{
        alert("Correo electronico no reconocido")
    }
}
