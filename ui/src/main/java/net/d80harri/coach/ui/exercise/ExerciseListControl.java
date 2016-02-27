package net.d80harri.coach.ui.exercise;

import java.io.IOException;

import org.fxmisc.easybind.EasyBind;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ExerciseListControl extends BorderPane {
	@FXML
	private ListView<ExerciseListControl.Cell> listExercise;
	
	private ObservableList<ExerciseModel> model = FXCollections.observableArrayList();

	public ExerciseListControl() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise_list.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		
		bindModel();
	}
	
	public void setModel(ObservableList<ExerciseModel> model) {
		this.model = model;
		bindModel();
	}

	public ObservableList<ExerciseModel> getModel() {
		return model;
	}
	
	private void bindModel() {
		listExercise.setItems(EasyBind.map(model, i -> new Cell(i)));
	}
	
	private static class Cell extends BorderPane {
		private ExerciseModel model;
		
		private Label lblId;
		private Label lblName;
		private Label lblDescription;
		
		public Cell(ExerciseModel model) {
			this.model = model;
			
			HBox hbox = new HBox();
			lblId = new Label();
			lblName = new Label();
			lblDescription = new Label();
			
			hbox.getChildren().add(lblId);
			hbox.getChildren().add(lblName);
			
			this.setTop(hbox);
			this.setCenter(lblDescription);
			
			lblId.textProperty().bind(EasyBind.map(model.idProperty(), i -> i.toString()));
			lblName.textProperty().bind(model.nameProperty());
			lblDescription.textProperty().bind(model.descriptionProperty());
		}
		
	}
}
