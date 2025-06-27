<%@tag import="org.hopto.depositodivisa.dao.BairroDAO"%>
<%@tag import="com.mysql.cj.jdbc.*"%>
<%@tag import="org.hopto.depositodivisa.factory.ConexaoFactory"%>
<%@tag import="com.mysql.cj.protocol.Resultset"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags/" prefix="depositodivisa" %>
<%
    BairroDAO bairro = new BairroDAO();
    Integer pagMax = null;
    Integer limite = 4;
    Integer numRegistros;
    numRegistros = (Integer.valueOf(bairro.contagemRegistros((String) session.getAttribute("bairroPesquisar"), (String) session.getAttribute("tipoPesquisa"))));
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
<h1>Listagem de bairros: </h1>
<c:set var="contador" value="0"/>
<c:set var="corA" value="lightblue"/>
<c:set var="corB" value="white"/>

<table border="1">
    <tr><td colspan="6"><a href="novoBairro.jsp" title="Criar Novo Bairro"><img src="imagens/bairro.jpg" alt="Novo bairro" height="50px" widht="50px"> </td></tr>
                <tr class="tabelaLinhaespecial">
                    <td><a href="BairrosController?acao=listar&ordenacaoBairro=bairroNome">Nome</a></td>
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

                        <td> ${bairros['bairroNome']} </td>
                        <td> ${bairros['ativo']} </td>
                        <td><a href="alteraBairro.jsp?bairroNome=${bairros['bairroNome']}&ativo=${bairros['ativo']} "><img src="imagens/Editar.png" height="50px" width="50px" alt="Editar" title="Editar usuário ${bairros['bairroNome']}"></a></td>
                                                        <c:choose>
                                    <c:when test="${bairros['ativo']==1}">
                                        <c:set var="bairroAlterarEstado" value="${bairros['bairroNome']}" scope="session"/>
                                <td> <a href="BairrosController?processar=desativar&bairroAlterarEstado=${bairros['bairroNome']}"><img src="imagens/desativar.png" height="50px" width="50px" alt="desativar" title="Desativar o usuário ${bairros['bairroNome']}"></a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="bairroAlterarEstado" value="${bairros['bairroNome']}" scope="session"/>
                                <td> <a href="BairrosController?processar=ativar&bairroAlterarEstado=${bairros['bairroNome']}"><img src="imagens/ativar.png" height="50px" width="50px" alt="ativar" title="Ativar o usuário ${bairros['bairroNome']}"></a></td>
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
                <tr><td class="tabelaLinhaespecial" colspan="9">Página ${numeroPagina}/${pagMax} - Total de bairros registrados: <depositodivisa:contagemBairros/> - Ordenação: ${ordenacaoBairro}</td></tr>
                <tr><form action="BairrosController?processar=pesquisar" autocomplete="false">
                    <td colspan="9">Pesquisar por:
                        <select name="tipoPesquisa"  accesskey="o">
                            <%
                                if (tipoPesquisa.equalsIgnoreCase("bairroNome")) {
                                    out.println("<option value='bairroNome' selected='selected'>Nome do bairro</option>");
                                } else {
                                    out.println("<option value='bairroNome'>Nome do usuário</option>");
                                }
                              
                                if (tipoPesquisa.equalsIgnoreCase("ativo")) {
                                    out.println("<option value='ativo' selected='selected'>Ativo</option>");
                                } else {
                                    out.println("<option value='ativo'>Ativo</option>");
                                }
                            %>

                        </select>(o)
                        <input type="text" name="bairroPesquisar" accesskey="p" autofocus="true" title="Pesquisar Bairros" value=
                               <%
                                   if (session.getAttribute("bairroPesquisar") != null)
                                       out.println(session.getAttribute("bairroPesquisar"));
                               %>
                               >(p)
                        <button type="submit" name="processar" value="pesquisar" accesskey="l" title="alt+shift+l"><img src="imagens/lupa.png" width="25px" height="25px" alt="Pesquisar!"/>(l)</button></td>
                </form></tr>
</table>
