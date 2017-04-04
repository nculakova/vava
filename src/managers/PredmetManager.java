package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Predmet;

public class PredmetManager extends AllTablesManager {

	protected Predmet processRow(ResultSet rs) throws SQLException {
		return(new Predmet(rs.getInt("id"), rs.getString("nazov")));
	}

	@SuppressWarnings("unchecked")
	public List<Predmet> getAllPredmet() throws SQLException {
		return(selectQuery("SELECT * FROM predmet ORDER BY name"));
	}

	public void insertPredmet(Predmet predmet) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String createStatementString = "INSERT INTO predmet(kod, nazov) VALUES(?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, predmet.getKod());
			stmt.setString(2, predmet.getNazov());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
				System.err.println("predmet already exists");
			}
		} finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
}
