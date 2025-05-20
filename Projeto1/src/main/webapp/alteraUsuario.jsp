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
            <input type="text"  name="nomeUsuario" value="${param.nomeUsuario}" readonly autofocus maxlength="40"/>
        </div>
        <div class="campos">
            <label for="nomeCompletoUsuario">Nome Completo Usuário.:</label>
            <input type="text"  name="nomeCompletoUsuario" required value="${param.nomeCompletoUsuario}" maxlength="50" size="50"/>
        </div>
        <div class="campos">
            <label for="acessoUsuario">Acessos Usuário.:</label>
            <input type="text"  name="acessoUsuario" required value="${param.acessoUsuario}" maxlength="34" size="34"/>
        </div>
        <div class="campos">
            <label for="gruposUsuario">Grupos Usuário.:</label>
            <input type="text" name="gruposUsuario" required value="${param.gruposUsuario}" maxlength="30" />
        </div>
        <div class="campos">
            <label for="dataCadastro">Data Cadastro.:</label>
            <input type="text" name="dataCadastro" required value="${param.dataCadastro}" readonly />
        </div>
        <div class="campos">
            <label for="dataUltimoAcesso">Data último acesso.:</label>
            <input type="text"  name="dataUltimoAcesso" value="${param.dataUltimoAcesso}" readonly/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <input type="checkbox"  name="ativo" <% if (request.getParameter("ativo").equals("1"))%>checked="on"<% else %>  />
        </div>
        <div class="campos">
            <button type="submit"  name="enviarAlteracoesUsuario" label="Alterar" title="Gravar alterações no cadastro deste usuário">Alterar Usuário</>
        </div>
    </form>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />