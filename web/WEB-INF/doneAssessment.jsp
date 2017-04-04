<%-- 
    Document   : doneAssessment
    Created on : 14-Mar-2017, 20:13:11
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JavaPeda - Assessment</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp" />
        <h2>You've done your Assessment! Do you wish to go back to your dashboard?</h2>
        <form method="GET" action="DashboardControl.do">
            <input type=submit value="Yes"> 
        </form> 
        
    </body>
</html>
