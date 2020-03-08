function validar() {
    //ponganle id="usuario" al usuario y id="contraseña" a la contraseña
    let usuario=document.getElementById("login").usuario.value
    let contraseña=document.getElementById("login").contraseña.value
    var pass = 0;
    if((usuario=="Kevin_Jafet" && contraseña=="MoranDT01") || (usuario=="Leo_Yeudiel" && contraseña=="PiñaDT02") || (usuario=="Daniel_Josias" && contraseña=="CuellarDT03") || (usuario=="Cristopher_De_La_O" && contraseña=="FloresDT04")  || (usuario=="Marco_Antonio" && contraseña=="MotaDT05") || (usuario=="Carlos_Saúl" && contraseña=="AlferezDT06")){
        pass = 1;
    }
    if (pass == 1){
        alert("Hola "+usuario)
    }
    else{
        alert("No reconocido 'version de prueba'")
    } 
}
