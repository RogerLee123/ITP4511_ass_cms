/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.bean.BookingBean;
import ict.bean.VenueBean;
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
public class BookingDB {
    private String url = "";
    private String username = "";
    private String password = "";
    
    public BookingDB(String url, String username, String password){
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
    
    public ArrayList<BookingBean> queryBooking(){
        Connection cnnct = null;
        Statement stmnt = null;
        ResultSet rs = null;
        ArrayList<BookingBean> bookings = new ArrayList();
        String sql = "";

        BookingBean bb = null;
        try{
            cnnct = getConnection();
            
            sql = "SELECT * FROM booking";
            stmnt = cnnct.createStatement();
            stmnt.execute(sql);
            
            rs = stmnt.getResultSet();

            while(rs.next()){
                bb = new BookingBean();
                bb.setId(rs.getInt("id"));
                bb.setMemberId(rs.getInt("MemberId"));
                bb.setGuestListId(rs.getInt("guestlist_id"));
                bb.setNotificationId(rs.getInt("notification_id"));
                bb.setVenueId(rs.getInt("venue_id"));
                bb.setStatus(rs.getInt("status"));
                bb.setCreateTime(rs.getString("create_time"));
                bb.setFee(rs.getDouble("fee"));
                bb.setCheckoutTime(rs.getString("checkout_time"));
                bb.setStartDate(rs.getString("checkin_time"));
                bb.setStartHour(rs.getString("start_date"));
                bb.setTotalHour(rs.getString("start_hour"));
                bb.setTotalHour(rs.getString("total_hour"));
                bookings.add(bb);
            }
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return bookings;
    }
    
    public boolean addBooking(int id, int memberId, int guestListId, int notificationId, int venueId, int status, String createTime, String checkoutTime, String checkinTime, String startDate, String startHour, String totalHour, double fee){
        Connection cnnct = null;
   
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try{
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO booking(member_id, guestlist_id, notification_id, venue_id, status, create_time, fee, checkout_time, checkin_time, start_date, start_hour, total_hour) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, memberId);
            pStmnt.setInt(2, guestListId);
            pStmnt.setInt(3, notificationId);
            pStmnt.setInt(4, venueId);
            pStmnt.setInt(5, status);
            pStmnt.setString(6, createTime);
            pStmnt.setDouble(7, fee);
            pStmnt.setString(8, checkoutTime);
            pStmnt.setString(9, checkinTime);
            pStmnt.setString(10, startDate);
            pStmnt.setString(11, startHour);
            pStmnt.setString(12, totalHour);
            
           
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
        } catch(IOException ex){ 
            ex.printStackTrace(); 
        }
        
        return isSuccess;
    }
    
    public boolean editBooking(BookingBean bb){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        ArrayList<BookingBean> bookings = new ArrayList();
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "UPDATE booking SET member_id = ?, guestlist_id = ?, notification_id = ?, venue_id = ?, status = ?, create_time = ?, fee = ?, checkout_time = ?, checkin_time = ?, start_date = ?, start_hour = ?, total_hour = ? WHERE id = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bb.getMemberId());
            pStmnt.setInt(2, bb.getGuestListId());
            pStmnt.setInt(3, bb.getNotificationId());
            pStmnt.setInt(4, bb.getVenueId());
            pStmnt.setInt(5, bb.getStatus());
            pStmnt.setString(6, bb.getCreateTime());
            pStmnt.setDouble(7, bb.getFee());
            pStmnt.setString(8, bb.getCheckoutTime());
            pStmnt.setString(9, bb.getCheckinTime());
            pStmnt.setString(10, bb.getStartDate());
            pStmnt.setString(11, bb.getStartHour());
            pStmnt.setString(12, bb.getTotalHour());
            pStmnt.setInt(13, bb.getId());
            

            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return (rowsAffected > 0);
    }
    
    public boolean delRecord(String id){
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try{
            cnnct = getConnection();
            
            String preQueryStatement = "DELETE FROM booking WHERE custId = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            
            rowsAffected = pStmnt.executeUpdate();
            
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex){
            while (ex != null) { ex.printStackTrace(); ex = ex.getNextException();}
        } catch (IOException ex) { ex.printStackTrace();}
        return (rowsAffected > 0);
    }
    
    
}
