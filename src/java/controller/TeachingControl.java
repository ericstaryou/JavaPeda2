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
import model.Topic;
import model.User;

/**
 *
 * @author ericstaryou
 */
public class TeachingControl extends HttpServlet {

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

        String subtopicName = request.getParameter("opt");
        Topic selectedTopic = new Topic(conn, subtopicName);
        User user = (User) session.getAttribute("userbean");
        int subtopicID = selectedTopic.getSubtopic().getSubtopicID();

//        if (request.getParameter("opt").equals("Resume")) {
//            subtopicName = (String) session.getAttribute("subtopicName");
//            selectedTopic = new Topic(conn, subtopicName);
//            //update user state
//            user.getState().setSubtopicID(subtopicID);
//            user.updateSelectedTopic(subtopicID);
//            session.setAttribute("selectedTopic", selectedTopic);
//            request.setAttribute("codeVal", selectedTopic.getDemoCode());
//            request.getRequestDispatcher("/WEB-INF/teaching.jsp").forward(request, response);
//        }

        //if user selected to start Assessment (from teaching.jsp)
        if (request.getParameter("opt").equals("Assessment")) {
            request.getRequestDispatcher("AssessmentControl.do").forward(request, response);
        }

        //update user state
        user.getState().setSubtopicID(subtopicID);
        user.updateSelectedTopic(subtopicID);

        request.setAttribute("subtopicName", request.getParameter("opt"));
        session.setAttribute("selectedTopic", selectedTopic);
        request.setAttribute("codeVal", selectedTopic.getDemoCode());

        request.getRequestDispatcher("/WEB-INF/teaching.jsp").forward(request, response);
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
