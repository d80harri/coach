package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.monadic;

import java.util.UUID;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ExerciseViewModel {
	private final ObjectProperty<ExerciseModel> exercise = new SimpleObjectProperty<>(this, "exercise");
	
	private final Property<String> name = monadic(exercise).selectProperty(i -> i.nameProperty());
	private final ObservableValue<String> id = monadic(exercise).selectProperty(i -> i.idProperty()).map(this::uuidToString);
	private final Property<String> description = monadic(exercise).selectProperty(i -> i.descriptionProperty());
	
	private final BooleanProperty idVisible = DebugUtils.log(this, "idVisible", new SimpleBooleanProperty());
	
	public ObjectProperty<ExerciseModel> exerciseProperty() {
		return exercise;
	}
	
	public ObservableValue<String> idProperty() {
		return id;
	}

	public Property<String> nameProperty() {
		return name;
	}

	public Property<String> descriptionProperty() {
		return description;
	}

	public BooleanProperty idVisibleProperty() {
		return idVisible;
	}

	public boolean isIdVisible() {
		return idVisibleProperty().get();
	}
	
	private String uuidToString(UUID uuid) {
		if (uuid == null) {
			return "null";
		} else {
			return uuid.toString();
		}
	}

}
