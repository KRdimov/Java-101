package week09;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class MySQLHelper {
	private Connection conn;
	private Statement stmt;
	
	public MySQLHelper(String dbName, String dbUser, String dbPass) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + dbName ,dbUser, dbPass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean insertInto(String table, Object object) {
		//TODO
		return false;
	}

	public boolean deleteFrom(String string, int id) {
		//TODO
		return false;
	}

	public boolean update(String string, Object object) {
		//TODO
		return false;
	}
	
	public List<List<String>> selectFrom(String table) { 
		return selectFrom(table, "1");
	}

	public List<List<String>> selectFrom(String table, String where) {
		try {
			List<List<String>> result = new LinkedList<>();
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM " + table + " WHERE " + where;
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
			return null;
		}
	}
}
