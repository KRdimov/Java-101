package week10;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataBase {
	private Connection conn;
	private Statement stmt;
	
	public DataBase(String dbName, String dbUser, String dbPass) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + dbName ,dbUser, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertInto(String table, Map<String, String> columnValue) {
		try {
			stmt = conn.createStatement();
			StringBuilder sql;
			StringBuilder columns = new StringBuilder("(");
			StringBuilder values = new StringBuilder("(");
			sql = new StringBuilder("INSERT INTO " +  table + " ");
			for(Map.Entry<String, String> entry : columnValue.entrySet()) {
			    String column = entry.getKey();
			    String value = entry.getValue();
			    columns.append(column + ",");
			    values.append(value + ",");
			}
			columns.deleteCharAt(sql.length() - 1);
			values.deleteCharAt(sql.length() - 1);
			columns.append(")");
			values.append(")");
			sql.append(columns + " VALUES " + values);
			stmt.executeQuery(sql.toString());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean update(String table, Map<String, String> columnValue, int id) {
		try {
			stmt = conn.createStatement();
			StringBuilder sql;
			sql = new StringBuilder("UPDATE " +  table + " SET ");
			for(Map.Entry<String, String> entry : columnValue.entrySet()) {
			    String column = entry.getKey();
			    String value = entry.getValue();
			    sql.append(column + "=" + value + ",");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append("WHERE id=" + id);
			stmt.executeQuery(sql.toString());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteFrom(String table, int id) {
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "DELETE FROM " +  table + " WHERE id=" + id;
			stmt.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<List<String>> selectFrom(String table, String orderBy, boolean ascendingOrder) {
		try {
			List<List<String>> result = new LinkedList<>();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM " + table + " ORDER BY " + orderBy;
			if(!ascendingOrder) {
				sql += " DESC"; 
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rs.getMetaData().getColumnCount();
				List<String> row = new LinkedList<>();
				for (int i = 1; i <= columns; i++) {
					String columnName = rsmd.getColumnName(i);
					row.add(rs.getString(columnName));
				}
				result.add(row);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<List<String>> selectFrom(String table, String where, String orderBy, boolean ascendingOrder) {
		try {
			List<List<String>> result = new LinkedList<>();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM " + table + " WHERE " + where + " ORDER BY " + orderBy;
			if(!ascendingOrder) {
				sql += " DESC"; 
			}
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columns = rs.getMetaData().getColumnCount();
				List<String> row = new LinkedList<>();
				for (int i = 1; i <= columns; i++) {
					String columnName = rsmd.getColumnName(i);
					row.add(rs.getString(columnName));
				}
				result.add(row);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
