package net.d80harri.coach.domain;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import net.d80harri.coach.domain.program.Program;
import net.d80harri.coach.domain.program.SequenceAction;
import net.d80harri.coach.domain.program.SupersetAction;
import net.d80harri.coach.domain.program.entities.ProgramEntity;
import net.d80harri.coach.domain.program.entities.SequenceActionEntity;
import net.d80harri.coach.domain.program.entities.SupersetActionEntity;

public class DomainMapper extends ConfigurableMapper {
	@Override
	protected void configure(MapperFactory factory) {
		configureProglram2ProgramEntity(factory);
		configureSequenceAction2SequenceActionEntity(factory);
		configureSupersetAction2SupersetActionEntity(factory);
	}

	private void configureSequenceAction2SequenceActionEntity(MapperFactory factory) {
		factory.classMap(SequenceAction.class, SequenceActionEntity.class)
		 .byDefault().register();
	}

	private void configureProglram2ProgramEntity(MapperFactory factory) {
		factory.classMap(Program.class, ProgramEntity.class).byDefault().register();
	}
	
	private void configureSupersetAction2SupersetActionEntity(MapperFactory factory) {
		factory.classMap(SupersetAction.class, SupersetActionEntity.class).byDefault().register();
	}
}
