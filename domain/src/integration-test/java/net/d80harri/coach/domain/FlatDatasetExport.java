package net.d80harri.coach.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseDataSourceConnection;
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

	private DataSource datasource;

	public FlatDatasetExport(DataSource datasource) {
		this.datasource = datasource;
	}

	public String exportToString(String... rootTables) {
		try {
			IDatabaseConnection dbConnection = new DatabaseDataSourceConnection(datasource, "PUBLIC");

			String[] depTableNames = TablesDependencyHelper.getAllDependentTables(dbConnection, rootTables);
			IDataSet depDataset = dbConnection.createDataSet(depTableNames);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FlatXmlDataSet.write(depDataset, baos);
			return baos.toString();
		} catch (SearchException | DataSetException | SQLException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
