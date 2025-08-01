function validarSenha(input){
    if(input.value !==document.getElementById("senhaNova1").value){
        input.setCustomValidity('As senhas não são iguais');
    } else {
        input.setCustomValidity('Ok');
    }
    
}

function formata_mascara(campo_passado,mascara){
    var campo = campo_passado.value.lenght;
    var saida = mascara.substring(0,1);
    var texto = mascara.substring(campo);
    if(texto.substring(0,1) !== saida){
        campo_passado.value += texto.substring(0,1);
        
    }
    
}

