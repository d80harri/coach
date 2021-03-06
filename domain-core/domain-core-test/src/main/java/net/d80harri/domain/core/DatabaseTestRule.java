package net.d80harri.domain.core;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.sql.SQLException;
import java.util.function.Consumer;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.d80harri.coach.domain.repository.IDbInitializer;
import net.d80harri.coach.domain.repository.ISessionHolder;
import net.d80harri.coach.domain.repository.ISessionManager;

public class DatabaseTestRule implements TestRule {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseTestRule.class);
	private final ISessionManager sessionManager;
	private final IDbInitializer dbInitializer;

	public DatabaseTestRule(ISessionManager manager, IDbInitializer initializer) {
		this.dbInitializer = initializer;
		this.sessionManager = manager;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new InitStatement(base, description, sessionManager, dbInitializer);
	}

	private static class InitStatement extends Statement {
		private final Statement base;
		private final Description description;
		private final ISessionManager sessionManager;
		private final IDbInitializer dbInitializer;

		public InitStatement(Statement base, Description description, ISessionManager sessionManager,
				IDbInitializer dbInitializer) {
			this.base = base;
			this.description = description;
			this.sessionManager = sessionManager;
			this.dbInitializer = dbInitializer;
		}

		@Override
		public void evaluate() throws Throwable {
			handleAnnotation(DatabaseSetup.class, this::insertTestdata);

			base.evaluate();

			handleAnnotation(DBStateLogging.class, this::printDataset);
			handleAnnotation(ExpectedDatabase.class, this::validateDatabase);
		}

		private <T extends Annotation> void handleAnnotation(Class<T> val, Consumer<T> consumer) {
			T annotation = description.getAnnotation(val);
			if (annotation != null)
				consumer.accept(annotation);
		}

		private void doWithConnection(ConnectionConsumer consumer) {
			try (ISessionHolder sh = sessionManager.getOrCreateSession()) {
				sh.doWork(c -> {
					IDatabaseConnection connection;
					try {
						connection = new DatabaseConnection(c);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					consumer.accept(connection);
				});
			}
		}

		private void validateDatabase(ExpectedDatabase expectedDatabaseAnnotation) {
			doWithConnection(new ConnectionConsumer() {

				@Override
				public void accept(IDatabaseConnection c) throws SQLException {
					FlatXmlDataSetBuilder b = new FlatXmlDataSetBuilder();
					FlatDatasetExport export = new FlatDatasetExport(c);

					try {
						URL datasetFile = description.getTestClass().getResource(expectedDatabaseAnnotation.value());
						IDataSet expectedDataset = export.getDataset(expectedDatabaseAnnotation.table());
						Assertion.assertEquals(b.build(datasetFile), expectedDataset);
					} catch (DatabaseUnitException e) {
						throw new RuntimeException(e);
					}
				}
			});
		}

		private void printDataset(DBStateLogging stateLoggingAnnotation) {
			doWithConnection(new ConnectionConsumer() {

				@Override
				public void accept(IDatabaseConnection c) throws SQLException {
					FlatDatasetExport export = new FlatDatasetExport(c);
					String exportToString = export.exportToString(stateLoggingAnnotation.value());
					logger.debug(exportToString);
				}
			});
		}

		private void insertTestdata(DatabaseSetup annotation) {
			dbInitializer.cleanMigrate();
			doWithConnection(c -> {
				try {
					if (annotation.value() != null && !"".equals(annotation.value())) {
						IDataSet dataSet = new FlatXmlDataSetBuilder()
								.build(description.getTestClass().getResource(annotation.value()));

						DatabaseOperation.INSERT.execute(c, dataSet);
					}
				} catch (DatabaseUnitException e) {
					throw new RuntimeException(e);
				}
			});
		} 
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface DatabaseSetup {
		String value() default "";

		Operation[] operations() default { Operation.TRUNCATE_TABLE, Operation.INSERT };
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface DBStateLogging {
		String[] value();
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface ExpectedDatabase {
		String value();

		String table();
	}

	public static enum Operation {
		NONE(DatabaseOperation.NONE), UPDATE(DatabaseOperation.UPDATE), INSERT(DatabaseOperation.INSERT), REFRESH(
				DatabaseOperation.REFRESH), DELETE(DatabaseOperation.DELETE), DELETE_ALL(
						DatabaseOperation.DELETE_ALL), TRUNCATE_TABLE(DatabaseOperation.TRUNCATE_TABLE);

		private DatabaseOperation operation;

		Operation(DatabaseOperation operation) {
			this.operation = operation;
		}
	}

	private static interface ConnectionConsumer {
		public void accept(IDatabaseConnection connection) throws SQLException;
	}
}
