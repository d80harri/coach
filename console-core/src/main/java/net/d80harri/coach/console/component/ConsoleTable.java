package net.d80harri.coach.console.component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConsoleTable implements ConsoleComponent {
	List<ConsoleTableColumn> columns = new ArrayList<>();

	public ConsoleTable(int numCols) {
		for (int i=0; i<numCols; i++) {
			columns.add(new ConsoleTableColumn());
		}
	}
	
	public void addLine(Object... values) {
		int i = 0;
		for (ConsoleTableColumn column : columns) {
			Object value = values[i];
			column.addValue(value);
			i++;
		}
	}

	@Override
	public void print(OutputStream stream) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream));
		
		List<Iterator<String>> lines = new ArrayList<>();
		for (ConsoleTableColumn column : columns) {
			List<String> rendered = column.getRenderedValues();
			lines.add(rendered.iterator());
		}
		
		boolean finished = true;
		
		do {
			finished = true;
			for (Iterator<String> line : lines) {
				if (line.hasNext()) {
					finished = false;
					writer.write(line.next());
					writer.write('|');
				}
				writer.write('\n');
			}
		} while (!finished);
		
		writer.flush();
	}


}
