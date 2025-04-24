<link rel="stylesheet" type="text/css" href="css/estilos_1.css" media="screen">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java EE 4 - Projeto 1</title>
    </head>
<body>
        <div id="paginatotal">
            <div id="cabeçalho">
                <header>
                    <h1 id="texto1">Deposito Divisa</h1>
                </header>
            </div>
        
     <div id="conteudo">
                <div id="menu"> 
                    <nav>
                        <h1>MENUS</h1>
                        <ol>
                            <li><a href="views\cadastros.jsp">Cadastros (clientes, produtos, etc.)</a></li>
                            <li><a href="views\saida.jsp">Vender Material</a></li>
                            <li><a href="views\entrada.jsp">Receber Material</a></li>
                            <li><a href="views\financeiro.jsp">Financeiro</a></li>
            
                            <li><a href="views\relatorios.jsp">Relatorios</a></li>
            
                            <li><a href="views\saida.jsp">Sair</a></li>
                        </ol>
                    </nav>
                </div>
               
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
 <div id="rodape">
                <footer>
                    Depósito Divisa - Usuário ativo:      Data:
                </footer>
  </div>
        </div>   
  </body>   
</html>
 
