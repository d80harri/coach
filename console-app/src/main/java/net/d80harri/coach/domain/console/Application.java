package net.d80harri.coach.domain.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import net.d80harri.coach.console.ConsoleEngine;
import net.d80harri.coach.domain.Entity;
import net.d80harri.coach.domain.program.ProgramDao;
import net.d80harri.coach.domain.program.ProgramEntity;

public class Application {

	public static void main(String[] args) throws IOException {
		Collection<Entity> ds = new ArrayList<>();
		ProgramDao dataSource = new ProgramDao(ds);
		dataSource.put(new ProgramEntity(UUID.randomUUID(), "Plan 1"));
		dataSource.put(new ProgramEntity(UUID.randomUUID(), "Plan 2"));
		dataSource.put(new ProgramEntity(UUID.randomUUID(), "Plan 3"));

		ConsoleEngine engine = new ConsoleEngine(new MainConsole(dataSource), System.in, System.out);
		engine.start();
	}

}
