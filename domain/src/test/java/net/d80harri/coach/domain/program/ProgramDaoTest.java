package net.d80harri.coach.domain.program;

import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ProgramDaoTest {

	@Test
	public void testGetAll() {
		ProgramDao programDao = new ProgramDao();
		programDao.add(new ProgramEntity(UUID.randomUUID(), "Plan 1"));
		programDao.add(new ProgramEntity(UUID.randomUUID(), "Plan 2"));
		programDao.add(new ProgramEntity(UUID.randomUUID(), "Plan 3"));

		Assertions.assertThat(programDao.getAll(ProgramEntity.class)).hasSize(3);
	}
}
