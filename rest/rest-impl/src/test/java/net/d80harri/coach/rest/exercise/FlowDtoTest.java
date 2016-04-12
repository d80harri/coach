package net.d80harri.coach.rest.exercise;

import net.d80harri.coach.domain.exercise.Timing;
import net.d80harri.coach.domain.exercise.TimingUnit;
import net.d80harri.coach.rest.exercise.atomic.AtomicExerciseDto;
import net.d80harri.coach.rest.exercise.flow.FlowDto;
import net.d80harri.coach.rest.exercise.flow.FlowItemDto;

public class FlowDtoTest {

	public void simpleStructure() {
		FlowDto torsoFlow = new FlowDto("TorsoFlow");

		AtomicExerciseDto hollowBody = new AtomicExerciseDto();
		AtomicExerciseDto yoga = new AtomicExerciseDto();
		AtomicExerciseDto forwardBend = new AtomicExerciseDto();
		AtomicExerciseDto bridge = new AtomicExerciseDto();

		torsoFlow.getItems().add(new FlowItemDto(hollowBody, new Timing(10, TimingUnit.BREATH)));
		torsoFlow.getItems().add(new FlowItemDto(yoga, new Timing(10, TimingUnit.BREATH)));
		torsoFlow.getItems().add(new FlowItemDto(forwardBend, new Timing(10, TimingUnit.BREATH)));
		torsoFlow.getItems().add(new FlowItemDto(bridge, new Timing(10, TimingUnit.BREATH)));
	}
}
