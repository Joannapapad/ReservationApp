package com.example.reservation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoreTest {

    private Store store;
    private Request request1, request2, request3;

    @Before
    public void setUp() {
        // Initialize Store object with mock data
        store = new Store(1001, "Benjamin", "123 Mesogeivn", "Agia Paraskeui", "Burger Restaurant", 50, 10);

        // Create mock request objects
        request1 = new Request(101, 1001, 102, "2024-12-01", "18:00", 4, Request.ReservationStatus.PENDING, "Window seat");
        request2 = new Request(102, 1001, 103,"2024-12-01", "19:00", 2, Request.ReservationStatus.PENDING, "Near the door");
        request3 = new Request(103, 1001, 104,"2024-12-01", "18:00", 4, Request.ReservationStatus.PENDING, "Next to the bar");

        // Add requests to the store
        store.addRequest(request1);
        store.addRequest(request2);
        store.addRequest(request3);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1001, store.getStoreId());
        assertEquals("Benjamin", store.getName());
        assertEquals("123 Mesogeivn", store.getAddress());
        assertEquals("Agia Paraskeui", store.getLocation());
        assertEquals("Burger Restaurant", store.getType());
        assertEquals(50, store.getCapacity());
        assertEquals(10, store.getTableNum());
    }

    @Test
    public void testAddRequest() {
        Request newRequest = new Request(104, 1001, 102,"2024-12-02", "20:00", 3, Request.ReservationStatus.PENDING, "Special request");
        store.addRequest(newRequest);
        assertEquals(4, store.getRequests().size());
        assertTrue(store.getRequests().contains(newRequest));
    }

    @Test
    public void testAcceptRequest() {
        store.acceptRequest(request1);
        assertEquals(Request.ReservationStatus.APPROVED, request1.getReservationStatus());
    }

    @Test
    public void testDenyRequest() {
        store.denyRequest(request2);
        assertEquals(Request.ReservationStatus.REJECTED, request2.getReservationStatus());
    }

    @Test
    public void testCancelConflictingRequests() {
        // Initially, request3 has the same time and number of people as request1, so it should be denied when request1 is accepted
        store.acceptRequest(request1);
        assertEquals(Request.ReservationStatus.REJECTED, request3.getReservationStatus());
    }

    @Test
    public void testShowRequestDetails() {
        // This is a method that prints output, so we need to check if it's called.
        // Ideally, this method would be tested by verifying the printed output,
        // but as it currently stands, you'd have to use a tool like System.setOut to capture the output.
        // For simplicity, this test assumes the showRequestDetails is invoked without errors.

        store.showRequestDetails();
        // No assertions are made here, it's just to check if the method executes correctly
        // Optionally, you can mock System.out and verify the output.
    }

    @Test
    public void testToString() {
        String expectedString = "Store{storeId=1001, name='Benjamin', address='123 Mesogeivn', location='Agia Paraskeui', type='Burger Restaurant', capacity=50, tableNum=10}";
        assertEquals(expectedString, store.toString());
    }

    @Test
    public void testEmptyRequestList() {
        Store emptyStore = new Store(1002, "Empty Store", "456 Empty Address", "Empty Location", "Empty Type", 0, 0);
        assertTrue(emptyStore.getRequests().isEmpty());
    }
}
