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
import net.d80harri.coach.rest.model.Project;
import net.d80harri.coach.rest.model.Task;

@JsonApiResourceRepository(Project.class)
@Component
public class ProjectRepository {
    private static final Map<Long, Project> REPOSITORY = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(124);
    private final TaskRepository taskRepository;

    @Autowired @Lazy
    public ProjectRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        Project project = new Project(123L);
        project.setName("Great Project");
        save(project);
    }

    @JsonApiSave
    public <S extends Project> S save(S entity) {
        if (entity.getId() == null) {
            entity.setId(ID_GENERATOR.getAndIncrement());
        }
        REPOSITORY.put(entity.getId(), entity);
        return entity;
    }

    @JsonApiFindOne
    public Project findOne(Long projectId, QueryParams requestParams) {
        if (projectId == null) {
            return null;
        }
        Project project = REPOSITORY.get(projectId);
        if (project == null) {
            throw new ResourceNotFoundException("Project not found!");
        }
        if (project.getTasks().isEmpty()) {
            Iterable<Task> tasks = taskRepository.findAll(null);
            tasks.forEach(task -> {
                if (task.getProjectId().equals(project.getId())) {
                    project.getTasks().add(task);
                }
            });
            save(project);
        }
        return project;
    }

    @JsonApiFindAll
    public Iterable<Project> findAll(QueryParams requestParams) {
        return REPOSITORY.values();
    }

    @JsonApiFindAllWithIds
    public Iterable<Project> findAll(Iterable<Long> projectIds, QueryParams requestParams) {
        return REPOSITORY.entrySet()
                .stream()
                .filter(p -> Iterables.contains(projectIds, p.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .values();
    }

    @JsonApiDelete
    public void delete(Long projectId) {
        REPOSITORY.remove(projectId);
    }
}
