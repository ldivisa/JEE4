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
    Integer numRegistros;
    numRegistros = (Integer.valueOf(login.contagemRegistros((String) session.getAttribute("usuarioPesquisar"),(String) session.getAttribute("tipoPesquisa"))));
    if (session.getAttribute("limite") != null) {
        limite = Integer.parseInt((String) session.getAttribute("limite"));
    }
    pagMax = (numRegistros / limite);
    if (numRegistros % limite > 0) {
        pagMax++;
    }
    session.setAttribute("pagMax", String.valueOf(pagMax));
%>
<h1>Listagem de usuários do sistema:</h1>
<c:set var="contador" value="0"/>
<c:set var="corA" value="lightblue"/>
<c:set var="corB" value="white"/>

<table border="1">
    <tr><td colspan="6"><a href="novoUsuario.jsp" title="Criar Novo Usuário"><img src="imagens/novoUsuario.jpg" alt="Novo usuário" height="50px" widht="50px"> </td></tr>
                <tr class="tabelaLinhaespecial">
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=nomeUsuario">Nome</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=nomeCompletoUsuario">Nome Completo</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=acessoUsuario">Nivel Acesso</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=gruposUsuario">Grupo Usuários</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=dataCadastro">Data Cadastro</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=dataUltimoAcesso">Data último acesso</a></td>
                    <td><a href="UsuariosController?acao=listar&ordenacaoUsuario=ativo">Ativo</a></td>
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
                        <td><a href="alteraUsuario.jsp?nomeUsuario=${usuarios['nomeUsuario']}&nomeCompletoUsuario=${usuarios['nomeCompletoUsuario']}&acessoUsuario=${usuarios['acessoUsuario']}&gruposUsuario=${usuarios['gruposUsuario']}&dataCadastro=${usuarios['dataCadastro']}&dataUltimoAcesso=${usuarios['dataUltimoAcesso']}&ativo=${usuarios['ativo']} "><img src="imagens/Editar.png" height="50px" width="50px" alt="Editar" title="Editar usuário ${usuarios['nomeUsuario']}"></a></td>
                                <c:choose>
                                    <c:when test="${usuarios['ativo']==1}">
                                        <c:set var="usuarioAlterarEstado" value="${usuarios['nomeUsuario']}" scope="session"/>
                                <td> <a href="UsuariosController?processar=desativar&usuarioAlterarEstado=${usuarios['nomeUsuario']}"><img src="imagens/desativar.png" height="50px" width="50px" alt="desativar" title="Desativar o usuário ${usuarios['nomeUsuario']}"></a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="usuarioAlterarEstado" value="${usuarios['nomeUsuario']}" scope="session"/>
                                <td> <a href="UsuariosController?processar=ativar&usuarioAlterarEstado=${usuarios['nomeUsuario']}"><img src="imagens/ativar.png" height="50px" width="50px" alt="ativar" title="Ativar o usuário ${usuarios['nomeUsuario']}"></a></td>
                                    </c:otherwise>
                                </c:choose>

                    </tr>   
                    <c:set var="contador" value="${contador+1}"/>
                </c:forEach>
                <tr>            


                    <td colspan="7">
                        <%
                            if (Integer.parseInt((String) session.getAttribute("numeroPagina")) > 1) {
                                out.print("<a href='UsuariosController?processar=paginaAnterior'>Anterior</a> ");
                            }
                            for (int i = 1; i <= pagMax; i++) {
                                if (Integer.parseInt((String) session.getAttribute("numeroPagina")) == i) {
                                    out.print("<b>" + i + "</b> ");
                                } else {
                                    out.print("<a href=\"listausuariosPaginada.jsp?irPagina=" + i + "\">" + i + "</a> ");
                                }
                            }
                            if (Integer.parseInt((String) session.getAttribute("numeroPagina")) < Integer.parseInt((String) session.getAttribute("pagMax"))) {
                                out.print(" <a href='UsuariosController?processar=proximaPagina'>Posterior</a>");
                            }
                        %>
                    </td>
                </tr>
                <tr><td class="tabelaLinhaespecial" colspan="9">Página ${numeroPagina}/${pagMax} - Total de usuários registrados: <depositodivisa:contagemUsuarios/> - Ordenação: ${ordenacaoUsuario}</td></tr>
                <tr><form action="UsuariosController?processar=pesquisar" autocomplete="false">
                    <td colspan="9">Pesquisar por:
                        <select name="tipoPesquisa"  accesskey="o">
                            <% 
                            String tipoPesquisa = (String) session.getAttribute("tipoPesquisa");
                            if (tipoPesquisa.equalsIgnoreCase("nomeUsuario"))
                            out.println("<option value='nomeUsuario' selected='selected'>Nome do usuário</option>");
                            else
                            out.println("<option value='nomeUsuario'>Nome do usuário</option>");
                            
                            if (tipoPesquisa.equalsIgnoreCase("acessoUsuario"))
                            out.println("<option value='acessoUsuario' selected='selected'>Nível</option>");
                            else
                            out.println("<option value='acessoUsuario'>Nível</option>");

                            if (tipoPesquisa.equalsIgnoreCase("nomeCompletoUsuario"))
                            out.println("<option value='nomeCompletoUsuario' selected='selected'>Nome Completo Usuário</option>");
                            else
                            out.println("<option value='nomeCompletoUsuario'>Nome Completo Usuário</option>");
                            
                            if (tipoPesquisa.equalsIgnoreCase("ativo"))
                            out.println("<option value='ativo' selected='selected'>Ativo?</option>");
                            else
                            out.println("<option value='ativo'>Ativo</option>");
                            %>
                          
                        </select>(o)
                        <input type="text" name="usuarioPesquisar" accesskey="p" autofocus="true" title="Pesquisar usuários" value=
                                                      <%
                                                          if (session.getAttribute("usuarioPesquisar") != null)
                                                              out.println(session.getAttribute("usuarioPesquisar"));
                                                      %>
                                                      >(p)
                        <button type="submit" name="processar" value="pesquisar" accesskey="l" title="alt+shift+l"><img src="imagens/lupa.png" width="25px" height="25px" alt="Pesquisar!"/>(l)</button></td>
                </form></tr>

</table>
