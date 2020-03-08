function validar() {
    //ponganle id="usuario" al usuario y id="contraseña" a la contraseña
    let usuario=document.getElementById("login").usuario.value
    let contraseña=document.getElementById("login").contraseña.value
    //usuario Kevin
    if(usuario=="Kevin_Jafet" && contraseña=="MoranDT01"){
        alert("Hola "+usuario)
    //usuario Marco
    }else if(usuario=="Marco_Antonio" && contraseña=="MotaDT02"){
        alert("Hola "+usuario)
    //usuario Daniel
    }else if(usuario=="Daniel_Cuellar" && contraseña=="BetancourtDT03"){
        alert("Hola "+usuario)
    //usuario De la O
    }else if(usuario=="Cristopher_De_La_O" && contraseña=="FloresDT04"){
        alert("Hola "+usuario)
    //usuario Piña
    }else if(usuario=="Leo_Yeudiel" && contraseña=="PiñaDT05"){
        alert("Hola "+usuario)
    }else{
        alert("no reconocido 'version de prueba'")
    }
}
