/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Interface Progress Tracker
 */

// Interface
public interface ProgressTracker {
    // Specify classes must use addActivity method
    void addActivity(Activity activity);
    // Specify classes must use calculateCaloriesBurned method
    double calculateTotalCaloriesBurned();
    // Specify classes must use generateProgressReport method
    String generateProgressReport();
}
