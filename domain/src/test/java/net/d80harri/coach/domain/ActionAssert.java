package net.d80harri.coach.domain;

import net.d80harri.coach.domain.program.Action;

public class ActionAssert extends AbstractActionAssert<ActionAssert, Action>{

	protected ActionAssert(Action actual) {
		super(actual, ActionAssert.class);
	}

}
