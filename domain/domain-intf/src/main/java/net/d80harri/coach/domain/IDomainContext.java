package net.d80harri.coach.domain;

import net.d80harri.coach.domain.exercise.IExerciseRepository;

public interface IDomainContext {

	IExerciseRepository getExerciseRepository();
}
