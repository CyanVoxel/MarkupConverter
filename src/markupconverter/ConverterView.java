package markupconverter;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ConverterView extends Application {

	// GUI Boxes ----------------------------------------------------------
	private HBox hboxMaster = new HBox(0);
	private VBox vboxCenterControls = new VBox(0);
	private TextArea textAreaInput = new TextArea();
	private TextArea textAreaOutput = new TextArea();

	private Button buttonConvert = new Button("Convert");

	@Override
	public void start(Stage primaryStage) throws Exception {

		hboxMaster.setAlignment(Pos.CENTER);
		hboxMaster.setPadding(new Insets(20));
		vboxCenterControls.setPadding(new Insets(20));

		textAreaInput.setWrapText(true);
		textAreaInput.setPrefRowCount(120);
		textAreaInput.setMinWidth(200);
		// textAreaInput.setPrefColumnCount(10);
		// textAreaInput.setPrefWidth(100);
		// textAreaInput.setPadding(new Insets(20, 20, 20, 20));

		textAreaOutput.setWrapText(true);
		textAreaOutput.setPrefRowCount(120);
		textAreaOutput.setMinWidth(200);
		// textAreaOutput.setPrefColumnCount(10);
		// textAreaOutput.setPrefWidth(100);
		// textAreaOutput.setPadding(new Insets(20, 20, 20, 20));

		buttonConvert.setMinWidth(100);

		vboxCenterControls.getChildren().add(buttonConvert);
		vboxCenterControls.setAlignment(Pos.BOTTOM_CENTER);

		hboxMaster.getChildren().add(textAreaInput);
		hboxMaster.getChildren().add(vboxCenterControls);
		hboxMaster.getChildren().add(textAreaOutput);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(400);

		// Sets up the scene, stage, and title
		Scene scene = new Scene(hboxMaster, 1000, 600);
		primaryStage.setTitle("Markdown Converter");
		primaryStage.setScene(scene);
		primaryStage.show();
	} // start()

	public static void main(String[] args) {
		Application.launch(args);
	} // main()

} // ConverterView class
