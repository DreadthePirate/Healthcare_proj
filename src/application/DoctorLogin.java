package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class DoctorLogin extends Application {
    public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";
    private static final String CREDENTIALS_DIR = "*/Healthcare_proj-main/src";

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Login");

        // Main layout is a VBox
        VBox DrLogin = new VBox(10);
        DrLogin.setPadding(new Insets(10, 10, 10, 10));

        // Go Back button
        Button GoBack = new Button("Go Back");
        HBox topControls = new HBox(GoBack);
        topControls.setAlignment(Pos.TOP_LEFT);
        GoBack.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);

        // Title
        Text Title = new Text("Doctor Login");
        Title.setFont(Font.font("Arial", 18));
        HBox TitleBox = new HBox(Title);
        TitleBox.setAlignment(Pos.CENTER);
        TitleBox.setPadding(new Insets(20, 0, 25, 0));

        // Grid for the form fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        grid.add(new Label("Username:"), 0, 0);
        TextField usrnmField = new TextField();
        grid.add(usrnmField, 0, 1);
        grid.add(new Label("Password:"), 0, 2);
        TextField psswrdField = new PasswordField();
        grid.add(psswrdField, 0, 3);
        grid.add(new Label("Patient ID:"), 0, 4);
        TextField ptIDField = new TextField();
        grid.add(ptIDField, 0, 5);
        VBox.setMargin(grid, new Insets(0, 0, 40, 0));

        // Error message label
        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;");
        errorMessage.setVisible(false);

        // Save button
        Button btnLogin = new Button("Login");
        HBox bottomControls = new HBox(btnLogin);
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT);

        // Styling buttons
        btnLogin.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);

        // Button events
        GoBack.setOnAction(e -> new Main().start(primaryStage));
        btnLogin.setOnAction(e -> {
        	String username = usrnmField.getText();
        	String password = psswrdField.getText();
        	String idText = ptIDField.getText();
        	
        	if (idText.isEmpty() || password.isEmpty() || username.isEmpty()) {
                errorMessage.setText("Login fields must not be empty.");
                errorMessage.setVisible(true);
            } else {
            	// Verify Doctor Login
            	try {
            		String[] data = Data.readDoctorFile(username);
            		if (data[0].equals(password)) {
            			// Do nothing and resume execution
            			
            		} else {
            			errorMessage.setText("Incorrect Password.");
                        errorMessage.setVisible(true);
                        return;
            		}
            	} catch (IOException ex) {
                	errorMessage.setText("Incorrect Username.");
                    errorMessage.setVisible(true);
                }
            	
            	
            	try {
                    int id = Integer.parseInt(idText); // Turns idText into an int - handles int error
                    String[] data = Data.readPatientFile(id); // Checks if file exists - handles user not found error
                    
                    DoctorView doctorView = new DoctorView();
                    doctorView.setPatientID(id);
                    doctorView.setName(usrnmField.getText());
                	doctorView.start(primaryStage);
                    System.out.println("Doctor " + username + " Login successful");
                    
                } catch (NumberFormatException ex) {
                	errorMessage.setText("Patient ID must be only numbers.");
                    errorMessage.setVisible(true);
                    
                } catch (IOException ex) {
                	errorMessage.setText("Patient ID not found.");
                    errorMessage.setVisible(true);
                }
            }
        });

        // Adding components to the VBox
        DrLogin.getChildren().addAll(topControls, TitleBox, grid, errorMessage, bottomControls);

        // Set up the Scene
        Scene scene = new Scene(DrLogin, 350, 500);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}