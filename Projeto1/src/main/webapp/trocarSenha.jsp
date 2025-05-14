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
        if (!login.getPermissao((String) sessao.getAttribute("acessoUsuario"), "S"))
            response.sendRedirect("index.jsp");
    %>
    <div id="tituloNovoUsuario">
        <h1>Trocar senha atual</h1>
    </div>
    <div id="formNovoUsuario">
                <form id="formTrocarSenha" method="get" action="trocarSenha">
        <div class="campos">
            <label for="senhaAtual">Senha Atual.......................:</label>
            <input type="password"  name="senhaAtual" required autofocus="true"/>
        </div>
        <div class="campos">
            <label for="senhaNova1">Nova Senha.......................:</label>
            <input type="password"  name="senhaNova1" required/>
        </div>
        <div class="campos">
            <label for="senhaNova2">Confirme a nova Senha.....:</label>
            <input type="password"  name="senhaNova2" required />
        </div>
        <div class="campos">
            <input type="submit"  name="trocarSenha" title="Gravar informações no cadastro deste novo usuário" />
        </div>
    </form>
        <h1> ${mensagem} </h1>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />