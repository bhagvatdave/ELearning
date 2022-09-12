package com.project.e_learning.Model;

public class History {

    int id;
    String Username;
    String Subject;
    int Attempted_Questions;
    int Correct;
    int Incorrect;
    int Total;
    String Date;

    public History(int id, String username, String subject, int attempted_Questions, int correct, int incorrect, int total, String date) {
        this.id = id;
        Username = username;
        Subject = subject;
        Attempted_Questions = attempted_Questions;
        Correct = correct;
        Incorrect = incorrect;
        Total = total;
        Date = date;
    }

    public History(String username, String subject, int attempted_Questions, int correct, int incorrect, int total, String date) {
        Username = username;
        Subject = subject;
        Attempted_Questions = attempted_Questions;
        Correct = correct;
        Incorrect = incorrect;
        Total = total;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public int getAttempted_Questions() {
        return Attempted_Questions;
    }

    public void setAttempted_Questions(int attempted_Questions) {
        Attempted_Questions = attempted_Questions;
    }

    public int getCorrect() {
        return Correct;
    }

    public void setCorrect(int correct) {
        Correct = correct;
    }

    public int getIncorrect() {
        return Incorrect;
    }

    public void setIncorrect(int incorrect) {
        Incorrect = incorrect;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
