package net.d80harri.coach.ui.conf;

import static org.fxmisc.easybind.EasyBind.*;

import org.fxmisc.easybind.monadic.PropertyBinding;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ConfigurationView extends VBox {
	private DebugUtils debugUtils = new DebugUtils(this);
	
	CheckBox cbDebug;
	private ObjectProperty<ConfigurationViewModel> model = new SimpleObjectProperty<>(this, "model");
	PropertyBinding<Boolean> debug = monadic(modelProperty()).selectProperty(i -> i.debugProperty());

	public ConfigurationView() {
		cbDebug = new CheckBox("Debug");

		this.getChildren().add(cbDebug);
		
		cbDebug.selectedProperty().bindBidirectional(debug);
		
		debugUtils.logChanges("model", model);
		debugUtils.logChanges("cbDebug.selected", cbDebug.selectedProperty());
	}

	public final ObjectProperty<ConfigurationViewModel> modelProperty() {
		return this.model;
	}

	public final net.d80harri.coach.ui.conf.ConfigurationViewModel getModel() {
		return this.modelProperty().get();
	}

	public final void setModel(final net.d80harri.coach.ui.conf.ConfigurationViewModel model) {
		this.modelProperty().set(model);
	}

}
