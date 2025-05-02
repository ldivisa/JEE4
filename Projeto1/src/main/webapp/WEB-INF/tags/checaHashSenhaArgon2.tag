<%@tag body-content="empty"%>
<%@tag description="Checar senha para hash argon2id" pageEncoding="UTF-8"%>
<%@attribute name="hash" type="String" required="true" description="hash argon2id que será checado" rtexprvalue="true"%>
<%@attribute name="senhaEntrada" type="String" required="true" description="Senha limpa que será conferida " rtexprvalue="true"%>
<%@tag import="org.hopto.depositodivisa.funcoes.HashSenhasArgo2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%
  HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
  boolean senhaOk=maquinaHash.checaHashSenha(hash, senhaEntrada);
  request.setAttribute("senhaOk", senhaOk);
%>
