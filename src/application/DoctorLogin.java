package application;

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

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class DoctorLogin extends Application {
    public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";
    private static final String CREDENTIALS_DIR = "*/Healthcare_proj-main/src/resources";

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Doctor Login");

        // Main layout is a VBox
        VBox DrLogin = new VBox(10);
        DrLogin.setPadding(new Insets(10, 10, 10, 10));

        // Go Back button
        Button GoBack = new Button("Go Back");
        HBox topControls = new HBox(GoBack);
        topControls.setAlignment(Pos.TOP_LEFT);

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
        TextField psswrdField = new TextField();
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
        bottomControls.setAlignment(Pos.BOTTOM_LEFT);

        // Styling buttons
        btnLogin.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);

        // Button events
        GoBack.setOnAction(e -> new Main().start(primaryStage));
        btnLogin.setOnAction(e -> {
            String usrnm = usrnmField.getText();
            String psswrd = psswrdField.getText();
            String ptID = ptIDField.getText();
            if (usrnm.isEmpty() || psswrd.isEmpty() || ptID.isEmpty()) {
                errorMessage.setText("Username, Password, and Patient ID must not be empty.");
                errorMessage.setVisible(true);
            } else if (!verifyLogin(usrnm, psswrd, ptID)) {
                errorMessage.setText("Username, Password, or Patient ID are incorrect.");
                errorMessage.setVisible(true);
            } else {
                // Proceed to the next view
                // new DoctorView().start(primaryStage);
                System.out.println("asflj");
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

    private boolean verifyLogin(String username, String password, String patientID) {
        try (Stream<Path> paths = Files.walk(Paths.get(CREDENTIALS_DIR))) {
            return paths
                    .filter(Files::isRegularFile)
                    .anyMatch(path -> {
                        try {
                            List<String> lines = Files.readAllLines(path);
                            String fileUsername = "";
                            String filePassword = "";
                            String filePatientID = "";
                            for (String line : lines) {
                                if (line.trim().isEmpty()) {
                                    // Blank line indicates end of one credential set
                                    if (fileUsername.equals(username) && filePassword.equals(password) && filePatientID.equals(patientID)) {
                                        return true;
                                    }
                                    // Reset for next set
                                    fileUsername = "";
                                    filePassword = "";
                                    filePatientID = "";
                                } else {
                                    String[] parts = line.split(":");
                                    if (parts.length == 2) {
                                        String key = parts[0].trim();
                                        String value = parts[1].trim();
                                        switch (key.toLowerCase()) {
                                            case "username":
                                                fileUsername = value;
                                                break;
                                            case "password":
                                                filePassword = value;
                                                break;
                                            case "patient id":
                                                filePatientID = value;
                                                break;
                                        }
                                    }
                                }
                            }
                            // Check the last set in the file
                            return fileUsername.equals(username) && filePassword.equals(password) && filePatientID.equals(patientID);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}