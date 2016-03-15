package net.d80harri.domain.core;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.sql.SQLException;
import java.util.function.Consumer;
import java.util.function.Function;

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

import net.d80harri.coach.domain.repository.DomainCoreConfiguration;
import net.d80harri.coach.domain.repository.IDomainCoreContext;
import net.d80harri.coach.domain.repository.ISessionHolder;

public class DatabaseTestRule implements TestRule {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseTestRule.class);
	private final Function<DomainCoreConfiguration, IDomainCoreContext> contextFactory;
	private IDomainCoreContext currentContext;
	
	public DatabaseTestRule(Function<DomainCoreConfiguration, IDomainCoreContext> contextFactory) {
		this.contextFactory = contextFactory;
	}
	
	public IDomainCoreContext getCurrentContext() {
		return currentContext;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		DomainCoreConfiguration config = new DomainCoreConfiguration();
		config.setConnectionUrl("jdbc:h2:.testdbs/"+ description.getClassName().replace(".", "/") + "/" +description.getMethodName()+";AUTO_SERVER=TRUE");
		
		currentContext = contextFactory.apply(config);
		
		return new InitStatement(base, description, currentContext);
	}

	private static class InitStatement extends Statement {
		private final Statement base;
		private final IDomainCoreContext context;
		private final Description description;

		public InitStatement(Statement base, Description description, IDomainCoreContext context) {
			this.base = base;
			this.context = context;
			this.description = description;
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
			try (ISessionHolder sh = context.getSessionManager().getOrCreateSession()) {
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
			context.getDbInitializer().cleanMigrate();
			doWithConnection(c -> {
				try {
					IDataSet dataSet = new FlatXmlDataSetBuilder()
							.build(description.getTestClass().getResource(annotation.value()));

					DatabaseOperation.INSERT.execute(c, dataSet);
				} catch (DatabaseUnitException e) {
					throw new RuntimeException(e);
				}
			});
		}
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public static @interface DatabaseSetup {
		String value();

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
