package com.hansoncoyne.simple;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;

/**
 *
 * @author nhanson
 */
public class SimpleRestApp {

    private final SimpleRecordService simple;
    List<Person> persons = new ArrayList();

    public SimpleRestApp() {

        simple = new SimpleRecordService();
    }

    public String putRecord(String record) {
        
        try {
            simple.addRecord(persons, record);
            
            StringBuilder sb = new StringBuilder();
            sb.append("Successfully added record ... ");
            sb.append(persons.size()).append(" ... records now exist.");
            return sb.toString();
        
        } catch (IOException ex) {
            Logger.getLogger(SimpleRestApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Person> getListByGender() {
        simple.sortByGender(persons);
        return persons;
    }

    public List<Person> getListByName() {
        simple.sortByName(persons);
        return persons;
    }

    public List<Person> getListByBirthdate() {
        simple.sortByBirthdate(persons);
        return persons;
    }

    public String loadExampleData() {
        try {
            simple.loadExampleData(persons);
        } catch (IOException ex) {
            Logger.getLogger(SimpleRestApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Loading Example Data... ");
        sb.append(persons.size()).append(" ... record loaded.");
        return sb.toString();
    }
    
    
    public String reset() {
        int size = persons.size();
        persons.clear();
        
        StringBuilder sb = new StringBuilder();
        sb.append("Removing records ... ");
        sb.append(size).append(" ... record removed.");
        return sb.toString();
    }

    /**
     * main entry point for rest server.
     * 
     * @param args 
     */
    public static void main(String[] args) {

        SimpleRestApp simpleRestService = new SimpleRestApp();

        port(5678);

        // a couple of admin functions
        get("/load", (request, response) -> simpleRestService.loadExampleData());
        get("/reset", (request, response) -> simpleRestService.reset());

        
        Gson gson = new Gson();
        get("/records/gender", (request, response) -> simpleRestService.getListByGender(), gson::toJson);
        get("/records/birthdate", (request, response) -> simpleRestService.getListByBirthdate(), gson::toJson);
        get("/records/name", (request, response) -> simpleRestService.getListByName(), gson::toJson);
        post("/records", (request, response) -> simpleRestService.putRecord(request.body()));

    }

}
