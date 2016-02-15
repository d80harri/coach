package net.d80harri.coach.domain;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.program.Program;
import net.d80harri.coach.domain.program.entities.Action;

public class DtoAssertions extends Assertions {

	public static ProgramAssert assertThat(Program dto) {
		return new ProgramAssert(dto);
	}

	public static ActionAssert assertThat(Action action) { 
		return new ActionAssert(action);
	}

}
