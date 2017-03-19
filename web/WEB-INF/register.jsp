<%-- 
    Document   : register
    Created on : 02-Mar-2017, 01:49:02
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JavaPeda - Register</title>
    </head>
    <body>
        <jsp:include page="header2.jsp"/>
        <h2>Register</h2>
        <form action="RegControl.do" method="post">
            First Name:<br>
            <input type="text" name="firstname" placeholder="firstname" id="firstname">
            <br>
            Last Name:<br>
            <input type="text" name="lastname" placeholder="lastname" id="lastname">
            <br>
            Username:<br>
            <input type="text" name="username" placeholder="username" id="username">
            <br>
            Password:<br>
            <input type="password" name="password" placeholder="password" id="pw">
            <br>
            Confirm-password:<br>
            <input type="password" name="cpassword" placeholder="password" id="cpw">
            <br><br>
            <input type="submit" value="Register" id="register">
            <div id="error"></div>
        </form>
    </body>
</html>
