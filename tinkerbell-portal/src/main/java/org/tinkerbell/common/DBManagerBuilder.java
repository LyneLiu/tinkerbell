package org.tinkerbell.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.DatabaseMetaDataCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DBManagerBuilder {


	@Autowired
	@Qualifier("tinkerbellDataSource")
	private DataSource tinkerbellDataSource;

	/**
	 * 通过JdbcUtils获取column names
	 * @param tableName
	 * @return
	 */
	public List<String> queryColumns(String tableName) {
		final List<String> columnList = new ArrayList<>();

		try {

			JdbcUtils.extractDatabaseMetaData(tinkerbellDataSource, new DatabaseMetaDataCallback() {
				@Override
				public Object processMetaData(DatabaseMetaData dbmd) throws SQLException, MetaDataAccessException {
					ResultSet rs = dbmd.getColumns(null, null, tableName, null);
					while (rs.next()) {
						String columnName = rs.getString("COLUMN_NAME");
						columnList.add(columnName);
					}
					rs.close();
					return null;
				}
			});

		} catch (Exception e) {
			// do nothing
		}
		return columnList;
	}


}
