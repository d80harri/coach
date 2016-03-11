package net.d80harri.coach.domain;

import org.flywaydb.core.Flyway;

public class DBInitializer {
	private Flyway flyway;
	
	public DBInitializer(Flyway flyway) {
		this.flyway = flyway;
	}
	
	public void clean() {
		this.flyway.clean();
	}
	
	public void migrate() {
		this.flyway.migrate();
	}
	
	public void cleanMigrate() {
		clean();
		migrate();
	}
}
