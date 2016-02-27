package net.d80harri.coach.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import net.d80harri.coach.ui.exercise.ExerciseModel;

public class MainModel {
	private ObjectProperty<ObservableList<ExerciseModel>> visibleExercises;
	
	public MainModel() {
	}
	
    public final ObjectProperty<ObservableList<ExerciseModel>> visibleExercisesProperty() {
        if (visibleExercises == null) {
            visibleExercises = new SimpleObjectProperty<>(this, "items");
        }
        return visibleExercises;
    }
    
    public ObservableList<ExerciseModel> getVisibleExercises() {
		return visibleExercisesProperty().get();
	}
    
    public void setVisibleExercises(ObservableList<ExerciseModel> visibleExercises) {
		this.visibleExercisesProperty().set(visibleExercises);
	}
	
	
	
}
