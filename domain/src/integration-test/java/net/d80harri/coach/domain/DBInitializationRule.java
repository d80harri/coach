package net.d80harri.coach.domain;

import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.operation.DeleteAllOperation;
import org.dbunit.operation.DeleteOperation;
import org.dbunit.operation.InsertOperation;
import org.dbunit.operation.RefreshOperation;
import org.dbunit.operation.TruncateTableOperation;
import org.dbunit.operation.UpdateOperation;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DBInitializationRule implements TestRule {
	private DomainContext context;

	public DBInitializationRule(DomainContext context) {
		this.context = context;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new InitStatement(base, context, description);
	}

	private static class InitStatement extends Statement {
		private final Statement base;
		private final DomainContext context;
		private final Description description;

		public InitStatement(Statement base, DomainContext context, Description description) {
			this.base = base;
			this.context = context;
			this.description = description;
		}

		@Override
		public void evaluate() throws Throwable {
			DatabaseSetup initAnnotation = description.getAnnotation(DatabaseSetup.class);
			if (initAnnotation != null) {
				this.insertTestdata(initAnnotation);
			}
			base.evaluate();
		}

		private void insertTestdata(DatabaseSetup annotation) {
			context.getSessionManager().getOrCreateSession().doWork(c -> {
				IDatabaseConnection connection;
				try {
					connection = new DatabaseConnection(c);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				try {
					IDataSet dataSet = new FlatXmlDataSetBuilder().build(description.getTestClass().getResource(annotation.value()));
					
					CompositeOperation compositeOperation = new CompositeOperation(unwrap(annotation.operations()));
					compositeOperation.execute(connection, dataSet);
				} catch (DatabaseUnitException e) {
					throw new RuntimeException(e);
				} finally {
					connection.close();
				}

			});
		}

		private DatabaseOperation[] unwrap(Operation[] operations) {
			DatabaseOperation[] result = new DatabaseOperation[operations.length];
			
			for (int i=0; i<result.length; i++) {
				result[i] = operations[i].operation;
			}
			
			return result;
		}

	}

	public static @interface DatabaseSetup {
		String value();
		Operation[] operations() default {Operation.TRUNCATE_TABLE, Operation.INSERT};
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
}
