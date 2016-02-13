package net.d80harri.coach.domain.program;

import java.util.Collection;

import net.d80harri.coach.domain.AbstractDao;
import net.d80harri.coach.domain.Entity;

public class ProgramDao extends AbstractDao<ProgramEntity> {
	
	public ProgramDao(Collection<Entity> entities) {
		super(entities);
	}

}
