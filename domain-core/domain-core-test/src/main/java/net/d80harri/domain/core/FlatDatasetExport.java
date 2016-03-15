package net.d80harri.domain.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.util.search.SearchException;

/**
 * Contains functions to export to a {@link FlatXmlDataSet}.
 * 
 * @author d80harri
 *
 */
public class FlatDatasetExport {

	private IDatabaseConnection connection;

	public FlatDatasetExport(IDatabaseConnection connection) {
		this.connection = connection;
	}

	public String exportToString(String... rootTables) {
		try {
			IDataSet depDataset = getDataset(rootTables);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FlatXmlDataSet.write(depDataset, baos);
			return baos.toString();
		} catch (DataSetException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	public IDataSet getDataset(String... rootTables) {
		try {
			String[] depTableNames = TablesDependencyHelper.getAllDependentTables(connection, rootTables);
			return connection.createDataSet(depTableNames);
		} catch (SearchException | DataSetException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
