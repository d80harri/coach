package net.d80harri.coach.domain.console;

import java.util.List;

import net.d80harri.coach.console.CommandConsole;
import net.d80harri.coach.console.ConsoleResult;
import net.d80harri.coach.console.component.ConsoleTable;
import net.d80harri.coach.console.exception.UnknownCommandException;
import net.d80harri.coach.domain.program.Program;
import net.d80harri.coach.domain.program.ProgramLogic;

public class ProgramsListConsole extends CommandConsole {
	private ProgramLogic logic;
	
	public ProgramsListConsole(ProgramLogic logic) {
		super("programs> ");
		this.logic = logic;
	}

	@Override
	protected ConsoleResult executeCommand(String cmd, String[] args) throws UnknownCommandException {
		if ("list".equals(cmd)) {
			ConsoleTable table = new ConsoleTable(1);
			
			List<Program> programs = logic.getAll();
			addLines(table, programs);
			return new ConsoleResult(this, table);
		}
		throw new UnknownCommandException();
	}

	private void addLines(ConsoleTable table, List<Program> programs) {
		for (Program program : programs) {
			addLine(table, program);
		}
	}

	private void addLine(ConsoleTable table, Program program) {
		table.addLine(program.getName());
	}

}
