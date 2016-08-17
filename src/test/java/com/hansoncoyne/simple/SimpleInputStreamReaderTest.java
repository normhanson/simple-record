package com.hansoncoyne.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nhanson
 */
public class SimpleInputStreamReaderTest {
    
    
    String samplePipe = "simple.pipe";
    String sampleComma = "simpleData.csv";
    String sampleTxt = "simple.txt";
    String empty = "empty.txt";
    String skipRowsPipe = "skip_rows.pipe";
    String missingColumnsTxt = "missing_columns.txt";
    
    InputStream pipeIn = this.getClass().getClassLoader().getResourceAsStream(samplePipe);
    InputStream commaIn = this.getClass().getClassLoader().getResourceAsStream(sampleComma);
    InputStream spaceIn = this.getClass().getClassLoader().getResourceAsStream(sampleTxt);
    InputStream emptyIn = this.getClass().getClassLoader().getResourceAsStream(empty);
    InputStream skipRows = this.getClass().getClassLoader().getResourceAsStream(skipRowsPipe);
    InputStream missingColumns = this.getClass().getClassLoader().getResourceAsStream(missingColumnsTxt);
    
    public SimpleInputStreamReaderTest() {
        
    }
    
    /** 
     * KISS test with a file
     */
    @Test
    public void testPipe() {
        
        try {
            SimpleInputStreamReader sisr = new SimpleInputStreamReader();            
            List<Person> persons = sisr.readRecords(pipeIn);
            assertEquals(4, persons.size());
            
            assertEquals(persons.get(0).getLastName(),"Anderson");
            assertEquals(persons.get(1).getLastName(),"Hanson");
            assertEquals(persons.get(2).getLastName(),"Smith");
            assertEquals(persons.get(3).getLastName(),"Smythe");
            
            assertEquals(persons.get(0).getFormattedDob(),"01/31/1974");
            assertEquals(persons.get(1).getFormattedDob(),"06/06/1939");
            assertEquals(persons.get(2).getFormattedDob(),"02/28/1907");
            assertEquals(persons.get(3).getFormattedDob(),"02/27/1907");
                        
        } catch (IOException ex) {
            Logger.getLogger(SimpleInputStreamReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    /**
     * tests the having lifting method with a variety of files 
     * 
     */
    @Test
    public void testInputStreamParsing() {
        
        try {
            SimpleInputStreamReader sisr = new SimpleInputStreamReader();            
            List<Person> persons = sisr.readRecords(pipeIn);
            assertEquals(4, persons.size());
            
            List<Person> personsComma = sisr.readRecords(commaIn);
            assertEquals(3, personsComma.size());
            
            List<Person> personsSpace = sisr.readRecords(spaceIn);
            assertEquals(3, personsSpace.size());
            
        } catch (IOException ex) {
            Logger.getLogger(SimpleInputStreamReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testEmptyFile() {
        
        try {
            SimpleInputStreamReader sisr = new SimpleInputStreamReader();               
            List<Person> emptyTest = sisr.readRecords(emptyIn);
            assertTrue( emptyTest.isEmpty());                          
            
        } catch (IOException ex) {
            Logger.getLogger(SimpleInputStreamReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testSkipRowsFile() {
        
        try {
            SimpleInputStreamReader sisr = new SimpleInputStreamReader();   
            List<Person> skipRowsTest = sisr.readRecords(skipRows);
            assertEquals(8, skipRowsTest.size());
            
        } catch (IOException ex) {
            Logger.getLogger(SimpleInputStreamReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @Test
    public void testMissingColumns() {
        
        try {
            SimpleInputStreamReader sisr = new SimpleInputStreamReader();   
            List<Person> missingCol = sisr.readRecords(missingColumns);
            assertEquals(3, missingCol.size());
            
        } catch (IOException ex) {
            Logger.getLogger(SimpleInputStreamReaderTest.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
