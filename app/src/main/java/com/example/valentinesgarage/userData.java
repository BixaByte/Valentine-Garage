package com.example.valentinesgarage;

public class userData {

    private String access, dob, department, email, first_Name, surname, password;
    private int userID;
    public userData(){
        //Default constructor
    }
//

    public userData( String access, String dob, String department, String email, String first_Name, String surname, String password, int userID) {
        this.access = access;
        this.dob = dob;
        this.department = department;
        this.email = email;
        this.first_Name = first_Name;
        this.surname = surname;
        this.password = password;
        this.userID = userID;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
