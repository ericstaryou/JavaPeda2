<%-- 
    Document   : registerError
    Created on : 04-Mar-2017, 20:37:25
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="register.jsp"/>
        <h2><%= request.getAttribute("error") %></h2>
    </body>
</html>
