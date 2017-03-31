/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author ericstaryou
 */
public class QuestionHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        Connection conn = (Connection) request.getServletContext().getAttribute("connection");

        Assessment assessment = (Assessment) session.getAttribute("asmt");
        int noOfQuestion = assessment.getNoOfQuestion();
        Integer questionCounter = (Integer) session.getAttribute("questionCounter");
        String answer = assessment.getQuestion()[questionCounter].getAnswer();

        if(request.getParameter("opt") == null){ //if user did not select an answer
            request.setAttribute("question", assessment.constructPage(questionCounter));
            request.getRequestDispatcher("/WEB-INF/assessment.jsp").include(request, response);
            out.print("Please select an answer!");
        }else if (!request.getParameter("opt").equals(answer)) { //if user selected the wrong answer
            request.setAttribute("question", assessment.constructPage(questionCounter));
            request.getRequestDispatcher("/WEB-INF/assessment.jsp").include(request, response);
            out.print("Please try again!");
        } else {
            if((questionCounter + 1) == noOfQuestion){ //when user done the assessment
                request.getRequestDispatcher("/WEB-INF/doneAssessment.jsp").forward(request, response);
            }
            else if ((questionCounter + 2) == noOfQuestion) { //if this is the last question
                session.setAttribute("questionCounter", questionCounter + 1);
                request.setAttribute("question", assessment.constructPage(questionCounter + 1));
                request.getRequestDispatcher("/WEB-INF/assessment.jsp").include(request, response);
                //out.print("this is the last question");
            }
            else{ //if user got it right, navigate them to the next question
                session.setAttribute("questionCounter", questionCounter + 1);
                request.setAttribute("question", assessment.constructPage(questionCounter + 1));
                request.getRequestDispatcher("/WEB-INF/assessment.jsp").forward(request, response);
            }            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
