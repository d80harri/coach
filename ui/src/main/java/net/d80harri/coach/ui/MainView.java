package net.d80harri.coach.ui;

import static org.fxmisc.easybind.EasyBind.monadic;

import java.io.IOException;

import org.fxmisc.easybind.EasyBind;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.conf.ConfigurationView;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.exercise.ExerciseListView;
import net.d80harri.coach.ui.exercise.ExerciseView;
import net.d80harri.coach.ui.utils.DebugUtils;

public class MainView extends BorderPane {
	private final DebugUtils debutUtils = new DebugUtils(this);
	
	@FXML ConfigurationView configView;
	@FXML ExerciseView exerciseView;
	@FXML ExerciseListView exerciseListView;

	ObjectProperty<MainModel> model;

	Property<Boolean> debug;
	

	public MainView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		debug = monadic(modelProperty()).selectProperty(i -> i.debugProperty());
		exerciseListView.modelProperty().bindBidirectional(monadic(modelProperty()).selectProperty(i -> i.exerciseListProperty()));
		debug.bindBidirectional(exerciseListView.getModel().debugProperty());
		
		exerciseView.configModelProperty().bindBidirectional(EasyBind.monadic(modelProperty()).selectProperty(i -> i.configProperty()));
		configView.modelProperty().bindBidirectional(EasyBind.monadic(modelProperty()).selectProperty(i -> i.configProperty()));
		
		setModel(new MainModel());
		ConfigurationViewModel config = new ConfigurationViewModel(true);
		getModel().setConfig(config);
		
		debutUtils.logChanges("model", modelProperty());
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
