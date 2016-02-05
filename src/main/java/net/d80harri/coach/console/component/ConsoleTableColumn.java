package net.d80harri.coach.console.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleTableColumn {
	private List<Object> lines = new ArrayList<Object>();
	private ConsoleRenderer renderer = null;

	public void addValue(Object value) {
		lines.add(value);
	}

	public ConsoleRenderer getRenderer() {
		return renderer;
	}

	public ConsoleRenderer getRendererForValue(Object value) {
		ConsoleRenderer result = getRenderer();
		if (result != null) {
			return result;
		} else {
			return i -> Optional.ofNullable(value).orElse("[null]").toString();
		}
	}

	public List<String> getRenderedValues() {
		List<String> result = new ArrayList<>();

		for (Object value : lines) {
			ConsoleRenderer renderer = getRendererForValue(value);
			result.add(renderer.render(value));
		}

		return result;
	}

}
