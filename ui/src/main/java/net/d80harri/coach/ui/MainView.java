package net.d80harri.coach.ui;

import java.io.IOException;

import org.fxmisc.easybind.EasyBind;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.conf.ConfigurationModel;
import net.d80harri.coach.ui.conf.ConfigurationView;
import net.d80harri.coach.ui.exercise.ExerciseListView;
import net.d80harri.coach.ui.exercise.ExerciseView;

public class MainView extends BorderPane {
	@FXML
	ConfigurationView configView;
	@FXML
	ExerciseView exerciseView;
	@FXML
	ExerciseListView exerciseListView;

	ObjectProperty<MainModel> model;

	public MainView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		initConfiguration();
		exerciseListView.modelProperty().bind(EasyBind.select(modelProperty()).selectObject(i -> i.visibleExercisesProperty()));
	}

	private void initConfiguration() {
		ConfigurationModel configModel = new ConfigurationModel(false);

		configView.setModel(configModel);
		exerciseView.setConfigModel(configModel);
		exerciseListView.setConfigModel(configModel);
	}

	public final ObjectProperty<MainModel> modelProperty() {
		if (model == null) {
			model = new SimpleObjectProperty<>(this, "model");
		}
		return this.model;
	}

	public final net.d80harri.coach.ui.MainModel getModel() {
		return this.modelProperty().get();
	}

	public final void setModel(final net.d80harri.coach.ui.MainModel model) {
		this.modelProperty().set(model);
	}

}
