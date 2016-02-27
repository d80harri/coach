package net.d80harri.coach.ui.conf;

import static org.fxmisc.easybind.EasyBind.*;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ConfigurationView extends VBox {
	private DebugUtils debugUtils = new DebugUtils(this);
	
	private CheckBox cbDebug;
	private ObjectProperty<ConfigurationModel> model = new SimpleObjectProperty<>(this, "model");

	public ConfigurationView() {
		cbDebug = new CheckBox("Debug");

		this.getChildren().add(cbDebug);
		
		cbDebug.selectedProperty().bindBidirectional(monadic(modelProperty()).selectProperty(i -> i.debugProperty()));
		
		debugUtils.logChanges("model", model);
		debugUtils.logChanges("cbDebug.selected", cbDebug.selectedProperty());
	}

	public final ObjectProperty<ConfigurationModel> modelProperty() {
		return this.model;
	}

	public final net.d80harri.coach.ui.conf.ConfigurationModel getModel() {
		return this.modelProperty().get();
	}

	public final void setModel(final net.d80harri.coach.ui.conf.ConfigurationModel model) {
		this.modelProperty().set(model);
	}

}
