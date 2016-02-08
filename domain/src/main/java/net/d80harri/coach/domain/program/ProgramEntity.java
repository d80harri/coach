package net.d80harri.coach.domain.program;

import java.util.UUID;

import net.d80harri.coach.domain.Entity;

public class ProgramEntity extends Entity {
	private String name;
	
	public ProgramEntity() {
	}

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

}
