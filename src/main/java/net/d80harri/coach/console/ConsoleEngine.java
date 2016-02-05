package net.d80harri.coach.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;

import net.d80harri.coach.console.exception.ConsoleException;

public class ConsoleEngine {
	private Stack<Console> stack = new Stack<Console>();
	private BufferedReader reader;
	private BufferedWriter writer;
	private OutputStream out;

	public ConsoleEngine(Console rootConsole, InputStream in, OutputStream out) {
		stack.push(rootConsole);
		this.reader = new BufferedReader(new InputStreamReader(in));
		this.writer = new BufferedWriter(new OutputStreamWriter(out));
		this.out = out;
	}

	public void start() throws IOException {
		Console current = peekConsoleOrNull();
		while (current != null) {
			writer.write(current.getPrompt());
			writer.flush();
			Console subConsole = processLine(current);

			if (subConsole == null) {
				stack.pop();
				current = peekConsoleOrNull();
			} else if (subConsole != current) {
				current = subConsole;
				stack.push(current);
			}
		}
	}

	private Console processLine(Console current) throws IOException {
		Console subConsole = null;

		String line = reader.readLine();
		if (line.equals("exit")) {
			return null;
		} else {
			try {
				ConsoleResult consoleResult = current.execute(line);
				if (consoleResult != null) {
					subConsole = consoleResult.getConsole();
				}
				if (consoleResult.getComponent() != null) {
					consoleResult.getComponent().print(out);
				}
			} catch (ConsoleException ex) {
				subConsole = current;
				ex.printStackTrace(new PrintWriter(writer));
			}
			return subConsole;
		}
	}

	private Console peekConsoleOrNull() {
		if (stack.isEmpty())
			return null;
		else
			return stack.peek();
	}

}
