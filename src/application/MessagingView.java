package application;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	private int patientID;
	private String name;
	private String type; // "patient" or "doctor"

	@Override
	public void start(Stage primaryStage) {
		final String BUTTOM_BACK = "button-small-style";
		final String BUTTON_SMALL_CSS_CLASS = "button-small-message-style";
		final String BUTTON_LARGE_CSS_CLASS = "button-small-wide-message-style";

		VBox chatbox = new VBox(10);
		ScrollPane chatScrollPane = new ScrollPane();
		ComboBox<String> conversationSelector = new ComboBox<>();
		TextField newConversationField = new TextField();
		TextField messageInput = new TextField();

		// Title
		primaryStage.setTitle("Messaging Portal");
		Text title = new Text("Messaging Portal");
		title.setFont(Font.font("Arial", 24));
		HBox titleBox = new HBox(title);
		titleBox.setAlignment(Pos.CENTER);
		titleBox.setPadding(new Insets(0, 0, 20, 0));

		// New Conversation input and button
		newConversationField.setPromptText("New Conversation Name");
		newConversationField.setPrefWidth(275); // Set preferred width
		Button startNewConversationButton = new Button("New Conversation");
		startNewConversationButton.getStyleClass().add(BUTTON_LARGE_CSS_CLASS);

		// Go Back button
		Button GoBack = new Button("Go Back");
		HBox topControls = new HBox(GoBack);
		topControls.setPrefSize(50, 95);
		topControls.setAlignment(Pos.TOP_LEFT);
		GoBack.getStyleClass().add(BUTTOM_BACK);
		GoBack.setOnAction(e -> {
			if (this.type.equals("patient")) {
				PatientView patientView = new PatientView();
				patientView.setPatientID(this.patientID);
				patientView.start(primaryStage);
			} else {
				DoctorView doctorView = new DoctorView();
				doctorView.setPatientID(this.patientID);
				doctorView.start(primaryStage);
			}
		});

		HBox newConversationBox = new HBox(-5, newConversationField, startNewConversationButton);
		newConversationBox.setAlignment(Pos.CENTER);

		// Conversation selector
		String[] conversationsArray = Data.findConversations(this.patientID);
		ObservableList<String> conversationsList = FXCollections.observableArrayList(conversationsArray);
		conversationSelector.setItems(conversationsList);
		conversationSelector.setValue("Select An Existing Conversation");
		conversationSelector.setPrefWidth(300);
		conversationSelector.setOnAction(e -> {
			String selected = conversationSelector.getValue();
			String[] messages = Data.readMessageFile(this.patientID, selected);
			for (String message : messages) {
				Label messageLabel = new Label(message);
				chatbox.getChildren().add(messageLabel);
			}

			chatScrollPane.setVvalue(1.0);
		});

		// Chat area inside a ScrollPane
		chatbox.setPadding(new Insets(10));
		chatScrollPane.setContent(chatbox);
		chatScrollPane.setFitToWidth(true);
		chatScrollPane.setPrefHeight(300);

		// Message input and send button
		messageInput.setPromptText("Message To Send");
		messageInput.setPrefWidth(300);
		Button sendMessageButton = new Button("Send");
		sendMessageButton.getStyleClass().add(BUTTON_SMALL_CSS_CLASS);

		sendMessageButton.setOnAction(e -> {
			String messageText = messageInput.getText().trim();
			if (conversationSelector.getValue() != "Select An Existing Conversation" && !messageText.isEmpty()) {
				String message = this.name + " : " + messageText;
				Label messageLabel = new Label(message);
				chatbox.getChildren().add(messageLabel);
				messageInput.clear();
				chatScrollPane.setVvalue(1.0); // Scroll to the bottom
				Data.writeMessageFile(this.patientID, conversationSelector.getValue(), message);
			}
		});

		startNewConversationButton.setOnAction(e -> {
			String newConversation = newConversationField.getText().trim();
			if (!newConversation.isEmpty()) {
				chatbox.getChildren().clear();
				conversationSelector.getItems().add(newConversation);
				conversationSelector.setValue(newConversation);
				newConversationField.clear();
			}
		});

		HBox messageInputBox = new HBox(-5, messageInput, sendMessageButton);
		messageInputBox.setAlignment(Pos.CENTER);

		// Main layout
		VBox layout = new VBox(20, topControls, titleBox, newConversationBox, conversationSelector, chatScrollPane,
				messageInputBox);
		layout.setPadding(new Insets(20));
		layout.setAlignment(Pos.TOP_CENTER);

		// Setting up the Scene
		Scene scene = new Scene(layout, 600, 605);
		scene.getStylesheets().add(getClass().getResource("/resources/styles.css").toExternalForm());

		// Set the stage
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
