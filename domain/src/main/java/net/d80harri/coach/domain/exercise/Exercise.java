package net.d80harri.coach.domain.exercise;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Exercise")
public class Exercise {
	@Id
	private UUID id;
	private String name;
	private String description;
	
	public Exercise(UUID id) {
		this.id = id;
	}
	
	public Exercise(UUID id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
