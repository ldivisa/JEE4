<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
    <util:verificarSessaoAtiva />
    <%//Checando se o usuario ativo tem a permissao neste modulo
        HttpSession sessao = request.getSession();
        LoginDAO login = new LoginDAO();
        if (!login.getPermissao((String) sessao.getAttribute("acessoUsuario"), "U"))
            response.sendRedirect("index.jsp");
    %>
    <div id="tituloAlterarUsuario">
        <h1>Alterar dados do  Usuário</h1>
    </div>
    <div id="formAlteraUsuario">
    <form id="formAlteraUsuario" method="get" action="alterarUsuario">
        <div class="campos">
            <label for="usuario">Usuário.:</label>
            <input type="text"  name="nomeUsuario" value="${param.nomeUsuario}" readonly="true" autofocus="true"/>
        </div>
        <div class="campos">
            <label for="nomeCompletoUsuario">Nome Completo Usuário.:</label>
            <input type="text"  name="nomeCompletoUsuario" required value="${param.nomeCompletoUsuario}"/>
        </div>
        <div class="campos">
            <label for="acessoUsuario">Acessos Usuário.:</label>
            <input type="text"  name="acessoUsuario" required value="${param.acessoUsuario}"/>
        </div>
        <div class="campos">
            <label for="gruposUsuario">Grupos Usuário.:</label>
            <input type="text"  name="gruposUsuario" required value="${param.gruposUsuario}"/>
        </div>
        <div class="campos">
            <label for="dataCadastro">Data Cadastro.:</label>
            <input type="date"  name="dataCadastro" required value="${param.dataCadastro}" readonly="true"/>
        </div>
        <div class="campos">
            <label for="dataUltimoAcesso">Data último acesso.:</label>
            <input type="text"  name="dataUltimoAcesso" " value="${param.dataUltimoAcesso}" readonly="true"/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <input type="checkbox"  name="ativo" <% if (request.getParameter("ativo").equals("1"))%>checked="on"<% else %>  />
        </div>
        <div class="campos">
            <input type="submit"  name="enviarAlteracoesUsuario" title="Gravar alterações no cadastro deste usuário" />
        </div>
    </form>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />