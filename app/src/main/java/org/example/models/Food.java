package org.example.models;

import java.util.ArrayList;

public class Food {

    private static ArrayList<Food> foods = new ArrayList<>();

    private static ArrayList<String> foodNames = new ArrayList<>();

    public static void setNewFood(Food food){
        foods.add(food);
        foodNames.add(food.getName());
    }

    public static int getFoodsNumber(){
        return foods.size();
    }

    public static Food getFood(int index) {
        return foods.get(index);
    }

    public static boolean foodExistCheck(String name){
        return foodNames.contains(name);
    }

    public String name;

    public double cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
