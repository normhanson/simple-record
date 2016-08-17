package com.hansoncoyne.simple;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author nhanson
 */
public class SimpleRecordApp {

    private static String samplePipe = "simple.pipe";
    private static String sampleComma = "simpleData.csv";
    private static String sampleTxt = "simple.txt";
    private static String printDelim = " | ";

    private InputStream pipeIn = this.getClass().getClassLoader().getResourceAsStream(samplePipe);
    private InputStream commaIn = this.getClass().getClassLoader().getResourceAsStream(sampleComma);
    private InputStream spaceIn = this.getClass().getClassLoader().getResourceAsStream(sampleTxt);

    public SimpleRecordApp() {

        SimpleFileReader sfr = new SimpleFileReader();
        List<Person> persons = new ArrayList();
        try {

            persons.addAll(sfr.readRecords(pipeIn));
            persons.addAll(sfr.readRecords(commaIn));
            persons.addAll(sfr.readRecords(spaceIn));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println();
        System.out.println("Output 1:");
        Collections.sort(persons, new ComparatorGenderThenLastName());
        printList(persons);

        System.out.println();
        System.out.println("Output 2:");
        Collections.sort(persons, new ComparatorBirthDate());
        printList(persons);

        System.out.println();
        System.out.println("Output 3:");
        Collections.sort(persons, Collections.reverseOrder(new ComparatorLastName()));
        printList(persons);

    }

    /* main entry point */
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Usage: using sample data");
            SimpleRecordApp simple = new SimpleRecordApp();
        } else {

        }
    }

    /**
     * print the List of Persons
     *
     * @param persons
     */
    protected static void printList(List<Person> persons) {
        for (Person p : persons) {
            StringBuilder sb = new StringBuilder();
            sb.append(p.getLastName()).append(printDelim);
            sb.append(p.getFirstName()).append(printDelim);
            sb.append(p.getGender()).append(printDelim);
            sb.append(p.getFavColor()).append(printDelim);
            sb.append(p.getFormattedDob());
            //sb.append(printDelim).append(p.getNanos());
            System.out.println(sb);
        }
    }

}
