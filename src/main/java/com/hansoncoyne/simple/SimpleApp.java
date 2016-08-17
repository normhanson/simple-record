package com.hansoncoyne.simple;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhanson
 */
public class SimpleApp {
    
    private List<Person> persons = new ArrayList();
    private SimpleRecordService service = new SimpleRecordService();
    
    public SimpleApp() {
        service.loadExampleData(persons);
        this.printRecords(persons);
    }
    
    /**
     * Main entry point for Step 1
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SimpleApp simple = new SimpleApp();
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
