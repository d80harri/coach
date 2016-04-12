package net.d80harri.coach.domain.exercise;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Timing {
	private int value;
	private TimingUnit unit;

	public Timing() {
	}
	
	public Timing(int value, TimingUnit unit) {
		this.value = value;
		this.unit = unit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Enumerated(EnumType.STRING)
	public TimingUnit getUnit() {
		return unit;
	}
	
	public void setUnit(TimingUnit unit) {
		this.unit = unit;
	}
}
