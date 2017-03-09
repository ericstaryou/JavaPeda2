/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class User {

    String username;
    String password;
    String fname;
    String lname;
    State state;

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;

    public User() {
        this.state = new State();
    }

    public User(Connection conn) {
        this.connect(conn);
        this.state = new State();
    }

    public User(Connection conn, String username, String password, String fname, String lname) {
        this.connection = conn;
        this.username = username;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.state = new State();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return this.lname;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ResultSet getRs() {
        return this.rs;
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
            select("select username from user where username='" + user + "'");
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
            ps.setString(2, this.hashPassword(str[1].trim())); //password
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

    public boolean validateUsername(String username) {
        return this.exists(username);
    }

    //for registration
    public void createUser(String username, String password, String fname, String lname) {
        String str[] = {username, password, fname, lname};
        this.insert(str);
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //encrypt plaintext to Sha1
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] result = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    //for topic selection *******
    public void updateSelectedTopic(int subtopicID){
        this.state.subtopicID = subtopicID;
    }

    public static void main(String[] args) {
        Statement statement = null;
        ResultSet rs = null;
        String str = "SELECT COUNT(*) FROM claims WHERE mem_id='er-yew'";
        String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('meaydin', 'meaydin')";
        String update = "UPDATE `Users` SET `password`='eaydin' WHERE `username`='eaydin' ";
        String db = "javapeda";

        User user = new User();
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + db.trim(), "root", "");
            user.connect(conn);

            //System.out.println(user.hashPassword("Test1"));
            user.createUser("terrywcl", "Test1", "Terry", "Wong");
        } catch (Exception e) {

        }

        //user.connect(conn);
        //System.out.println(user.exists("2"));
    }
}
