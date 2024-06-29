/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Meal activity
 */

import java.time.LocalDate;

// Subclass of Activity
public class Meal extends Activity {
    // Fields
    private final double calories;
    private final double fat;
    private final double sodium;
    private final double carbs;
    private final double sugar;
    private final double protein;

    // Constructor
    public Meal(String name, LocalDate date, double calories, double fat, double sodium, double carbs, double sugar, double protein) {
        super(name, date);
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbs = carbs;
        this.sugar = sugar;
        this.protein = protein;
    }

    // Override activity calculate calories
    @Override
    public double calculateCalories() {
        return calories;
    }

    // Show name, date, and calories
    @Override
    public String toString() {
        return super.toString() + ", \nCalories: " + calories;
    }
}
