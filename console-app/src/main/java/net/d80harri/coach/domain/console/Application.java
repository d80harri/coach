package net.d80harri.coach.domain.console;

import java.io.IOException;
import java.util.UUID;

import net.d80harri.coach.console.ConsoleEngine;
import net.d80harri.coach.domain.program.ProgramDao;
import net.d80harri.coach.domain.program.ProgramEntity;

public class Application {

	public static void main(String[] args) throws IOException {
		ProgramDao dataSource = new ProgramDao();
		dataSource.add(new ProgramEntity(UUID.randomUUID(), "Plan 1"));
		dataSource.add(new ProgramEntity(UUID.randomUUID(), "Plan 2"));
		dataSource.add(new ProgramEntity(UUID.randomUUID(), "Plan 3"));

		ConsoleEngine engine = new ConsoleEngine(new MainConsole(dataSource), System.in, System.out);
		engine.start();
	}

}
