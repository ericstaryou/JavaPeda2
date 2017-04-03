<%-- 
    Document   : loginError
    Created on : Apr 3, 2017, 1:54:01 PM
    Author     : Eric
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="login.jsp"/>
        <h2><%= request.getAttribute("error") %></h2>
    </body>
</html>
