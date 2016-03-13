package net.d80harri.coach.domain.program;

import java.util.Optional;
import java.util.UUID;

import net.d80harri.coach.domain.program.entities.ProgramEntity;

public interface IProgramDao {

	/**
	 * Finds the {@link ProgramEntity} with the given id and joins the complete action tree.
	 * 
	 * @param uuid the UUID of the of the {@link ProgramEntity}
	 * @return the {@link ProgramEntity} with the given UUID or {@link Optional#empty()}
	 */
	public Optional<ProgramEntity> getById(UUID uuid);
}
