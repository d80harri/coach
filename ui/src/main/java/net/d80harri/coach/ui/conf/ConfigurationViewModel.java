package net.d80harri.coach.ui.conf;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ConfigurationViewModel {
	private final DebugUtils debugUtils = new DebugUtils(this);
	private final BooleanProperty debug = new SimpleBooleanProperty(false);

	public ConfigurationViewModel() {
		debugUtils.logChanges("debug", debug);
	}
	
	public ConfigurationViewModel(boolean debug) {
		this();
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
