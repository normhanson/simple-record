package com.hansoncoyne.simple;

import java.util.Comparator;

/**
 *
 * @author nhanson
 */
public class ComparatorLastName implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {

        int rv = p1.getLastNameLower().compareTo(p2.getLastNameLower());

        if (rv == 0) {
            rv = (p1.getNanos() < p2.getNanos() ? -1 : 1);
        }

        return rv;
    }
}
