package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public abstract class AllTablesManager {
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/bc_praca";
	static final String USER = "postgres";
	static final String PASS = "0000";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List selectQuery(String queryString) throws SQLException{
		List result = new LinkedList();
		Connection conn = null;
		Statement stmt = null;
	    try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()){
				result.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	    return result;
	}

	protected abstract Object processRow(ResultSet rs) throws SQLException;
}
