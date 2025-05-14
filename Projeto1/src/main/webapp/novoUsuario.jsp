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
    <div id="tituloNovoUsuario">
        <h1>Novo Usuário</h1>
    </div>
    <div id="formNovoUsuario">
        <form id="formNovoUsuario" method="get" action="criarNovoUsuario">
        <div class="campos">
            <label for="usuario">Usuário.:</label>
            <input type="text"  name="nomeUsuario" required autofocus="true" maxlength="30" size="30"/>
        </div>
        <div class="campos">
            <label for="nomeCompletoUsuario">Nome Completo Usuário.:</label>
            <input type="text"  name="nomeCompletoUsuario" required maxlength="100" size="30"/>
        </div>
        <div class="campos">
            <label for="acessoUsuario">Acessos Usuário.:</label>
            <input type="text"  name="acessoUsuario" required maxlenght="34" size="34"/>
        </div>
        <div class="campos">
            <label for="gruposUsuario">Grupos Usuário.:</label>
            <input type="text"  name="gruposUsuario" required autocomplete="false"  maxlength="20" size="20"/>
        </div>
        <div class="campos">
            <label for="dataCadastro">Data Cadastro.:</label>
            <% String hoje =new java.text.SimpleDateFormat("YYYY-MM-dd").format(new java.util.Date());%>
            <input type="date"  name="dataCadastro" required value="<%=hoje%>" min="2025-05-01" />
        </div>
        <div class="campos">
            <label for="senha">Senha .:</label>
            <input type="password"  name="senha" required autocomplete="false"  maxlength="20" size="20"/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <input type="checkbox"  name="ativo" checked="on" />
        </div>
        <div class="campos">
            <input type="submit"  name="enviarNovoUsuario" title="Gravar informações no cadastro deste novousuário" />
        </div>
    </form>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />