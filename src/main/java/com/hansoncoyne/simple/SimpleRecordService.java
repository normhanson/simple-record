package com.hansoncoyne.simple;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author nhanson
 */
public class SimpleRecordService {

    private static String samplePipe = "simple.pipe";
    private static String sampleComma = "simpleData.csv";
    private static String sampleTxt = "simple.txt";
    private static String printDelim = " | ";

    private InputStream pipeIn = this.getClass().getClassLoader().getResourceAsStream(samplePipe);
    private InputStream commaIn = this.getClass().getClassLoader().getResourceAsStream(sampleComma);
    private InputStream spaceIn = this.getClass().getClassLoader().getResourceAsStream(sampleTxt);

    private SimpleInputStreamReader sisr = new SimpleInputStreamReader();

    private static Comparator genderThenLastName = new ComparatorGenderThenLastName();
    private static Comparator lastNameReverse = Collections.reverseOrder(new ComparatorLastName());
    private static Comparator birthdate = new ComparatorBirthDate();
    
    
    /**
     * Load example data from resource files
     *
     * @param persons
     */
    public void loadExampleData(List<Person> persons) {

        try {

            persons.addAll(sisr.readRecords(pipeIn));
            persons.addAll(sisr.readRecords(commaIn));
            persons.addAll(sisr.readRecords(spaceIn));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * takes in a record as String and process as a stream
     *
     * @param persons
     * @param record
     */
    public void addRecord(List<Person> persons, String record) {

        InputStream stream = new ByteArrayInputStream(record.getBytes(StandardCharsets.UTF_8));
        try {
            persons.addAll(sisr.readRecords(stream));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    

    public void sortByGender(List<Person> persons) {
        Collections.sort(persons, genderThenLastName);
    }

    public void sortByName(List<Person> persons) {
        Collections.sort(persons, lastNameReverse );
    }

    public void sortByBirthdate(List<Person> persons) {
        Collections.sort(persons, birthdate);
    }

    /**
     *
     * @param persons
     * @return
     */
    public String getListAsString(List<Person> persons) {

        StringBuilder sb = new StringBuilder();
        for (Person p : persons) {
            sb.append(p.getLastName()).append(printDelim);
            sb.append(p.getFirstName()).append(printDelim);
            sb.append(p.getGender()).append(printDelim);
            sb.append(p.getFavColor()).append(printDelim);
            sb.append(p.getFormattedDob());
            sb.append("\n");
        }

        return sb.toString();

    }

}
