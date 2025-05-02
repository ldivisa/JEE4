<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
       <c:when test="${nomeCompletoUsuario !=null}">${nomeCompletoUsuario}</c:when>
    <c:otherwise> ninguém </c:otherwise>   
</c:choose>
