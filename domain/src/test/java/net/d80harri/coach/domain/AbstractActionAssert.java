package net.d80harri.coach.domain;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.program.Action;
import net.d80harri.coach.domain.program.SequenceAction;

public abstract class AbstractActionAssert<M extends AbstractActionAssert<M, T>, T extends Action> extends DtoAssert<M, T>{

	protected AbstractActionAssert(T actual, Class<? extends M> selfType) {
		super(actual, selfType);
	}
	
	public SequenceActionAssert isSequenceAction() {
		Assertions.assertThat(actual).isInstanceOf(SequenceAction.class);
		return new SequenceActionAssert((SequenceAction)actual);
	}

}
