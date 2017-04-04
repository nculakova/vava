package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Predmet;

public class VolitelnyPredmetManager extends AllTablesManager {
	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Predmet(rs.getInt("id"), rs.getString("nazov")));
	}

	public void insertVolitelnyPredmet(Predmet predmet) throws SQLException {
		System.out.println("inserting: " + predmet);

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String createStatementString = "INSERT INTO volitelny_predmet(kod, nazov, pocet_studentov, a, b, c, d, e, fx) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, predmet.getKod());
			stmt.setString(2, predmet.getNazov());
			stmt.setInt(3, predmet.getPocetStudentov());
			stmt.setDouble(4, predmet.getA());
			stmt.setDouble(5, predmet.getB());
			stmt.setDouble(6, predmet.getC());
			stmt.setDouble(7, predmet.getD());
			stmt.setDouble(8, predmet.getE());
			stmt.setDouble(9, predmet.getFx());
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

	public List<Predmet> getVolitelne() throws SQLException {
		List<Predmet> predmety = new ArrayList<Predmet>();
		Connection conn = null;
		Statement stmt = null;
        try {
        	conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT volitelny_predmet.kod, volitelny_predmet.nazov FROM volitelny_predmet");
			while(rs.next()){
				predmety.add(new Predmet(rs.getString(1), rs.getString(2)));
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
		return predmety;
	}
}
