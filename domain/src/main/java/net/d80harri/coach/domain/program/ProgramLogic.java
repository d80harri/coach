package net.d80harri.coach.domain.program;

import java.util.ArrayList;
import java.util.List;

public class ProgramLogic {
	private ProgramDao dataSource;
	
	public ProgramLogic(ProgramDao dataSource) {
		this.dataSource = dataSource;
	}

	public List<Program> getAll() {
		List<ProgramEntity> entities = dataSource.getAll(ProgramEntity.class);
		return convertToProgram(entities);
	}

	private List<Program> convertToProgram(List<ProgramEntity> entities) {
		List<Program> result = new ArrayList<Program>();
		
		for (ProgramEntity entity : entities) {
			result.add(convertToProgram(entity));
		}
		
		return result;
	}
	
	private Program convertToProgram(ProgramEntity entity) {
		Program result = new Program();
		result.setName(entity.getName());
		return result;
	}

}
