package net.d80harri.coach.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.annotations.JsonApiDelete;
import io.katharsis.repository.annotations.JsonApiFindAll;
import io.katharsis.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.repository.annotations.JsonApiFindOne;
import io.katharsis.repository.annotations.JsonApiResourceRepository;
import io.katharsis.repository.annotations.JsonApiSave;
import io.katharsis.resource.exception.ResourceNotFoundException;
import net.d80harri.coach.rest.model.Task;

@Component
@JsonApiResourceRepository(Task.class)
public class TaskRepository {
    private static final Map<Long, Task> REPOSITORY = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(4);

    private final ProjectRepository projectRepository;

    @Autowired @Lazy
    public TaskRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
        Task task = new Task(1L, "Create tasks");
        task.setProjectId(123L);
        save(task);
        task = new Task(2L, "Make coffee");
        task.setProjectId(123L);
        save(task);
        task = new Task(3L, "Do things");
        task.setProjectId(123L);
        save(task);
    }

    @JsonApiSave
    public <S extends Task> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(ID_GENERATOR.getAndIncrement());
        }
        REPOSITORY.put(entity.getId(), entity);
        return entity;
    }

    @JsonApiFindOne
    public Task findOne(Long taskId, QueryParams requestParams) {
        Task task = REPOSITORY.get(taskId);
        if (task == null) {
            throw new ResourceNotFoundException("Project not found!");
        }
        if (task.getProject() == null) {
            task.setProject(projectRepository.findOne(task.getProjectId(), null));
        }
        return task;
    }

    @JsonApiFindAll
    public Iterable<Task> findAll(QueryParams requestParams) {
        return REPOSITORY.values();
    }

    @JsonApiFindAllWithIds
    public Iterable<Task> findAll(Iterable<Long> taskIds, QueryParams requestParams) {
        return REPOSITORY.entrySet()
                .stream()
                .filter(p -> Iterables.contains(taskIds, p.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .values();
    }

    @JsonApiDelete
    public void delete(Long taskId) {
        REPOSITORY.remove(taskId);
    }
}
