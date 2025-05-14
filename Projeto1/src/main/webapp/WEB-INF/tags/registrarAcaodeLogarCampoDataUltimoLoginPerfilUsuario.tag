<%@tag import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:choose>
    <c:when test="${nomeUsuario !=null}">
        <% 
            LoginDAO login = new LoginDAO();
            String usuarioNome =(String) session.getAttribute("nomeUsuario");
            login.registrarDataUltimoLoginPerfilUsuario(usuarioNome);
        %>

    </c:when>
    
</c:choose>