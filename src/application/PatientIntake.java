package application;

import java.io.IOException;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientIntake extends Application {
	public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("Patient Intake Form");

        // Main layout is a VBox
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10, 10, 10, 10));

        // Go Back button
        Button btnGoBack = new Button("Go Back");
        HBox topControls = new HBox(btnGoBack);
        topControls.setAlignment(Pos.TOP_LEFT);

        // Title
        Text title = new Text("Patient Intake Form");
        title.setFont(Font.font("Arial", 18));
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 25, 0));

        // Grid for the form fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField dobField = new TextField();
        TextField weightField = new TextField();
        TextField heightField = new TextField();
        TextField bloodPressureField = new TextField();
        TextField allergiesField = new TextField();
        TextField healthConcernsField = new TextField();
        
        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("Date Of Birth:"), 0, 2);
        grid.add(dobField, 1, 2);
        grid.add(new Label("Weight:"), 0, 3);
        grid.add(weightField, 1, 3);
        grid.add(new Label("Height:"), 0, 4);
        grid.add(heightField, 1, 4);
        grid.add(new Label("Blood Pressure:"), 0, 5);
        grid.add(bloodPressureField, 1, 5);
        grid.add(new Label("Allergies:"), 0, 6);
        grid.add(allergiesField, 1, 6);
        grid.add(new Label("Health Concerns:"), 0, 7);
        grid.add(healthConcernsField, 1, 7);
        VBox.setMargin(grid, new Insets(0, 0, 40, 0));

        // Save button
        Button btnSave = new Button("Save");
        HBox bottomControls = new HBox(btnSave);
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT);
        
        // Styling buttons
        btnGoBack.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        btnSave.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        
        // Button events
        btnGoBack.setOnAction(e -> new Main().start(primaryStage));
        btnSave.setOnAction(e -> {
        	Random rand = new Random();
        	// Generate a random 6-digit number for patientID
            int patientID = rand.nextInt(900000) + 100000;
            TextField[] textFields = {firstNameField, lastNameField, dobField, weightField, heightField, bloodPressureField, allergiesField, healthConcernsField};
            Data.writePatientFile(patientID, textFields);
            new Main().start(primaryStage);
        });

        // Adding components to the VBox
        mainLayout.getChildren().addAll(topControls, titleBox, grid, bottomControls);
        
        // Set up the Scene
        Scene scene = new Scene(mainLayout, 475, 600);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        
        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    //Class functions here

    public static void main(String[] args) {
        launch(args);
    }
}
