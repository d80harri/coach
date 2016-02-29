package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.listBind;
import static org.fxmisc.easybind.EasyBind.map;

import java.io.IOException;

import org.fxmisc.easybind.Subscription;

import javafx.beans.binding.BooleanExpression;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ExerciseListView extends BorderPane {
	@FXML
	protected ListView<ExerciseListView.Cell> listExercise;

	protected final ExerciseListViewModel model;

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

		this.model = new ExerciseListViewModel();
		ssc_exerciseList = listBind(listExercise.getItems(),
				map(model.getExercises(), i -> new Cell(i, model.debugProperty())));
	}

	public ExerciseListViewModel getModel() {
		return model;
	}

	private static class Cell extends BorderPane {
		protected final ExerciseModel model;
		protected final BooleanExpression debug;

		protected final Label lblId;
		protected final Label lblName;
		protected final Label lblDescription;

		public Cell(ExerciseModel model, BooleanExpression debug) {
			this.model = model;
			this.debug = debug;

			HBox hbox = new HBox();
			lblId = new Label();
			lblName = new Label();
			lblDescription = new Label();

			hbox.getChildren().add(lblId);
			hbox.getChildren().add(lblName);

			this.setTop(hbox);
			this.setCenter(lblDescription);

			lblId.textProperty().bind(map(model.idProperty(), i -> i.toString()));
			lblId.visibleProperty().bind(debug);
			lblId.managedProperty().bind(debug);
			lblName.textProperty().bind(model.nameProperty());
			lblDescription.textProperty().bind(model.descriptionProperty());
		}

	}
}
