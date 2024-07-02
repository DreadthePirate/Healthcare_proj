package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

public class DoctorView extends Application {
    public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Portal");

        // Create layout
        BorderPane borderPane = new BorderPane();

        // Top layout with "Go Back" button
        /*
         * Text title = new Text("\tDoctor View");
         * title.setFont(Font.font("Arial", 24));
         * Button goBackButton = new Button("Go Back");
         * goBackButton.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
         * HBox topLayout = new HBox(goBackButton);
         * topLayout.setPadding(new Insets(10));
         * topLayout.setAlignment(Pos.TOP_LEFT);
         * borderPane.setTop(topLayout);
         * goBackButton.setMaxWidth(120);
         * goBackButton.setMaxHeight(35);
         */
        Text title = new Text("Doctor View");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        HBox topLayout = new HBox();
        topLayout.setPadding(new Insets(10));
        topLayout.setAlignment(Pos.CENTER);

        Button goBackButton = new Button("Go Back");
        goBackButton.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        goBackButton.setMaxWidth(120);
        goBackButton.setMaxHeight(35);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        topLayout.getChildren().addAll(goBackButton, leftSpacer, title, rightSpacer);
        borderPane.setTop(topLayout);

        // Center layout with form
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(40);

        Label medicationLabel = new Label("Medication:");
        TextArea medicationTextArea = new TextArea();
        medicationTextArea.setPrefHeight(50);
        medicationTextArea.setMaxWidth(400);
        gridPane.add(medicationLabel, 0, 0);
        gridPane.add(medicationTextArea, 1, 0, 2, 1);

        Label doctorNotesLabel = new Label("Doctor's Notes:");
        TextArea doctorNotesTextArea = new TextArea();
        doctorNotesTextArea.setPrefHeight(50);
        doctorNotesTextArea.setMaxWidth(400);
        gridPane.add(doctorNotesLabel, 0, 1);
        gridPane.add(doctorNotesTextArea, 1, 1, 2, 1);

        Label immunizationRecordLabel = new Label("Immunization Record:");
        TextArea immunizationRecordTextArea = new TextArea();
        immunizationRecordTextArea.setPrefHeight(50);
        immunizationRecordTextArea.setMaxWidth(400);
        gridPane.add(immunizationRecordLabel, 0, 2);
        gridPane.add(immunizationRecordTextArea, 1, 2, 2, 1);

        // Vitals Label and Text section
        VBox vitalsBox = new VBox(10);
        vitalsBox.setPadding(new Insets(10));
        vitalsBox.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        Label vitalsLabel = new Label("--Vitals--");

        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField();
        heightField.setText(""); // Pull from text file
        heightField.setEditable(false);

        Label weightLabel = new Label("Weight:");
        TextField weightField = new TextField();
        weightField.setText(""); // Pull from text file
        weightField.setEditable(false);

        Label bloodPressureLabel = new Label("Blood Pressure:");
        TextField bloodPressureField = new TextField();
        bloodPressureField.setText(""); // Pull from text file
        bloodPressureField.setEditable(false);

        Label allergiesLabel = new Label("Allergies:");
        TextField allergiesField = new TextField();
        allergiesField.setText(""); // Pull from text file
        allergiesField.setEditable(false);

        Label concernsLabel = new Label("Concerns:");
        TextField concernsField = new TextField();
        concernsField.setText(""); // Pull from text file
        concernsField.setEditable(false);

        Label previousHealthLabel = new Label("Previous Health Issues:");
        TextField previousHealthIssuesField = new TextField();
        previousHealthIssuesField.setText(""); // Pull from text file
        previousHealthIssuesField.setEditable(false);

        Label previousPrescriptionLabel = new Label("Previously Prescribed Medication:");
        TextField previouslyPrescribedMedicationField = new TextField();
        previouslyPrescribedMedicationField.setText(""); // Pull from text file
        previouslyPrescribedMedicationField.setEditable(false);

        Label immunizationLabel = new Label("Immunization History:");
        TextField immunizationHistoryField = new TextField();
        immunizationHistoryField.setText(""); // Pull from text file
        immunizationHistoryField.setEditable(false);
        //

        // Vitals Spacing Section
        GridPane vitalsGridPane = new GridPane();
        vitalsGridPane.setVgap(10);
        vitalsGridPane.setHgap(10);
        vitalsGridPane.add(vitalsLabel, 0, 0, 2, 1);
        vitalsGridPane.add(heightLabel, 0, 1);
        vitalsGridPane.add(heightField, 1, 1);
        vitalsGridPane.add(weightLabel, 0, 2);
        vitalsGridPane.add(weightField, 1, 2);
        vitalsGridPane.add(bloodPressureLabel, 0, 3);
        vitalsGridPane.add(bloodPressureField, 1, 3);
        vitalsGridPane.add(allergiesLabel, 0, 4);
        vitalsGridPane.add(allergiesField, 1, 4);
        vitalsGridPane.add(concernsLabel, 0, 5);
        vitalsGridPane.add(concernsField, 1, 5);
        vitalsGridPane.add(previousHealthLabel, 0, 6);
        vitalsGridPane.add(previousHealthIssuesField, 1, 6);
        vitalsGridPane.add(previousPrescriptionLabel, 0, 7);
        vitalsGridPane.add(previouslyPrescribedMedicationField, 1, 7);
        vitalsGridPane.add(immunizationLabel, 0, 8);
        vitalsGridPane.add(immunizationHistoryField, 1, 8);

        gridPane.add(vitalsGridPane, 3, 0, 1, 3);

        // Bottom Layout
        GridPane bottomLayout = new GridPane();
        bottomLayout.setPadding(new Insets(10));
        bottomLayout.setHgap(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        bottomLayout.getColumnConstraints().addAll(col1, col2);

        // Bottom Button Layout
        Button saveButton = new Button("Save");
        saveButton.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        Button messagePatientButton = new Button("Message Patient");
        messagePatientButton.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
        messagePatientButton.setMinWidth(180);

        bottomLayout.add(saveButton, 0, 0);
        bottomLayout.add(messagePatientButton, 1, 0);
        GridPane.setMargin(saveButton, new Insets(-30, 0, 0, 150));
        GridPane.setMargin(messagePatientButton, new Insets(-30, 0, 0, 190));

        borderPane.setCenter(gridPane);
        borderPane.setBottom(bottomLayout);

        // Button Actions
        // goBackButton.setOnAction(e-> new Main().start(primaryStage));
        // messagePatientButton.setOnAction(e-> new
        // MessagePatient().start(primaryStage));
        // saveButton.setOnAction();

        // Scene and Stage
        Scene scene = new Scene(borderPane, 950, 450);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
