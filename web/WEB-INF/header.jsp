<%-- 
    Document   : header
    Created on : 03-Mar-2017, 16:29:18
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="htmlTestEric.js"></script>
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
    </head>
    <body>
        <div>
            <div onclick="redirect()" style="display: inline-block;cursor: pointer;">
                <h1 style="margin-top: 0px;">JavaPeda</h1>
            </div>
            <div style="display: inline-block; float:right">
                <form action="LogoutControl.do" method="get">
                    <input type="submit" value="Logout"/>
                </form>
            </div>
        </div>
        <script type="text/javascript">
            function redirect(){
                window.location = "DashboardControl.do";
            }
        </script>
    </body>
</html>
