package net.d80harri.coach.rest.exercise;

import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.repository.IRepository;
import net.d80harri.coach.rest.DefaultController;
import net.d80harri.coach.rest.RestCoachMapper;


public class ExerciseController extends DefaultController<Exercise, ExerciseDto> {
	public ExerciseController(IRepository<Exercise> repository, RestCoachMapper mapper) {
		super(Exercise.class, ExerciseDto.class, repository, mapper);
	}
}
