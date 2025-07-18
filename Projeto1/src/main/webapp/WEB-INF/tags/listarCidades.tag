<%@tag  body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>

    <h1>Listagem de usuários do sistema:</h1>
    <c:set var="contador" value="0"/>
    <c:set var="corA" value="lightblue"/>
    <c:set var="corB" value="white"/>
    <table border="1">
        <tr><td colspan="6"><a href="novaCidade.jsp" title="Registrar Nova Cidade"><img src="imagens/novaCidade.jpg" alt="Nova cidade" height="50px" widht="50px"> </td></tr>
        <tr class="tabelaLinhaespecial">
            <td>Nome</td>
            <td>Ativo</td>
            <td>Alterar</td>
            <td>Excluir</td>
        </tr>
        <c:forEach var="cidades" items="${sessaoListaCidades}">
            <c:choose>
                <c:when test="${contador %2 == 0}">
                    <tr style="background: ${corA}">
                    </c:when>        
                    <c:otherwise>
                    <tr style="background: ${corB}">
                    </c:otherwise>
                </c:choose>
                      
                <td> ${cidades['nomeCidade']} </td>
                <td> ${cidades['ativo']} </td>
                <td><a href="alteraCidade.jsp?nomeCidade=${cidades['nomeCidade']}&ativo=${cidades['ativo']} "><img src="imagens/Editar.png" height="50px" width="50px" alt="Editar" title="Editar cidade ${cidades['nomeCidade']}"></a></td>
                <c:choose>
                    <c:when test="${cidades['ativo']==1}">
                        <c:set var="cidadeAlterarEstado" value="${cidades['nomeCidade']}" scope="session"/>
                        <td> <a href="desativarCidade?cidadeAlterarEstado=${cidades['nomeCidade']}"><img src="imagens/desativar.png" height="50px" width="50px" alt="desativar" title="Desativar a cidade ${cidades['nomeCidade']}"></a></td>
                    </c:when>
                    <c:otherwise>
                        <c:set var="cidadeAlterarEstado" value="${usuarios['nomeCidade']}" scope="session"/>
                        <td> <a href="ativarCidade?cidadeAlterarEstado=${cidades['nomeCidade']}"><img src="imagens/ativar.png" height="50px" width="50px" alt="ativar" title="Ativar a cidade ${cidades['nomeCidade']}"></a></td>
                    </c:otherwise>
                </c:choose>
                
            </tr>   
            <c:set var="contador" value="${contador+1}"/>
        </c:forEach>
        <tr><td class="tabelaLinhaespecial" colspan="9">Total de cidades registradas: ${contador}</td></tr>

    </table>
