<%-- 
    Document   : animation
    Created on : 02-Mar-2017, 04:17:24
    Author     : ericstaryou
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

    <!--
    
    This file, java_visualize/index.html, is based on
    visualize.html from the Online Python Tutor.
    Changes made by David Pritchard (daveagp@gmail.com);
    see README for more details.
    
    Summary of changes made:
    - different file locations
    - uses Java, not Python
    - uses CodeMirror latest version
    - lazier approach for loading examples
    
    ==== Header from visualize.html ====
    
    Online Python Tutor
    https://github.com/pgbovine/OnlinePythonTutor/
    
    Copyright (C) 2010-2013 Philip J. Guo (philip@pgbovine.net)
    
    Permission is hereby granted, free of charge, to any person obtaining a
    copy of this software and associated documentation files (the
    "Software"), to deal in the Software without restriction, including
    without limitation the rights to use, copy, modify, merge, publish,
    distribute, sublicense, and/or sell copies of the Software, and to
    permit persons to whom the Software is furnished to do so, subject to
    the following conditions:
    
    The above copyright notice and this permission notice shall be included
    in all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
    OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
    CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
    TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
    SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
    
    -->
    <head>
        <title>Java Visualizer</title>

        <!-- requirements for pytutor.js -->
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/d3.v2.min.js"></script>
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/jquery.ba-bbq.min.js"></script> <!-- for handling back button and URL hashes -->
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/jquery.jsPlumb-1.3.10-all-min.js "></script> <!-- for rendering SVG connectors
                      DO NOT UPGRADE ABOVE 1.3.10 OR ELSE BREAKAGE WILL OCCUR -->
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/jquery-ui-1.8.24.custom.min.js"></script> <!-- for sliders and other UI elements -->

        <link type="text/css" href="./OnlinePythonTutor/v3/css/ui-lightness/jquery-ui-1.8.24.custom.css" rel="stylesheet" />

        <!-- for annotation bubbles -->
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/jquery.qtip.min.js"></script>
        <link type="text/css" href="./OnlinePythonTutor/v3/css/jquery.qtip.css" rel="stylesheet" />

        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/pytutor.js"></script>
        <script type="text/javascript" src="./OnlinePythonTutor/v3/js/hooks.js"></script>
        <script type="text/javascript" src="pytutor-customizations.js?2"></script>
        <link rel="stylesheet" href="./OnlinePythonTutor/v3/css/pytutor.css"/>
        <link rel="stylesheet" href="pytutor-customizations.css"/>

        <!-- requirements for opt-frontend.js -->

        <!-- codemirror.net online code editor -->
        <script type="text/javascript" src="./CodeMirror/lib/codemirror.js"></script>
        <link type="text/css" href="./CodeMirror/lib/codemirror.css" rel="stylesheet" />
        <script type="text/javascript" src="./CodeMirror/mode/clike/clike.js"></script>
        <script type="text/javascript" src="./CodeMirror/addon/edit/matchbrackets.js"></script>

        <script type="text/javascript" src="./config.js.php"></script> <!-- used to be opt-frontend.js -->
        <script type="text/javascript" src="./jv-frontend.js?8"></script> <!-- used to be opt-frontend.js -->
        <link rel="stylesheet" href="./OnlinePythonTutor/v3/css/opt-frontend.css"/>
        <!--load code value into -->
        <script type="text/javascript">
            var codeVal = '${codeVal}';
            document.addEventListener("DOMContentLoaded", function() {
                setCodeMirrorVal(codeVal);
            });
        </script>

    </head>

    <body>
<!--        <table style="margin:0px auto 0px auto;">
            <tr>
                <td>
                    <img title="Based on icons by Jacob Halton and Francesco Terzini of the Noun Project" src="img/jv64.png">
                </td>
                <td style="text-align: center">
                    <span style="font-size:150%">Java Visualizer</span>
                    <br>
                    <i>(beta:</i><a href="mailto:daveagp@gmail.com">report a bug</a>)                    
                </td>
        </table>-->
