package net.d80harri.coach.rest.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Timing {
	@JsonProperty
	private int value;
	@JsonProperty
	private TimingUnit timingUnit;

	public Timing(int value, TimingUnit timingUnit) {
		this.value = value;
		this.timingUnit = timingUnit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
