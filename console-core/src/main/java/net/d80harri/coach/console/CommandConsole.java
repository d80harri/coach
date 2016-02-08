package net.d80harri.coach.console;

import net.d80harri.coach.console.exception.ConsoleException;

public abstract class CommandConsole extends Console {

	public CommandConsole(String prompt) {
		super(prompt);
	}

	@Override
	public ConsoleResult execute(String command) throws ConsoleException {
		String[] split = command.split("\\s+");
		if (split.length == 0) {
			return new ConsoleResult(this, null);
		} else {
			String cmd = split[0];
			String[] args = new String[split.length-1];
			System.arraycopy(split, 1, args, 0, args.length);
			
			return executeCommand(cmd, args);
		}
	}

	protected abstract ConsoleResult executeCommand(String cmd, String[] args) throws ConsoleException;

}
