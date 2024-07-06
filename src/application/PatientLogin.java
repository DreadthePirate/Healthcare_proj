package application;

import java.io.IOException;

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

public class PatientLogin extends Application {
    
    public void start(Stage primaryStage) {
    	final String BUTTON_SMALL_CSS_CLASS = "button-small-style";
    	
        primaryStage.setTitle("Patient Login");

        // Main layout is a VBox
        VBox patientLogin = new VBox(10);
        patientLogin.setPadding(new Insets(10, 10, 10, 10));

        // Go Back button
        Button goBack = new Button("Go Back");
        HBox topControls = new HBox(goBack);
        topControls.setAlignment(Pos.TOP_LEFT);

        // Title
        Text title = new Text("Patient Login");
        title.setFont(Font.font("Arial", 18));
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 25, 0));

        // Grid for the form fields
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(30);
        grid.setVgap(20);
        grid.add(new Label("ID:"), 0, 0);
        TextField idField = new TextField();
        grid.add(idField, 1, 0);
        VBox.setMargin(grid, new Insets(0, 0, 40, 0));

        // Error message label
        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: red;");
        errorMessage.setVisible(false);

        // Login button
        Button btnLogin = new Button("Login");
        HBox bottomControls = new HBox(10, btnLogin);
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT);

        // Styling buttons
        goBack.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        btnLogin.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);

        // Button events
        goBack.setOnAction(e -> new Main().start(primaryStage));
        
        // Login
        btnLogin.setOnAction(e -> {
            String idText = idField.getText();
            
            if (idText.isEmpty()) {
                errorMessage.setText("ID must not be empty.");
                errorMessage.setVisible(true);
            } 
            else {
            	try {
                    int id = Integer.parseInt(idText); // Turns idText into an int - handles int error
                    String[] data = Data.readPatientFile(id); // Checks if file exists - handles user not found error
                    PatientView patientView = new PatientView();
                	patientView.setPatientID(id);
                	patientView.start(primaryStage);
                    System.out.println("Patient " + id + " Login successful");
                    
                } catch (NumberFormatException ex) {
                	errorMessage.setText("ID must be only numbers.");
                    errorMessage.setVisible(true);
                    
                } catch (IOException ex) {
                	errorMessage.setText("Patient ID not found.");
                    errorMessage.setVisible(true);
                }
            }
        });

        // Adding components to the VBox
        patientLogin.getChildren().addAll(topControls, titleBox, grid, errorMessage, bottomControls);

        // Set up the Scene
        Scene scene = new Scene(patientLogin, 350, 300);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
