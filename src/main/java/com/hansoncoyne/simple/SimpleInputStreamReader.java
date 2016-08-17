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
public class SimpleInputStreamReader {

    private static int newLine = 10;
    private static int carriageReturn = 13;
    private static int pipe = 124;
    private static int comma = 44;
    private static int space = 32;

    public SimpleInputStreamReader() {
    }

    /**
     * Creates a List of Person from the given InputStream
     *
     * @param in
     * @return
     * @throws IOException
     */
    protected List<Person> readRecords(InputStream in) throws IOException {

        InputStreamReader isr = new InputStreamReader(in);
        List<Person> persons = null;

        try {
            persons = this.readStream(isr);
        } finally {
            isr.close();
        }
        return persons;
    }

    protected List<Person> readStream(InputStreamReader isr) throws IOException {
        int i;
        int itemIndex = 0;
        List<Person> persons = new ArrayList();

        //prime the pump
        i = isr.read();
        StringBuilder sb = new StringBuilder();
        Person person = new Person();

        // keep reading until EOF character
        while (i != -1) {

            // if the character is a delimiter (or a new line), process the StringBuilder accoringly
            if ((i == pipe || i == comma || i == space || i == newLine)) {

                processDelimiter(person, sb, itemIndex);
                //reset the StringBuilder
                sb = new StringBuilder();
                itemIndex++;

                //if its a new line, some add'l work add the record to our list
                if (i == newLine) {
                    //reset the item index, add the person to the list if they are valid
                    itemIndex = 0;
                    if (person.isValid()) {
                        persons.add(person);
                    }
                    person = null;
                    person = new Person();
                }

            } else {
                // if was not a delimiter, add the char to the StringBuilder, except windows carriage returns
                if (i != carriageReturn) {
                    sb.append((char) i);
                }
            }
            i = isr.read();
        }

        // we need to collect the last record.
        // this can happen if there is no newline before the EOF
        if (sb.length() > 0) {
            processDelimiter(person, sb, itemIndex);
            if (person.isValid()) {
                persons.add(person);
            }
        }

        return persons;
    }

    protected void processDelimiter(Person person, StringBuilder sb, int itemIndex) {
        if (itemIndex == 0) {
            //System.out.println("setLastName:" + sb);
            person.setLastName(sb.toString());

        } else if (itemIndex == 1) {
            //System.out.println("setFirstName:" + sb);
            person.setFirstName(sb.toString());

        } else if (itemIndex == 2) {
            //System.out.println("setGender:" + sb);
            person.setGender(sb.toString());

        } else if (itemIndex == 3) {
            //System.out.println("setColor:" + sb);
            person.setFavColor(sb.toString());

        } else if (itemIndex == 4) {
            //System.out.println("setDOB:" + sb);
            person.setDob(sb.toString());
        }
    }
}
