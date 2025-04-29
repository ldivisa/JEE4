<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="org.hopto.depositodivisa.model.Login"%>
<%@page import="org.hopto.depositodivisa.model.Login"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>

<c:import url="cabecalho.jsp" />
<div id="central">
    <h1>Lista usuários via scriptlet Java</h1>
    <% 
    List<Login> listaUsuarios= (List<Login>) request.getAttribute("sessaoListaUsuarios");
    for (Iterator iterator = listaUsuarios.iterator();
    iterator.hasNext();){
        Login usuario = (Login) iterator.next();
        out.println(usuario.getNomeUsuario());
        out.println(usuario.getNomeCompletoUsuario());
        out.println("<br>");
        
        }
        out.println("<br>Fim do iterator<br>");
    %>
    
    <% 
    List<Login> listaUsuarios2= (List<Login>) request.getAttribute("sessaoListaUsuarios");
    for (Login usuario:listaUsuarios2){
        out.print(usuario.getNomeUsuario());
        out.print(usuario.getSenhaUsuario());
        }
       
        out.println("<br>Fim do for<br>");
    %>
           
    </div>
</div>
<c:import url="rodape.jsp" />