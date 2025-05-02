<%@tag body-content="empty"%>
<%@tag description="Criar has argon2id para senha" pageEncoding="UTF-8"%>
<%@attribute name="senhaEntrada" required="true" description="Senha limpa para passar para hash argon2" rtexprvalue="true" type="String"%>
<%@tag import="org.hopto.depositodivisa.funcoes.HashSenhasArgo2"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    

<%
  HashSenhasArgo2 maquinaHash = new HashSenhasArgo2();
  String senhaHash=maquinaHash.criaHashSenha(senhaEntrada);
  request.setAttribute("senhaHash", senhaHash);
%>
