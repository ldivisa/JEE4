<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
    <util:verificarSessaoAtiva />
    <%//Checando se o bairro ativo tem a permissao neste modulo
        HttpSession sessao = request.getSession();
        LoginDAO login = new LoginDAO();
        if (!login.getPermissao((String) sessao.getAttribute("acessoUsuario"), "B"))
            response.sendRedirect("index.jsp");
    %>
    <div id="tituloNovoBairro">
        <h1>Novo Bairro</h1>
    </div>
    <div id="formNovoBairro">
        <form id="formNovoBairro" method="get" action="BairrosController">
        <div class="campos">
            <label for="bairro">Bairro.:</label>
            <input type="text"  name="bairroNome" required autofocus maxlength="30" size="30"/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <input type="checkbox"  name="ativo" checked />
        </div>
        <div class="campos">
            <button type="submit"  name="processar" value="novo" title="Gravar informações no cadastro deste novo bairro">Gravar novo bairro</>
        </div>
    </form>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />