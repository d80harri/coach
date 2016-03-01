package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.monadic;
import static org.fxmisc.easybind.EasyBind.select;

import java.io.IOException;
import java.util.OptionalDouble;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ExerciseView extends BorderPane { // TODO: rename this to ExerciseEditorView or something like that
	@FXML Label lblId;
	@FXML TextField txtName;
	@FXML TextArea txtDescription;

	private final ObjectProperty<ExerciseViewModel> model = new SimpleObjectProperty<>(this, "model");

	public ExerciseView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		monadic(model).subscribe(new ChangeListener<ExerciseViewModel>() {

			@Override
			public void changed(ObservableValue<? extends ExerciseViewModel> observable, ExerciseViewModel oldValue,
					ExerciseViewModel newValue) {
				onModelChanged(newValue);
			}
		});
//		subscribe(model, this::onModelChanged);
	}
	
	public ObjectProperty<ExerciseViewModel> modelProperty() {
		return model;
	}
	

	public ExerciseViewModel getModel() {
		return modelProperty().get();
	}
	
	private void onModelChanged(ExerciseViewModel model) {
		lblId.textProperty().bind(model.idProperty());
		txtName.textProperty().bindBidirectional(model.nameProperty());
		txtDescription.textProperty().bindBidirectional(model.descriptionProperty());
		
		lblId.visibleProperty().bind(model.idVisibleProperty());
		lblId.managedProperty().bind(model.idVisibleProperty());
	}

	public void save(ActionEvent evt) {
		System.out.println("Saving " + model.toString());
	}

}
