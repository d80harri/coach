package net.d80harri.coach.ui;

import java.io.IOException;

import org.fxmisc.easybind.EasyBind;
import org.fxmisc.easybind.select.SelectBuilder;

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

		exerciseListView.modelProperty().bindBidirectional(EasyBind.monadic(modelProperty()).selectProperty(i -> i.visibleExercisesProperty()));
		exerciseView.configModelProperty().bindBidirectional(EasyBind.monadic(modelProperty()).selectProperty(i -> i.configProperty()));
		configView.modelProperty().bindBidirectional(EasyBind.monadic(modelProperty()).selectProperty(i -> i.configProperty()));
		
		modelProperty().addListener((obs, o, n)-> System.out.println("MainView#model: " + n));
		EasyBind.monadic(modelProperty()).selectProperty(i -> i.configProperty()).addListener((obs, o, n) -> System.out.println("MainView#model.config: " + n));
		configView.modelProperty().addListener((obs, o, n) -> System.out.println("MainView#configView.model: " + n));
		
		setModel(new MainModel());
		ConfigurationModel config = new ConfigurationModel(true);
		getModel().setConfig(config);
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
