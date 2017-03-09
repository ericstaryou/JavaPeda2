<%-- 
    Document   : index
    Created on : 18-Feb-2017, 17:46:44
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
        <h1>JavaPeda</h1>
        <form method="GET" action="UserServlet.do">
            <input type="radio" name="tbl" value="Login" checked="check">Login<br/>
            <input type="radio" name="tbl" value="Register">Register<br/>
            <br/>
            <input type=submit value="Login"> <br/>
        </form> 
    </body>
</html>
