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
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JavaPeda - Teaching</title>
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
                <div><h3>Topic</h3></div>
                <jsp:include page="topicWithExplanation.jsp"/>
            </div>
            <div style="display:inline-block; width: 80%">
                <div style="margin-left: 11px"><h3>Explanation</h3></div>
                <div style="border: 1px solid #DDDDDD;margin-left: 11px; margin-top: 10px;text-align: justify; padding: 10px; font-size: 15px">
                    <p style="margin: 0px"><%= request.getAttribute("explanation") %></p>
                </div>
                <div style="margin-left: 11px"><h3><%= request.getAttribute("subtopicName")%></h3></div>
                <div style="border: 1px solid #DDDDDD;margin-left: 11px">
                    <jsp:include page="/WEB-INF/ani/animation.jsp"/>
                </div>
            </div>
        </div>
    </body>
</html>
