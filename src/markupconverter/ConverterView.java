package markupconverter;

import javafx.collections.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ConverterView extends Application {

	// GUI Boxes ---------------------------------------------------------------
	private HBox hBoxMaster = new HBox(0);
	private VBox vBoxCenterControls = new VBox(20);

	private VBox vBoxInput = new VBox(20);
	private TextArea textAreaInput = new TextArea();
	private ComboBox<String> comboInput = new ComboBox<String>();

	private VBox vBoxOutput = new VBox(20);
	private TextArea textAreaOutput = new TextArea();
	private ComboBox<String> comboOutput = new ComboBox<String>();

	private Button buttonConvert = new Button("Convert");

	private VBox vBoxCenterSubControls = new VBox(20);
	private RadioButton radioCullTags = new RadioButton("Remove incompatible tags");
	private RadioButton radioForceUl = new RadioButton("Treat \"_\" as an underline");

	private ObservableList<String> oListMarkupChoices = FXCollections.observableArrayList("Markdown", "BBCode", "HTML5");

	@Override
	public void start(Stage primaryStage) throws Exception {

		hBoxMaster.setAlignment(Pos.CENTER);
		hBoxMaster.setPadding(new Insets(20));
		vBoxCenterControls.setPadding(new Insets(20));

		textAreaInput.setWrapText(true);
		textAreaInput.setPrefRowCount(120);
		textAreaInput.setMinWidth(200);
		// textAreaInput.setMaxWidth(1000);
		// textAreaInput.setPrefColumnCount(10);
		textAreaInput.setPrefWidth(2000);
		// textAreaInput.setPadding(new Insets(20, 20, 20, 20));

		textAreaOutput.setWrapText(true);
		textAreaOutput.setPrefRowCount(120);
		textAreaOutput.setMinWidth(200);
		// textAreaOutput.setMaxWidth(1000);
		// textAreaOutput.setPrefColumnCount(10);
		textAreaOutput.setPrefWidth(2000);
		// textAreaOutput.setPadding(new Insets(20, 20, 20, 20));

		buttonConvert.setMinWidth(100);
		ConvertHandler convertHandler = new ConvertHandler();
		buttonConvert.setOnAction(convertHandler);

		// Input Text Area -----------------------------------------------------
		comboInput.setItems(oListMarkupChoices);
		comboInput.setValue("Markdown");
		comboInput.setMinWidth(200);
		textAreaInput.setPromptText("Input text");
		// comboInput.setAlign
		vBoxInput.getChildren().add(comboInput);
		vBoxInput.getChildren().add(textAreaInput);
		vBoxInput.setAlignment(Pos.CENTER);

		// Output Text Area ----------------------------------------------------
		comboOutput.setItems(oListMarkupChoices);
		comboOutput.setValue("BBCode");
		comboOutput.setMinWidth(200);
		textAreaOutput.setPromptText("Output text");
		textAreaOutput.setEditable(false);
		vBoxOutput.getChildren().add(comboOutput);
		vBoxOutput.getChildren().add(textAreaOutput);
		vBoxOutput.setAlignment(Pos.CENTER);

		// Center Controls -----------------------------------------------------
		radioForceUl.setMnemonicParsing(false); // Allows for the "_" character
		radioForceUl.setSelected(true);
		radioCullTags.setWrapText(true);
		vBoxCenterSubControls.getChildren().add(radioCullTags);
		vBoxCenterSubControls.getChildren().add(radioForceUl);
		vBoxCenterSubControls.setAlignment(Pos.CENTER_LEFT);

		vBoxCenterControls.getChildren().add(vBoxCenterSubControls);
		vBoxCenterControls.getChildren().add(buttonConvert);
		vBoxCenterControls.setAlignment(Pos.BOTTOM_CENTER);
		vBoxCenterControls.setMinWidth(225);

		// Master Layout -------------------------------------------------------
		hBoxMaster.getChildren().add(vBoxInput);
		hBoxMaster.getChildren().add(vBoxCenterControls);
		hBoxMaster.getChildren().add(vBoxOutput);
		primaryStage.setMinWidth(800);
		primaryStage.setMinHeight(400);

		// Sets up the scene, stage, and title
		Scene scene = new Scene(hBoxMaster, 1000, 600);
		primaryStage.setTitle("Markup Converter");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon_512.png")));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon_48.png")));
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon_32.png")));
	} // start()

	public static void main(String[] args) {
		Application.launch(args);
	} // main()

	class ConvertHandler implements EventHandler<ActionEvent> {

		ConverterController controller = new ConverterController();

		@Override
		public void handle(ActionEvent e) {

			try {
				String convertedOutput = controller.convertMarkup(textAreaInput.getText(), comboInput.getValue(),
						comboOutput.getValue(), radioForceUl.isSelected(), radioCullTags.isSelected());

				textAreaOutput.setText(convertedOutput);
			} catch (IllegalArgumentException a) {
				System.out.println("ERROR: Could not convert text!");
			}

		}

	} // ConvertHandler class

} // ConverterView class
