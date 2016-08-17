package com.hansoncoyne.simple;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import static spark.Spark.*;


/**
 *
 * @author nhanson
 */
public class SimpleRestService {
    
    private final SimpleRecordService simple;
    List<Person> persons = new ArrayList();
            
    public SimpleRestService() {
        
       simple = new SimpleRecordService();
    }
    
    public String putRecord(String record) {
        simple.addRecord(persons, record);

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
        simple.loadExampleData(persons);
        StringBuilder sb = new StringBuilder();
        sb.append("Loading Example Data... ");
        sb.append(persons.size()).append(" ... record loaded.");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        
        SimpleRestService simpleRestService = new SimpleRestService();
        
        port(5678); 

        get("/load", (request, response) -> simpleRestService.loadExampleData());
        
        Gson gson = new Gson();
        get("/records/gender", (request, response) -> simpleRestService.getListByGender(), gson::toJson);
        get("/records/birthdate", (request, response) -> simpleRestService.getListByBirthdate(), gson::toJson);
        get("/records/name", (request, response) -> simpleRestService.getListByName(), gson::toJson);
        post("/records", (request, response) -> simpleRestService.putRecord(request.body()));
        
        

    }
    
}
