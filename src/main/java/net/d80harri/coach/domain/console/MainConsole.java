package net.d80harri.coach.domain.console;

import net.d80harri.coach.console.CommandConsole;
import net.d80harri.coach.console.ConsoleResult;
import net.d80harri.coach.console.exception.UnknownCommandException;
import net.d80harri.coach.domain.program.ProgramDao;

public class MainConsole extends CommandConsole {
	private ProgramDao dataSource;
	
	public MainConsole(ProgramDao dataSource) {
		super("main> ");
		this.dataSource = dataSource;
	}

	@Override
	protected ConsoleResult executeCommand(String cmd, String[] args) throws UnknownCommandException {
		if ("programs".equals(cmd)) {
			return new ConsoleResult(new ProgramsListConsole(dataSource), null);
		}
		throw new UnknownCommandException();
	}
}
