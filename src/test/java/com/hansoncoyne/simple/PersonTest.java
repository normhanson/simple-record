package com.hansoncoyne.simple;
/**
 * 
 * @author nhanson
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Remember ... Spaces are not valid characters, so we dont need to test for blanks
 * @author nhanson
 */
public class PersonTest {
    
    public PersonTest() {
    }
  
    @Test 
    public void testLastName() 
    {      
        //basic
        String smith = "SMITH";
        Person person = new Person();
        Person person2 = new Person();
        
        person.setLastName(smith);
        person2.setLastName(smith);
        
        assertEquals(smith, person.getLastName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //null
        person.setLastName(null);
        person2.setLastName(null);
        
        assertNull(person.getLastName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
       //empty
        String empty = "";
        person.setLastName(empty);
        person2.setLastName(empty);
        
        assertNull(person.getLastName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
    }
    
    @Test 
    public void testFirstName () 
    {       
        //basic
        String smith = "SMITH";
        Person person = new Person();
        Person person2 = new Person();
        
        person.setFirstName(smith);
        person2.setFirstName(smith);
        
        assertEquals(smith, person.getFirstName());
        assertEquals(smith, person.getFirstName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //null
        person.setFirstName(null);
        person2.setFirstName(null);
        
        assertNull(person.getFirstName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //empty
        String empty = "";
        person.setFirstName(empty);
        person2.setFirstName(empty);
        
        assertNull(person.getFirstName());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
            
    }
    
    @Test 
    public void testColor() {
        
        //basic
        String myColor = "PiNK";
        Person person = new Person();
        Person person2 = new Person();
        
        person.setFavColor(myColor);
        person2.setFavColor(myColor);
        
        assertEquals(myColor.toLowerCase(), person.getFavColor());
        assertEquals(myColor.toLowerCase(), person.getFavColor());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //null
        person.setFavColor(null);
        person2.setFavColor(null);
        
        assertNull(person.getFavColor());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //empty
        myColor = "";
        person.setFavColor(myColor);
        assertNull(person.getFavColor());
    }
    
    @Test 
    public void testDOB() {
        
        //basic
        String realDate = "12/25/1985";
        
        Person person = new Person();
        Person person2 = new Person();
        
        person.setDob(realDate);
        person2.setDob(realDate);
        
        assertEquals(realDate, person.getFormattedDob());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //null
        person.setDob(null);
        person2.setDob(null);
        
        assertNull(person.getFormattedDob());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //parseException
        String badDate = "GH/25/1985";
                
        person.setDob(badDate);
        person2.setDob(badDate);
        
        assertNull(person.getFormattedDob());
        assertNull(person.getFormattedDob());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
        
        //empty
        badDate = "";
                
        person.setDob(badDate);
        person2.setDob(badDate);
        
        assertNull(person.getFormattedDob());
        assertNull(person.getFormattedDob());
        assertFalse(person.equals(person2));
        assertTrue(person.hashCode() != 0 );
    }
    
    @Test 
    public void testGender() {
        
        //basic
        String gender = "male";
        Person person = new Person();
        
        person.setGender(gender);
                
        gender = "MaLE";
        person.setGender(gender);        
        assertEquals('M', person.getGender());
        
        gender = "female";
        person.setGender(gender);        
        assertEquals('F', person.getGender());
                
        gender = "FEmale";
        person.setGender(gender);        
        assertEquals('F', person.getGender());
                
        gender = "Im.not.answering";
        person.setGender(gender);        
        assertEquals('X', person.getGender()); 
        
        gender = "";
        person.setGender(gender);        
        assertEquals('X', person.getGender());
        
        //null
        person.setGender(null);
        assertEquals('X', person.getGender());
        assertTrue(person.hashCode() != 0 );
        
    }
    
    @Test
    public void testIsValid() {
        Person person = new Person();
        assertFalse(person.isValid());
        person.setLastName("Test");
        assertTrue(person.isValid());
    }
    
    
    @Test
    public void testEmptyEquals() {
        Person person = new Person();
        Person person2 = new Person();
        assertFalse(person.equals(person2));
        
    }
            
}
