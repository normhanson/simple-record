package com.hansoncoyne.simple;

import java.util.Date;

/**
 *
 * @author nhanson
 */
public class Person {

    @Override
    public String toString() {
        return "Person{" + "lastName=" + lastName + ", firstName=" + firstName + ", dob=" + dob + ", favColor=" + favColor + ", gender=" + gender + '}';
    }
    
    private String lastName;
    private String firstName;
    private Date dob;
    private String favColor;
    private String gender;
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = favColor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
