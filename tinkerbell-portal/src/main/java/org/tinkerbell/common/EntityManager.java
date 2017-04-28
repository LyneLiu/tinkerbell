package org.tinkerbell.common;

import java.lang.reflect.Field;

/**
 * 获取Entity中的Columns信息
 */
public class EntityManager {

	private String className;
	
	private String tableName;
	
	private Field[] columns;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Field[] getColumns() {
		return columns;
	}

	public void setColumns(Field[] columns) {
		this.columns = columns;
	}
	
}
