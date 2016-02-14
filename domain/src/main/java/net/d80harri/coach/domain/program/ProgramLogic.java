package net.d80harri.coach.domain.program;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ma.glasnost.orika.MapperFacade;
import net.d80harri.coach.domain.program.entities.ProgramEntity;

public class ProgramLogic {
	private ProgramDao dataSource;
	private MapperFacade mapper;
	
	public ProgramLogic(ProgramDao dataSource, MapperFacade mapper) {
		this.dataSource = dataSource;
		this.mapper = mapper;
	}

	public List<Program> getAll() {
		List<ProgramEntity> entities = dataSource.getAll(ProgramEntity.class);
		return mapper.mapAsList(entities, Program.class);
	}

	public Optional<Program> getById(UUID uuid) {
		Optional<ProgramEntity> entity = dataSource.getById(uuid, ProgramEntity.class);
		if (entity.isPresent()) {
			return Optional.of(mapper.map(entity.get(), Program.class));
		} else {
			return Optional.empty();
		}
	}

}
