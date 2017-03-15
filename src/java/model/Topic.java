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
public class Topic {

    int topicID;
    Subtopic[] subtopics;
    Subtopic subtopic;
    Assessment assessment;
    String topicName;
    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    //for selected topic use (in TeachingControl)
    public Topic(Connection conn, String subtopicName) {
        try {
            this.connect(conn);
            this.subtopic = new Subtopic();
            this.subtopic.title = subtopicName;
            this.select("SELECT * FROM subtopic WHERE title ='" + subtopicName + "'");
            while (rs.next()) {
                this.topicID = rs.getInt(2);
                this.subtopic.subtopicID = rs.getInt(1);
                this.subtopic.textualExplanation = rs.getString(4);
                this.subtopic.codeValue = rs.getString(5);
            }
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //for UI generation use (in DashboardControl)
    public Topic(Connection conn, int id) {
        try {
            this.connect(conn);
            this.topicID = id;
            this.select("SELECT * FROM topic WHERE topicID =" + id);
            while (rs.next()) {
                this.topicName = rs.getString(3);
            }

            this.select("SELECT COUNT(*) FROM subtopic WHERE topicID=" + id);
            int noOfSubtopic = 0;
            while (rs.next()) {
                noOfSubtopic = rs.getInt(1);
            }
            this.subtopics = new Subtopic[noOfSubtopic];
            for (int i = 0; i < noOfSubtopic; i++) {
                this.subtopics[i] = new Subtopic();
            }

            this.select("SELECT * FROM subtopic WHERE topicID =" + topicID);
            for (int i = 0; i < noOfSubtopic; i++) {
                rs.next();
                this.subtopics[i].subtopicID = rs.getInt(1);
                this.subtopics[i].title = rs.getString(3);
                this.subtopics[i].textualExplanation = rs.getString(4);
                this.subtopics[i].codeValue = rs.getString(5);
            }

            //this.assessment = new Assessment();
        } catch (Exception ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Subtopic getSubtopic() {
        return subtopic;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public Subtopic[] getSubtopics() {
        return subtopics;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
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

    //for the use of selected topic only (in TeachingControl)
    public String getDemoCode() {
        return this.subtopic.codeValue;
    }

    public String getSubtopicTitles() {
        StringBuilder b = new StringBuilder();
        try {
            select("SELECT * FROM subtopic WHERE topicID=" + this.topicID);
            while (rs.next()) {
                b.append("<input class=\"subt\" name=\"opt\" type=\"submit\" value=\"" + rs.getString(3) + "\"><br>");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return b.toString();
    }

    public static void main(String args[]) {
        Statement statement = null;
        ResultSet rs = null;
        String str = "SELECT COUNT(*) FROM claims WHERE mem_id='er-yew'";
        String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='eaydin' ";
        String db = "javapeda";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + db.trim(), "root", "");

            Topic topic = new Topic(conn, 1);
            //System.out.println(topic.topicName);
            topic.select("SELECT COUNT(*) FROM subtopic WHERE topicID=" + 1);
            rs = topic.getRs();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            //System.out.println(topic.getRs());

        } catch (Exception e) {

        }
    }

}
