package com.example.reservation;

import org.junit.*;
import static org.junit.Assert.*;

public class RequestTest {

    private Request request;

    @Before
    public void setUp() {
        request = new Request(101, 1, 102,"2024-12-01", "18:00", 4, Request.ReservationStatus.PENDING, "A table near the window");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(101, request.getReservationID());
        assertEquals(1, request.getStoreID());
        assertEquals(102, request.getStoreID());

        assertEquals("2024-12-01", request.getDate());
        assertEquals("18:00", request.getTime());
        assertEquals(4, request.getNumofpeople());
        assertEquals(Request.ReservationStatus.PENDING, request.getReservationStatus());
        assertEquals("A table near the window", request.getComment());
    }

    @Test
    public void testSettersAndGetters() {
        request.setReservationID(102);
        assertEquals(102, request.getReservationID());

        request.setStoreID(2);
        assertEquals(2, request.getStoreID());

        request.setCustomerId(102);
        assertEquals(102, request.getStoreID());

        request.setDate("2024-12-02");
        assertEquals("2024-12-02", request.getDate());

        request.setTime("19:00");
        assertEquals("19:00", request.getTime());

        request.setNumofpeople(6);
        assertEquals(6, request.getNumofpeople());

        request.setReservationStatus(Request.ReservationStatus.APPROVED);
        assertEquals(Request.ReservationStatus.APPROVED, request.getReservationStatus());

        request.setComment("Birthday dinner");
        assertEquals("Birthday dinner", request.getComment());
    }

    @Test
    public void testStatusChange() {
        request.setReservationStatus(Request.ReservationStatus.APPROVED);
        assertEquals(Request.ReservationStatus.APPROVED, request.getReservationStatus());

        request.setReservationStatus(Request.ReservationStatus.REJECTED);
        assertEquals(Request.ReservationStatus.REJECTED, request.getReservationStatus());
    }

    @Test
    public void testEmptyComment() {
        request.setComment("");
        assertEquals("", request.getComment());
    }

    @Test
    public void testEdgeCaseNumOfPeople() {
        request.setNumofpeople(1);
        assertEquals(1, request.getNumofpeople());

        request.setNumofpeople(100);
        assertEquals(100, request.getNumofpeople());
    }
}
