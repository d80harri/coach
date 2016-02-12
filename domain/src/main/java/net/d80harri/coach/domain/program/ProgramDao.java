package net.d80harri.coach.domain.program;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.d80harri.coach.domain.Entity;

public class ProgramDao {
	private Collection<Entity> datasource;
	
	public ProgramDao() {
		this.datasource = new ArrayList<>();
	}
	
	public ProgramDao(Collection<Entity> entities) {
		this.datasource = entities;
	}
	
	public List<ProgramEntity> getAll(final Class<ProgramEntity> type) {
		return filterSubType(type).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Entity> Stream<T> filterSubType(Class<T> type) {
		return (Stream<T>)datasource.stream().filter(i -> type.isAssignableFrom(i.getClass()));
	}

	public void add(Entity entity) {
		this.datasource.add(entity);
	}

}
