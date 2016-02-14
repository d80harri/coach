package net.d80harri.coach.domain.program;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import net.d80harri.coach.domain.DomainMapper;
import net.d80harri.coach.domain.DtoAssertions;
import net.d80harri.coach.domain.Entity;
import net.d80harri.coach.domain.program.entities.ProgramEntity;
import net.d80harri.coach.domain.program.entities.SequenceActionEntity;
import net.d80harri.coach.domain.program.entities.SupersetActionEntity;

public class ProgramLogicTest {

	@Test
	public void testRetrieveProgram() {
		Collection<Entity> ds = new ArrayList<>();
		ProgramDao dao = new ProgramDao(ds);
		ProgramLogic logic = new ProgramLogic(dao, new DomainMapper());
				
		ProgramEntity program = new ProgramEntity(UUID.randomUUID(), "Handstand One");
		SequenceActionEntity programAction = new SequenceActionEntity(UUID.randomUUID());
		SequenceActionEntity warmupAction = new SequenceActionEntity(UUID.randomUUID());
		SequenceActionEntity skill = new SequenceActionEntity(UUID.randomUUID());
		SupersetActionEntity strength = new SupersetActionEntity(UUID.randomUUID());
		
		program.setAction(programAction);
		programAction.getSequence().addAll(Arrays.asList(warmupAction, skill, strength));
		
		dao.put(program);
		
		Program readProgram = logic.getById(program.getUuid()).get();
		
		DtoAssertions.assertThat(readProgram).isNotNull().hasId(program.getUuid()).hasActionId(programAction.getUuid());
		DtoAssertions.assertThat(readProgram.getAction()).hasId(programAction.getUuid()).isSequenceAction().containsExactlyActionIds(warmupAction.getUuid(), skill.getUuid(), strength.getUuid());
	}
	
	@Test
	public void testRetrieveProgramThatDoesNotExist() {
		Collection<Entity> ds = new ArrayList<>();
		ProgramDao dao = new ProgramDao(ds);
		ProgramLogic logic = new ProgramLogic(dao, new DomainMapper());
		Assertions.assertThat(logic.getById(UUID.randomUUID())).isEmpty();
	}
}
