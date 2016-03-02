package net.d80harri.coach.ui;

import static org.fxmisc.easybind.EasyBind.monadic;
import static org.fxmisc.easybind.EasyBind.select;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseListViewModel;
import net.d80harri.coach.ui.exercise.ExerciseViewModel;
import net.d80harri.coach.ui.utils.DebugUtils;

public class MainModel {

	private final ExerciseListViewModel exerciseList;
	private final ExerciseViewModel selectedExercise;
	private final ConfigurationViewModel config;

	public MainModel(ExerciseListViewModel exerciseList, ExerciseViewModel seletedExercise, ConfigurationViewModel config) {
		this.exerciseList = exerciseList;
		this.selectedExercise = seletedExercise;
		this.config = config;
		
		selectedExercise.idVisibleProperty().bind(config.debugProperty());
		exerciseList.debugProperty().bind(config.debugProperty());
	}
	
	public ExerciseViewModel getSelectedExercise() {
		return selectedExercise;
	}

	public final net.d80harri.coach.ui.conf.ConfigurationViewModel getConfig() {
		return this.config;
	}

	public final ExerciseListViewModel getExerciseList() {
		return this.exerciseList;
	}

}
