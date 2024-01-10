/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import beans.LoginBean;

/**
 *
 * @author SEPTIA ANGGRAINI
 */
public class LoginTest {

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLoginUser() {
        System.out.println("Test Login User");

        LoginBean instance = new LoginBean();
        instance.setEmail("yeni@gmail.com");
        instance.setPassword("123456");

        String expResult = "dashboard";
        String result = instance.login();
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}
