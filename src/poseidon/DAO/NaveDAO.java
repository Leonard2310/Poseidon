package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poseidon.entity.Nave;

public class NaveDAO {
	
	public static void creaNave(Nave n) throws SQLException {
		// PRECONDITION: il parametro di input e' un riferimento ad un oggetto valido della classe Nave
		// POSTCONDITION: la nave viene inserita nella tabella NAVE
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO NAVE VALUES (?, ?, ?, ?, ?)");
			
			statement.setString(1, n.getNome());			
			statement.setInt(2,n.getCapienzaAutoveicoli());
			statement.setInt(3, n.getCapienzaPassegeri());
			statement.setString(4, n.getCategoria());
			statement.setInt(5, n.getCodiceCorsa());
 			
			statement.executeUpdate();
		
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
	}
	
	public static Nave readNave(String nome) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella NAVE

		Nave nave = null;
		Connection connection = null;
		PreparedStatement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("SELECT * FROM NAVE WHERE nome = ?" );
		s.setString(1, nome);

		r = s.executeQuery();

		if (r.next()) {
			int capienzaAutoveicoli = r.getInt("capienzaAutoveicoli");
			int capienzaPasseggeri = r.getInt("capienzaPasseggeri");
			int codiceCorsa = r.getInt("codiceCorsa");
			String categoria = r.getString("categoria");

			nave = new Nave(nome, capienzaAutoveicoli, capienzaPasseggeri, categoria, codiceCorsa);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return nave;
	}

	public static List<Nave> readallNave() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella NAVE

		List<Nave> lista_nave = new ArrayList<Nave>();
		Nave nave = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM NAVE");

		while (r.next()) {
			String nome = r.getString("nome");
			int capienzaAutoveicoli = r.getInt("capienzaAutoveicoli");
			int capienzaPasseggeri = r.getInt("capienzaPasseggeri");
			int codiceCorsa = r.getInt("codiceCorsa");
			String categoria = r.getString("categoria");

			nave = new Nave(nome, capienzaAutoveicoli, capienzaPasseggeri, categoria, codiceCorsa);
			lista_nave.add(nave);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return lista_nave;
	}
	
	public static void deleteCorsa(String nome, int capienzaAutoveicoli, int capienzaPasseggeri, int codiceCorsa, String categoria) throws SQLException {
		// PRECONDITION: � stata aggiunta almeno una nave all'interno del database.
		// POSTCONDITION: elimina la nave dalla tabella NAVE. Se non la trova non modifica il database.
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();
		
		try {
			statement = connection.prepareStatement("DELETE FROM NAVE WHERE " + 
					"nome = ? AND capienzaAutoveicoli = ? AND capienzaPasseggeri = ? AND codiceCorsa = ? AND categoria = ?");
			
			statement.setString(1, nome);
			statement.setInt(2, capienzaAutoveicoli);
			statement.setInt(3, capienzaPasseggeri);
			statement.setInt(4, codiceCorsa);
			statement.setString(5, categoria);
			statement.executeUpdate();
			
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
		
		DBManager.getInstance().closeConnection();
		
	}

	public static void deleteallNave() throws SQLException {
		// PRECONDITION: - 
		// POSTCONDITION: la tabella NAVE � stata eliminata.
		
		Connection connection = null;
		Statement statement = null;
				
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM NAVE");
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
				
		DBManager.getInstance().closeConnection();	
		
	}


}
