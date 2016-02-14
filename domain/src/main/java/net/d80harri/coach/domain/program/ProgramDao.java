package net.d80harri.coach.domain.program;

import java.util.Collection;

import net.d80harri.coach.domain.AbstractDao;
import net.d80harri.coach.domain.Entity;
import net.d80harri.coach.domain.program.entities.ProgramEntity;

public class ProgramDao extends AbstractDao<ProgramEntity> implements IProgramDao {
	
	public ProgramDao(Collection<Entity> entities) {
		super(entities, ProgramEntity.class);
	}
}
