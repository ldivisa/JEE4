<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String nomeCompletoUsuario;
    if (session.getAttribute("nomeCompletoUsuario") != null) {
        nomeCompletoUsuario = (String) session.getAttribute("nomeCompletoUsuario");
    } else {
        nomeCompletoUsuario = "ninguém logado no sistema";
    }
    request.setAttribute("nomeCompletoUsuario", nomeCompletoUsuario);
%>
<%=session.getAttribute("nomeCompletoUsuario")%> -  Id da sessão do usuário: <%= session.getId() %>