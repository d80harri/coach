package net.d80harri.coach.rest;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.RelationshipRepository;
import io.katharsis.repository.ResourceRepository;
import io.katharsis.utils.PropertyUtils;

public class DefaultRelationshipController<T, D> implements RelationshipRepository<T, String, D, String> {

	private final ResourceRepository<D, String> targetRepository;
	private final ResourceRepository<T, String> sourceRepository;

	public DefaultRelationshipController(ResourceRepository<T, String> sourceRepository,
			ResourceRepository<D, String> repository) {
		this.targetRepository = repository;
		this.sourceRepository = sourceRepository;
	}

	@Override
	public void setRelation(T source, String targetId, String fieldName) {
		throw new RuntimeException(String.format("setRelation(%s, %s, %s)", source, targetId, fieldName));
	}

	@Override
	public void setRelations(T source, Iterable<String> targetIds, String fieldName) {
		throw new RuntimeException(String.format("setRelation(%s, %s, %s)", source, targetIds, fieldName));
	}

	@Override
	public void addRelations(T source, Iterable<String> targetIds, String fieldName) {
		throw new RuntimeException(String.format("addRelations(%s, %s, %s)", source, targetIds, fieldName));
	}

	@Override
	public void removeRelations(T source, Iterable<String> targetIds, String fieldName) {
		throw new RuntimeException(String.format("removeRelations(%s, %s, %s)", source, targetIds, fieldName));
	}

	@Override
	public D findOneTarget(String sourceId, String fieldName, QueryParams queryParams) {
		throw new RuntimeException(String.format("findOneTarget(%s, %s, %s)", sourceId, fieldName, queryParams));
	}

	@Override
	public Iterable<D> findManyTargets(String sourceId, String fieldName, QueryParams queryParams) {
		T project = sourceRepository.findOne(sourceId, queryParams);
		try {
			return (Iterable<D>) PropertyUtils.getProperty(project, fieldName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		// throw new RuntimeException(String.format("findManyTargets(%s, %s,
		// %s)", sourceId, fieldName, queryParams));
	}

}
