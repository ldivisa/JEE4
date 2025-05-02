<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:choose>
    <c:when test="${usuarioAtual == null}"> 
        <%response.sendRedirect("login.jsp");%>
     </c:when>
     
</c:choose>
