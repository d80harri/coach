package net.d80harri.coach.console;

import net.d80harri.coach.console.component.ConsoleComponent;
import net.d80harri.coach.console.component.ConsoleTable;

public class ConsoleResult {
	private Console console;
	private ConsoleComponent component;
	
	public ConsoleResult(Console console, ConsoleTable component) {
		this.console = console;
		this.component = component;
	}

	public ConsoleComponent getComponent() {
		return component;
	}
	public Console getConsole() {
		return console;
	}

}
