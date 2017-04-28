package org.tinkerbell.common;

import java.util.List;

/**
 * 获取DB中的Columns信息
 */
public class DBManager {

	private String tableName;
	
	private List<String> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	

}
