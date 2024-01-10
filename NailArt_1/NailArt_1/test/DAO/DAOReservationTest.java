/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import POJO.Reservation;
import java.util.List;
import java.util.Date;

/**
 *
 * @author SEPTIA ANGGRAINI
 */
public class DAOReservationTest {
    
    public DAOReservationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("// Starting Test //");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("// Ending Test //");
    }
    
    @Before
    public void setUp() {
        System.out.println("\n");
    }
    
    @After
    public void tearDown() {
        System.out.println("\n");
    }
    
    @Test
    public void testGetAllReservations() {
        System.out.println("Test List Reservation");
        DAOReservation reservation = new DAOReservation();
        List<Reservation> result = reservation.getAllReservations();
        System.out.println("Total Reservation inside: " + result.toArray().length);
        assertFalse(result.isEmpty());
    }
    
    @Test
    public void testInsertReservation() {
        System.out.println("Test Insert Reservation");
        
        Date date = new Date();
        Reservation reservation = new Reservation();
        reservation.setName("Test");
        reservation.setPhoneNumber("086668313678");
        reservation.setDate(date);
        reservation.setHours(date);

        DAOReservation DaoReservation = new DAOReservation();
        DaoReservation.insertReservation(reservation);

        assertNotNull(reservation);
    }
    
    @Test
    public void testUpdateReservation() {
        System.out.println("Test Update Reservation");
        
        Date date = new Date();
        Reservation reservation = new Reservation();
        reservation.setId(50);
        reservation.setName("Test");
        reservation.setPhoneNumber("086668313678");
        reservation.setDate(date);
        reservation.setHours(date);

        DAOReservation DaoReservation = new DAOReservation();
        DaoReservation.updateReservation(reservation);

        assertNotNull(reservation);
    }
    
    @Test
    public void testDeleteReservation() {
        System.out.println("Test Delete Reservation");
        DAOReservation DaoReservation = new DAOReservation();
        boolean result = DaoReservation.deleteReservation(50);
        
        assertTrue(result);        
    }
    
    @Test
    public void testGetReservationById() {
        System.out.println("Test Get Reservation By Id");
        DAOReservation DaoReservation = new DAOReservation();
        Reservation reservation = DaoReservation.getReservationById(2);
        
        assertNotNull(reservation);
    }
    
}
