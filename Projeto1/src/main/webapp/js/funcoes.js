function validarSenha(input){
    if(input.value !==document.getElementById("senhaNova1").value){
        input.setCustomValidity('As senhas não são iguais');
    } else {
        input.setCustomValidity('Ok');
    }
    
}

