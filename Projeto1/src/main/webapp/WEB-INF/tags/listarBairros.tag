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
    numRegistros = (Integer.valueOf(login.contagemRegistros((String) session.getAttribute("bairroPesquisar"), (String) session.getAttribute("tipoPesquisa"))));
    if (session.getAttribute("limite") != null) {
        limite = Integer.parseInt((String) session.getAttribute("limite"));
    }
    pagMax = (numRegistros / limite);
    if (numRegistros % limite > 0) {
        pagMax++;
    }
    session.setAttribute("pagMax", String.valueOf(pagMax));
    String tipoPesquisa = (String) session.getAttribute("tipoPesquisa");
    String bairroPesquisar = (String) session.getAttribute("bairroPesquisar");
%>
<h1>Listagem de usuários do sistema:</h1>
<c:set var="contador" value="0"/>
<c:set var="corA" value="lightblue"/>
<c:set var="corB" value="white"/>

<table border="1">
    <tr><td colspan="6"><a href="novoBairro.jsp" title="Criar Novo Usuário"><img src="imagens/novoBairro.jpg" alt="Novo usuário" height="50px" widht="50px"> </td></tr>
                <tr class="tabelaLinhaespecial">
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=nomeBairro">Nome</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=nomeCompletoBairro">Nome Completo</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=acessoBairro">Nivel Acesso</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=gruposBairro">Grupo Usuários</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=dataCadastro">Data Cadastro</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=dataUltimoAcesso">Data último acesso</a></td>
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=ativo">Ativo</a></td>
                    <td>Alterar</td>
                    <td>Excluir</td>
                </tr>
                <c:forEach var="bairros" items="${sessaoListaBairros}">
                    <c:choose>
                        <c:when test="${contador %2 == 0}">
                            <tr style="background: ${corA}">
                            </c:when>        
                            <c:otherwise>
                            <tr style="background: ${corB}">
                            </c:otherwise>
                        </c:choose>

                        <td> ${bairros['nomeBairro']} </td>
                        <td> ${bairros['nomeCompletoBairro']} </td>
                        <td> ${bairros['acessoBairro']} </td>
                        <td> ${bairros['gruposBairro']} </td>
                        <td> ${bairros['dataCadastro']} </td>
                        <td> ${bairros['dataUltimoAcesso']} </td>
                        <td> ${bairros['ativo']} </td>
                        <td><a href="alteraBairro.jsp?nomeBairro=${bairros['nomeBairro']}&nomeCompletoBairro=${bairros['nomeCompletoBairro']}&acessoBairro=${bairros['acessoBairro']}&gruposBairro=${bairros['gruposBairro']}&dataCadastro=${bairros['dataCadastro']}&dataUltimoAcesso=${bairros['dataUltimoAcesso']}&ativo=${bairros['ativo']} "><img src="imagens/Editar.png" height="50px" width="50px" alt="Editar" title="Editar usuário ${bairros['nomeBairro']}"></a></td>
                                <c:choose>
                                    <c:when test="${bairros['ativo']==1}">
                                        <c:set var="bairroAlterarEstado" value="${bairros['nomeBairro']}" scope="session"/>
                                <td> <a href="BairrosController?processar=desativar&bairroAlterarEstado=${bairros['nomeBairro']}"><img src="imagens/desativar.png" height="50px" width="50px" alt="desativar" title="Desativar o usuário ${bairros['nomeBairro']}"></a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="bairroAlterarEstado" value="${bairros['nomeBairro']}" scope="session"/>
                                <td> <a href="BairrosController?processar=ativar&bairroAlterarEstado=${bairros['nomeBairro']}"><img src="imagens/ativar.png" height="50px" width="50px" alt="ativar" title="Ativar o usuário ${bairros['nomeBairro']}"></a></td>
                                    </c:otherwise>
                                </c:choose>
                    </tr>   
                    <c:set var="contador" value="${contador+1}"/>
                </c:forEach>
                <tr>            
                    <td colspan="7">
                        <%
                            if (Integer.parseInt((String) session.getAttribute("numeroPagina")) > 1) {
                                out.print("<a href='BairrosController?processar=paginaAnterior&tipoPesquisa=" + tipoPesquisa + "&bairroPesquisar=" + bairroPesquisar + "'>Anterior</a> ");
                            }
                            for (int i = 1; i <= pagMax; i++) {
                                if (Integer.parseInt((String) session.getAttribute("numeroPagina")) == i) {
                                    out.print("<b>" + i + "</b> ");
                                } else {
                                    out.print("<a href='listabairrosPaginada.jsp?irPagina=" + i + "&tipoPesquisa=" + tipoPesquisa + "&bairroPesquisar=" + bairroPesquisar + "&processar=listar'> " + i + "</a>");
                                }
                            }
                            if (Integer.parseInt((String) session.getAttribute("numeroPagina")) < Integer.parseInt((String) session.getAttribute("pagMax"))) {
                                out.print(" <a href='BairrosController?processar=proximaPagina&tipoPesquisa=" + tipoPesquisa + "&bairroPesquisar=" + bairroPesquisar + "'>Posterior</a>");
                            }
                        %>
                    </td>
                </tr>
                <tr><td class="tabelaLinhaespecial" colspan="9">Página ${numeroPagina}/${pagMax} - Total de usuários registrados: <depositodivisa:contagemBairros/> - Ordenação: ${ordenacaoBairro}</td></tr>
                <tr><form action="BairrosController?processar=pesquisar" autocomplete="false">
                    <td colspan="9">Pesquisar por:
                        <select name="tipoPesquisa"  accesskey="o">
                            <%
                                if (tipoPesquisa.equalsIgnoreCase("nomeBairro")) {
                                    out.println("<option value='nomeBairro' selected='selected'>Nome do usuário</option>");
                                } else {
                                    out.println("<option value='nomeBairro'>Nome do usuário</option>");
                                }
                              
                                if (tipoPesquisa.equalsIgnoreCase("ativo")) {
                                    out.println("<option value='ativo' selected='selected'>Ativo</option>");
                                } else {
                                    out.println("<option value='ativo'>Ativo</option>");
                                }
                            %>

                        </select>(o)
                        <input type="text" name="bairroPesquisar" accesskey="p" autofocus="true" title="Pesquisar usuários" value=
                               <%
                                   if (session.getAttribute("bairroPesquisar") != null)
                                       out.println(session.getAttribute("bairroPesquisar"));
                               %>
                               >(p)
                        <button type="submit" name="processar" value="pesquisar" accesskey="l" title="alt+shift+l"><img src="imagens/lupa.png" width="25px" height="25px" alt="Pesquisar!"/>(l)</button></td>
                </form></tr>
</table>
