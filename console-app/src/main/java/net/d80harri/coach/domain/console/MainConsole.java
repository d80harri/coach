package net.d80harri.coach.domain.console;

import net.d80harri.coach.console.CommandConsole;
import net.d80harri.coach.console.ConsoleResult;
import net.d80harri.coach.console.exception.UnknownCommandException;

public class MainConsole extends CommandConsole {
	private ProgramsListConsole programListConsole;
	
	public MainConsole(ProgramsListConsole programListConsole) {
		super("main> ");
		this.programListConsole = programListConsole;
	}

	@Override
	protected ConsoleResult executeCommand(String cmd, String[] args) throws UnknownCommandException {
		if ("programs".equals(cmd)) {
			return new ConsoleResult(programListConsole, null);
		}
		throw new UnknownCommandException();
	}
}
