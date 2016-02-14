package net.d80harri.coach.domain.program.entities;

import java.util.UUID;

import net.d80harri.coach.domain.Entity;

public abstract class ActionEntity extends Entity implements IActionParent {

	public ActionEntity(UUID uuid) {
		super(uuid);
	}

}
