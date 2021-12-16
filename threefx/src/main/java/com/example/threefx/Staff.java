package com.example.threefx;

public class Staff {
    private int id;
    private String username;
    private int salary;
    private boolean paid;
    private String email;

    public Staff() {
    }

    public Staff( String username, int salary, boolean paid, String email) {

        this.username = username;
        this.salary = salary;
        this.paid = paid;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
