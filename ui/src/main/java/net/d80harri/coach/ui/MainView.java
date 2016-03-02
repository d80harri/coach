package net.d80harri.coach.ui;

import java.io.IOException;

import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.conf.ConfigurationView;
import net.d80harri.coach.ui.exercise.ExerciseListView;
import net.d80harri.coach.ui.exercise.ExerciseView;

public class MainView extends BorderPane {
	
	@FXML ConfigurationView configView;
	@FXML ExerciseView exerciseView;
	@FXML ExerciseListView exerciseListView;

	final MainModel model;

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
		
		this.model = new MainModel(exerciseListView.getModel(), exerciseView.getModel(), configView.getModel());
	}

	public final MainModel getModel() {
		return this.model;
	}

}
