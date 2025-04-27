<%@tag import="org.hopto.depositodivisa.funcoes.DiaSemana"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:useBean id="dataAtual" class="java.util.Date" />
<jsp:useBean id="DiaSemana" class="org.hopto.depositodivisa.funcoes.DiaSemana" />
<fmt:formatDate value="${dataAtual}" type="date" />
<% 
String diaSemana = DiaSemana.verificarDiaSemana();
request.setAttribute("diaSemana", diaSemana);
%>
- ${diaSemana}

