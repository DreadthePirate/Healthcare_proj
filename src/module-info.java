module MODULE {
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
	requires javafx.graphics;
	requires java.base;

	
	opens application to javafx.graphics, javafx.fxml;
	exports application;
}
