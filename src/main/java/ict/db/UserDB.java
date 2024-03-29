/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.UserBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author roger
 */
public class UserDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public UserDB(String url, String username, String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException, IOException {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return DriverManager.getConnection(url, username, password);
    }
    
    public boolean isValidUser(String email, String pwd){
        boolean isValid = false;
        Connection connection;
        PreparedStatement pstate;
        ResultSet rs;
        
        try{
            connection = getConnection();
            String preQueryStatement = "SELECT * FROM user WHERE email = ? AND pwd = ?";
            pstate = connection.prepareStatement(preQueryStatement);
            pstate.setString(1, email);
            pstate.setString(2, pwd);
            //System.out.println(pstate);
            pstate.executeQuery();
            rs = pstate.getResultSet();
            
            if(rs.next()){
               isValid = true;
            }
        } catch (SQLException e) {
            while (e != null) {
                e.printStackTrace();
                e = e.getNextException();
            }
        } catch (IOException e) {
            System.out.print(e);
        }
        return isValid;
    }
    
    public int queryUserRoleById(int id){
        Connection connection;
        PreparedStatement pstate;
        ResultSet rs;
        int role = 0;

        try{
            connection = getConnection();
            String preQueryStatement = "SELECT role FROM user WHERE id = ?";
            pstate = connection.prepareStatement(preQueryStatement);
            pstate.setInt(1, id);
            pstate.executeQuery();
            
            rs = pstate.getResultSet();
            role = rs.getInt("role");
                        
            pstate.close();
            connection.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return role;
    }
    
    public ArrayList<UserBean> queryUser(){
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<UserBean> users = new ArrayList();
        String sql = "";

        UserBean bb = null;
        try{
            cnnct = getConnection();
            
            sql = "SELECT * FROM user";
            stmnt = cnnct.createStatement();
            stmnt.execute(sql);
            
            rs = stmnt.getResultSet();

            while(rs.next()){
                bb = new UserBean();
                bb.setId(rs.getInt("id"));
                bb.setFname(rs.getString("fname"));
                bb.setLname(rs.getString("lname"));
                bb.setEmail(rs.getString("email"));
                bb.setPhone(rs.getInt("phone"));
                bb.setPwd(rs.getString("pwd"));
                bb.setRole(rs.getInt("role"));

                users.add(bb);
            }
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return users;
    }
    
    public String queryUserName(String email, String pwd){
        Connection connection;
        PreparedStatement pstate;
        ResultSet rs;
        String name="";


        try{
            connection = getConnection();
            String preQueryStatement = "SELECT lname, fname FROM user WHERE email = ? AND pwd = ?";
            pstate = connection.prepareStatement(preQueryStatement);
            pstate.setString(1, email);
            pstate.setString(2, pwd);
            pstate.executeQuery();
            
            rs = pstate.getResultSet();
            
            name = rs.getString("fname") + rs.getString("lname");
            
            
            pstate.close();
            connection.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return name;
    }
    
    public boolean addUser(String fname, String lname, String email, int phone, String pwd, int role){
        Connection cnnct = null;
   
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO user VALUES (default, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, fname);
            pStmnt.setString(2, lname);
            pStmnt.setString(3, email);
            pStmnt.setInt(4, phone);
            pStmnt.setString(5, pwd);
            pStmnt.setInt(6, role);
            System.out.println(pStmnt);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch(SQLException ex){
             while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch(IOException ex){ ex.printStackTrace(); }
            return isSuccess;
        
    }
    
    public boolean editUser(int id, String fname, String lname, String email, int phone, String pwd, int role){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "UPDATE user SET fname = ?, lname = ?, email = ?, phone = ?, pwd = ?, role = ? WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, fname);
            pStmnt.setString(2, lname);
            pStmnt.setString(3, email);
            pStmnt.setInt(4, phone);
            pStmnt.setString(5, pwd);
            pStmnt.setInt(6, role);
            pStmnt.setInt(7, id);
            int rowCount = pStmnt.executeUpdate();
            if(rowCount >= 1){
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch(SQLException ex){
             while(ex != null){
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch(IOException ex){ ex.printStackTrace(); }
            return isSuccess;
    }
    
    
    public boolean delUser(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "DELETE FROM user WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            
            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        
        return (rowsAffected > 0);
    }
}
