package net.d80harri.coach.console.component;

import java.io.IOException;
import java.io.OutputStream;

public interface ConsoleComponent {

	public void print(OutputStream stream) throws IOException;

}