package javaFXX;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class DoctorPortal extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Portal");

        // Create layout
        BorderPane borderPane = new BorderPane();

        // Top layout with "Go Back" button
        Button goBackButton = new Button("Go Back");
        HBox topLayout = new HBox(goBackButton);
        topLayout.setPadding(new Insets(10));
        topLayout.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(topLayout);

        // Center layout with form
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        Label medicationLabel = new Label("Medication:");
        TextArea medicationTextArea = new TextArea();
        medicationTextArea.setPrefHeight(50);
        gridPane.add(medicationLabel, 0, 0);
        gridPane.add(medicationTextArea, 1, 0, 2, 1);

        Label doctorNotesLabel = new Label("Doctor's Notes:");
        TextArea doctorNotesTextArea = new TextArea();
        doctorNotesTextArea.setPrefHeight(50);
        gridPane.add(doctorNotesLabel, 0, 1);
        gridPane.add(doctorNotesTextArea, 1, 1, 2, 1);

        Label immunizationRecordLabel = new Label("Immunization Record:");
        TextArea immunizationRecordTextArea = new TextArea();
        immunizationRecordTextArea.setPrefHeight(50);
        gridPane.add(immunizationRecordLabel, 0, 2);
        gridPane.add(immunizationRecordTextArea, 1, 2, 2, 1);

        // Vitals section
        VBox vitalsBox = new VBox(10);
        vitalsBox.setPadding(new Insets(10));
        vitalsBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label vitalsLabel = new Label("--Vitals--");

        Label weightLabel = new Label("Weight:");
        Label weightValueLabel = new Label("Weight: 70 kg");
        Label heightLabel = new Label("Height:");
        Label heightValueLabel = new Label("Height: 70 cm");

        TextField bloodPressureField = new TextField();
        bloodPressureField.setPromptText("Blood Pressure:");

        TextField allergiesField = new TextField();
        allergiesField.setPromptText("Allergies:");

        TextField concernsField = new TextField();
        concernsField.setPromptText("Concerns:");

        TextField previousHealthIssuesField = new TextField();
        previousHealthIssuesField.setPromptText("Previous health issues:");

        TextField previouslyPrescribedMedicationField = new TextField();
        previouslyPrescribedMedicationField.setPromptText("Previously prescribed medication:");

        TextField immunizationHistoryField = new TextField();
        immunizationHistoryField.setPromptText("Immunization history:");

        vitalsBox.getChildren().addAll(vitalsLabel, heightValueLabel, weightValueLabel, bloodPressureField, allergiesField, concernsField, previousHealthIssuesField, previouslyPrescribedMedicationField, immunizationHistoryField);

        gridPane.add(vitalsBox, 3, 0, 1, 3);

        // Bottom layout with buttons
        HBox bottomLayout = new HBox(10);
        bottomLayout.setPadding(new Insets(10));
        bottomLayout.setAlignment(Pos.CENTER);

        Button saveButton = new Button("Save");
        Button messagePatientButton = new Button("Message Patient");

        bottomLayout.getChildren().addAll(saveButton, messagePatientButton);

        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottomLayout);

        // Scene and Stage
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}