package net.d80harri.coach.console;

import net.d80harri.coach.console.exception.ConsoleException;

public abstract class Console {
	private String prompt;
	
	public Console(String prompt) {
		super();
		this.prompt = prompt;
	}

	public abstract ConsoleResult execute(String command) throws ConsoleException;


	public String getPrompt() {
		return prompt;
	}
}