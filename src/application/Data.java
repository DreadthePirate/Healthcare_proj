package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.scene.control.TextField;

public class Data {
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
    
    public static String[] readPatientFile(int id) {
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
        } catch (IOException e) {
            System.out.println("Error for file: " + e.getMessage());
        }
        
        return (dataLines);
    }
}


