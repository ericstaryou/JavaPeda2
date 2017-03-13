/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ericstaryou
 */
public class Assessment {

    int assessmentID;
    int noOfQuestion;
    Question[] questions;

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public Assessment(Connection conn, String assessmentName) {
        try {
            this.connect(conn);
            int assessmentID = Integer.parseInt(assessmentName.substring(11));
            this.assessmentID = assessmentID;
            select("SELECT * FROM assessment WHERE assessmentID =" + assessmentID);
            while (rs.next()) {
                this.noOfQuestion = rs.getInt(2);
            }

            select("SELECT COUNT(*) FROM question WHERE assessmentID=" + assessmentID);
            this.noOfQuestion = 0;
            while (rs.next()) {
                noOfQuestion = rs.getInt(1);
            }
            this.questions = new Question[noOfQuestion];
            for (int i = 0; i < noOfQuestion; i++) {
                this.questions[i] = new Question();
            }

            select("SELECT * FROM question WHERE assessmentID=" + assessmentID);
            for (int i = 0; i < noOfQuestion; i++) {
                rs.next();
                this.questions[i].questionID = rs.getInt(1);
                this.questions[i].question = rs.getString(3);
                for (int j = 0; j < 4; j++) {
                    this.questions[i].options[j] = rs.getString(4 + j);
                }
                this.questions[i].answer = rs.getString(8);
            }

        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public Question[] getQuestion() {
        return questions;
    }

    public void setQuestion(Question[] question) {
        this.questions = question;
    }

    public void connect(Connection con) {
        connection = con;
    }

    public void closeAll() {
        try {
            rs.close();
            statement.close();
            //connection.close();                                         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void select(String query) {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean exists(String user) {
        boolean exist = false;
        try {
            select("select topicID from topic where topicID='" + user + "'");
            if (rs.next()) {
                exist = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    private void insert(String[] str) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO user VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, str[0].trim()); //username
            //ps.setString(2, this.hashPassword(str[1].trim())); //password
            ps.setString(3, str[2].trim()); //fname
            ps.setString(4, str[3].trim()); //lname
            ps.executeUpdate();

            ps.close();
            System.out.println("-->1 row added.");
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete(String user) {

        String query = "DELETE FROM Users "
                + "WHERE username = '" + user.trim() + "'";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("way way" + e);
            //results = e.toString();
        }
    }

    public String constructQuestion() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < this.noOfQuestion; i++) {
            b.append("<p class=\"question\">" + (i + 1) + ") " + this.questions[i].question + "</p>");
            b.append("<ul class=\"answers\">");
            for (int j = 0; j < 4; j++) {
                b.append("<input type=\"radio\" name=\"q" + (i + 1) + "\" value=\"" + this.questions[i].options[j] + "\" id=\"q1a\"><label for=\"q1a\">" + this.questions[i].options[j] + "</label><br/>");
            }
            b.append("</ul>");
        }
        return b.toString();
    }

    public String constructPage(int qNo) {
        StringBuilder b = new StringBuilder();
        b.append("<p class=\"question\">" + (qNo + 1) + ") " + this.questions[qNo].question + "</p>");
        b.append("<ul class=\"answers\">");
        for (int j = 0; j < 4; j++) {
            b.append("<input type=\"radio\" name=\"q" + (qNo + 1) + "\" value=\"" + this.questions[qNo].options[j] + "\" id=\"q1a\"><label for=\"q1a\">" + this.questions[qNo].options[j] + "</label><br/>");
        }
        b.append("</ul>");
        return b.toString();
    }

    public static void main(String args[]) {
        Statement statement = null;
        ResultSet rs = null;
        String db = "javapeda";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + db.trim(), "root", "");

            Assessment assessment = new Assessment(conn, "Assessment 1");
//            System.out.println("ID: " + assessment.assessmentID);
//            System.out.println("noID: " + assessment.noOfQuestion);
//            for (int i = 0; i < assessment.noOfQuestion; i++) {
//                System.out.println("Qid: " + assessment.questions[i].questionID);
//                System.out.println("Question: " + assessment.questions[i].question);
//                for (int j = 0; j < 4; j++) {
//                    System.out.println("Option: " + assessment.questions[i].options[j]);
//                }
//                System.out.println("Answer: " + assessment.questions[i].answer);
//            }

            System.out.println(assessment.constructQuestion());
        } catch (Exception e) {

        }
    }
}
