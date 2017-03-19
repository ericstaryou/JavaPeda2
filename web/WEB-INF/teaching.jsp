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
                <div style="margin-left: 11px"><h3><%= request.getAttribute("subtopicName")%></h3></div>
                <div style="border: 1px solid #DDDDDD;margin-left: 11px">
                    <jsp:include page="/WEB-INF/ani/animation.jsp"/>
                </div>
                <div style="margin-left: 11px"><h3>Explanation</h3></div>
                <div style="border: 1px solid #DDDDDD;margin-left: 11px; margin-top: 10px;text-align: justify; padding: 10px; font-size: 15px">
                    <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla auctor interdum magna, aliquam bibendum libero euismod nec. Integer lacinia eu dolor in dignissim. Morbi vestibulum ligula id diam tristique consectetur. Morbi et tempus turpis. Fusce malesuada dignissim tortor, molestie pellentesque massa vehicula non. Aliquam venenatis rutrum pellentesque. Integer blandit ligula ut orci faucibus, non hendrerit turpis aliquet. Nunc sodales blandit enim. Pellentesque laoreet mauris nulla, ut venenatis est gravida in. Mauris id bibendum orci. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.

                        In id massa quis mi molestie porta a quis dui. Suspendisse maximus metus mi, non commodo massa aliquet ut. Fusce sed interdum ipsum, sed pretium enim. Nunc placerat commodo nisi eu blandit. Praesent venenatis sapien eget metus malesuada, vitae pellentesque ex aliquet. Phasellus imperdiet metus sed arcu dapibus, ut hendrerit felis finibus. Sed posuere, neque eu porttitor mollis, tortor turpis ullamcorper velit, quis varius ante dolor eget nisl. Sed lacinia eros et justo faucibus, at euismod elit porta. Duis ultrices, enim ac tempor lacinia, neque mi tempus risus, vel ultrices tellus erat vitae massa. Mauris elementum metus enim, at vestibulum massa scelerisque eu. Quisque consectetur eget odio vel blandit. Fusce sed posuere ipsum, ut porttitor urna. Nullam maximus faucibus dapibus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi ac suscipit libero. </p>
                </div>
            </div>
        </div>
    </body>
</html>
