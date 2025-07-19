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
        if (!login.getPermissao((String) sessao.getAttribute("acessoUsuario"), "B"))
            response.sendRedirect("index.jsp");
    %>
    <div id="tituloAlterarBairro">
        <h1>Alterar dados do  Bairro</h1>
    </div>
    <%
        session.setAttribute("bairroOriginal",request.getParameter("bairroNome"));
    
    %>
    <div id="formAlteraBairro">
    <form id="formAlteraBairro" method="get" action="BairrosController">
        <div class="campos">
            <label for="usuario">Bairro.:</label>
            <input type="text"  name="bairroNome" value="${param.bairroNome}"  autofocus maxlength="40"/>
        </div>
        <div class="campos">
            <label for="ativo">Ativo.:</label>
            <input type="checkbox"  name="ativo" <% if (request.getParameter("ativo").equals("1"))
                   out.print("checked");
                   else
                   out.print("");
                   %>
                   />
        </div>
        <div class="campos">
            <button type="button" hidden  name="bairroOriginal" value="<%=session.getAttribute("bairroOriginal")%>"></button>
        </div>
        <div class="campos">
            <button type="submit"  name="processar" value="alterar" title="Gravar alterações no cadastro deste bairro">Alterar Bairro</>
        </div>
    </form>
    </div>
</div>
</div>
<c:import url="rodape.jsp" />