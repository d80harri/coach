package net.d80harri.coach.domain;

import java.util.UUID;

public class Entity {
	private UUID uuid;
	
	public Entity() {}
	
	public Entity(UUID uuid) {
		this.uuid = uuid;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
