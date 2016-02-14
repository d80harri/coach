package net.d80harri.coach.domain;

import net.d80harri.coach.domain.program.Program;
import net.d80harri.coach.domain.program.entities.Action;

public class DtoAssertions {

	public static ProgramAssert assertThat(Program dto) {
		return new ProgramAssert(dto);
	}

	public static ActionAssert assertThat(Action action) { 
		return new ActionAssert(action);
	}

}
