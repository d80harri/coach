package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.conf.ConfigurationModel;

public class ExerciseView extends BorderPane {
	@FXML
	TextField txtName;
	@FXML
	TextArea txtDescription;

	private final ExerciseModel model = new ExerciseModel();
	private final ConfigurationModel configModel;

	public ExerciseView(ConfigurationModel configModel) {
		this.configModel = configModel;
		
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
		Bindings.bindBidirectional(txtDescription.textProperty(), model.descriptionProperty());
	}

	public ExerciseModel getModel() {
		return model;
	}
	
	public void save(ActionEvent evt) {
		System.out.println("Saving " + model.toString());
	}
}
