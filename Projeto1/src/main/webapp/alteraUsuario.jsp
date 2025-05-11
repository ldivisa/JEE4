<link rel="stylesheet" type="text/css" href="css/estilos.css" media="screen">
<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
    <h1>Altera Usuário</h1>
    <util:verificarSessaoAtiva />
    <%
        HttpSession sessao = request.getSession();
        LoginDAO login = new LoginDAO();

        if (!login.getPermissao((String) sessao.getAttribute("acessoUsuario"), "U"))
            response.sendRedirect("index.jsp");
    %>

    <form id="formAlteraUsuario" method="get" action="alterarUsuario">
        <div class="campos">
            <label for="usuario">Usuário.:</label>
            <input type="text" disabled  name="nomeUsuario" value="${param.nomeUsuario}"/>
        </div>
        <div class="campos">
            <label for="nomeCompletoUsuario">Nome Completo Usuário.:</label>
            <input type="text"  name="nomeUsuario" value="${param.nomeCompletoUsuario}"/>
        </div>
        <div class="campos">
            <label for="acessoUsuario">Acessos Usuário.:</label>
            <input type="text"  name="acessoUsuario" value="${param.acessoUsuario}"/>
        </div>
        <div class="campos">
            <label for="gruposUsuario">Grupos Usuário.:</label>
            <input type="text"  name="gruposUsuario" value="${param.gruposUsuario}"/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <c:choose>
                <c:when test="${param.ativo}==1">
                    <c:set scope="session" value="true" var="estadoUsuario"/>
                </c:when>
            </c:choose>
            <input type="checkbox"  value=" " name="ativo" checked="${session.estadoUsuario}" />
        </div>
    </form>
</div>
</div>
<c:import url="rodape.jsp" />