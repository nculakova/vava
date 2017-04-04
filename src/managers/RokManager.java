package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Rok;

public class RokManager extends AllTablesManager {

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Rok(rs.getInt("rok_1"), rs.getInt("rok_2")));
	}

	@SuppressWarnings("unchecked")
	public List<Rok> getAllRok() throws SQLException {
		return(selectQuery("SELECT * FROM rok ORDER BY rok_1"));
	}
}
