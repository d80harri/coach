package net.d80harri.coach.ui.conf;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ConfigurationView extends VBox {
	private ConfigurationModel model;

	public ConfigurationView(ConfigurationModel model) {
		this.model = model;
		
		CheckBox cbDebug = new CheckBox("Debug");
		cbDebug.selectedProperty().bindBidirectional(model.debugProperty());
		
		this.getChildren().add(cbDebug);
	}
}
