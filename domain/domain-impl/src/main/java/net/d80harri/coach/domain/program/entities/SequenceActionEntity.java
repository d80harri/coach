package net.d80harri.coach.domain.program.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SequenceActionEntity extends ActionEntity {
	private List<ActionEntity> sequence = new ArrayList<>();
	
	public SequenceActionEntity(UUID uuid) {
		super(uuid);
	}

	public List<ActionEntity> getSequence() {
		return sequence;
	}

}
