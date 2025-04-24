<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="cabecalho.jsp" />
 <div id="central">
            
<form action="ServletLogar" method="GET">
    <table border="1">
        <tr> <td colspan="2">Acesso ao sistema: </td></tr>
        <tr>
            <td>Usuário.:</td>
            <td><input type="text" name="usuario"></td>
       </tr>
        <tr>
            <td>Senha.:</td>
            <td><input type="password" name="senha"></td>
       </tr>
       <tr><td colspan="2">Status... </td></tr>
    </table>
</form>
</div>
</div>
<c:import url="rodape.jsp" /> 
