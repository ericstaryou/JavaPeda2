<%-- 
    Document   : dashboard
    Created on : 07-Mar-2017, 22:36:14
    Author     : ericstaryou
--%>

<%@page import="model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%  Topic topics[] = new Topic[3];
    for (int i = 0; i < 3; i++) {
        topics[i] = (Topic) session.getAttribute("topic" + i);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <link type="text/css" href="./javapeda.css" rel="stylesheet" />
        <title>JSP Page</title>
        <style>
            button.accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }

            button.accordion.active, button.accordion:hover {
                background-color: #ddd;
            }

            button.accordion:after {
                content: '\02795'; /* Unicode character for "plus" sign (+) */
                font-size: 13px;
                color: #777;
                float: right;
                margin-left: 5px;
            }

            button.accordion.active:after {
                content: "\2796"; /* Unicode character for "minus" sign (-) */
            }
            
            button.accordion2 {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }

            button.accordion2.active, button.accordion2:hover {
                background-color: #ddd;
            }

            button.accordion2:after {
                content: '\02795'; /* Unicode character for "plus" sign (+) */
                font-size: 13px;
                color: #777;
                float: right;
                margin-left: 5px;
            }

            button.accordion2.active:after {
                content: "\2796"; /* Unicode character for "minus" sign (-) */
            }

            div.panel {
                /*padding: 0 18px;*/
                background-color: white;
                max-height: 0;
                overflow: hidden;
                transition: max-height 0.2s ease-out;
            }
            
            div.panel2 {
                /*padding: 0 18px;*/
                background-color: white;
                max-height: 0;
                overflow: hidden;
                transition: max-height 0.2s ease-out;
            }

            input.subt{
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }
        </style>
    </head>
    <body>
        <div>
            <button class="accordion"><%= topics[0].getTopicName()%></button>
            <div class="panel">
<!--                <button class="accordion2"><%= topics[0].getTopicName()%></button>
                <div class="panel2">
                    <div>
                        <form action="TeachingControl.do" method="get">
                            <%= topics[0].getSubtopicTitles()%>
                        </form>
                    </div>
                </div>-->
                <div>
                    <form action="TeachingControl.do" method="get">
                        <%= topics[0].getSubtopicTitles()%>
                    </form>
                    <form action="AssessmentControl.do" method="get">
                        <input class="subt" name="assessment" type="submit" value="Assessment 1"><br>
                    </form>
                </div>
            </div>

            <button class="accordion"><%= topics[1].getTopicName()%></button>
            <div class="panel">
                <div>
                    <form action="TeachingControl.do" method="get">
                        <%= topics[1].getSubtopicTitles()%>
                        <input class="subt" name="opt" type="submit" value="Assessment"><br>
                    </form>
                </div>
            </div>

            <button class="accordion"><%= topics[2].getTopicName()%></button>
            <div class="panel">
                <div>
                    <form action="TeachingControl.do" method="get">
                        <%= topics[2].getSubtopicTitles()%>
                        <input class="subt" name="opt" type="submit" value="Assessment"><br>
                    </form>
                </div>
            </div>
        </div>

        <script>
            var acc = document.getElementsByClassName("accordion");
            var acc2 = document.getElementsByClassName("accordion2");
            var i;

            for (i = 0; i < acc.length; i++) {
                acc[i].onclick = function () {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.maxHeight) {
                        panel.style.maxHeight = null;
                    } else {
                        panel.style.maxHeight = panel.scrollHeight + "px";
                    }
                }
            }
            
            for (i = 0; i < acc2.length; i++) {                
                acc2[i].onclick = function () {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.maxHeight) {
                        panel.style.maxHeight = null;
                    } else {
                        panel.style.maxHeight = panel.scrollHeight + "px";
                    }
                }
            }
        </script>
    </body>
</html>
