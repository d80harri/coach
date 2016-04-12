package net.d80harri.coach.rest.exercise.atomic;

import net.d80harri.coach.domain.exercise.AtomicExercise;
import net.d80harri.coach.domain.repository.IRepository;
import net.d80harri.coach.rest.DefaultController;
import net.d80harri.coach.rest.RestCoachMapper;

public class AtomicExerciseController extends DefaultController<AtomicExercise, AtomicExerciseDto> {

	public AtomicExerciseController(IRepository<AtomicExercise> repository, RestCoachMapper mapper) {
		super(AtomicExercise.class, AtomicExerciseDto.class, repository, mapper);
	}

}
