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
public class VenueBean {
    private int id, staffId;
    private String name, address, desc, img, type;
    private int capacity;
    private double fee;
    private String lastModifiedFee;

    public VenueBean(int id, int staffId, String name, String address, String desc, String img, String type, int capacity, double fee, String lastModifiedFee) {
        this.id = id;
        this.staffId = staffId;
        this.name = name;
        this.address = address;
        this.desc = desc;
        this.img = img;
        this.type = type;
        this.capacity = capacity;
        this.fee = fee;
        this.lastModifiedFee = lastModifiedFee;
    }


    public VenueBean() {
    }
    
    
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getLastModifiedFee() {
        return lastModifiedFee;
    }

    public void setLastModifiedFee(String lastModifiedFee) {
        this.lastModifiedFee = lastModifiedFee;
    }

}
