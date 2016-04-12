package net.d80harri.coach.domain.exercise;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="FlowItem")
public class FlowItem extends net.d80harri.coach.domain.repository.Entity {
	
	private Flow flow;
	private AtomicExercise exercise;
	private Timing timing;
	
	@ManyToOne
	@JoinColumn(name="flow_id")
	public Flow getFlow() {
		return flow;
	}
	
	public void setFlow(Flow flow) {
		this.flow = flow;
	}
	
	@Embedded
	public Timing getTiming() {
		return timing;
	}
	public void setTiming(Timing timing) {
		this.timing = timing;
	}
	
	@ManyToOne
	@JoinColumn(name="exercise_id")
	public AtomicExercise getExercise() {
		return exercise;
	}
	public void setExercise(AtomicExercise exercise) {
		this.exercise = exercise;
	}
}
