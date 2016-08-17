package com.hansoncoyne.simple;

import java.util.Comparator;

/**
 *
 * @author nhanson
 */
public class ComparatorGenderThenLastName implements Comparator<Person> {

        @Override
        public int compare(Person p1, Person p2) {
            
            int rv = p1.getGender() - p2.getGender();
            
            if (rv == 0 ) {
                rv = p1.getLastName().compareToIgnoreCase(p2.getLastName());
            }
            
            if (rv == 0 ) {
                rv = (p1.getNanos() < p2.getNanos() ? -1 : 1);
            }
            
            return rv;
        }
}
