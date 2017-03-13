<%-- 
    Document   : dashboard
    Created on : 07-Mar-2017, 22:36:14
    Author     : ericstaryou
--%>

<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User user = (User) session.getAttribute("userbean");%>
<%  Topic topics[] = new Topic[3];
    for (int i = 0; i < 3; i++) {
        topics[i] = (Topic) session.getAttribute("topic"+i);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>Welcome to your Dashboard <%= user.getFname()%></h2>
        <jsp:include page="topic.jsp"/>
    </body>
</html>
