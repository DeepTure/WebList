function Log_In() {
    let usr = document.getElementById("LogIn").Account.value
    let passwd = document.getElementById("LogIn").Password.value
    if((usr == "Kevin_Jafet" && passwd == "MoranDT01") || (usr == "Leo_Yeudiel" && passwd =="PiñaDT02") || (usr == "Daniel_Josias" && passwd == "CuellarDT03") || (usr =="Cristopher_De_La_O" && passwd == "FloresDT04")  || (usr =="Marco_Antonio" && passwd == "MotaDT05") || (usr =="Carlos_Saúl" && passwd == "AlferezDT06")){
        alert("Hola "+ usr)
    }else{
        alert("No se encontro el usuario")
    }
}
