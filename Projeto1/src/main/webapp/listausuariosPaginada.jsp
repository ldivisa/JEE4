<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="org.hopto.depositodivisa.factory.ConexaoFactory"%>
<%@page import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="util" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="central">
       
    <%
        int offset = 1;
        Connection conexao = new ConexaoFactory().getConnection();
        String SQL = "select * from login limit 10 offset " + offset;
        System.out.println("\n SQL: "+SQL);
        PreparedStatement ps;
        ResultSet resultSet;
        ps = conexao.prepareStatement(SQL);
        resultSet = ps.executeQuery();
        HttpSession sessao = request.getSession();
        LoginDAO login = new LoginDAO();
      
        while (resultSet.next()) {
            System.out.println("\n Usuario: " + resultSet.getNString("nomeUsuario"));
        }
    %>
    
</div>
</div>
