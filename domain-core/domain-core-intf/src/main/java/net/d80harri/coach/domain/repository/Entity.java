package net.d80harri.coach.domain.repository;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@javax.persistence.Entity(name="Entity")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Entity {
	private String id;

	public Entity() {
	}

	public Entity(String id) {
		this.id = id;
	}

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
