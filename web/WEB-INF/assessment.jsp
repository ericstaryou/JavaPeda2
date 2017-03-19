<%-- 
    Document   : assessment
    Created on : 12-Mar-2017, 22:54:48
    Author     : ericstaryou
--%>

<%@page import="model.Assessment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% Assessment assessment = (Assessment) session.getAttribute("asmt");%>
<%%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JavaPeda - Assessment</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/header.jsp" />
        <div>            
            <form action="QuestionHandler.do" method="get">
                <%= request.getAttribute("question")%>
                <br/>
                <input type=submit value="Proceed"><br/>
            </form>
        </div>
    </body>
</html>
