<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="mensagem" tagdir="/WEB-INF/tags/" %>
<c:import url="cabecalhoLogin.jsp" />
<div id="central">

    <div id="conteudo">
                <div id="menu"> 
                    <nav>
                        <h1>MENUS</h1>
                        <ol>
                            <%
                                LoginDAO login = new LoginDAO();
                            if(session.getAttribute("acessoUsuario")==null){
                                response.sendRedirect("login.jsp");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"B")){
                            out.print("<li><a href='BairrosController?processar=listar'><img src='imagens/Usuarios.png' alt='alt' height='50px' width='50px' title='Bairros'</a></li>");
                                }
                                %>
                    </h1> </td></tr>
        </table>
    </form>
</div>

<c:import url="rodape.jsp" /> 
