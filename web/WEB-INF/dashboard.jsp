<%-- 
    Document   : dashboard
    Created on : 07-Mar-2017, 22:36:14
    Author     : ericstaryou
--%>

<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User user = (User) session.getAttribute("userbean");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JavaPeda - Dashboard</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h2>Welcome to your Dashboard <%= user.getFname()%></h2>
        <div style="display:flex">
            <div style="width:20%; display: inline-block">
                <jsp:include page="topic.jsp"/>
            </div>
            <div style="display: inline-block;width: 79%; margin-left: 11px">
                <h3>Your Progress</h3>
                <div style="border: 1px solid #DDDDDD; display: flex">
                    <div style="display:inline-block; width: 50%;margin: 0px 5px 10px 10px;">
                        <p>Subtopic last left off</p>
                        <div style="border: 1px solid #DDDDDD;display: flex">
                            <div style="display: inline-block">
                                <img src="img/javacup.png" alt="java" style="width:200px;height:200px;">
                            </div> 
                            <div style="display: inline-block">
                                <p><%= session.getAttribute("subtopicProgress") %>.</p>
                                <form action="SubtopicUserProgressControl.do" method="GET">
                                    <input type=submit name="opt" value="Resume">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div style="display:inline-block; width: 50%;margin: 0px 10px 10px 5px;">
                        <p>Assessment last left off</p>
                        <div style="border: 1px solid #DDDDDD;display: flex">
                            <div style="display: inline-block;width:200px;height:204px;">
                                <img src="img/icon.png" alt="java" style="width:151px;height:160px;margin-top: 24px;">
                            </div>  
                            <div>
                                <p><%= session.getAttribute("assessmentProgress") %>.</p>
                                <form action="AssessmentUserProgressControl.do" method="GET">
                                    <input type=submit name="opt" value="Resume">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
