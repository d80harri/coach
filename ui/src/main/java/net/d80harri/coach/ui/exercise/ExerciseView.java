package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class ExerciseView extends BorderPane { // TODO: rename this to ExerciseEditorView or something like that
	@FXML Label lblId;
	@FXML TextField txtName;
	@FXML TextArea txtDescription;

	private final ExerciseViewModel model;

	public ExerciseView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		model = new ExerciseViewModel();
		
		lblId.textProperty().bind(model.idProperty());
		txtName.textProperty().bindBidirectional(model.nameProperty());
		txtDescription.textProperty().bindBidirectional(model.descriptionProperty());
		
		lblId.visibleProperty().bind(model.idVisibleProperty());
		lblId.managedProperty().bind(model.idVisibleProperty());
	}
	
	public ExerciseViewModel getModel() {
		return model;
	}
	
	public void save(ActionEvent evt) {
		System.out.println("Saving " + model.toString());
	}

}
