package net.d80harri.coach.domain.repository;

import java.util.List;

public interface IRepository<T extends Entity> {
	<R extends T> List<R> getAll(Class<R> type);

	<R extends T> R getByID(Class<R> type, String id);

	<R extends T> void saveOrUpdate(R exercise);
}
