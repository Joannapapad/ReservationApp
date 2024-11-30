package com.example.reservation;

import java.util.ArrayList;
import java.util.List;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Customer")

public class Customer {
    @PrimaryKey(autoGenerate = true)

    private int customerId;
    private String name;
    private List<Request> reservations;  // List of reservations made by the customer

    // Constructor
    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.reservations = new ArrayList<>();
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Request> getReservations() {
        return reservations;
    }

    public void createRequest(Store store, String date, String time, int numOfPeople, String comment) {
        if (store == null) {
            System.out.println("Store not found.");
            return;
        }

        Request request = new Request(generateReservationId(), store.getStoreId(), this.getCustomerId(), date, time, numOfPeople, Request.ReservationStatus.PENDING, comment);

        store.addRequest(request);

        reservations.add(request);

        System.out.println("Reservation request made successfully for " + store.getName() + "!");
    }

    public void showRequests() {
        if (reservations.isEmpty()) {
            System.out.println("You have no reservation requests.");
            return;
        }

        for (Request request : reservations) {
            System.out.println("Request ID: " + request.getReservationID());
            System.out.println("Store ID: " + request.getStoreID());
            System.out.println("Date and Time: " + request.getDate() + " " + request.getTime());
            System.out.println("Status: " + request.getReservationStatus());
            System.out.println("Number of People: " + request.getNumofpeople());
            System.out.println("Comment: " + request.getComment());
            System.out.println("-----------------------------");
        }
    }

    public void cancelRequest(Request deny_request) {
        for (Request request : reservations) {
            if (request.getReservationID() == deny_request.getReservationID()) {
                findStorebyID(deny_request.getStoreID()).denyRequest(request);
                reservations.remove(request);
                System.out.println("Reservation request with ID " + deny_request.getReservationID() + " has been canceled.");
                return;
            }
        }

        System.out.println("Reservation request with ID " + deny_request.getReservationID() + " not found.");
    }
    private int generateReservationId() {
        return (int) (Math.random() * 10000);
    }
}
