<%-- 
    Document   : test
    Created on : 02-Mar-2017, 02:03:52
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="frontend.js"></script>
    </head>
    <body>
        <%
            if (session == null) {
                out.print("Please login first");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        %>   
        
        <jsp:include page="/WEB-INF/header.jsp" />
        <div style="display:flex">
            <div style="display:inline-block; width: 20%">
                <jsp:include page="topic.jsp"/>
            </div>
            <div style="display:inline-block; width: 80%">
                <jsp:include page="/WEB-INF/ani/animation.jsp"/>
            </div>
        </div>
    </body>
</html>
