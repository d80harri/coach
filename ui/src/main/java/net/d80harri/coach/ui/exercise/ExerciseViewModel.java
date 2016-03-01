package net.d80harri.coach.ui.exercise;

import static org.fxmisc.easybind.EasyBind.map;
import static org.fxmisc.easybind.EasyBind.monadic;
import static org.fxmisc.easybind.EasyBind.select;

import java.util.UUID;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import net.d80harri.coach.ui.utils.DebugUtils;

public class ExerciseViewModel {
	private ObjectProperty<ExerciseModel> exercise = new SimpleObjectProperty<>(this, "exercise");
	
	private BooleanProperty idVisible = DebugUtils.log(this, "idVisible", new SimpleBooleanProperty());
	
	public ObservableValue<String> idProperty() {
		return map(select(exercise).selectObject(i -> i.idProperty()), this::uuidToString);
	}

	public Property<String> nameProperty() {
		return monadic(exercise).selectProperty(i -> i.nameProperty());
	}

	public Property<String> descriptionProperty() {
		return monadic(exercise).selectProperty(i -> i.descriptionProperty());
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
