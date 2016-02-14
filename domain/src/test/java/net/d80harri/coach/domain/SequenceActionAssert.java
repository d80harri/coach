package net.d80harri.coach.domain;

import java.util.UUID;

import org.assertj.core.api.Assertions;

import net.d80harri.coach.domain.program.SequenceAction;

public class SequenceActionAssert extends AbstractActionAssert<SequenceActionAssert, SequenceAction> {

	protected SequenceActionAssert(SequenceAction actual) {
		super(actual, SequenceActionAssert.class);
	}

	public SequenceActionAssert containsExactlyActionIds(UUID... uuid) {
		Assertions.assertThat(actual.getSequence()).extracting("uuid").containsExactly((Object[])uuid);
		return this;
	}

}
