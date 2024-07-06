package application;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;


public class PatientView extends Application{
	private int patientID;
	
	public void start(Stage primaryStage) {
		final String BUTTON_SMALL_CSS_CLASS = "button-small-style";
		final String BUTTON_SMALL_WIDE_CSS_CLASS = "button-small-wide-style";
		
		String[] patientData;
		String[] noteData;
		
		// Initialize data - Login handles bad Id, needed to start app from java file.
		try {
			patientData = Data.readPatientFile(this.patientID);
		} catch (IOException e) {
			patientData = new String[9];
		}
		String name = patientData[0];
		
		try {
			noteData = Data.readDoctorNotesFile(this.patientID);
		} catch (IOException e) {
			noteData = new String[3];
		}
		
		primaryStage.setTitle("Patient View");
		primaryStage.setResizable(false);
		
		// Main layout stacks
        HBox stack1 = new HBox(10);
        VBox stack2 = new VBox(25);
        VBox stack3 = new VBox(25);
        
        // Miscellaneous Labels
        Label vitalLabel = new Label("--Vitals--");
        vitalLabel.setPadding(new Insets(20,10,10,60));
        
        //textArea stack
        Label historyLabel = new Label("--History--");
        historyLabel.setPadding(new Insets(10,10,10,80));
        
        // Go Back button
        Button goBack = new Button("Log Out");
        Button messageDoctor = new Button("Message Doctor");
        HBox topControls = new HBox(goBack);
        topControls.setAlignment(Pos.TOP_LEFT);
        messageDoctor.setPadding(new Insets(10,10,10,10));
        topControls.setPadding(new Insets(10,30,10,20));
        goBack.setMinWidth(150);
        goBack.setMinHeight(45);
        
        //VBox formatting
        stack1.getChildren().add(new Label("Patient: Body temperature: " + "*TEMPERATURE INFORMATION* \n" + "Previously prescribed medication: \n" + patientData[7])); //Do we have temperature information
        stack1.getChildren().add(new Label("Patient Information"));
        stack1.setAlignment(Pos.TOP_CENTER);
        stack1.setMaxWidth(300);
        stack1.setPadding(new Insets(10,10,30,10));
        
        stack2.getChildren().add(new Label("Weight:"));
        stack2.getChildren().add(new Label("Height:"));
        stack2.getChildren().add(new Label("Blood Pressure:"));
        stack2.getChildren().add(new Label("Allergies:"));
        stack2.getChildren().add(new Label("Concerns:"));
        stack2.setMinWidth(170);
        stack2.setPadding(new Insets(40.5, 10, 30, 10));

        
        stack3.getChildren().add(new Label("Previous Health Issues:\n\n"));
        stack3.getChildren().add(new Label("Previously prescribed medication:\n\n\n\n"));
        stack3.getChildren().add(new Label("Immunization history:"));
        stack3.setPadding(new Insets(40.5, 10, 10, 10));
		
        // Styling buttons
        goBack.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        messageDoctor.getStyleClass().add(BUTTON_SMALL_WIDE_CSS_CLASS);
     
  
        //patientdata[0]=First Name
        //patientdata[1]=Last Name
        //patientdata[2]=DOB
        //patientdata[3]=Weight
        //patientdata[4]=Height
        //patientdata[5]=Blood Pressure
        //patientdata[6]=Allergies
        //patientdata[7]=Health Concerns
        
        
        //initialize textArea fields
        VBox textStack1 = new VBox(8);
        VBox textStack2 = new VBox(8);

        textStack1.setPadding(new Insets(40, 10, 31, 10));
        textStack2.setPadding(new Insets(40, 10, 0, 10));
        
        TextArea[] textArea = new TextArea[8];
        for (int i = 0; i < textArea.length; i++) {
        	textArea[i] = new TextArea();
        	textArea[i].setPrefWidth(100);
        	
        	textArea[i].setPrefHeight(32);
            textArea[i].setEditable(false);
        	if (i < 5) {
        		textArea[i].setMinHeight(20);
        		textArea[i].setMinWidth(125);
                textStack1.getChildren().add(textArea[i]);
        	}
        	else {
        		textArea[i].setPrefHeight(60);
        		textArea[i].setPrefWidth(200);
        		textStack2.getChildren().add(textArea[i]);
        	}
        	
        }
        textStack1.setMaxWidth(100);

        // Set Data
        for (int i = 3; i < patientData.length - 1; ++i) {
        	textArea[i - 3].setText(patientData[i]);
        }
        textArea[5].setText(noteData[1]);
        textArea[6].setText(noteData[0]);
        textArea[7].setText(noteData[2]);
        

        // Grid for the form fields
        GridPane grid = new GridPane();
        Label header = new Label("Patient Body temperature: " + "NULL \n");
        Text title = new Text("\tPatient View");

        TextArea presMed = new TextArea();
        presMed.setPrefHeight(60);
        presMed.setMaxWidth(150);
        presMed.setText(patientData[7]);
        presMed.setEditable(false);
        
        header.setPadding(new Insets(30, 10, 10, 10));
        
        title.setFont(Font.font("Arial", 24));
        header.setAlignment(Pos.BASELINE_CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(120));
        grid.getColumnConstraints().add(new ColumnConstraints(150));
        grid.getColumnConstraints().add(new ColumnConstraints(200));
        grid.getRowConstraints().add(new RowConstraints(55));
        grid.setPadding(new Insets(0,0,10,0));
        
        
        grid.add(vitalLabel, 0,3);
        grid.add(historyLabel, 3, 3);
        grid.add(title, 2, 0);
        grid.add(stack2, 0,4);
        grid.add(topControls, 0, 0);
        grid.add(textStack1, 1, 4);
        grid.add(stack3, 2, 4);
        grid.add(textStack2, 3, 4);
        grid.add(messageDoctor, 3, 5);

        // Button events
        goBack.setOnAction(e -> new PatientLogin().start(primaryStage));
        messageDoctor.setOnAction(e -> {
        	MessagingView messagingView = new MessagingView();
        	messagingView.setType("patient");
        	messagingView.setPatientID(this.patientID);
        	messagingView.setName(name);
        	messagingView.start(primaryStage);
        });

        Scene scene = new Scene(grid, 730, 475);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	// Helper functions
	public void setPatientID (int patientID) {
		this.patientID = patientID;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	
}
