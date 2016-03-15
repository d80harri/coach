package net.d80harri.coach.domain.repository;

public interface IDbInitializer {

	void cleanMigrate();

	void migrate();

	void clean();

}