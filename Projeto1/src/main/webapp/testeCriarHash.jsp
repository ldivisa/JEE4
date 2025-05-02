<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
    <util:criarHashSenhaArgon2 senhaEntrada="Caralho@estudos1" />
    Senha gerada: ${senhaHash}
    
    
    <util:checaHashSenhaArgon2 hash="${senhaHash}" senhaEntrada="teste"/>
    Teste de senha: ${senhaOk}
</div>
</div>
<c:import url="rodape.jsp" />