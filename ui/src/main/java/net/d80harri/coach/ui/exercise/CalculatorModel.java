package net.d80harri.coach.ui.exercise;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.converter.NumberStringConverter;

public class CalculatorModel {

	private IntegerProperty first = new SimpleIntegerProperty();
	private IntegerProperty second = new SimpleIntegerProperty();
	private IntegerProperty sum = new SimpleIntegerProperty();
	
	public CalculatorModel(ExerciseControl exerciseControl) {
		Bindings.bindBidirectional(exerciseControl.txtFirst.textProperty(), first, new NumberStringConverter());
		Bindings.bindBidirectional(exerciseControl.txtSecond.textProperty(), second, new NumberStringConverter());
		Bindings.bindBidirectional(exerciseControl.txtSum.textProperty(), sum, new NumberStringConverter());
		
		sum.bind(first.add(second));
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
	
	public final IntegerProperty sumProperty() {
		return this.sum;
	}
	
	public final int getSum() {
		return this.sumProperty().get();
	}
	
	public final void setSum(final int sum) {
		this.sumProperty().set(sum);
	}
	
}
