package org.example.models;


import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDateTime;


public class Order implements Comparable<Order>{
    public Order(String phoneNumber){
        setUserPhoneNumber(phoneNumber);
        totalCost = 0;
    }

    private static ArrayList<Order> openOrders = new ArrayList<>();

    private HashMap<Food, Integer> cart = new HashMap<>();


    public static ArrayList<Order> getOpenOrders() {
        return openOrders;
    }

    public static Integer getNumberOfOrders() { return openOrders.size();}

    public static void setOpenOrder(Order order) {
        openOrders.add(order);
    }

    private double totalCost;

    private String userPhoneNumber;

    private LocalDateTime timeStamp;

    public void setTimeStamp(LocalDateTime timeStamp){
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }

    public void setCartItem(Food food) {
        if (cart.keySet().contains(food)) {
            cart.put(food ,cart.get(food) + 1);

        }else{
            cart.put(food, 1);
        }
    }

    public Food getCartItem(int index){
        ArrayList<Food> foods = new ArrayList<>(cart.keySet());
        return foods.get(index);
    }

    public int getCartSize(){
        return cart.size();
    }

    public Integer getFoodsNumber(Food food){
        return cart.get(food);
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int compareTo(Order order){
        if(timeStamp.isAfter(order.getTimeStamp())){
            return 1;
        }else if(timeStamp.isBefore(order.getTimeStamp())){
            return -1;
        }else{
            return 0;
        }
    }

}
