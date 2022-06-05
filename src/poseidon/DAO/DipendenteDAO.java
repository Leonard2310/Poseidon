package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poseidon.entity.Dipendente;

public class DipendenteDAO {

	public static void creaDipendente(Dipendente d) throws SQLException{
		// PRECONDITIONS: il parametro in input è un riferimento ad un oggetto valido 
		// della classe Dipendente
		// POSTCONDITIONS: il dipendente è stato correttamente inserito all'interno della tabella DIPENDENTE
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO DIPENDENTE VALUES (?, ?, ?, ?)");
			
			statement.setInt(1, d.getCodiceImpiegato());
			statement.setString(2, d.getCognome());
			statement.setString(3, d.getNome());
			statement.setString(4, d.getPassword());
			statement.executeUpdate();
			
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
		
	}
	
	public static List<Dipendente> readallDipendente() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della tabella DIPENDENTE
		
		List<Dipendente> lista = new ArrayList<Dipendente>();
		Dipendente d = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();
		
		r = s.executeQuery("SELECT * FROM DIPENDENTE");
	
		while (r.next()) {
			int codiceImpiegato = r.getInt("codiceimpiegato");
			String cognome = r.getString("cognome");
			String nome = r.getString("nome");
			String password = r.getString("password");
			
			d = new Dipendente(cognome, nome, password, codiceImpiegato);
			lista.add(d);
		}
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return lista;
	}
}
