package org.example;

import org.example.utils.InputOutputManager;
import org.example.utils.callbacks.*;
import org.example.models.*;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);
    private static String adminPassword = "1234";

    public static void main(String[] args){
        start();
    }

    private static void start(){
        InputOutputManager.programStartGreeting();
        while (true){
            Boolean isExit = InputOutputManager.showMainMenu(new MainMenuCallback() {

                @Override
                public void selectCustomer() {
                    InputOutputManager.customerForm(new CustomerFormCallback() {
                        public void signIn() {
                            String phoneNumber = InputOutputManager.userSignIn();

                            openCustomerPanel(User.getUser(phoneNumber));
                        }

                        public void signUp() {
                            String phoneNumber = InputOutputManager.userSignUp(new CustomerSignUpCallback() {
                                @Override
                                public void userSet(User user) {
                                    User.setUser(user);
                                }
                            });
                            openCustomerPanel(User.getUser(phoneNumber));
                        }

                        @Override
                        public void openCustomerPanel(User user) {
                            InputOutputManager.showCustomerPanel(new CustomerPanelCallback() {
                                public void increaseBalance(double amount) {
                                    user.setBalance(user.getBalance() + amount);
                                }

                                public void setOrder(Order order) {
                                    user.setOrder(order);
                                    Order.setOpenOrder(order);
                                }

                                public ArrayList<Order> getOrdersList() {
                                    return user.getOrdersList();
                                }

                                public String getFirstName() {
                                    return user.getFirstName();
                                }

                                ;

                                public String getLastName() {
                                    return user.getLastName();
                                }

                                public double getBalance() {
                                    return user.getBalance();
                                }

                                public String getPhoneNumber() {
                                    return user.getPhoneNumber();
                                }

                            });
                        }

                    });
                }

                @Override
                public void selectAdmin() {
                    InputOutputManager.showAdminPanel(new AdminPanelCallback() {
                        @Override
                        public boolean adminPasswordCheck(String password) {
                            if (password.equals(adminPassword)) {
                                return true;
                            } else {
                                return false;
                            }
                        }

                        @Override
                        public void addNewFood(Food food) {

                            Food.setNewFood(food);
                        }
                    });
                }
            });

            if(isExit == true){
                break;
            }
        }
    }
}
