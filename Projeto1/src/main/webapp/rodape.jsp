<%@taglib  prefix="depositodivisa" tagdir="/WEB-INF/tags/" %> 
<div id="rodape">
                <footer>
                    Depósito Divisa - Usuário ativo:<depositodivisa:mostrarUsuarioAtual />   -   Data: <depositodivisa:exibirDataAtual/> <depositodivisa:mostrarPathBanco /> - ID: <%=session.getId()%>
                </footer>
  </div>
        </div>   
  </body>   
</html>
