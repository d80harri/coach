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
import net.d80harri.coach.ui.conf.ConfigurationModel;

public class ExerciseListControl extends BorderPane {
	@FXML
	private ListView<ExerciseListControl.Cell> listExercise;
	
	private ObservableList<ExerciseModel> model = FXCollections.observableArrayList();
	private final ConfigurationModel configModel;
	
	public ExerciseListControl(ConfigurationModel configModel) {
		this.configModel = configModel;
		
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
		listExercise.setItems(EasyBind.map(model, i -> new Cell(i, configModel)));
	}
	
	private static class Cell extends BorderPane {
		private final ExerciseModel model;
		private final ConfigurationModel configModel;
		
		private final Label lblId;
		private final Label lblName;
		private final Label lblDescription;
		
		public Cell(ExerciseModel model, ConfigurationModel configModel) {
			this.model = model;
			this.configModel = configModel;
			
			HBox hbox = new HBox();
			lblId = new Label();
			lblName = new Label();
			lblDescription = new Label();
			
			hbox.getChildren().add(lblId);
			hbox.getChildren().add(lblName);
			
			this.setTop(hbox);
			this.setCenter(lblDescription);
			
			lblId.textProperty().bind(EasyBind.map(model.idProperty(), i -> i.toString()));
			lblId.visibleProperty().bind(configModel.debugProperty());
			lblId.managedProperty().bind(configModel.debugProperty());
			lblName.textProperty().bind(model.nameProperty());
			lblDescription.textProperty().bind(model.descriptionProperty());
		}
		
	}
}
