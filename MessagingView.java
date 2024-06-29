package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MessagingView extends Application {

    private List<String> conversations;
    private VBox chatbox = new VBox(10);
    private ScrollPane chatScrollPane = new ScrollPane();
    private ComboBox<String> conversationSelector = new ComboBox<>();
    private TextField newConversationField = new TextField();
    private TextField messageInput = new TextField();
    private int index = 0;

    @Override
    public void start(Stage primaryStage) {
        // Title
        primaryStage.setTitle("Messaging Portal");
        Text title = new Text("Messaging Portal");
        title.setFont(Font.font("Arial", 18));
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 25, 0));

        // New Conversation input and button
        newConversationField.setPromptText("Sample_Conversation");
        newConversationField.setPrefWidth(300); // Set preferred width
        Button startNewConversationButton = new Button("New Conversation");
        startNewConversationButton.getStyleClass().add("button-large-style");

        HBox newConversationBox = new HBox(-4, newConversationField, startNewConversationButton);
        newConversationBox.setAlignment(Pos.CENTER);

        // Conversation selector
//        conversationSelector.getItems().addAll(viewConversations());
        conversationSelector.setValue("Sample_Conversation");
        conversationSelector.setOnAction(e -> System.out.println("test"));

        // Chat area inside a ScrollPane
        chatbox.setPadding(new Insets(10));
        chatScrollPane.setContent(chatbox);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setPrefHeight(200);

        // Message input and send button
        messageInput.setPromptText("Message_To_Send");
        messageInput.setPrefWidth(300);
        Button sendMessageButton = new Button("Send");

        sendMessageButton.setOnAction(e -> {
            String messageText = messageInput.getText().trim();
            if (!messageText.isEmpty()) {
                Label messageLabel = new Label("Patient: \"" + messageText + "\"");
                chatbox.getChildren().add(messageLabel);
                messageInput.clear();
                chatScrollPane.setVvalue(1.0); // Scroll to the bottom
            }
        });

        startNewConversationButton.setOnAction(e -> {System.out.println("");
//            String newConversation = newConversationField.getText().trim();
//            if (!newConversation.isEmpty()) {
//                chatbox.getChildren().clear();
//                conversationSelector.getItems().add(newConversation);
//                conversationSelector.setValue(newConversation);
//                newConversationField.clear();
//            }
        });

        HBox messageInputBox = new HBox(20, messageInput, sendMessageButton);
        messageInputBox.setAlignment(Pos.CENTER);

        // Main layout
        VBox layout = new VBox(10, titleBox, newConversationBox, conversationSelector, chatScrollPane, messageInputBox);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);

        // Setting up the Scene
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

        // Set the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }



//    private void loadConversation(int ID) {
//        chatbox.getChildren().clear();
//        conversations = List.of(Data.readPatientFile(ID));
//        for (String message : conversations) {
//            chatbox.getChildren().add(new Label(message));
//        }
//    }
//
//    private List<String> viewConversations() {
//        File folder = new File("src/conversations");
//        return Stream.of(folder.listFiles())
//                .filter(file -> file.isFile() && file.getName().endsWith(".txt"))
//                .map(file -> file.getName().replace(".txt", ""))
//                .collect(Collectors.toList());
//    }

    public static void main(String[] args) {launch(args);}
}
