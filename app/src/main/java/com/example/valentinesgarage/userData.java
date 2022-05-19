package com.example.valentinesgarage;

public class userData {

    private String Access, DOB, Department, Email, First_Name, Surname, Password;
    private int userID;
    public userData(){
        //Default constructor
    }

    public userData(int id, String access, String DOB, String department, String email, String first_Name, String surname, String password) {
        Access = access;
        this.DOB = DOB;
        Department = department;
        Email = email;
        First_Name = first_Name;
        Surname = surname;
        Password = password;
        userID = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccess() {
        return Access;
    }

    public void setAccess(String access) {
        Access = access;
    }


    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
