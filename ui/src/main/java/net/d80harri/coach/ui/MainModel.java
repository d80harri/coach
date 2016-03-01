package net.d80harri.coach.ui;

import static org.fxmisc.easybind.EasyBind.monadic;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseListViewModel;
import net.d80harri.coach.ui.exercise.ExerciseViewModel;
import net.d80harri.coach.ui.utils.DebugUtils;

public class MainModel {
	private final DebugUtils debugUtils = new DebugUtils(this);

	private final ObjectProperty<ExerciseListViewModel> exerciseList = new SimpleObjectProperty<>(this, "exercises");
	private final ObjectProperty<ExerciseViewModel> selectedExercise = new SimpleObjectProperty<>(this, "selectedExercise");
	private final ObjectProperty<ConfigurationViewModel> config = new SimpleObjectProperty<>(this, "config", new ConfigurationViewModel());

	public MainModel() {
		debugUtils.logChanges("config", config);
	}

	public ObjectProperty<ExerciseViewModel> selectedExerciseProperty() {
		return selectedExercise;
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

	private Property<Boolean> debug;

	public final Property<Boolean> debugProperty() {
		if (debug == null) {
			debug = monadic(config).selectProperty(i -> i.debugProperty());
		}
		return debug;
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
