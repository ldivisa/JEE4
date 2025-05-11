<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="cabecalho.jsp" />
<div id="central">
        <util:verificarSessaoAtiva />
    <%
        HttpSession sessao = request.getSession();
        LoginDAO login = new LoginDAO();
        
        if (!login.getPermissao((String)sessao.getAttribute("acessoUsuario"),"U"))
            response.sendRedirect("index.jsp");
    %>
    <util:listarUsuarios />
</div>
</div>
<c:import url="rodape.jsp" />