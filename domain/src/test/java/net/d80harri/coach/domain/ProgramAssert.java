package net.d80harri.coach.domain;

import java.util.UUID;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.program.Action;
import net.d80harri.coach.domain.program.Program;

public class ProgramAssert extends DtoAssert<ProgramAssert, Program> {

	protected ProgramAssert(Program actual) {
		super(actual, ProgramAssert.class);
	}

	public ProgramAssert hasActionId(UUID uuid) {
		actionIsNotNull(actual.getAction());
		Assertions.assertThat(actual.getAction().getUuid()).describedAs("action id", uuid).isEqualTo(uuid);
		return this;
	}

	public ProgramAssert actionIsNotNull(Action action) {
		Assertions.assertThat(actual.getAction()).isNotNull();
		return this;
	}

}
