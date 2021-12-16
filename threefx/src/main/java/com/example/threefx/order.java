package com.example.threefx;

import java.util.Date;

public class order {
    private int customer_id;
    private String name;
    private int quantity;
    private boolean paid;
    private int amount;
    private String email;
    private Date created_at;
    private int phone;

    public order(String name, String email, int quantity, boolean paid, int amount, int phone, Date created_at) {
//        this.customer_id=customer_id;
        this.name=name;
        this.quantity = quantity;
        this.paid = paid;
        this.amount = amount;
        this.email = email;
        this.created_at=created_at;
        this.phone=phone;
    }

//    public order() {
//    }


    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
