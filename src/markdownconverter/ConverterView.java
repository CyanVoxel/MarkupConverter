package markdownconverter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ConverterView extends Application {
	
	// GUI Boxes ----------------------------------------------------------
	private VBox vboxMaster = new VBox(0);

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Sets up the scene, stage, and title
		Scene scene = new Scene(vboxMaster, 1000, 600);
		primaryStage.setTitle("Markdown Converter");
		primaryStage.setScene(scene); 
		primaryStage.show();
	} // start()
	
	public static void main(String[] args) {
		Application.launch(args);
    } // main()

} // ConverterView class
