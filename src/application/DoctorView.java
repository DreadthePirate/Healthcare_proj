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

public class DoctorView extends Application {
    public static final String BUTTON_SMALL_CSS_CLASS = "button-small-style";

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
        bottomControls.setAlignment(Pos.BOTTOM_RIGHT);

        // Styling buttons
        GoBack.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);
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
            } //else if(usrnm != known values || psswrd != known values || ptID != known values){
              //  errorMessage.setText("Username, Password, or Patient are wrong.");
              //  errorMessage.setVisible(true);
            // }
            //else
            // setOnAction(e -> new DoctorView().start(primaryStage));
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
