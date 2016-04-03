package net.d80harri.coach.rest.exercise;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExerciseTestData {
	private List<ExerciseDto> exerciseDtos = new ArrayList<>();
	
	public ExerciseTestData() {
		addAtomic("atomic1", "Atomic1");
		addFlow("flow", 10, TimingUnit.BREATH);
	}

	private void addFlow(String name, int time, TimingUnit timingUnit) {
		FlowDto result = new FlowDto(name);
		AtomicExerciseDto exercise = new AtomicExerciseDto();
		exercise.setId("asdf");
		result.getItems().add(new FlowItemDto(exercise, new Timing(time, timingUnit)));
		exerciseDtos.add(result);
	}

	private void addAtomic(String name, String description) {
		AtomicExerciseDto result = new AtomicExerciseDto();
		result.setName(name);
		result.setDescription(description);
		exerciseDtos.add(result);
	}
	
	public List<ExerciseDto> getExerciseDtos() {
		return exerciseDtos;
	}
}
