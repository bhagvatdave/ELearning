package com.project.e_learning.Model;

public class User {

    int ID;
    String Firstname;
    String Lastname;
    String Email;
    String Password;

    public User(int ID, String firstname, String lastname, String email, String password) {
        this.ID = ID;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
    }

    public User(String firstname, String lastname, String email, String password) {
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
