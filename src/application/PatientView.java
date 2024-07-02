package application;

import javafx.stage.Stage;
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
	public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";
	
	public void start(Stage primaryStage) {
		String[] patientData = Data.readPatientFile(123456); //this line is for testing; this class should be displaying patient data along with medical history
		primaryStage.setTitle("Patient View");
		primaryStage.setResizable(false);
		
		// Main layout stacks
	        HBox stack1 = new HBox(10);
	        VBox stack2 = new VBox(25);
	        VBox stack3 = new VBox(25);
	        
	        //Misc Labels
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
	        messageDoctor.getStyleClass().add("button-small-style-wide");
	        
	        //patientdata[0]=Weight
	        //patientdata[1]=Height
	        //patientdata[2]=Blood Pressure
	        //patientdata[3]=Allergies
	        //patientdata[4]=Concerns
	        //patientdata[5]=Previous health issues
	        //patientdata[6]=Prescribed medication
	        //patientdata[7]=Immunization history
	        
	        //initialize textArea fields
	        VBox textStack1 = new VBox(8);
	        VBox textStack2 = new VBox(8);
	
	        textStack1.setPadding(new Insets(40, 10, 31, 10));
	        textStack2.setPadding(new Insets(40, 10, 0, 10));
	        
	        TextArea[] textArea = new TextArea[8];
	        for (int i = 0; i < textArea.length; i++) {
	        	textArea[i] = new TextArea();
	        	textArea[i].setText(patientData[i]);
	        	textArea[i].setPrefWidth(100);
	        	
	        	textArea[i].setPrefHeight(32);
	            textArea[i].setEditable(false);
	        	if (i < 5) {
	        		textArea[i].setMinHeight(20);
	                textStack1.getChildren().add(textArea[i]);
	        	}
	        	else {
	        		textArea[i].setPrefHeight(60);
	        		textArea[i].setPrefWidth(200);
	        		textStack2.getChildren().add(textArea[i]);
	        	}
	        	
	        }
	        textStack1.setMaxWidth(100);
	        
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
	        
	        title.setFont(Font.font("Arial",24));
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
	        goBack.setOnAction(e -> new Main().start(primaryStage));
	        //messageDoctor.setOnAction(e -> new MessageDoctor().start(primaryStage)); code to be added once message doctor class is added
	
	        Scene scene = new Scene(grid,730,420);
	        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
	
	        // Set the stage
	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
