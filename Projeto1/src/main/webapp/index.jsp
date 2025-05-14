<%-- 
    Document   : index2
    Created on : 22 de abr. de 2025, 19:13:43
    Author     : luiz.souza
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>
<depositodivisa:verificarSessaoAtiva />
<depositodivisa:registrarAcaodeLogarCampoDataUltimoLoginPerfilUsuario />
<c:import url="cabecalho.jsp" />
<div id="central"></div>
</div>
<c:import url="rodape.jsp" />