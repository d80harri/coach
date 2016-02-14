package net.d80harri.coach.domain.program;

import net.d80harri.coach.domain.Dto;
import net.d80harri.coach.domain.program.entities.Action;

public class Program extends Dto {
	private String name;
	private Action action;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
}
