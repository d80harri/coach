package net.d80harri.coach.domain.program;

import java.util.Optional;
import java.util.UUID;

import net.d80harri.coach.domain.IDao;
import net.d80harri.coach.domain.program.entities.ProgramEntity;

public interface IProgramDao extends IDao<ProgramEntity> {

	@Override
	public <R extends ProgramEntity> Optional<R> getById(UUID uuid, Class<R> type);
}
