package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.listBind;
import static org.fxmisc.easybind.EasyBind.map;
import static org.fxmisc.easybind.EasyBind.monadic;
import static org.fxmisc.easybind.EasyBind.select;
import static org.fxmisc.easybind.EasyBind.subscribe;

import java.io.IOException;

import org.fxmisc.easybind.Subscription;
import org.fxmisc.easybind.monadic.PropertyBinding;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class ExerciseListView extends BorderPane {
	@FXML
	protected ListView<ExerciseListView.Cell> listExercise;

	protected final ExerciseListViewModel model = new ExerciseListViewModel();

	private Subscription modelChanging;

	public ExerciseListView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise_list.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		listBind(listExercise.getItems(), map(model.getExercises(), i -> toCell(i)));
		model.selectedExerciseProperty().bind(monadic(listExercise.getSelectionModel().selectedItemProperty()).map(i -> i.model));
	}

	private Cell toCell(ExerciseModel exercise) {
		Cell result = new Cell(exercise);
		result.debugProperty().bind(model.debugProperty());
		return result;
	}

	public final net.d80harri.coach.ui.exercise.ExerciseListViewModel getModel() {
		return model;
	}

	public static class Cell extends BorderPane {
		protected final ExerciseModel model;
		protected final BooleanProperty debug = new SimpleBooleanProperty(this, "debug");

		protected final Label lblId;
		protected final Label lblName;
		protected final Label lblDescription;

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

			lblId.textProperty().bind(map(model.idProperty(), i -> i.toString()));
			lblId.visibleProperty().bind(debug);
			lblId.managedProperty().bind(debug);
			lblName.textProperty().bind(model.nameProperty());
			lblDescription.textProperty().bind(model.descriptionProperty());
		}

		public final BooleanProperty debugProperty() {
			return this.debug;
		}

		public final boolean isDebug() {
			return this.debugProperty().get();
		}

		public final void setDebug(final boolean debug) {
			this.debugProperty().set(debug);
		}

	}
}
