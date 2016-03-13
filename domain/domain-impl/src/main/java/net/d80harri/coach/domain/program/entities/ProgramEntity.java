package net.d80harri.coach.domain.program.entities;

import java.util.UUID;

import net.d80harri.coach.domain.Entity;

public class ProgramEntity extends Entity implements IActionParent {
	private String name;
	private ActionEntity action;

	public ProgramEntity(UUID uuid, String name) {
		super(uuid);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionEntity getAction() {
		return action;
	}

	public void setAction(ActionEntity action) {
		this.action = action;
	}

}
