<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
    <%
        session.setAttribute("permissaoNecessaria","U");
    %>
    <util:verificarSessaoAtiva />
   
    <util:listarUsuarios />
</div>
</div>
<c:import url="rodape.jsp" />