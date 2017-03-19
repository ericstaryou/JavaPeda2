<%-- 
    Document   : login
    Created on : 02-Mar-2017, 01:48:40
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JavaPeda - Login</title>
        <script type="text/javascript" src="frontend.js"></script>
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="header2.jsp"/>
        <h2>Login</h2>
        <form action="LoginControl.do" method="post">
            Username:<br>
            <input type="text" name="username" placeholder="username" id="username" value="">
            <br>
            Password:<br>
            <input type="password" name="password" placeholder="password" id="pw">
            <br><br>
            <input type="submit" value="Login" id="login">
            <div id="error"></div>
        </form>

        <script>
//            $(document).ready(function () {
//                $('#login').click(function () {
//                    var username = $('#username').val();
//                    var pw = $('#pw').val();
//                    $.post("LoginControl.do",
//                            {
//                                name: username,
//                                city: pw
//                            },
//                            function (data) {
//                                if(data == "password match"){
//                                    window.location = 'animation.jsp';
//                                }else{
//                                    $("#error").text(data);
//                                }
//                                
//                            });
//                });
//            });
        </script>

    </body>
</html>
