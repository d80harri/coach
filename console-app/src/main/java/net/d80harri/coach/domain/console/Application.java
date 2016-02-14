package net.d80harri.coach.domain.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import net.d80harri.coach.console.ConsoleEngine;
import net.d80harri.coach.domain.DomainMapper;
import net.d80harri.coach.domain.Entity;
import net.d80harri.coach.domain.program.ProgramDao;
import net.d80harri.coach.domain.program.ProgramLogic;
import net.d80harri.coach.domain.program.entities.ProgramEntity;

public class Application {

	public static void main(String[] args) throws IOException {
		DomainMapper mapper = new DomainMapper();
		
		Collection<Entity> ds = new ArrayList<>();
		ProgramDao programDao = new ProgramDao(ds);
		programDao.put(new ProgramEntity(UUID.randomUUID(), "Plan 1"));
		programDao.put(new ProgramEntity(UUID.randomUUID(), "Plan 2"));
		programDao.put(new ProgramEntity(UUID.randomUUID(), "Plan 3"));

		ConsoleEngine engine = new ConsoleEngine(new MainConsole(new ProgramsListConsole(new ProgramLogic(programDao, mapper))), System.in, System.out);
		engine.start();
	}

}
