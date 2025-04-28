<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/estilosLogin.css" media="screen">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deposito Divisa - Página de erro</title>
    </head>
    <body>
        <c:import url="cabecalhoLogin.jsp"    />
    <div:central>
        
    <h1><center>Atenção!</h1></center><br><!-- comment -->
        <h3><center> O sistema apresentou um problema, favor entrar em contato com o desenvolvedor</h3></center>
        <center><a href="login.jsp">Ir à página inicial!</a></center>
    </div:central>
    </body>
</html>
<c:import url="rodape.jsp"    />