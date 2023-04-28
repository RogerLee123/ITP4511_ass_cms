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
                vb.setStaffId(rs.getInt("staffId"));
                vb.setAddress(rs.getString("address"));
                vb.setDesc(rs.getString("desc"));
                vb.setImg(rs.getString("img"));
                vb.setType(rs.getString("type"));
                vb.setCapacity(rs.getInt("capacity"));
                vb.setFee(rs.getDouble("fee"));
                vb.setLastModifiedFee(rs.getString("last_modified_fee"));
                venues.add(vb);
            }
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return venues;
    }
    
    public boolean addVenue(int staffId, String name, String address, String desc, String img, String type, int capacity, double fee, String lastModifiedFee){
        Connection cnnct = null;
   
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO venue(staffId, name, address, desc, img, type, capacity, fee, last_modified_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public boolean editVenue(VenueBean cb){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        ArrayList<VenueBean> venues = new ArrayList();
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "UPDATE venue SET staffId = ?, name = ?, address = ?, desc = ?, img = ?, type = ?, capacity = ?, fee = ?, last_modified_fee = ? WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, cb.getStaffId());
            pStmnt.setString(1, cb.getName());
            pStmnt.setString(1, cb.getAddress());
            pStmnt.setString(1, cb.getDesc());
            pStmnt.setString(1, cb.getImg());
            pStmnt.setString(1, cb.getType());
            pStmnt.setInt(2, cb.getCapacity());
            pStmnt.setDouble(3, cb.getFee());
            pStmnt.setString(4, cb.getLastModifiedFee());

            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return (rowsAffected > 0);
    }
    
    public boolean delRecord(String custId){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "DELETE FROM CUSTOMER WHERE custId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, custId);
            
            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return (rowsAffected > 0);
    }
}
