package net.d80harri.coach.domain.program;

import java.util.ArrayList;
import java.util.List;

import net.d80harri.coach.domain.program.entities.Action;

public class SequenceAction extends Action {
	private List<Action> sequence = new ArrayList<>();
	
	public List<Action> getSequence() {
		return sequence;
	}

	public void setSequence(List<Action> sequence) {
		this.sequence = sequence;
	}
}
