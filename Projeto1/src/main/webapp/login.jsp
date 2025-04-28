<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="mensagem" tagdir="/WEB-INF/tags/" %>
<c:import url="cabecalhoLogin.jsp" />
<div id="central">

    <form action="ServletLogar1" method="GET">
        <table border="1" id="table" class="tabela">
            <tr> <td colspan="2"><h1 id="TextoInformativo">Acesso ao sistema:</h1> </td></tr>
            <tr>
                <td>Usuário.:</td>
                <td><input placeholder="usuario" autocomplete="on" autofocus type="text" name="usuario" required></td>
            </tr>
            <tr>
                <td>Senha.:</td>
                <td><input placeholder="senha" type="password" name="senha" required></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="login" value="Acessar" >
                    <input type="reset" name="limpar" value="Limpar">
                </td>
            </tr>
            <tr><td colspan="2"><h1 id="TextoStatus">
                        <mensagem:mensagemStatusLogin />
                    </h1> </td></tr>
        </table>
    </form>
</div>

<c:import url="rodape.jsp" /> 
