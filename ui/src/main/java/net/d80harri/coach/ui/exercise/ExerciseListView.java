package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.listBind;
import static org.fxmisc.easybind.EasyBind.map;

import java.io.IOException;

import org.fxmisc.easybind.Subscription;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;

public class ExerciseListView extends BorderPane {
	@FXML
	protected ListView<ExerciseListView.Cell> listExercise;

	private final ObservableList<ExerciseModel> model = FXCollections.observableArrayList();
	private ConfigurationViewModel configModel;
	
	private Subscription ssc_exerciseList;

	public ExerciseListView() {
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

	public void setConfigModel(ConfigurationViewModel configModel) {
		this.configModel = configModel;
		bindModel();
	}

	private void bindModel() {
		ssc_exerciseList = listBind(listExercise.getItems(), 
				map(model, i -> new Cell(i, configModel)));
	}

	public final javafx.collections.ObservableList<net.d80harri.coach.ui.exercise.ExerciseModel> getModel() {
		return this.model;
	}

	private static class Cell extends BorderPane {
		private final ExerciseModel model;
		private final ConfigurationViewModel configModel;

		private final Label lblId;
		private final Label lblName;
		private final Label lblDescription;

		public Cell(ExerciseModel model, ConfigurationViewModel configModel) {
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

			lblId.textProperty().bind(map(model.idProperty(), i -> i.toString()));
//			lblId.visibleProperty().bind(configModel.debugProperty());
//			lblId.managedProperty().bind(configModel.debugProperty());
			lblName.textProperty().bind(model.nameProperty());
			lblDescription.textProperty().bind(model.descriptionProperty());
		}

	}
}
