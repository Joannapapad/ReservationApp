package com.example.reservation;

public class Request {
    private int reservationID;
    private int storeID;
    private int customerId;

    private String date;
    private String time;
    private int numofpeople;
    private ReservationStatus reservationStatus;
    private String comment;

    public enum ReservationStatus {
        PENDING,    // Request is awaiting action
        APPROVED,   // Request has been approved
        REJECTED;   // Request has been rejected
    }

    public Request(int reservationID,int storeID, int customerId ,String date, String time,int numofpeople,ReservationStatus status, String comment){
        this.reservationID = reservationID;
        this.storeID = storeID;
        this.customerId = customerId;
        this.date = date;
        this.time = time;
        this.numofpeople = numofpeople;
        this.reservationStatus = status;
        this.comment = comment;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public int getNumofpeople() {
        return numofpeople;
    }

    public void setNumofpeople(int numofpeople) {
        this.numofpeople = numofpeople;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
