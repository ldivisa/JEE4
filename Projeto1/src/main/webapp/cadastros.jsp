<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="mensagem" tagdir="/WEB-INF/tags/" %>
<c:import url="cabecalhoLogin.jsp" />
<div id="central">

    <div id="conteudo">
        <div id="cadastros"> 
S            <h1>CADASTROS</h1>
            <table id="tabelaCadastros">
                <tr><td><%
                            LoginDAO login = new LoginDAO();
                            if (session.getAttribute("acessoUsuario") == null) {
                                response.sendRedirect("login.jsp");
                            }

                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"), "B")) {
                                out.print("<a href='BairrosController?processar=listar'><img src='imagens/Usuarios.png' alt='Bairros' height='200px' width='200px' title='Bairros'</a>");
                            }
                        %></td></tr>
            </table>
        </div>
    </div>

    <c:import url="rodape.jsp" /> 
