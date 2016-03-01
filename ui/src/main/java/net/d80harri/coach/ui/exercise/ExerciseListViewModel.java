package net.d80harri.coach.ui.exercise;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExerciseListViewModel {
	private final ObservableList<ExerciseModel> exercises = FXCollections.observableArrayList();
	private final BooleanProperty debug = new SimpleBooleanProperty();
	
	public ExerciseListViewModel(){}
	
	public ExerciseListViewModel(List<ExerciseModel> exercises, boolean debug) {
		super();
		exercises = FXCollections.observableArrayList(exercises);
		setDebug(debug);
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
	
		
}
