package net.d80harri.coach.ui.exercise;

import java.util.UUID;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ExerciseModel {
	private ObjectProperty<UUID> id = new SimpleObjectProperty<>();
	private StringProperty name = new SimpleStringProperty();
	private StringProperty description = new SimpleStringProperty();

	public ExerciseModel() {
	}

	public ExerciseModel(UUID id, String name, String description) {
		setId(id);
		setName(name);
		setDescription(description);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final java.lang.String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final java.lang.String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty descriptionProperty() {
		return this.description;
	}

	public final java.lang.String getDescription() {
		return this.descriptionProperty().get();
	}

	public final void setDescription(final java.lang.String description) {
		this.descriptionProperty().set(description);
	}

	@Override
	public String toString() {
		return "AtomicExerciseModel [name=" + name + ", description=" + description + "]";
	}

	public final ObjectProperty<UUID> idProperty() {
		return this.id;
	}

	public final java.util.UUID getId() {
		return this.idProperty().get();
	}

	public final void setId(final java.util.UUID id) {
		this.idProperty().set(id);
	}

}
