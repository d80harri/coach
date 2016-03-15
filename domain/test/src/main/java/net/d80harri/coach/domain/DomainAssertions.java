package net.d80harri.coach.domain;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseAssert;

public class DomainAssertions extends Assertions {

	public static ExerciseAssert assertThat(Exercise exercise) {
		return new ExerciseAssert(exercise);
	}
}
