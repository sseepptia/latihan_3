/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Login;
import beans.LoginBean;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SEPTIA ANGGRAINI
 */
public class DAOLoginTest {
    
    public DAOLoginTest() {
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
    public void testLogin() {
        String testEmail = "yeni@gmail.com";
        String testPassword = "123456";
        
        DAOLogin DaoLogin = new DAOLogin();
     
        
        Login foundLogin = DaoLogin.findUserByEmail(testEmail);
        
        assertEquals(testEmail, foundLogin.getEmail());
        assertEquals(testPassword, foundLogin.getPassword());
    }
}
