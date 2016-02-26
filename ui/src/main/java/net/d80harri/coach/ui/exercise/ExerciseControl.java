package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ExerciseControl extends BorderPane {
	@FXML
	TextField txtName;
	@FXML
	TextArea txtDescription;

	private final AtomicExerciseModel model = new AtomicExerciseModel();

	public ExerciseControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		bindModel();
	}

	private void bindModel() {
		Bindings.bindBidirectional(txtName.textProperty(), model.nameProperty());
		Bindings.bindBidirectional(txtDescription.textProperty(), model.descriptonProperty());
	}

	public AtomicExerciseModel getModel() {
		return model;
	}
	
	public void save(ActionEvent evt) {
		System.out.println("Saving " + model.toString());
	}
}
