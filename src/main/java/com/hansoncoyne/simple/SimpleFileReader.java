/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansoncoyne.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhanson
 */
public class SimpleFileReader {

    InputStream in;
    char delimiter;
    private int newLine = 10;
    private int carriageReturn = 13;
    private int pipe = 124;
    private int comma = 44;
    private int space = 32;

    public SimpleFileReader() {
    }

    protected List<Person> readRecords(InputStream in) throws IOException {

        InputStreamReader isr = new InputStreamReader(in);
        List<Person> persons = null;
        
        try {
            persons = this.readline(isr);
            System.out.println (persons.size());

        } finally {
            isr.close();
        }
        return persons;
    }

    protected List<Person> readline(InputStreamReader isr) throws IOException {
        int i;
        int itemIndex = 0;
        List<Person> persons = new ArrayList();
        StringBuilder sb = new StringBuilder();
        Person person = null;
        
        //prime the pump
        i = isr.read();
        
        // keep reading until EOF character
        while(i !=-1) {

            // if the character is a delimiter, process the sb accoringly
            if (i == pipe || i == comma || i == space || i == newLine) {
                //System.out.println("delimiter");
                //System.out.println("ItemIndex:" + itemIndex);
                if (itemIndex == 0) {
                    System.out.println("setLastName:" + sb);
                    person = new Person();
                    person.setLastName(sb.toString());
                    sb = new StringBuilder();
                    itemIndex++;

                } else if (itemIndex == 1) {
                    System.out.println("setFirstName:" + sb);
                    person.setFirstName(sb.toString());
                    sb = new StringBuilder();
                    itemIndex++;

                } else if (itemIndex == 2) {
                    System.out.println("setGender:" + sb);
                    person.setGender(sb.toString());
                    sb = new StringBuilder();
                    itemIndex++;

                } else if (itemIndex == 3) {
                    System.out.println("setColor:" + sb);
                    person.setFavColor(sb.toString());
                    sb = new StringBuilder();
                    itemIndex++;

                } else if (itemIndex == 4) {
                    System.out.println("setDOB:" + sb);
                    person.setDob(null);
                    sb = new StringBuilder();
                    
                    //reset the item index
                    itemIndex = 0;
                    
                    System.out.println(person);
                    
                    //record is complete, add it to the list
                    persons.add(person);
                    person = null;
                }

            } else {
                // if was not a delimiter, 
                // just keep reading, dont add bs characters to string builder
                if (i != carriageReturn ) {
                    sb.append((char)i);
                    //System.out.println(sb);
                }
            }
            i = isr.read();
        } 
        
        // we need to collect the last record.
        // this can happen if there is no newline before the EOF
        if (sb.length() > 0 && itemIndex == 4){
            System.out.println("setDOB:" + sb);
            person.setDob(null);
            System.out.println(person);
            persons.add(person);
        }
        
        return persons;
    }
    
    
}
