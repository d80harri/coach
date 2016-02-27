package net.d80harri.coach.ui.conf;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class ConfigurationView extends VBox {
	private CheckBox cbDebug;
	private ConfigurationModel model;

	public ConfigurationView() {
		this.model = new ConfigurationModel(false);
		
		cbDebug = new CheckBox("Debug");
		
		this.getChildren().add(cbDebug);
	}
	
	public void setModel(ConfigurationModel model) {
		this.model = model;
		bindModel();
	}

	private void bindModel() {
		cbDebug.selectedProperty().bindBidirectional(model.debugProperty());		
	}
}
