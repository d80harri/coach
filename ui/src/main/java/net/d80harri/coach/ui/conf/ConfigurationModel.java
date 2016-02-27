package net.d80harri.coach.ui.conf;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class ConfigurationModel {
	private BooleanProperty debug = new SimpleBooleanProperty(false);

	public ConfigurationModel(boolean debug) {
		setDebug(debug);
	}
	
	public final BooleanProperty debugProperty() {
		return this.debug;
	}

	public final boolean isDebug() {
		return this.debugProperty().get();
	}

	public final void setDebug(final boolean debug) {
		this.debugProperty().set(debug);
	}

}
