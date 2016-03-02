package net.d80harri.coach.ui.exercise;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExerciseListViewModel {
	private final ObservableList<ExerciseModel> exercises = FXCollections.observableArrayList();
	private final BooleanProperty debug = new SimpleBooleanProperty();
	private final ObjectProperty<ExerciseModel> selectedExercise = new SimpleObjectProperty<>(this, "selectedExercise");

	public ExerciseListViewModel() {
	}

	public final javafx.collections.ObservableList<net.d80harri.coach.ui.exercise.ExerciseModel> getExercises() {
		return this.exercises;
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

	public final ObjectProperty<ExerciseModel> selectedExerciseProperty() {
		return this.selectedExercise;
	}

	public final ExerciseModel getSelectedExercise() {
		return this.selectedExerciseProperty().get();
	}

	public final void setSelectedExercise(final ExerciseModel selectedExercise) {
		this.selectedExerciseProperty().set(selectedExercise);
	}

}
