/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author roger
 */
public class BookingBean {
    private int id, memberId, guestListId, notificationId, status, venueId;
    private String createTime, checkoutTime, checkinTime, startDate, startHour, totalHour;
    private double fee;

    public BookingBean(int id, int memberId, int guestListId, int notificationId, int status, int venueId, String createTime, String checkoutTime, String checkinTime, String start_date, String start_hour, String total_hour, double fee) {
        this.id = id;
        this.memberId = memberId;
        this.guestListId = guestListId;
        this.notificationId = notificationId;
        this.status = status;
        this.venueId = venueId;
        this.createTime = createTime;
        this.checkoutTime = checkoutTime;
        this.checkinTime = checkinTime;
        this.startDate = start_date;
        this.startHour = start_hour;
        this.totalHour = total_hour;
        this.fee = fee;
    }

    public BookingBean() {
    }
    
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public int getVenueId() {
        return venueId;
    }
    
    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getGuestListId() {
        return guestListId;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getStatus() {
        return status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public String getCheckinTime() {
        return checkinTime;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setGuestListId(int guestListId) {
        this.guestListId = guestListId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(String totalHour) {
        this.totalHour = totalHour;
    }

    public double getFee() {
        return fee;
    }
    
   
}
