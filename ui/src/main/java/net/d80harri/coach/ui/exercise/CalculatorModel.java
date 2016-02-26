package net.d80harri.coach.ui.exercise;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableNumberValue;

public class CalculatorModel {

	private IntegerProperty first = new SimpleIntegerProperty();
	private IntegerProperty second = new SimpleIntegerProperty();
	private ObservableNumberValue sum;

	public CalculatorModel() {
		sum = Bindings.add(firstProperty(), secondProperty());
	}

	public final IntegerProperty firstProperty() {
		return this.first;
	}
	
	public final int getFirst() {
		return this.firstProperty().get();
	}
	
	public final void setFirst(final int first) {
		this.firstProperty().set(first);
	}
	
	public final IntegerProperty secondProperty() {
		return this.second;
	}
	
	public final int getSecond() {
		return this.secondProperty().get();
	}
	
	public final void setSecond(final int second) {
		this.secondProperty().set(second);
	}
	
	public final ObservableNumberValue getSum() {
		return this.sum;
	}
	
	
}
