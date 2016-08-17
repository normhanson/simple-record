package com.hansoncoyne.simple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nhanson
 */
public class SimpleRecordServiceTest {
    private SimpleRecordService service = new SimpleRecordService();
    
    public SimpleRecordServiceTest() {
    }
    
    @Test
    public void testLoadExampleData() {
        try {
            List<Person> persons = new ArrayList();
            service.loadExampleData(persons);
            assertEquals(persons.size(), 10);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test 
    public void testAddRecord() {
        try {
            List<Person> persons = new ArrayList();
            String exampleRecord = "Anderson|Ted|Male|Blue|01/31/1974";
            service.addRecord(persons, exampleRecord);
            assertEquals(persons.size(), 1);
            assertEquals(persons.get(0).getFormattedDob(), "01/31/1974");
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testGetRecordsByBirthDate() {
        try {
            List<Person> persons = new ArrayList();
            service.loadExampleData(persons);
            service.sortByBirthdate(persons);
            Date smallestDate = persons.get(0).getDob();
            Date largestDate = persons.get(persons.size()-1).getDob();
            Long diff = largestDate.getTime() - smallestDate.getTime();
            assertTrue(diff > 0);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testGetRecordsByGender() {
        try {
            List<Person> persons = new ArrayList();
            service.loadExampleData(persons);
            service.sortByGender(persons);
            assertEquals(persons.get(0).getGender(), 'F');
            assertEquals(persons.get(4).getGender(), 'F');
            assertTrue(persons.get(0).getLastName().compareToIgnoreCase(persons.get(4).getLastName()) < 0);
            assertEquals(persons.get(persons.size()-1).getGender(), 'M');
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    
    @Test
    public void testGetRecordsByName() {
        try {
            List<Person> persons = new ArrayList();
            service.loadExampleData(persons);
            service.sortByName(persons);
            assertTrue(persons.get(0).getLastName().compareToIgnoreCase(persons.get(8).getLastName()) > 0);
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testGetListAsString() {
        try {
            List<Person> persons = new ArrayList();
            
            String exampleRecord = "Anderson|Ted|Male|Blue|01/31/1974";
            service.addRecord(persons, exampleRecord);
            
            String exampleRecord2 = "Jones|Mike|Male|Blue|03/15/1976";
            service.addRecord(persons, exampleRecord2);
            
            String listAsString = service.getListAsString(persons);
            assertTrue(listAsString.startsWith("Anderson"));
            assertTrue(listAsString.contains("Jones"));
        } catch (IOException ex) {
            fail(ex.getMessage());
        }
                
    }
    
}
