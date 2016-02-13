package net.d80harri.coach.domain;

import java.util.UUID;

public class Entity {
	private final UUID uuid;
	
	public Entity(UUID uuid) {
		this.uuid = uuid;
	}
	
	public UUID getUuid() {
		return uuid;
	}
}
