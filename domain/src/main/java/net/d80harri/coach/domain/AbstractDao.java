package net.d80harri.coach.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.d80harri.coach.domain.exception.DuplicateIdException;

public abstract class AbstractDao<T extends Entity> {
	private final Collection<Entity> datasource;

	public AbstractDao(Collection<Entity> datasource) {
		this.datasource = datasource;
	}

	@SuppressWarnings("unchecked")
	public <R extends T> R put(R entity) {
		Optional<R> result = (Optional<R>) getById(entity.getUuid(), (Class<T>) entity.getClass());
		if (result.isPresent()) {
			throw new RuntimeException("NYI");
		} else {
			this.datasource.add(entity);
		}
		return entity;
	}

	public <R extends T> Optional<R> getById(final UUID uuid, final Class<R> type) {
		List<R> results = filterSubType(type).filter(i -> i.getUuid().equals(uuid)).collect(Collectors.toList());

		if (results.isEmpty()) {
			return Optional.empty();
		}
		if (results.size() > 1) {
			throw new DuplicateIdException(String.format("More than one entity with ID %s found.", uuid));
		}
		return Optional.of(results.get(0));
	}

	public <R extends T> List<R> getAll(final Class<R> type) {
		return filterSubType(type).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private <R extends T> Stream<R> filterSubType(Class<R> type) {
		return (Stream<R>) (Stream<?>) datasource.stream().filter(i -> type.isAssignableFrom(i.getClass()));
	}
}
