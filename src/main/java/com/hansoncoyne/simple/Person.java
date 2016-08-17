package com.hansoncoyne.simple;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * 
 * @author nhanson
 */
public class Person {

    private String lastName;
    private String firstName;
    private Date dob;
    private String favColor;
    private char gender = NONE;

    //just a little something to gaurantee sorting tiebraker
    private long nanos;

    // gender domain
    private static char MALE = 'M';
    private static char FEMALE = 'F';
    private static char NONE = 'X';

    //DOB formatter
    private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    /**
     * simple helper
     *
     * @param s
     * @return
     */
    private static boolean isEmpty(String s) {
        //return (s == null || s.trim().length() == 0);
        return (s == null || s.length() == 0);
    }
    
    protected Person() {
        nanos = System.nanoTime();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.lastName);
        hash = 31 * hash + Objects.hashCode(this.firstName);
        hash = 31 * hash + Objects.hashCode(this.dob);
        hash = 31 * hash + Objects.hashCode(this.favColor);
        hash = 31 * hash + this.gender;
        hash = 31 * hash + (int) (this.nanos ^ (this.nanos >>> 32));
        return hash;
    }
    
    /** 
     * added some validation for real records 
     * 
     * @return 
     */
    public boolean isValid() {
        if (lastName != null ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.dob, other.dob)) {
            return false;
        }
        if (!Objects.equals(this.favColor, other.favColor)) {
            return false;
        }
        if (this.gender != other.gender) {
            return false;
        }
        if (this.nanos != other.nanos) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "lastName=" + lastName + ", firstName=" + firstName + ", dob=" + this.getFormattedDob() + ", favColor=" + favColor + ", gender=" + gender + '}';
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = (isEmpty(lastName) ? null : lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = (isEmpty(firstName) ? null : firstName);
    }

    public String getFormattedDob() {

        return (dob != null) ? df.format(dob) : null;
    }
    
    public Date getDob() {

        return dob;
    }

    /**
     * expects String in the MM/dd/yyyy format. just uses SimpleDateFormat as
     * the validation rule. oddly enough 25/25/1985 was valid and returned
     * 01/25/1987 in testing ... who knew. anything exception is eaten and a
     * null is set;
     *
     * @param dob
     */
    public void setDob(String dobString) {
        if (dobString != null) {
            try {
                dob = df.parse(dobString);
            } catch (ParseException e) {
                dob = null;
            }
        } else {
            dob = null;
        }
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = (isEmpty(favColor) ? null : favColor.toLowerCase());
    }

    public char getGender() {
        return gender;
    }

    /**
     * a little bit of logic here to normalize the gender
     *
     * @param gender
     */
    public void setGender(String gender) {
        //default
        this.gender = NONE;
        if (!isEmpty(gender)) {
            char test = gender.charAt(0);
            if (test == 'M' || test == 'm') {
                this.gender = MALE;
            } else if (test == 'F' || test == 'f') {
                this.gender = FEMALE;
            }
        }
    }
    
    protected long getNanos() {
        return nanos;
    }
    
}
