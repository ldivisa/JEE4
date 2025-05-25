<%@tag import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@tag import="com.mysql.cj.jdbc.*"%>
<%@tag import="org.hopto.depositodivisa.factory.ConexaoFactory"%>
<%@tag import="com.mysql.cj.protocol.Resultset"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>
<%
    LoginDAO login = new LoginDAO();
    Integer pagMax = null;
    Integer limite = 4;
    Integer numRegistros = Integer.parseInt(login.contagemRegistros());
    if (session.getAttribute("limite")!=null){
    limite = Integer.parseInt((String) session.getAttribute("limite"));
    }
    pagMax = (numRegistros / limite);
    if (numRegistros%limite >0)
        pagMax++;
    session.setAttribute("pagMax", String.valueOf(pagMax));
%>
    <h1>Listagem de usuários do sistema:</h1>
    <c:set var="contador" value="0"/>
    <c:set var="corA" value="lightblue"/>
    <c:set var="corB" value="white"/>
 
    <table border="1">
        <tr><td colspan="6"><a href="novoUsuario.jsp" title="Criar Novo Usuário"><img src="imagens/novoUsuario.jpg" alt="Novo usuário" height="50px" widht="50px"> </td></tr>
        <tr class="tabelaLinhaespecial">
            <td>Nome</td>
            <td>Nome Completo</td>
            <td>Nivel Acesso</td>
            <td>Grupo Usuários</td>
            <td>Data Cadastro</td>
            <td>Data último acesso</td>
            <td>Ativo</td>
            <td>Alterar</td>
            <td>Excluir</td>
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
                <td> ${usuarios['gruposUsuario']} </td>
                <td> ${usuarios['dataCadastro']} </td>
                <td> ${usuarios['dataUltimoAcesso']} </td>
                <td> ${usuarios['ativo']} </td>
                <td><a href="UsuariosController?processar=alterar&nomeUsuario=${usuarios['nomeUsuario']}&nomeCompletoUsuario=${usuarios['nomeCompletoUsuario']}&acessoUsuario=${usuarios['acessoUsuario']}&gruposUsuario=${usuarios['gruposUsuario']}&dataCadastro=${usuarios['dataCadastro']}&dataUltimoAcesso=${usuarios['dataUltimoAcesso']}&ativo=${usuarios['ativo']} "><img src="imagens/Editar.png" height="50px" width="50px" alt="Editar" title="Editar usuário ${usuarios['nomeUsuario']}"></a></td>
                <c:choose>
                    <c:when test="${usuarios['ativo']==1}">
                        <c:set var="usuarioAlterarEstado" value="${usuarios['nomeUsuario']}" scope="session"/>
                        <td> <a href="desativarUsuario?usuarioAlterarEstado=${usuarios['nomeUsuario']}"><img src="imagens/desativar.png" height="50px" width="50px" alt="desativar" title="Desativar o usuário ${usuarios['nomeUsuario']}"></a></td>
                    </c:when>
                    <c:otherwise>
                        <c:set var="usuarioAlterarEstado" value="${usuarios['nomeUsuario']}" scope="session"/>
                        <td> <a href="ativarUsuario?usuarioAlterarEstado=${usuarios['nomeUsuario']}"><img src="imagens/ativar.png" height="50px" width="50px" alt="ativar" title="Ativar o usuário ${usuarios['nomeUsuario']}"></a></td>
                    </c:otherwise>
                </c:choose>
                
                    </tr>   
                    <c:set var="contador" value="${contador+1}"/>
        </c:forEach>
        <tr>            
            <td><a href="ServletPaginaAnteriorUsuarios"
            </a>Anterior</td>
        <td><a href="ServletProximaPaginaUsuarios"
        </a>Posterior</td>
        <td colspan="7">
            <%
            for (int i=1;i<=pagMax;i++){
            if (Integer.parseInt((String)session.getAttribute("numeroPagina"))==i){
                    out.print("<b>"+i+"</b> ");                    
                } else {
                    out.print("<a href=\"listausuariosPaginada.jsp?irPagina="+i+"\">"+i+"</a> ");
                }
                }
            %>
        </td>
            </tr>
            <tr><td class="tabelaLinhaespecial" colspan="9">Página ${numeroPagina}/${pagMax} - Total de usuários registrados: <depositodivisa:contagemUsuarios/></td></tr>

    </table>
