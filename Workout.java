/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Workout subclass
 */

import java.time.LocalDate;

// Workout extends Activity
public class Workout extends Activity {
    // Variables
    private final double duration;
    private final String type;

    // Constructor
    public Workout(String name, LocalDate date, double duration, String type) {
        super(name, date);
        this.duration = duration;
        this.type = type;
    }

    // Getter to return duration
    public double getDuration() {
        return duration;
    }


    // Getter to return type
    public String getType() {
        return type;
    }

    // Overriding calculate calories
    @Override
    public double calculateCalories() {
        return duration * 7;
    }

    // Overriding toString that returns name, date, duration, and time
    @Override
    public String toString() {
        return super.toString() + ", \nDuration: " + duration + "min, \nCalories Burned: " + duration * 7;
    }
}

