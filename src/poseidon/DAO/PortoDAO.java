package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public static List<Porto> readallPorto() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella PORTO

		List<Porto> lista_porto = new ArrayList<Porto>();
		Porto porto = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM PORTO");

		while (r.next()) {
			String citta = r.getString("citta");
			
			porto = new Porto(citta);
			lista_porto.add(porto);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return lista_porto;
	}
	
	public static void deletePorto(String citta) throws SQLException {
		// PRECONDITION: e' stata aggiunta almeno un porto all'interno del database.
		// POSTCONDITION: elimina il porto dalla tabella PORTO. Se non la trova non modifica il database.
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();
		
		try {
			statement = connection.prepareStatement("DELETE FROM PORTO WHERE " + "citta = ?");

			statement.setString(1, citta);
			statement.executeUpdate();
			
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
		
		DBManager.getInstance().closeConnection();
		
	}

	public static void deleteallPorto() throws SQLException {
		// PRECONDITION: - 
		// POSTCONDITION: la tabella PORTO � stata eliminata.
		
		Connection connection = null;
		Statement statement = null;
				
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM PORTO");
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
				
		DBManager.getInstance().closeConnection();	
		
	}
	
}
