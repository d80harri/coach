package net.d80harri.coach.domain.exercise;

import javax.persistence.Entity;

@Entity(name = "AtomicExercise")
public class AtomicExercise extends Exercise {

	public AtomicExercise() {
		super();
	}
	
	public AtomicExercise(String id, String name, String description) {
		super(id, name, description);
	}

}
