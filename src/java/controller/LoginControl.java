/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ericstaryou
 */
public class LoginControl extends HttpServlet {

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

        try {
            //get params from login.jsp
            String username = request.getParameter("username");
            String password = User.hashPassword(request.getParameter("password"));
            
            Connection conn = (Connection) request.getServletContext().getAttribute("connection");

            User user = new User(conn);

            //check if username exist
            if (!user.validateUsername(username)) {
//                response.setContentType("text/html;charset=UTF-8");
//                response.getWriter().write("wrong username!");
//                return;
                request.getRequestDispatcher("/WEB-INF/wrongusername.jsp").forward(request, response);
            }

            user.select("select * from user where username ='" + username + "'");

            HashMap hm = new HashMap();

            ResultSet rs = user.getRs();

            String fname = null;
            String lname = null;
            while (rs.next()) {
                hm.put(rs.getString(1), rs.getString(2));
                fname = rs.getString(3);
                lname = rs.getString(4);
            }

            //if username & password match
            if (hm.get(username).equals(password)) {
                //response.sendRedirect("WEB-INF/animation.html");
                HttpSession session = request.getSession(); //create new session when user successfully logged in
                user.setUsername(username);
                user.setFname(fname);
                user.setLname(lname);
                user.select("select * from  subtopic_in_progress where username ='" + username + "'");
                ResultSet rs2 = user.getRs();
                while(rs2.next()){
                    user.getState().setSubtopicID(rs2.getInt(2));
                }
                user.select("select * from assessment_in_progress where username ='" + username + "'");
                ResultSet rs3 = user.getRs();
                while(rs3.next()){
                    user.getState().setAssessmentID(rs3.getInt(2));
                }
                session.setAttribute("userbean", user); //storing the user object and params for later use  
                //request.getRequestDispatcher("/WEB-INF/ani/animation.jsp").forward(request, response);
                request.getRequestDispatcher("DashboardControl.do").forward(request, response);
                //request.getRequestDispatcher("/WEB-INF/test.jsp").forward(request, response);

//                response.setContentType("text/html;charset=UTF-8");
//                response.getWriter().write("true");
            } else {
                //wrong password
//                response.setContentType("text/html;charset=UTF-8");
//                response.getWriter().write("wrong password!");
//                return;
                request.getRequestDispatcher("/WEB-INF/wrongPwd.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, e);
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
