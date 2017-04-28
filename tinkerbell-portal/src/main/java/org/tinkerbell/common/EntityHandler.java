package org.tinkerbell.common;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.persistence.Table;

/**
 * SQL Server DB的Entity维护过程中存在字段信息不一致的情况，通过查询，判定字段信息
 */
@Component
public class EntityHandler {

	@Autowired
	private DBManagerBuilder dbManagerBuilder;

	private List<String> entityNames = new ArrayList<>();

	public List<String> getEntityNames() {
		return entityNames;
	}

	public void setEntityNames(List<String> entityNames) {
		this.entityNames = entityNames;
	}

	/**
	 * 获取Classes
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	public List<EntityManager> getEntitiesFromPos() throws ClassNotFoundException {
		List<EntityManager> entityManagers = new ArrayList<>();
		for (int i = 0; i < getEntityNames().size(); i++) {
			EntityManager entityManager = new EntityManager();
			Class clazz = Class.forName(getEntityNames().get(i));
			Field[] fields = clazz.getDeclaredFields();
			String tableName = AnnotationUtils.findAnnotation(clazz, Table.class).name();
			entityManager.setClassName(clazz.getSimpleName());
			entityManager.setTableName(tableName);
			entityManager.setColumns(fields);
			entityManagers.add(entityManager);
		}
		return entityManagers;
	}

	/**
	 * 获取EntiryName
	 * 
	 * @return
	 */
	public void getEntitiesFromPath(String filePath,String commonPath) {

		File file = new File(filePath);
		File[] array = file.listFiles();

		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile() && array[i].getName().endsWith(".java")) {
				String className = array[i].getName().replaceAll(".java", "").trim();
				String packageName = array[i].getParent().substring(commonPath.length()).replaceAll("\\\\", ".");
				getEntityNames().add(packageName + "." + className);
			} else if (array[i].isDirectory()) {
				getEntitiesFromPath(array[i].getPath(),commonPath);
			}
		}
	}

	public List<DBManager> getDBEntities(List<EntityManager> entityManagers) {

		List<DBManager> dbManagers = new ArrayList<>();
		for (int i = 0; i < entityManagers.size(); i++) {
			DBManager dbManager = new DBManager();
			String tableName = entityManagers.get(i).getTableName();
			List<String> columnList = dbManagerBuilder.queryColumns(tableName);
			dbManager.setTableName(tableName);
			dbManager.setColumns(columnList);
			dbManagers.add(dbManager);
		}

		return dbManagers;
	}

	public static List<String> process(List<EntityManager> entityManagers, List<DBManager> dbManagers) {

		List<String> poList = new ArrayList<>();

		if (entityManagers != null && dbManagers != null) {
			for (int i = 0; i < entityManagers.size(); i++) {
				EntityManager entityManager = entityManagers.get(i);
				DBManager dbManager = dbManagers.get(i);
				int len = entityManager.getColumns().length;

				if (len != dbManager.getColumns().size()) {
					poList.add(entityManager.getClassName());
					continue;
				}

				Field[] fields = entityManager.getColumns();
				List<String> columnList = dbManager.getColumns();
				for (int j = 0; j < len; j++) {
					if (fields[j].getName().equalsIgnoreCase(columnList.get(j))) {
						continue;
					} else {
						poList.add(entityManager.getClassName());
						break;
					}
				}
			}
		}

		return poList;
	}

}
