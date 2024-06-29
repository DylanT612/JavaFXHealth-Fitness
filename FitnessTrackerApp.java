/*
I certify, that this computer program submitted by me is all of my own work.
Signed: Dylan Theis 6/22/2024

Author: Dylan Theis
Date: Summer 2024
Class: CSC322
Project: Health and Fitness Tracker
Description: Main JavaFX program
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// JavaFX Main extends Application
public class Main extends Application {
    // Initialize fitnessManager
    private final FitnessManager fitnessManager = new FitnessManager();

    // Override start in application class
    @Override
    public void start(Stage primaryStage) {
        // Window title with initial three boxes
        primaryStage.setTitle("Fitness and Health Tracker");
        Button addWorkoutButton = new Button("Add Workout");
        Button addMealButton = new Button("Add Meal");
        Button showReportButton = new Button("Show Report");

        // Buttons when clicked go to new screen
        addWorkoutButton.setOnAction(e -> showAddWorkoutScreen(primaryStage));
        addMealButton.setOnAction(e -> showAddMealScreen(primaryStage));
        showReportButton.setOnAction(e -> showReportScreen(primaryStage));

        // Home screen layout
        // Vertical box spacing 10 pixels
        VBox homeLayout = new VBox(10);
        // Padding around vbox is 20 pixels
        homeLayout.setPadding(new Insets(20));
        // Adding the buttons to the VBox
        homeLayout.getChildren().addAll(addWorkoutButton, addMealButton, showReportButton);

        // Size of scene, setScene, and display
        Scene homeScene = new Scene(homeLayout, 300, 200);
        primaryStage.setScene(homeScene);
        primaryStage.show();

        // Load in saved activities
        loadActivitiesFromFile();
    }

    // Add a workout screen
    private void showAddWorkoutScreen(Stage primaryStage) {
        // Create grid
        GridPane grid = createFormGrid();

        // Form fields and labels
        TextField nameField = new TextField();
        TextField dateField = new TextField();
        TextField durationField = new TextField();
        TextField typeField = new TextField();
        Label errorLabel = new Label();

        // Add the labels and fields to grid locations
        grid.add(new Label("Activity Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Date (yyyy-mm-dd):"), 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(new Label("Duration (minutes):"), 0, 2);
        grid.add(durationField, 1, 2);
        grid.add(new Label("Type:"), 0, 3);
        grid.add(typeField, 1, 3);
        grid.add(errorLabel, 0, 4, 2, 1);

        // Save and cancel buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        // Cancel button returns to home screen
        cancelButton.setOnAction(e -> primaryStage.setScene(createHomeScene(primaryStage)));

        // Save button submits information as workout
        saveButton.setOnAction(e -> {
            try {
                // Retrieve the values
                String name = nameField.getText();
                LocalDate date = LocalDate.parse(dateField.getText());
                double duration = Double.parseDouble(durationField.getText());
                String type = typeField.getText();

                // Add the workout to fitness manager
                Activity workout = new Workout(name, date, duration, type);
                fitnessManager.addActivity(workout);
                // Save to file
                saveActivitiesToFile();

                // Return to home screen
                primaryStage.setScene(createHomeScene(primaryStage));
                // Catch error
            } catch (Exception ex) {
                errorLabel.setText("Invalid input. Please check your entries.");
            }
        });

        // Layout for workout screen
        // New vertical box spacing 10 pixels
        VBox layout = new VBox(10);
        // Layout padding is 20 pixels
        layout.setPadding(new Insets(20));
        // Adds the grid, save, and cancel buttons to layout
        layout.getChildren().addAll(grid, saveButton, cancelButton);

        // Scene for workout screen
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
    }

    // Add meal screen
    private void showAddMealScreen(Stage primaryStage) {
        // Create grid
        GridPane grid = createFormGrid();

        // Fields and labels
        TextField nameField = new TextField();
        TextField dateField = new TextField();
        TextField caloriesField = new TextField();
        TextField fatField = new TextField();
        TextField sodiumField = new TextField();
        TextField carbsField = new TextField();
        TextField sugarField = new TextField();
        TextField proteinField = new TextField();
        Label errorLabel = new Label();

        // Add the fields and labels to grid locations
        grid.add(new Label("Meal Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Date (yyyy-mm-dd):"), 0, 1);
        grid.add(dateField, 1, 1);
        grid.add(new Label("Calories:"), 0, 2);
        grid.add(caloriesField, 1, 2);
        grid.add(new Label("Fat (grams):"), 0, 3);
        grid.add(fatField, 1, 3);
        grid.add(new Label("Sodium (mg):"), 0, 4);
        grid.add(sodiumField, 1, 4);
        grid.add(new Label("Carbs (grams):"), 0, 5);
        grid.add(carbsField, 1, 5);
        grid.add(new Label("Sugar (grams):"), 0, 6);
        grid.add(sugarField, 1, 6);
        grid.add(new Label("Protein (grams):"), 0, 7);
        grid.add(proteinField, 1, 7);
        grid.add(errorLabel, 0, 8, 2, 1);

        // Save and cancel buttons
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        // Cancel returns to home screen
        cancelButton.setOnAction(e -> primaryStage.setScene(createHomeScene(primaryStage)));

        // Save button submits info as a meal
        saveButton.setOnAction(e -> {
            try {
                // Retrieve the values
                String name = nameField.getText();
                LocalDate date = LocalDate.parse(dateField.getText());
                double calories = Double.parseDouble(caloriesField.getText());
                double fat = Double.parseDouble(fatField.getText());
                double sodium = Double.parseDouble(sodiumField.getText());
                double carbs = Double.parseDouble(carbsField.getText());
                double sugar = Double.parseDouble(sugarField.getText());
                double protein = Double.parseDouble(proteinField.getText());

                // Add the meal to fitnessManager
                Activity meal = new Meal(name, date, calories, fat, sodium, carbs, sugar, protein);
                fitnessManager.addActivity(meal);
                // Save to file
                saveActivitiesToFile();
                // Return to home screen
                primaryStage.setScene(createHomeScene(primaryStage));
                // Catch any errors
            } catch (Exception ex) {
                errorLabel.setText("Invalid input. Please check your entries.");
            }
        });

        // Layout for workout screen
        // New vertical box spacing 10 pixels
        VBox layout = new VBox(10);
        // Set padding 20 pixels
        layout.setPadding(new Insets(20));
        // Add grid, save, and cancel buttons to VBox
        layout.getChildren().addAll(grid, saveButton, cancelButton);

        // Scene for meal screen
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
    }

    // Create report
    private void showReportScreen(Stage primaryStage) {
        // Create new grid
        GridPane gridPane = new GridPane();
        // Set padding to 20 pixels
        gridPane.setPadding(new Insets(20));
        // Set gap between grid to 20 pixels
        gridPane.setHgap(20);
        // Column constraints for the gridPane
        // Column 1 set it to 50% of grid
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        // Column 2 set it to 50% of grid
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        // Create VBox for workouts with spacing 10 in the top left
        VBox workoutsBox = new VBox(10);
        workoutsBox.setAlignment(Pos.TOP_LEFT);
        // Create VBox for meals with spacing 10 in the top right
        VBox mealsBox = new VBox(10);
        mealsBox.setAlignment(Pos.TOP_RIGHT);

        // Create a comboBox for time selection
        ComboBox<String> filterComboBox = new ComboBox<>();
        filterComboBox.getItems().addAll("Today", "This Week", "This Month", "This Year", "All Time");
        // Default All Time
        filterComboBox.setValue("All Time");

        // Creating labels with padding
        Label totalBurnedLabel = new Label("Total Calories Burned: 0.0");
        totalBurnedLabel.setPadding(new Insets(10));
        Label totalConsumedLabel = new Label("Total Calories Consumed: 0.0");
        totalConsumedLabel.setPadding(new Insets(10));
        // Label for message allow it to wrap, center it, and set padding
        Label messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setPadding(new Insets(10));

        // When comboBox is changed, gets updated values
        filterComboBox.setOnAction(e -> updateReport(workoutsBox, mealsBox, filterComboBox.getValue(), totalBurnedLabel, totalConsumedLabel, messageLabel));

        // Set combobox in HBox and center HBox
        HBox topControls = new HBox(10, new Label("Show stats for:"), filterComboBox);
        topControls.setAlignment(Pos.CENTER);

        // VBox for controls and grid, spacing 10 and padding 20
        VBox layout = new VBox(10, topControls, gridPane);
        layout.setPadding(new Insets(20));

        // Scene size
        Scene scene = new Scene(layout, 600, 500);
        primaryStage.setScene(scene);

        // Add items to grid
        gridPane.add(workoutsBox, 0, 0);
        gridPane.add(mealsBox, 1, 0);
        gridPane.add(totalBurnedLabel, 0, 3);
        gridPane.add(totalConsumedLabel, 1, 3);
        gridPane.add(messageLabel, 0, 5, 2, 1);

        // Create disclaimer label, allow to wrap and place in grid
        Label disclaimerLabel = new Label("**Disclaimer: The human body is very complex, everybody has different needs, and a large majority of calories are burned just by your body performing normal functions, like sitting/standing/breathing/talking/having a functioning brain, so as a result take this calculator with a grain of sand.");
        disclaimerLabel.setWrapText(true);
        gridPane.add(disclaimerLabel, 0, 7, 2, 1);

        // Create back button that returns to home screen, and set in grid
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(createHomeScene(primaryStage)));
        gridPane.add(backButton, 0, 8, 1, 1);


        // Run updateReport method to get relevant data
        updateReport(workoutsBox, mealsBox, filterComboBox.getValue(), totalBurnedLabel, totalConsumedLabel, messageLabel);
    }


    // Calories burned from the workout box
    private double calculateTotalCaloriesBurned(VBox workoutsBox) {
        // Initialize variable
        double totalCaloriesBurned = 0.0;
        // Iterate each node in workoutBox
        for (Node node : workoutsBox.getChildren()) {
            // If node is instance of label
            if (node instanceof Label) {
                // Splits text and places text into array
                String[] labelParts = ((Label) node).getText().split(", ");
                // Iterate over each part of the array
                for (String part : labelParts) {
                    // If the current part starts with calories burned
                    if (part.startsWith("Calories Burned: ")) {
                        // If true extract substring following and add it to caloriesString
                        String caloriesString = part.substring("Calories Burned: ".length());
                        // Convert string into double and add it to calories burned
                        totalCaloriesBurned += Double.parseDouble(caloriesString);
                    }
                }
            }
        }
        // Return our totalCaloriesBurned
        return totalCaloriesBurned;
    }

    // Calories burned from the meals box
    private double calculateTotalCaloriesConsumed(VBox mealsBox) {
        // Initialize variable
        double totalCaloriesConsumed = 0.0;
        // Iterate each node in mealsBox
        for (Node node : mealsBox.getChildren()) {
            // If node is instance of label
            if (node instanceof Label) {
                // Splits text and places text into array
                String[] labelParts = ((Label) node).getText().split(", ");
                // Iterate over each part of the array
                for (String part : labelParts) {
                    // If the current part starts with calories
                    if (part.startsWith("Calories: ")) {
                        // If true extract substring following and add it to caloriesString
                        String caloriesString = part.substring("Calories: ".length());
                        // Convert string into double and add it to calories consumed
                        totalCaloriesConsumed += Double.parseDouble(caloriesString);
                    }
                }
            }
        }
        // Return our totalCaloriesConsumed
        return totalCaloriesConsumed;
    }

    // updateReport method
    private void updateReport(VBox workoutsBox, VBox mealsBox, String filter, Label totalBurnedLabel, Label totalConsumedLabel, Label messageLabel) {
        // Clear the boxes so new information can populate
        workoutsBox.getChildren().clear();
        mealsBox.getChildren().clear();

        // Filter selection
        LocalDate now = LocalDate.now();
        LocalDate startDate;

        switch (filter) {
            case "Today":
                startDate = now;
                break;
            case "This Week":
                startDate = now.minus(1, ChronoUnit.WEEKS);
                break;
            case "This Month":
                startDate = now.minus(1, ChronoUnit.MONTHS);
                break;
            case "This Year":
                startDate = now.minus(1, ChronoUnit.YEARS);
                break;
            case "All Time":
            default:
                startDate = LocalDate.MIN;
                break;
        }

        // Initialize burned and consumed
        double totalCaloriesBurned = 0.0;
        double totalCaloriesConsumed = 0.0;
        // Iterate through all activities in fitnessManager
        for (Activity activity : fitnessManager.getActivities().values()) {
            // Checks activities date is after or equal to date
            if (activity.getDate().isAfter(startDate) || activity.getDate().isEqual(startDate)) {
                // if true and workout
                if (activity instanceof Workout) {
                    // return workouts toString in workouts box
                    workoutsBox.getChildren().add(new Label(activity.toString()));
                    // Calories burned uses activity calc calories method
                    totalCaloriesBurned += activity.calculateCalories();
                    // If true and meal
                } else if (activity instanceof Meal) {
                    // return meal toString in mealsBox
                    mealsBox.getChildren().add(new Label(activity.toString()));
                    // Calories consumed uses activity calculate calories
                    totalCaloriesConsumed += activity.calculateCalories();
                }
            }
        }
        // Update labels
        totalBurnedLabel.setText("Total Calories Burned: " + totalCaloriesBurned);
        totalConsumedLabel.setText("Total Calories Consumed: " + totalCaloriesConsumed);

        // If burned > consumed display good job else keep going
        if (totalCaloriesBurned > totalCaloriesConsumed) {
            messageLabel.setText("Congratulations! You burnt off a total of " + (totalCaloriesBurned - totalCaloriesConsumed) + " calories! Way to go!");
        } else {
            messageLabel.setText("You're so close! You need to burn off " + (totalCaloriesConsumed - totalCaloriesBurned) + " more calories! I believe in you!");
        }

    }

    // Home scene
    private Scene createHomeScene(Stage primaryStage) {
        // Three buttons
        Button addWorkoutButton = new Button("Add Workout");
        Button addMealButton = new Button("Add Meal");
        Button showReportButton = new Button("Show Report");

        // Buttons go to new screen on select
        addWorkoutButton.setOnAction(e -> showAddWorkoutScreen(primaryStage));
        addMealButton.setOnAction(e -> showAddMealScreen(primaryStage));
        showReportButton.setOnAction(e -> showReportScreen(primaryStage));

        // Adding buttons to layout
        VBox homeLayout = new VBox(10);
        homeLayout.setPadding(new Insets(20));
        homeLayout.getChildren().addAll(addWorkoutButton, addMealButton, showReportButton);

        // Return scene size
        return new Scene(homeLayout, 300, 200);
    }

    // Create grid
    private GridPane createFormGrid() {
        // new grid
        GridPane grid = new GridPane();
        // Set gaps between cells to 10, padding 10 also
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));
        // Set columns to take up 50% of the grid
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        // Add to grid and return grid
        grid.getColumnConstraints().addAll(col1, col2);
        return grid;
    }

    // Save to file
    private void saveActivitiesToFile() {
        // Write to file name
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("activityLog.dat"))) {
            // Write the details of the activity
            oos.writeObject(fitnessManager.getActivities());
            // Catch errors for troubleshooting
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load activities from saved file
    private void loadActivitiesFromFile() {
        // Initialize file
        File file = new File("activityLog.dat");
        // If file DNE or is empty return nothing
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            // Read serialized object
            Object obj = ois.readObject();

            // Check if object is instance of Map<Integer, Activity> and assign to activities
            if (obj instanceof Map<Integer, Activity> activities) {

                // Clear activities in fitnessManager
                fitnessManager.getActivities().clear();
                // Iterate over entries in activities map and add to fitnessManager
                activities.forEach((id, activity) -> fitnessManager.addActivity(activity));
            } else {
                System.out.println("Unexpected data format in file.");
            }
            // If EOF
        } catch (EOFException e) {
            System.out.println("Reached end of the file.");
            // If other error
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    // Start main application
    public static void main(String[] args) {
        launch(args);
    }
}
