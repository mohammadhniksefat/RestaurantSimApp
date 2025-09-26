package org.example.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.example.utils.callbacks.*;
import org.example.models.*;



class InvalidInputException extends Exception{}


public class InputOutputManager {
    static Scanner input = new Scanner(System.in);
    public static void println(String text){
        System.out.println(text);
    }

    public static void print(String text){
        System.out.print(text);
    }

    public static void separator(){
        println("--------------------------");
    }

    public static void line(){
        System.out.println();
    }

    public static void programStartGreeting(){
        println("welcome to the resturant program!");
    }

    public static void printOrderDetail(Order order, boolean user){
        if(user){
            println("order from " + User.getUser(order.getUserPhoneNumber()).getFirstName() + " " +
                    User.getUser(order.getUserPhoneNumber()).getLastName() +
                    " phone: " + User.getUser(order.getUserPhoneNumber()).getPhoneNumber());
        }

        LocalDateTime timeStamp = order.getTimeStamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        String time = timeStamp.format(formatter);

        println("order date:\t" + time);
        println("order foods:");
        for(int i = 0; i < order.getCartSize(); i++){
            System.out.println("\t" + (i + 1) + ".  " +
                    order.getCartItem(i).getName() + "\t" +
                    order.getCartItem(i).getCost() + "  " +
                    order.getFoodsNumber(order.getCartItem(i)) +  "x");
        }
        line();
        println("total cost:\t" + order.getTotalCost());
        line();
    }


