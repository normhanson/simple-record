/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansoncoyne.simple;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nhanson
 */
public class SimpleRestAppTest {
    
    SimpleRestApp simpleRest = new SimpleRestApp();
    
    public SimpleRestAppTest() {
    }
    
    @Test
    public void loadExampleData() {
        simpleRest.reset();
        String response = simpleRest.loadExampleData();
        assertTrue(response.length() > 0);
    }
    
    @Test
    public void putRecord() {
        
        simpleRest.reset();
        String response = simpleRest.putRecord("Murphy,Jane,F,pink,2/28/1907");
        assertTrue(response.length() > 0);
    }
    
}
