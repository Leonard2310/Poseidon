package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import poseidon.entity.*;

public class PortoDAO {

	public static void creaPorto(Porto p) throws SQLException {
		// PRECONDITION: il parametro di input e' un riferimento ad un oggetto valido della classe Porto
		// POSTCONDITION: il porto viene inserito nella tabella PORTO
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO PORTO VALUES (?)");
			
			statement.setString(1, p.getCitta());
 			
			statement.executeUpdate();
		
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
	}
	
	public static Porto readPorto(String citta) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella PORTO

		Porto porto = null;
		Connection connection = null;
		PreparedStatement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("SELECT * FROM PORTO WHERE citta = ?" );
		s.setString(1, citta);

		r = s.executeQuery();

		if (r.next()) {
			citta = r.getString("citta");

			porto = new Porto(citta);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return porto;
	}
	
}
