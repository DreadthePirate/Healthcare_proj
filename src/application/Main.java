package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Main extends Application {
	public static final String BUTTON_CSS_CLASS = "button-style";

    @Override
    public void start(Stage primaryStage) {
    	 VBox vbox = new VBox(10);
         vbox.setAlignment(Pos.CENTER);

         // Title
         Text title = new Text("Welcome to Pediatric Kids Office System!");
         title.setFont(Font.font("Arial", 18));
         VBox titleBox = new VBox(title);
         titleBox.setAlignment(Pos.CENTER);
         titleBox.setPadding(new Insets(0, 0, 10, 0));
         
         // Buttons
         Button btnIntake = new Button("Intake Portal");
         Button btnPatient = new Button("Patient Portal");
         Button btnDoctor = new Button("Doctor Portal");

         // Styling buttons
         btnIntake.getStyleClass().add(BUTTON_CSS_CLASS);
         btnDoctor.getStyleClass().add(BUTTON_CSS_CLASS);
         btnPatient.getStyleClass().add(BUTTON_CSS_CLASS);

         // Button events
         btnIntake.setOnAction(e -> new PatientIntake().start(primaryStage));
         btnDoctor.setOnAction(e -> new DoctorLogin().start(primaryStage));
         btnPatient.setOnAction(e -> System.out.println("Patient Portal clicked"));

         // Adding components to the VBox
         vbox.getChildren().addAll(titleBox, btnIntake, btnPatient, btnDoctor);

         // Set up the Scene
         Scene scene = new Scene(vbox, 500, 325);
         scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

         // Set the stage
         primaryStage.setTitle("Pediatric Kids Office");
         primaryStage.setScene(scene);
         primaryStage.show();
    }

    // Class functions here


    public static void main(String[] args) {
        launch(args);
    }
}