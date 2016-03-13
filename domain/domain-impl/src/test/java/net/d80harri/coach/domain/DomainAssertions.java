package net.d80harri.coach.domain;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseAssert;
import net.d80harri.coach.domain.program.Action;
import net.d80harri.coach.domain.program.Program;

public class DomainAssertions extends Assertions {

	public static ProgramAssert assertThat(Program dto) {
		return new ProgramAssert(dto);
	}

	public static ActionAssert assertThat(Action action) { 
		return new ActionAssert(action);
	}

	public static ExerciseAssert assertThat(Exercise exercise) {
		return new ExerciseAssert(exercise);
	}
}
