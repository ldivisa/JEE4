<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.hopto.depositodivisa.model.Login"%>
<%@page import="org.hopto.depositodivisa.model.Login"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>

<c:import url="cabecalho.jsp" />
<div id="central">
    <h1>Lista usuários via scriptlet Java</h1>
    <table border="1">
        <tr>
            <td>Nome</td>
            <td>Nome Completo</td>
            <td>Nivel Acesso</td>
            <td>Grupo Usuários</td>
            <td>Data Cadastro</td>
            <td>Data último acesso</td>
            
        </tr>
     <%
    List<Login> listaUsuarios= (List<Login>) request.getAttribute("sessaoListaUsuarios");
    for (Iterator iterator = listaUsuarios.iterator();
    iterator.hasNext();){
       Login usuario = (Login) iterator.next();
        out.println("<tr>");
        out.println("<td>"+usuario.getNomeUsuario()+"</td>");
        out.println("<td>"+usuario.getNomeCompletoUsuario()+"</td>");
        out.println("<td>"+usuario.getAcessoUsuario()+"</td>");
        out.println("<td>"+usuario.getGrupoUsuarios()+"</td>");
        out.println("<td>"+usuario.getDataCadastro()+"</td>");
        out.println("<td>"+usuario.getDataUltimoAcesso()+"</td>");
        
        }
    %>
    
    
    </table>
    </div>
</div>
<c:import url="rodape.jsp" />