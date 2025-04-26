<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:choose>

    <c:when test="${status !=null}">
        ${status} 
    </c:when>
    <c:otherwise>
        Entre com seu usuário e senha
    </c:otherwise>   

</c:choose>
