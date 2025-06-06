<%@tag import="org.hopto.depositodivisa.dao.BairroDAO"%>
<%@tag import="org.hopto.depositodivisa.dao.LoginDAO"%>
<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  BairroDAO bairro = new BairroDAO();
  request.setAttribute("contagemBairos", bairro.contagemRegistros((String)session.getAttribute("bairroPesquisar"),(String) session.getAttribute("tipoPesquisa")));
  //System.out.println("\n"+login.contagemRegistros());
    out.print(bairro.contagemRegistros((String)session.getAttribute("bairroPesquisar"),(String) session.getAttribute("tipoPesquisa")));
%>

