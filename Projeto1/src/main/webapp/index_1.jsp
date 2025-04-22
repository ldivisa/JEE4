<%-- 
    Document   : index
    Created on : 22 de abr. de 2025, 16:47:18
    Author     : luiz.souza
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/estilos_1.css" media="screen">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java EE 4 - Projeto 1</title>
    </head>
    <body>
        <h1>java EE 4 - Projeto 1</h1>
        
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
                            
                            <li><a href="views\saida.jsp">Sair</li>
                        </ol>
                    </nav>
                </div>
                <div id="central">
             Central       
                </div>
            </div>
            
            <div id="rodape">
                <footer>
                    <h1>Depósito Divisa - Usuário ativo:      Data:</h1>
                </footer>
              
            </div>
        </div>   
    </body>
</html>
