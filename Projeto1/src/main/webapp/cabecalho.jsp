<%-- 
    Document   : index
    Created on : 22 de abr. de 2025, 16:47:18
    Author     : luiz.souza
--%>

<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/estilos.css" media="screen">
<!DOCTYPE html>
<html>
    <head>
        <script language="javascript" src="js/funcoes.js"> </script>
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
                            <%LoginDAO login = new LoginDAO();
                            if(session.getAttribute("acessoUsuario")==null){
                                response.sendRedirect("login.jsp");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"C")){
                            out.print("<li><a href=\"cadastros.jsp\"><img src=\"imagens/Gabinete.jpg\" alt=\"Cadastros\" height=\"50px\" width=\"50px\" title=\"Cadastros de clientes, produtos, fornecedores, etc.\"</a></li>");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"V")){
                            out.print("<li><a href=\"views/saida.jsp\"><img src=\"imagens/CaixaRegistradora.jpg\" alt=\"Vendas\" height=\"50px\" width=\"50px\" title=\"Vender material\"></a></li>");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"E")){
                            out.print("<li><a href=\"views/entrada.jsp\"><img src=\"imagens/EntradaEstoque.jpg\" alt=\"Entrada\" height=\"50px\" width=\"50px\" title=\"Receber Material\"></a></li>");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"F")){
                            out.print("<li><a href=\"views/financeiro.jsp\"><img src=\"imagens/Financeiro.jpg\" alt=\"alt\" height=\"50px\" width=\"50px\" title=\"Financeiro\"</a></li>");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"U")){
                            out.print("<li><a href=\"UsuariosController?processar=listar\"><img src=\"imagens/Usuarios.png\" alt=\"alt\" height=\"50px\" width=\"50px\" title=\"Usuários\"</a></li>");
                                }
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"R")){
                            out.print("<li><a href=\"views/relatorios.jsp\"><img src=\"imagens/Relatorios.jpg\" alt=\"alt\" height=\"50px\" width=\"50px\" title=\"Relatorios\"</a></li>");
                                }
                             %>
                             <br><br><br>
                             <%
                            if (login.getPermissao((String) session.getAttribute("acessoUsuario"),"S")){
                            out.print("<li><a href=\"trocarSenha.jsp?mensagem=Insira os dados\"><img src=\"imagens/trocarSenha.png\" alt=\"Trocar Senha\" height=\"50px\" width=\"50px\" title=\"Trocar Senha\"</a></li>");
                                }    
                            %>
                            <br><br><br><br><br>
                            <li><a href="saida.jsp"><img src="imagens/Saida.jpg" alt="Saída" height="50px" width="50px" title="Sair"></a></li>
                        </ol>
                    </nav>
                </div>
               