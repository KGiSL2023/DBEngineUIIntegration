package com.kgisl.dbEngine.src.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBEngineUIRepoImpl {

	@Autowired
	DataSource srcDataSource;

	public Map<String, List<String>> getTables() throws Exception {
		Map<String, List<String>> tableColumnMap = new HashMap<>();
		DatabaseMetaData metaData = srcDataSource.getConnection().getMetaData();
		ResultSet tables = metaData.getTables(null, null, "%", new String[] { "TABLE" });
		while (tables.next()) {
			List<String> columnNames = new ArrayList<String>();
			String tableName = tables.getString("TABLE_NAME");
			System.out.println(tableName);
			ResultSet columns = metaData.getColumns(null, null, tableName, "%");
			while (columns.next()) {
				columnNames.add(columns.getString("COLUMN_NAME"));
				System.out.println("\t" + columns.getString("COLUMN_NAME"));
			}
			tableColumnMap.put(tableName, columnNames);
		}
		return tableColumnMap;
	}

}
