package net.d80harri.coach.ui;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseModel;
import net.d80harri.coach.ui.utils.DebugUtils;

public class MainModel {
	private final DebugUtils debugUtils = new DebugUtils(this);
	
	private final ObservableList<ExerciseModel> visibleExercises = FXCollections.observableArrayList();
	private final ObjectProperty<ConfigurationViewModel> config = new SimpleObjectProperty<>(this, "config");
	
	public MainModel() {
		debugUtils.logChanges("config", config);
	}
	
    
    public ObservableList<ExerciseModel> getVisibleExercises() {
		return visibleExercises;
	}
    
	public final ObjectProperty<ConfigurationViewModel> configProperty() {
		return this.config;
	}
	

	public final net.d80harri.coach.ui.conf.ConfigurationViewModel getConfig() {
		return this.configProperty().get();
	}
	

	public final void setConfig(final net.d80harri.coach.ui.conf.ConfigurationViewModel config) {
		this.configProperty().set(config);
	}
	
	
	
	
}