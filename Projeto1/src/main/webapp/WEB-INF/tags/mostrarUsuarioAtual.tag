<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<c:choose>
    <c:when test="${usuarioAtual !=null}">${usuarioAtual}</c:when>
    <c:otherwise>N�o h� usu�rio ativo</c:otherwise>   
</c:choose>
