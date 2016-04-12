package net.d80harri.coach.rest;

import java.util.List;

import io.katharsis.queryParams.QueryParams;
import io.katharsis.repository.ResourceRepository;
import net.d80harri.coach.domain.repository.Entity;
import net.d80harri.coach.domain.repository.IRepository;

public class DefaultController<T extends Entity, D> implements ResourceRepository<D, String> {
	private final IRepository<T> repository;
	private final RestCoachMapper mapper;
	private final Class<D> resultType;
	private final Class<T> entityType;

	public DefaultController(Class<T> entityType, Class<D> resultType, IRepository<T> repository,
			RestCoachMapper mapper) {
		this.entityType = entityType;
		this.resultType = resultType;
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public D findOne(String id, QueryParams queryParams) {
		T resultEntity = repository.getByID(entityType, id);
		D resultDto = mapper.map(resultEntity, resultType);
		return resultDto;
	}

	@Override
	public Iterable<D> findAll(QueryParams queryParams) {
		List<T> resultEntities = repository.getAll(entityType);
		return mapper.mapAsList(resultEntities, resultType);
	}

	@Override
	public Iterable<D> findAll(Iterable<String> ids, QueryParams queryParams) {
		throw new RuntimeException("NYI");
	}

	@Override
	public <S extends D> S save(S resourceModel) {
		T entity = mapper.map(resourceModel, entityType);
		repository.saveOrUpdate(entity);
		return mapper.map(entity, (Class<S>)(Class)resourceModel.getClass());
	}

	@Override
	public void delete(String id) {
		throw new RuntimeException();
	}
	
	public Class<D> getResultType() {
		return resultType;
	}
}
