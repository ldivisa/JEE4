<%@tag import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  LoginDAO login = new LoginDAO();
  request.setAttribute("contagemBairos", login.contagemRegistros((String)session.getAttribute("bairroPesquisar"),(String) session.getAttribute("tipoPesquisa")));
  //System.out.println("\n"+login.contagemRegistros());
    out.print(login.contagemRegistros((String)session.getAttribute("bairroPesquisar"),(String) session.getAttribute("tipoPesquisa")));
%>

