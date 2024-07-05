package application;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Data {
	/*
	 * PATIENT
	 */
	public static void writePatientFile(int id, TextField[] textFields) {
        String filePath = "src/patients/" + id + "_Patient.txt"; // Path relative to src folder

        try (FileWriter writer = new FileWriter(filePath)) {
            for (TextField textField : textFields) {
                String text = textField.getText();
                if (text == null || text.trim().isEmpty()) {
                    writer.write("null\n");
                } else {
                    writer.write(text + "\n");
                }
            }
            System.out.println("Data saved to file: " + filePath + " Press refresh on src to see it.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static String[] readPatientFile(int id) throws IOException {
        String fileName = "src/patients/" + id + "_Patient.txt"; // Path relative to src folder
        String[] dataLines = new String[9];
        int i = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && i < dataLines.length) {
            	if (line.equals("null")) {
            		dataLines[i] = null;
            	} else {
            		dataLines[i] = line;
            	}
                i++;
            }
        }
        
        return (dataLines);
    }
    
    
	/*
	 * Doctor
	 */
    public static String[] readDoctorFile(String username) throws IOException {
        String fileName = "src/doctors/" + username + "_Doctor.txt"; // Path relative to src folder
        String[] dataLines = new String[1];
        int i = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && i < dataLines.length) {
            	if (line.equals("null")) {
            		dataLines[i] = null;
            	} else {
            		dataLines[i] = line;
            	}
                i++;
            }
        }
        
        return (dataLines);
    }
    
    public static void writeDoctorNotesFile(int id, TextArea[] textFields) {
        String filePath = "src/notes/" + id + "_DoctorNotes.txt"; // Path relative to src folder

        try (FileWriter writer = new FileWriter(filePath)) {
            for (TextArea textField : textFields) {
                String text = textField.getText();
                if (text == null || text.trim().isEmpty()) {
                    writer.write("null\n");
                } else {
                    writer.write(text + "\n");
                }
            }
            System.out.println("Data saved to file: " + filePath + " Press refresh on src to see it.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    public static String[] readDoctorNotesFile(int id) throws IOException {
        String fileName = "src/notes/" + id + "_DoctorNotes.txt"; // Path relative to src folder
        String[] dataLines = new String[3];
        int i = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && i < dataLines.length) {
            	if (line.equals("null")) {
            		dataLines[i] = null;
            	} else {
            		dataLines[i] = line;
            	}
                i++;
            }
        }
        
        return (dataLines);
    }
    
    /*
	 * Messaging
	 */
    public static String[] findConversations(int id) {
        String idString = id + ""; // Convert the ID to a string
        File directory = new File("src/messages/");
        List<String> conversations = new ArrayList<>();
        if (directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles((dir, name) -> name.startsWith(idString) && name.matches(idString + "_.*_Message\\.txt"))) {
                conversations.add(file.getName().substring(file.getName().indexOf('_') + 1, file.getName().lastIndexOf('_')));
            }
        }
        return conversations.toArray(new String[0]);
    }
    
    public static void writeMessageFile(int patientID, String conversation, String message) {
        String filePath = "src/messages/" + patientID + "_" + conversation + "_Message.txt";
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String[] readMessageFile(int id, String conversation) {
        String fileName = "src/messages/" + id + "_" + conversation + "_Message.txt"; // Path relative to src folder
        List<String> dataLines = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dataLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading message from file: " + e.getMessage());
        }
        
        return dataLines.toArray(new String[0]);
    }
}


