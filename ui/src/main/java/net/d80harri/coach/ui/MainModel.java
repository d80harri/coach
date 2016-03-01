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
	private final DebugUtils debugUtils = new DebugUtils(this);

	private final ObjectProperty<ExerciseListViewModel> exerciseList = new SimpleObjectProperty<>(this, "exercises", new ExerciseListViewModel());
	private final ObjectProperty<ExerciseViewModel> selectedExercise = new SimpleObjectProperty<>(this, "selectedExercise", new ExerciseViewModel());
	private final ObjectProperty<ConfigurationViewModel> config = new SimpleObjectProperty<>(this, "config", new ConfigurationViewModel());

	public MainModel() {
		debugUtils.logChanges("config", config);
		
		
		monadic(selectedExercise).selectProperty(i -> i.idVisibleProperty()).bind(select(config).selectObject(i -> i.debugProperty()));
	}

	public ObjectProperty<ExerciseViewModel> selectedExerciseProperty() {
		return selectedExercise;
	}
	
	public ExerciseViewModel getSelectedExercise() {
		return selectedExercise.get();
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

	public final ObjectProperty<ExerciseListViewModel> exerciseListProperty() {
		return this.exerciseList;
	}

	public final net.d80harri.coach.ui.exercise.ExerciseListViewModel getExerciseList() {
		return this.exerciseListProperty().get();
	}

	public final void setExerciseList(final net.d80harri.coach.ui.exercise.ExerciseListViewModel exerciseList) {
		this.exerciseListProperty().set(exerciseList);
	}

}
