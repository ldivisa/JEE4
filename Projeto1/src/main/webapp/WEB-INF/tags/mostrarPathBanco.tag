<%@tag body-content="empty"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@tag import="io.github.cdimascio.dotenv.Dotenv" %>
    <%
    Dotenv ambiente = Dotenv.load();
    String url;
    if (ambiente.get("DB_URL") == null) {
        url = "Banco não registrado";
    } else {
        url = "Banco:" + ambiente.get("DB_URL");
    }
    request.setAttribute("url", url);
%>
- ${url}
