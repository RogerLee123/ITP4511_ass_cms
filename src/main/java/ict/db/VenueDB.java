package ict.db;


import ict.bean.VenueBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author roger
 */
public class VenueDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public VenueDB(String url, String username, String password){
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
    
    public ArrayList<VenueBean> queryVenue(){
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<VenueBean> venues = new ArrayList();
        String sql = "";

        VenueBean vb = null;
        try{
            cnnct = getConnection();
            
            sql = "SELECT * FROM venue";
            stmnt = cnnct.createStatement();
            stmnt.execute(sql);
            
            rs = stmnt.getResultSet();

            while(rs.next()){
                vb = new VenueBean();
                vb.setId(rs.getInt("id"));
                vb.setName(rs.getString("name"));
                vb.setStaffId(rs.getInt("staffid"));
                vb.setAddress(rs.getString("address"));
                vb.setDesc(rs.getString("desc"));
                vb.setImg(rs.getString("img"));
                vb.setType(rs.getString("type"));
                vb.setCapacity(rs.getInt("capacity"));
                vb.setFee(rs.getDouble("fee"));
                vb.setLastModifiedFee(rs.getString("lastmodifiedfee"));
                venues.add(vb);
            }
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return venues;
    }
    
    public ArrayList<VenueBean> queryVenueNameById(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        ArrayList<VenueBean> venues = new ArrayList();
        

        VenueBean vb = null;
        try{
            cnnct = getConnection();
            
            String preQueryStatement = "SELECT * FROM venue WHERE id=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, id);
            pStmnt.executeQuery();
            
            rs = pStmnt.getResultSet();
            String venue = rs.getString("name");

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return venues;
    }
    
    public boolean addVenue(int staffId, String name, String address, String desc, String img, String type, int capacity, double fee){
        Connection cnnct = null;
   
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO venue VALUES (default, ?, ?, ?, ?, '123', ?, ?, ?, 0, default)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, staffId);
            pStmnt.setString(2, name);
            pStmnt.setString(3, address);
            pStmnt.setString(4, desc);
            //pStmnt.setString(5, img);
            pStmnt.setString(5, type);
            pStmnt.setInt(6, capacity);
            pStmnt.setDouble(7, fee);
            
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
    
    public boolean editVenue(int id, int staffId, String name, String address, String desc, String img, String type, int capacity, double fee, String lastModifiedFee){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        ArrayList<VenueBean> venues = new ArrayList();
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "UPDATE venue SET staffId = ?, name = ?, address = ?, desc = ?, img = ?, type = ?, capacity = ?, fee = ?, last_modified_fee = ? WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, staffId);
            pStmnt.setString(2, name);
            pStmnt.setString(3, address);
            pStmnt.setString(4, desc);
            pStmnt.setString(5, img);
            pStmnt.setString(6, type);
            pStmnt.setInt(7, capacity);
            pStmnt.setDouble(8, fee);
            pStmnt.setString(9, lastModifiedFee);
            pStmnt.setInt(10, id);

            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return (rowsAffected > 0);
    }
    
    public boolean delVenue(int id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "DELETE FROM venue WHERE id = ?";
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