    public static Boolean showMainMenu(MainMenuCallback callback){
        separator();
        println("1. continue as Customer");
        println("2. continue as Admin");
        println("3. exit program");
        separator();
        line();

        print("Enter your choice:\t");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    callback.selectCustomer();
                    return false;
                case 2:
                    callback.selectAdmin();
                    return false;
                case 3:
                    return true;
                default:
                    println("invalid value!");
                    return false;
            }
    }

    public static void customerForm(CustomerFormCallback callback){
        while(true) {
             try {
                 separator();
                 println("1. signUp\n2. signIn\n3. exit");
                 separator();
                 line();

                 print("Enter your choice:\t");
                 int choice = input.nextInt();

                 switch (choice) {
                     case 1:
                         callback.signUp();
                         break;
                     case 2:
                         callback.signIn();
                         break;
                     case 3:
                         return;
                     default:
                         throw new InvalidInputException();
                 }
             }catch (InputMismatchException ex){
                 println("invalid value!");
                 continue;
             }catch (InvalidInputException ex){
                 println("invalid value!");
                 continue;
             }
        }
    }

    public static boolean isValidNumber(String number){
        Pattern pattern = Pattern.compile("^09[0-9]{9}$");
        Matcher matcher = pattern.matcher(number);

        return matcher.find();
    }

    public static String userSignUp(CustomerSignUpCallback callback){
        while (true){
            print("Enter your first name:\t");
            String firstName = input.next();
            line();

            print("Enter your last name:\t");
            String lastName = input.next();
            line();

            print("Enter your phone number:\t");
            String phoneNumber = input.next();
            line();

            if(!isValidNumber(phoneNumber)){
                println("Enter a valid phone number!");
                continue;
            }else if(User.phoneNumberCheck(phoneNumber)){
                println("phone number exists!");
                continue;
            }

            User user = new User();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setBalance(0);

            callback.userSet(user);


            return phoneNumber;     //Open Customer Panel


        }
    }

    public static String userSignIn(){
        while (true){
            print("Enter your number:\t");
            String phoneNumber = input.next();
            line();

            if(!User.phoneNumberCheck(phoneNumber)){
                println("User not found!");
                continue;
            }

            return phoneNumber;
        }
    }


    public static void showCustomerPanel(CustomerPanelCallback callback){
         while (true) {
             try {
                 separator();
                 println("your welcome " + callback.getFirstName() + "!");
                 line();
                 println("Balance:\t" + callback.getBalance() + "$");
                 println("1. increase Balance");
                 println("2. set new order");
                 println("3. view all orders");
                 println("4. exit");
                 separator();
                 line();

                 print("Enter your choice:\t");

                 int choice = input.nextInt();

                 switch (choice){
                     case 1:
                         while(true) {
                             try {
                                 print("Enter amount of Balance increase:\t");
                                 double amount = input.nextDouble();

                                 if (amount < 0){
                                     throw new InvalidInputException();
                                 }

                                 callback.increaseBalance(amount);
                                 println("Done...");
                                 break;

                             }catch (InputMismatchException ex){
                                 println("invalid value!");
                                 continue;
                             }catch(InvalidInputException ex){
                                 println("invalid value!");
                                 continue;
                             }
                         }
                         break;

                     case 2:
                         for (Order order = new Order(callback.getPhoneNumber()); true; ){
                             try{

                                 separator();
                                 println("Enter \"0\" for set order");
                                 line();
                                 for(int j = 1; j <= Food.getFoodsNumber(); j++){
                                     println(j + ". " + Food.getFood(j - 1).getName() + "\t" + Food.getFood(j - 1).getCost() + "$");
                                 }
                                 separator();

                                 print("Enter your choice:\t");
                                 choice = input.nextInt();
                                 line();

                                 if (choice == 0){
                                    if (order.getCartSize() == 0){
                                        println("select at least one food!");
                                        continue;
                                    }else{
                                        LocalDateTime timeStamp = LocalDateTime.now();
                                        order.setTimeStamp(timeStamp);

                                        if (callback.getBalance() < order.getTotalCost()){
                                            println("insufficient Balance!");
                                            break;
                                        }

                                        callback.setOrder(order);
                                        println("Done...");
                                        break;
                                    }
                                 }
                                 if (!(choice >= 1 && choice <= Food.getFoodsNumber())){
                                     throw new Exception();
                                 }

                                 order.setCartItem(Food.getFood(choice - 1));
                                 order.setTotalCost(order.getTotalCost() + Food.getFood(choice - 1).getCost());


                             }catch(Exception ex){
                                println("invalid value!");
                                continue;
                             }
                         }

                     case 3:
                         ArrayList<Order> ordersList = callback.getOrdersList();
                         Collections.sort(ordersList);

                         separator();
                         for(int i = 0; i < ordersList.size(); i++){
                            printOrderDetail(ordersList.get(i), false);
                         }
                         separator();
                         line();

                         break;

                     case 4:
                         return;

                     default:
                         throw new Exception();
                 }

             }catch(Exception ex){
                 println("invalid value!");
                 continue;
             }
         }

    }

    public static void showAdminPanel(AdminPanelCallback callback){
        while (true) {
            print("Enter Admin Password:\t");
            String password = input.next();

            if (!callback.adminPasswordCheck(password)){
                line();
                println("incorrect password!");
                continue;
            }
            break;
        }
        while (true){
            try{
                line();
                separator();
                println("1. show open orders");
                println("2. add new food");
                println("3. change foods price");
                println("4. exit");
                separator();
                line();

                print("Enter your choice:\t");
                int choice = input.nextInt();
                line();

                switch(choice){
                    case 1:
                        if (Order.getNumberOfOrders() == 0){
                            separator();
                            println("there is no order yet!");
                            separator();
                            break;
                        }

                        ArrayList<Order> openOrders = Order.getOpenOrders();

                        separator();
                        for(Order order: openOrders){
                            printOrderDetail(order, true);
                        }
                        separator();
                        line();

                        break;

                    case 2:
                        while (true){
                            try{
                                print("Enter the food name:\t");
                                String foodName = input.next();
                                line();

                                if(Food.foodExistCheck(foodName)){
                                    throw new InvalidInputException();
                                }

                                print("Enter its cost:\t");
                                double cost = input.nextDouble();
                                line();

                                Food food = new Food();
                                food.setName(foodName);
                                food.setCost(cost);

                                callback.addNewFood(food);

                                println("1. add another");
                                println("2. exit");

                                print("Enter your choice:\t");
                                choice = input.nextInt();
                                line();

                                if(choice == 1){
                                    continue;
                                }else{
                                    break;
                                }

                            }catch(InputMismatchException ex){

                                println("invalid value!");
                                continue;

                            }catch(InvalidInputException ex){

                                println("this name exists!");
                                continue;

                            }
                        }
                        break;

                    case 3:
                        while(true) {
                            try {
                                if(Food.getFoodsNumber() == 0){
                                    separator();
                                    println("no food created!");
                                    separator();
                                    break;
                                }

                                for (int i = 1; i <= Food.getFoodsNumber(); i++) {
                                    println(i + ". " + Food.getFood(i - 1).getName() + "\t" + Food.getFood(i - 1).getCost());
                                }

                                print("Enter your choice:\t");
                                choice = input.nextInt();
                                line();

                                if(!(choice >= 1 && choice <= Food.getFoodsNumber())){
                                    throw new Exception();
                                }

                                println("Cost: " + Food.getFood(choice - 1).getCost());
                                print("Enter new Cost:\t");
                                double Cost = input.nextDouble();
                                line();

                                if (Cost < 0){
                                    throw new Exception();
                                }

                                Food.getFood(choice - 1).setCost(Cost);
                                println("Done...");
                                break;

                            } catch (Exception ex) {
                                println("invalid value!");
                                continue;
                            }
                        }
                            break;

                    case 4:
                        return;

                    default:
                        throw new Exception();

                }

            }catch(Exception ex){
                println("invalid value!");
                continue;
            }
        }
    }
}
