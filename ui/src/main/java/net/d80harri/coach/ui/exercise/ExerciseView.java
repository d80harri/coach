package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.map;
import static org.fxmisc.easybind.EasyBind.select;

import java.io.IOException;

import org.fxmisc.easybind.select.SelectBuilder;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import net.d80harri.coach.ui.conf.ConfigurationViewModel;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ExerciseView extends BorderPane {
	private DebugUtils debugUtils = new DebugUtils(this);
	
	@FXML Label lblId;
	@FXML TextField txtName;
	@FXML TextArea txtDescription;

	private final ExerciseModel model = new ExerciseModel();
	private ObjectProperty<ConfigurationViewModel> configModel;

	public ExerciseView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("exercise.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		bindModel();
		debugUtils.logChanges("configModel", configModel);
	}

	private void bindModel() {
		SelectBuilder<ConfigurationViewModel> configSelectionBuilder = select(configModelProperty());
		
		lblId.textProperty().bind(map(model.idProperty(), (i) -> i == null ? "null" : i.toString()));
		lblId.visibleProperty().bind(configSelectionBuilder.selectObject(i -> i.debugProperty()));
		lblId.managedProperty().bind(configSelectionBuilder.selectObject(i -> i.debugProperty()));
		Bindings.bindBidirectional(txtName.textProperty(), model.nameProperty());
		Bindings.bindBidirectional(txtDescription.textProperty(), model.descriptionProperty());
	}

	public ExerciseModel getModel() {
		return model;
	}

	public void save(ActionEvent evt) {
		System.out.println("Saving " + model.toString());
	}

	public final ObjectProperty<ConfigurationViewModel> configModelProperty() {
		if (configModel == null) {
			configModel = new SimpleObjectProperty<>(this, "configModel", new ConfigurationViewModel(false));
		}
		return this.configModel;
	}
	

	public final net.d80harri.coach.ui.conf.ConfigurationViewModel getConfigModel() {
		return this.configModelProperty().get();
	}
	

	public final void setConfigModel(final net.d80harri.coach.ui.conf.ConfigurationViewModel configModel) {
		this.configModelProperty().set(configModel);
	}
	
}