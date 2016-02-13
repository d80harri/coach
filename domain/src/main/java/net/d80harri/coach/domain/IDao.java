package net.d80harri.coach.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDao<T extends Entity> {

	<R extends T> R put(R entity);

	<R extends T> Optional<R> getById(UUID uuid, Class<R> type);

	<R extends T> List<R> getAll(Class<R> type);

}