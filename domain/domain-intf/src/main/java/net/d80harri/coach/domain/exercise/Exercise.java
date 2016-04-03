package net.d80harri.coach.domain.exercise;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name="Exercise")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Exercise extends net.d80harri.coach.domain.repository.Entity{
	private String name;
	private String description;
	
	public Exercise() {
	}
	
	public Exercise(String id) {
		super(id);
	}
	
	public Exercise(String id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
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
