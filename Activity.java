/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Abstract class Activity for Workout and Meal
 */


import java.io.Serializable;
import java.time.LocalDate;

// Abstract class using serializable
public abstract class Activity implements Serializable {
    // Serialization ID and instance variables
    private static final long serialVersionUID = 1L;
    private final String name;
    private final LocalDate date;

    // Constructor
    public Activity(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    // Get name and get date
    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    // abstract method
    public abstract double calculateCalories();

    // String for activity
    @Override
    public String toString() {
        return "Activity: " + name + ", Date: " + date.toString();
    }
}

