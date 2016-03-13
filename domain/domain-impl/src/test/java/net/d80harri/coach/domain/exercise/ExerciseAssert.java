package net.d80harri.coach.domain.exercise;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractObjectAssert;

public class ExerciseAssert extends AbstractObjectAssert<ExerciseAssert, Exercise> {

	public ExerciseAssert(Exercise actual) {
		super(actual, ExerciseAssert.class);
	}

	public ExerciseAssert hasId(String id) {
		assertThat(actual.getId()).isEqualTo(id);
		return this;
	}

	public ExerciseAssert hasName(String name) {
		assertThat(actual.getName()).isEqualTo(name);
		return this;
	}

	public ExerciseAssert hasDescription(String description) {
		assertThat(actual.getDescription()).isEqualTo(description);
		return this;
	}

	

}
