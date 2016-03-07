package net.d80harri.coach.rest.exercise;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.repository.annotations.JsonApiSave;
import net.d80harri.coach.domain.exercise.Exercise;
import net.d80harri.coach.domain.exercise.ExerciseRepository;
import net.d80harri.coach.rest.RestCoachMapper;

@JsonApiResourceRepository(ExerciseDto.class)
@Service
public class ExerciseController {

	private final ExerciseRepository exerciseRepository;
	private final RestCoachMapper mapper;
	
    @Autowired @Lazy
    public ExerciseController(ExerciseRepository exerciseRepository, RestCoachMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }
    
    @JsonApiFindAll
    public Iterable<ExerciseDto> findAll(QueryParams requestParams) {
        return mapper.mapAsList(exerciseRepository.getAll(), ExerciseDto.class);
    }
    
    @JsonApiSave
    public ExerciseDto save(ExerciseDto exercise) {
    	if (exercise.getId() == null) {
    		exercise.setId(UUID.randomUUID().toString());
    	}
    	exerciseRepository.saveOrUpdate(mapper.map(exercise, Exercise.class));
    	return mapper.map(exerciseRepository.getByID(exercise.getId()), ExerciseDto.class);
    }
}
