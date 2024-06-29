/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Goal Tracker tracks progress towards goal
 */

// GoalTracker class operates on any type and extends number
public class GoalTracker<T extends Number> {
    // Variables
    private final T goal;
    private double progress;

    // Constructor that initializes goal field and sets progress to 0
    public GoalTracker(T goal) {
        this.goal = goal;
        this.progress = 0;
    }

    // UpdateProgress adds amount to progress
    public void updateProgress(double amount) {
        progress += amount;
    }

    // True or False if goal is met
    public boolean isGoalMet() {
        // Compare goal to progress
        return progress >= goal.doubleValue();
    }

    // Change string to goal and the progress
    @Override
    public String toString() {
        return "Goal: " + goal.toString() + ", Progress: " + progress;
    }
}
