package com.hansoncoyne.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author nhanson
 */
public class SimpleRecordApp {
    
    String samplePipe = "simple.pipe";
    String sampleComma = "simpleData.csv";
    String sampleTxt = "simple.txt";
    
    public SimpleRecordApp() {
        InputStream pipeIn = this.getClass().getClassLoader().getResourceAsStream(samplePipe);
        InputStream commaIn = this.getClass().getClassLoader().getResourceAsStream(sampleComma);
        InputStream spaceIn = this.getClass().getClassLoader().getResourceAsStream(sampleTxt);
        SimpleFileReader sfr = new SimpleFileReader();
        try {
            List<Person> persons = sfr.readRecords(pipeIn);
            List<Person> personsComma = sfr.readRecords(commaIn);
            List<Person> personsSpace = sfr.readRecords(spaceIn);
            
        } catch (IOException ex) {
            
        }
    }

    /* main entry point */
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: using sample data");
            SimpleRecordApp simple = new SimpleRecordApp();
        } else {

        }
    }

}
