/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Fitness manager that sums and activities
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Implements ProgressTracker and Serializable
public class FitnessManager implements ProgressTracker, Serializable {
    // Serial ID
    private static final long serialVersionUID = 1L;
    // Each activity has own key
    private Map<Integer, Activity> activities;

    // Make activities map HashMap
    public FitnessManager() {
        activities = new HashMap<>();
    }

    // Getter and setter for activities map
    public Map<Integer, Activity> getActivities() {
        return activities;
    }

    public void setActivities(Map<Integer, Activity> activities) {
        this.activities = activities;
    }

    // Override addActivity adding new activity with its own ID
    @Override
    public void addActivity(Activity activity) {
        int id = activities.size() + 1;
        activities.put(id, activity);
    }

    // Calculate calories burned
    @Override
    public double calculateTotalCaloriesBurned() {
        // Get collection of activities from map and convert into stream
        return activities.values().stream()
                // filters stream to include only instances of workout
                .filter(activity -> activity instanceof Workout)
                // Map each workout to calories using calculateCalories
                .mapToDouble(Activity::calculateCaloriesBurned)
                // Add calorie counts of all workout together
                .sum();
    }

    // Calculate calories consumed
    public double calculateTotalCaloriesConsumed() {
        // Get collection of activities from map and convert into stream
        return activities.values().stream()
                // filters stream to include only instances of meal
                .filter(activity -> activity instanceof Meal)
                // Map each workout to calories using calculateCalories
                .mapToDouble(Activity::calculateCaloriesBurned)
                // Add calorie counts of all meals together
                .sum();
    }

    // Generate report of all activities and their calories
    @Override
    public String generateProgressReport() {
        // New StringBuilder report
        StringBuilder report = new StringBuilder("Progress Report:\n");
        // Iterate over each activity in the activities map
        for (Activity activity : activities.values()) {
            // append each activity to new report
            report.append(activity.toString()).append("\n");
        }
        // Add total calories burned to report
        report.append("Total Calories Burned: ").append(calculateTotalCaloriesBurned()).append("\n");
        // Add total calories consumed to report
        report.append("Total Calories Consumed: ").append(calculateTotalCaloriesConsumed());
        // return the report as progress report
        return report.toString();
    }
}
