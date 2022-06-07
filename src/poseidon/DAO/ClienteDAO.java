package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import poseidon.entity.Cliente;

public class ClienteDAO {

	public static void creaCliente(Cliente c) throws SQLException{
		// PRECONDITIONS: il parametro in input è un riferimento ad un oggetto valido  della classe Cliente
		// POSTCONDITIONS: il cliente è stato correttamente inserito all'interno della tabella CLIENTE
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO CLIENTE VALUES (?, ?, ?, ?)");
			
			statement.setInt(1, c.getCodiceCliente());
			statement.setString(2, c.getCognome());
			statement.setString(3, c.getNome());
			statement.setString(4, c.getPassword());
			statement.executeUpdate();
			
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
		
	}
	
	public static List<Cliente> readallCliente() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della tabella CLIENTE
		
		List<Cliente> lista = new ArrayList<Cliente>();
		Cliente c = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();
		
		r = s.executeQuery("SELECT * FROM CLIENTE");
	
		while (r.next()) {
			int codiceCliente = r.getInt("codicecliente");
			String cognome = r.getString("cognome");
			String nome = r.getString("nome");
			String password = r.getString("password");
			
			c = new Cliente(cognome, nome, password, codiceCliente);
			lista.add(c);
		}
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return lista;
	}
	
	public static Cliente readCliente(int codiceCliente) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella CLIENTE

		Cliente cliente = null;
		Connection connection = null;
		PreparedStatement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("SELECT * FROM CLIENTE WHERE codicecliente = ?");
		s.setInt(1, codiceCliente);

		r = s.executeQuery();

		if (r.next()) {
			String cognome = r.getString("cognome");
			String nome = r.getString("nome");
			String password = r.getString("password");
			
			cliente = new Cliente(cognome, nome, password, codiceCliente);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return cliente;
	}
}
