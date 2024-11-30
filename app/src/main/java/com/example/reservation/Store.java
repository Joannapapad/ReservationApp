package com.example.reservation;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Store")

public class Store {
    @PrimaryKey(autoGenerate = true)
    private int storeId;
    private String name;
    private String address;
    private String location;
    private String type;
    private int capacity;
    private int tableNum;
    private List<Request> requests;

    // Constructor
    public Store(int storeId, String name, String address, String location, String type, int capacity, int tableNum) {
        this.storeId = storeId;
        this.name = name;
        this.address = address;
        this.location = location;
        this.type = type;
        this.capacity = capacity;
        this.tableNum = tableNum;
        this.requests = new ArrayList<>();
    }

    // Getters and Setters
    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void acceptRequest(Request request) {
        request.setReservationStatus(Request.ReservationStatus.APPROVED);
        cancelConflictingRequests(request);
    }

    public void denyRequest(Request request) {
        request.setReservationStatus(Request.ReservationStatus.REJECTED);
    }

    private void cancelConflictingRequests(Request approvedRequest) {
        for (Request request : requests) {
            if (request.getReservationStatus() == Request.ReservationStatus.PENDING &&
                    request.getDate().equals(approvedRequest.getDate()) &&
                    request.getTime().equals(approvedRequest.getTime()) &&
                    request.getNumofpeople() == approvedRequest.getNumofpeople()) {
                denyRequest(request);
            }

            cancelCustomerRequest(approvedRequest);
        }
    }

    private void cancelCustomerRequest(@NonNull Request approvedRequest) {
        Customer customer = findCustomerById(approvedRequest.getCustomerId());
        List<Request> requests = customer.getReservations();
        for (Request request : requests) {

            if (customer != null && request.getReservationStatus() == Request.ReservationStatus.PENDING &&
                    request.getDate().equals(approvedRequest.getDate()) &&
                    request.getTime().equals(approvedRequest.getTime())) {

                customer.cancelRequest(request);
            }
        }
    }

    public void showRequestDetails() {
        for (Request request : requests) {
            System.out.println("Request ID: " + request.getReservationID());
            System.out.println("Date - Time: " + request.getDate() + " " + request.getTime());
            System.out.println("Request Status: " + request.getReservationStatus());
            System.out.println("Number of People: " + request.getNumofpeople());
            System.out.println("Comment: " + request.getComment());
            System.out.println("-----------------------------");
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId=" + storeId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", tableNum=" + tableNum +
                '}';
    }
}
