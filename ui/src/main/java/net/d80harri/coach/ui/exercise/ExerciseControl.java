package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ExerciseControl extends VBox {
	@FXML
	TextField txtFirst;
	@FXML
	TextField txtSecond;
	@FXML
	TextField txtSum;

	private CalculatorModel model;

	public ExerciseControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		model = new CalculatorModel(this);
	}

}
