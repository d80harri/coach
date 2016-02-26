package net.d80harri.coach.ui.exercise;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AtomicExerciseModel {
	private StringProperty name = new SimpleStringProperty();
	private StringProperty descripton = new SimpleStringProperty();

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final java.lang.String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final java.lang.String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty descriptonProperty() {
		return this.descripton;
	}

	public final java.lang.String getDescripton() {
		return this.descriptonProperty().get();
	}

	public final void setDescripton(final java.lang.String descripton) {
		this.descriptonProperty().set(descripton);
	}

	@Override
	public String toString() {
		return "AtomicExerciseModel [name=" + name + ", descripton=" + descripton + "]";
	}
}
