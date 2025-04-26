<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="cabecalhoLogin.jsp" />
<div id="central">

    <form action="ServletLogar1" method="GET">
        <table border="1">
            <tr> <td colspan="2"><h1 id="TextoInformativo">Acesso ao sistema:</h1> </td></tr>
            <tr>
                <td>Usuário.:</td>
                <td><input placeholder="usuario" autocomplete="on" autofocus="true" type="text" name="usuario" required="true"></td>
            </tr>
            <tr>
                <td>Senha.:</td>
                <td><input placeholder="senha" type="password" name="senha" required="true"></td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="login" value="Acessar" >
                </td>
                <td>
                    <input type="reset" name="limpar" value="Limpar">
                </td>
            </tr>
            <tr><td colspan="2"><h1 id="TextoStatus">
                       <% /*String status=(String) request.getAttribute("status");
                        if (status != null){
                            out.println(status);
                            } else{
                            out.println("Digite seu usuário e senha");
                            }
                            */
                        %>
                        <c:choose>
                            <c:when test="${status !=null}">
                                ${status} 
                                </c:when>
                               <c:otherwise>
                                 Entre com seu usuário e senha
                              </c:otherwise>   
                            
                        </c:choose>
                    </h1> </td></tr>
        </table>
    </form>
</div>

                        <c:import url="rodape.jsp" /> 
