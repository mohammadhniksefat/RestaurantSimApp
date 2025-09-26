package org.example.models;

import java.util.ArrayList;
import java.util.HashMap;

public class User {

    private static HashMap<String, User> users = new HashMap<>();

    public static boolean phoneNumberCheck(String phoneNumber){
        return users.keySet().contains(phoneNumber);
    }

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private double balance;

    private ArrayList<Order> ordersList = new ArrayList<>();

    public static void setUser(User user){
        users.put(user.getPhoneNumber(), user);
    }

    public static User getUser(String phoneNumber){
        return users.get(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOrder(Order order){
        balance -= order.getTotalCost();
        ordersList.add(order);
    }

    public ArrayList<Order> getOrdersList(){
        return ordersList;
    }



}
