package org.example.utils.callbacks;

import org.example.models.*;

import java.util.ArrayList;

public interface CustomerPanelCallback {
    void setOrder(Order order);

    void increaseBalance(double amount);

    ArrayList<Order> getOrdersList();

    String getFirstName();

    double getBalance();

    String getPhoneNumber();

}
