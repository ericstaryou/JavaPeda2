<%-- 
    Document   : header2
    Created on : 03-Mar-2017, 23:54:33
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1 onclick="redirect()" style="display: inline-block;cursor: pointer;">JavaPeda</h1>
        <script type="text/javascript">
            function redirect(){
                window.location = "index.jsp";
            }
        </script>
    </body>
</html>
