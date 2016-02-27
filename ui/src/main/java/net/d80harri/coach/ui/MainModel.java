package net.d80harri.coach.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.d80harri.coach.ui.conf.ConfigurationModel;
import net.d80harri.coach.ui.exercise.ExerciseModel;

public class MainModel {
	private ObjectProperty<ObservableList<ExerciseModel>> visibleExercises;
	private ObjectProperty<ConfigurationModel> config = new SimpleObjectProperty<>(this, "config");
	
	public MainModel() {
	}
	
    public final ObjectProperty<ObservableList<ExerciseModel>> visibleExercisesProperty() {
        if (visibleExercises == null) {
            visibleExercises = new SimpleObjectProperty<>(this, "items", FXCollections.observableArrayList());
        }
        return visibleExercises;
    }
    
    public ObservableList<ExerciseModel> getVisibleExercises() {
		return visibleExercisesProperty().get();
	}
    
    public void setVisibleExercises(ObservableList<ExerciseModel> visibleExercises) {
		this.visibleExercisesProperty().set(visibleExercises);
	}

	public final ObjectProperty<ConfigurationModel> configProperty() {
		return this.config;
	}
	

	public final net.d80harri.coach.ui.conf.ConfigurationModel getConfig() {
		return this.configProperty().get();
	}
	

	public final void setConfig(final net.d80harri.coach.ui.conf.ConfigurationModel config) {
		this.configProperty().set(config);
	}
	
	
	
	
}
