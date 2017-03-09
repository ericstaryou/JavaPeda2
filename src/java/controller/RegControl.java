/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author ericstaryou
 */
public class RegControl extends HttpServlet {

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

        //get params from register.jsp
        String fname = request.getParameter("firstname");
        String lname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");

        try {
            //check if fields are empty
            if(empty(fname) || empty(lname) || empty(username) || empty(password) || empty(cpassword)){
                request.setAttribute("error", "please complete all fields!");
                request.getRequestDispatcher("/WEB-INF/registerError.jsp").forward(request, response);
            }
            
            //check if password and confirmation password matches
            if(!password.equals(cpassword)){
                request.setAttribute("error", "passwords doesn't match!");
                request.getRequestDispatcher("/WEB-INF/registerError.jsp").forward(request, response);
            }
            
            Connection conn = (Connection) request.getServletContext().getAttribute("connection");
            User tempUser = new User(conn, username, password, fname, lname);
            
            //check username availability
            if(tempUser.validateUsername(username)){
                request.setAttribute("error", "username not available!");
                request.getRequestDispatcher("/WEB-INF/registerError.jsp").forward(request, response);
            }
            
            //insert a new record in database for this new user when there is no error
            tempUser.createUser(username, password, fname, lname);
            request.getRequestDispatcher("/WEB-INF/login.jsp").include(request, response);
            out.print("<h2>Registration successful. Please login with your username: " + tempUser.getUsername());

        } catch (Exception e) {
            Logger.getLogger(RegControl.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public static boolean empty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
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
