<%@tag  body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>

    <h1>Listagem de usuários do sistema:</h1>
    <c:set var="contador" value="0"/>
    <c:set var="corA" value="lightblue"/>
    <c:set var="corB" value="white"/>
    <table border="1">
        <tr style="background-color: grey">
            <td>Nome</td>
            <td>Nome Completo</td>
            <td>Nivel Acesso</td>
            <td>Grupo Usuários</td>
            <td>Data Cadastro</td>
            <td>Data último acesso</td>
        </tr>
        <c:forEach var="usuarios" items="${sessaoListaUsuarios}">
            <c:choose>
                <c:when test="${contador %2 == 0}">
                    <tr style="background: ${corA}">
                    </c:when>        
                    <c:otherwise>
                    <tr style="background: ${corB}">
                    </c:otherwise>
                </c:choose>

                <td> ${usuarios['nomeUsuario']} </td>
                <td> ${usuarios['nomeCompletoUsuario']} </td>
                <td> ${usuarios['acessoUsuario']} </td>
                <td> ${usuarios['grupoUsuarios']} </td>
                <td> ${usuarios['dataCadastro']} </td>
                <td> ${usuarios['dataUltimoAcesso']} </td>

            </tr>   
            <c:set var="contador" value="${contador+1}"/>
        </c:forEach>
        <tr><td colspan="6">Total de usuários registrados: ${contador}</td></tr>

    </table>