<div>
    
    <div id="pyInputPane" style="margin-left: 11px;max-width: 100%;">

            <p style="text-align:left;">Write your Java code here:</p>

            <div id="codeInputPane">
            </div> <!-- populate with a CodeMirror instance -->
            <div style="display: none">
                
            <table>
                <tr>
                    <td>
                        <button onclick="$('#options').toggle()">options</button>
                    </td>
                    <td>
                        <span id='options' style='display:none'>
                            <span style="white-space: nowrap;"> 
                                <input id='showStringsAsObjects' type='checkbox'>
                                <label for='showStringsAsObjects'>
                                Show String/Integer/etc objects, not just values
                            </span>
                            <span style="white-space: nowrap;"> 
                                <input id='showAllFields' type='checkbox'>
                                <label for='showAllFields'>
                                Show overridden fields, synthetics
                            </span>
                            <span style="white-space: nowrap;"> 
                                <input id='disableNesting' type='checkbox'>
                                <label for='disableNesting'>
                                Prefer non-nesting and vertical layouts
                            </span>
                            <span style="white-space: nowrap;"> 
                                <input id='verticalLists' type='checkbox'>
                                <label for='verticalLists'>
                                Force linked lists to display vertically
                            </span>
                        </span> <!-- #options -->
                    </td>
                </tr>
            </table>

            <div id='args'>
                <code>args</code>: <span id='argslist'></span>
                <button id='addarg' onclick='javascript:addArg("")'>+command-line argument</button>
            </div>

            <div id='stdin'>
                <button id='stdin-button' onclick='$("#stdin-xdiv").toggle()'>stdin</button>
                (also visualizes consumption of <a href="http://introcs.cs.princeton.edu/java/stdlib/"><code>StdIn</code></a>)
                <div id='stdin-xdiv' style='display:none;position:relative'>
                    <textarea id='stdinarea' style='-webkit-box-sizing: border-box;width:100%;height:100px;font-family:monospace'>
                    </textarea>
                    <sup style='position:absolute;top:5px;right:5px' class='closestdin' onclick='$("#stdin-xdiv").toggle()'>x</sup>
                </div>
            </div>
            </div>
            <style>
                button {
                    font-family: verdana, arial, helvetica, sans-serif;
                }
                .arg, #stdin-xdiv {
                    margin-right: 1px; 
                    padding: 5px 3px; 
                    border:1px solid #DDD;
                    background-color: #EEE;
                    border-radius: 5px;
                    -webkit-border-radius: 5px;
                }
                .arg input {
                    width: 50px
                }
                .closex {
                    font-weight:bold;
                    position:relative;
                    top:-4px;
                    left:2px;
                    text-decoration:none;
                    color:red;
                    cursor:pointer;
                }
                .closestdin {
                    font-weight:bold;
                    text-decoration:none;
                    color:red;
                    cursor:pointer;
                }
                .toggler { text-align:center; }
            </style>

            <script type='text/javascript'>
                addArg = function (init) {
                    $('#argslist').append('<span class="arg"><input type="text"></input><sup class="closex">x</sup></span>');
                    $('#argslist .arg:last-child input').val(init);
                };
                $('#args').on('click', '.closex', function (event) {
                    $(this).parents('span.arg').remove();
                });

                $(function () {
                    $('.toggler a').on('click', function (event) {
                        $('#faq').toggle();
                        return false;
                    });
                });

                topics = [
                    ["basic", ["(Default)", "Variables", "CmdLineArgs", "StdIn", "ControlFlow", "Sqrt", "ExecLimit", "Strings"]],
                    ["method", ["PassByValue", "Recursion", "StackOverflow"]],
                    ["oop", ["Rolex", "Person", "Complex", "Casting"]],
                    ["data structure", ["LinkedList", "StackQueue", "Postfix", "SymbolTable"]],
                    ["java feature", ["ToString", "Reflect", "Exception", "ExceptionFlow", "TwoClasses"]]
                ];
            </script>

            <p style="text-align:center">
                <button id="executeBtn" class="bigBtn" type="button">Visualize Execution</button>
            </p>
        </div>
    <div id="pyOutputPane" style="border: 1px solid #DDDDDD;margin-left: 12px;
margin-right: auto;"></div>
</div>
        
        
<!--        <div id="footer">
            <p style="margin-top: 8px;">
                Based on <a href="http://www.pythontutor.com">Online Python Tutor</a>, &copy; 2010-2013 <a href="http://www.pgbovine.net/">Philip Guo</a> all rights reserved. Java version by <a href="mailto:daveagp@gmail.com">David Pritchard</a>, <a href="https://github.com/wgwozdz">Will Gwozdz</a>.
                Source code: for this version's <a href="https://github.com/daveagp/java_jail/tree/master/cp/traceprinter">backend</a>;
                the <a href="https://github.com/daveagp/java_visualize">frontend and installation instructions</a>.
            </p>
        </div>-->
    </body>
</html>
