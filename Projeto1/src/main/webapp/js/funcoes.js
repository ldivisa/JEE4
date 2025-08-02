function validarSenha(input){
    if(input.value !==document.getElementById("senhaNova1").value){
        input.setCustomValidity('As senhas não são iguais');
    } else {
        input.setCustomValidity('Ok');
    }
    
}

function formata_mascara(campo_passado,mascara){
    var campo = campo_passado.value.length;
    var saida = mascara.substring(0,1);
    var texto = mascara.substring(campo);
    if(texto.substring(0,1) !== saida){
        campo_passado.value += texto.substring(0,1);
    }
}

function Numero(e){
    var tecla;
    navegador = /msie/i.test(navigator.userAgent);
    if(navegador)
            tecla = event.keyCode;
        else
            tecla = e.which;
    if(tecla > 47 && tecla < 58) // numeros 0 ate 9
        return true;
    else
    {
        if (tecla !==8) // backspace
            return false;
        else
            return true;
    }
}

function lettersOnly(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
        ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 65 || charCode > 90) &&
        (charCode < 97 || charCode > 122)) {
        alert("Este campo aceita apenas letras.");
        return false;
    }
    return true;
}

function somenteMaiusculas(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode :
        ((evt.which) ? evt.which : 0));
        
    if ((charCode < 65 || charCode > 90)&&(charCode !==8)) {
        alert("Este campo aceita apenas letras maiúsculas.");
        //alert(charCode);
        return false;
    }
    return true;
}