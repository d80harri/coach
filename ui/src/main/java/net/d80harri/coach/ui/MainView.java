package net.d80harri.coach.ui;

import static org.fxmisc.easybind.EasyBind.monadic;
import static org.fxmisc.easybind.EasyBind.select;
import static org.fxmisc.easybind.EasyBind.subscribe;

import java.io.IOException;

import org.fxmisc.easybind.EasyBind;
import org.fxmisc.easybind.Subscription;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private Subscription ssc_model;
	

	public MainView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		
		configView.modelProperty().bindBidirectional(monadic(modelProperty()).selectProperty(i->i.configProperty()));
		exerciseView.modelProperty().bindBidirectional(monadic(modelProperty()).selectProperty(i->i.selectedExerciseProperty()));
		exerciseListView.modelProperty().bindBidirectional(monadic(modelProperty()).selectProperty(i->i.exerciseListProperty()));
				
		debutUtils.logChanges("model", modelProperty());
	}

	public final ObjectProperty<MainModel> modelProperty() {
		if (model == null) {
			model = new SimpleObjectProperty<>(this, "model", new MainModel());
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
