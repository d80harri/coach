package net.d80harri.coach.ui;

import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseListViewModel;
import net.d80harri.coach.ui.exercise.ExerciseViewModel;

public class MainModel {

	private final ExerciseListViewModel exerciseList;
	private final ExerciseViewModel selectedExercise;
	private final ConfigurationViewModel config;

	public MainModel(ExerciseListViewModel exerciseList, ExerciseViewModel seletedExercise, ConfigurationViewModel config) {
		this.exerciseList = exerciseList;
		this.selectedExercise = seletedExercise;
		this.config = config;
		
		selectedExercise.idVisibleProperty().bind(config.debugProperty());
		selectedExercise.exerciseProperty().bind(exerciseList.selectedExerciseProperty());
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
