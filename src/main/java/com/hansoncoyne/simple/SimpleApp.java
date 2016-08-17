package com.hansoncoyne.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhanson
 */
public class SimpleApp {

    private List<Person> persons = new ArrayList();
    private SimpleRecordService service = new SimpleRecordService();

    public SimpleApp() {
        try {
            service.loadExampleData(persons);
        } catch (IOException ex) {
            Logger.getLogger(SimpleApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.printRecords(persons);
    }
    
    public SimpleApp(String[] files) {
        for (String filename : files) {
            try {
                File file = new File(filename);
                service.loadDataFromFile(persons, file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SimpleApp.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SimpleApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        
    }

    /**
     * Main entry point for Step 1
     *
     * @param args
     */
    public static void main(String[] args) {
        
        // if we have arguments, we will assume they are a list of filenames to attempt to load
        // else, we will run with the example data provided
        if (args != null && args.length > 0) {
            SimpleApp simple = new SimpleApp(args);
        } else {
            SimpleApp simple = new SimpleApp();
        }
            
        
    }

    /**
     * simple method for printing to System.out
     *
     * @param persons
     */
    public void printRecords(List<Person> persons) {
        System.out.println();
        System.out.println("Output 1:");
        service.sortByGender(persons);
        System.out.print(service.getListAsString(persons));

        System.out.println();
        System.out.println("Output 2:");
        service.sortByBirthdate(persons);
        System.out.print(service.getListAsString(persons));

        System.out.println();
        System.out.println("Output 3:");
        service.sortByName(persons);
        System.out.print(service.getListAsString(persons));
    }
}
