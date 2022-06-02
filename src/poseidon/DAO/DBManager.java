package poseidon.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
* Gestisce la logica di connessione al DBMS.
*
* Implementa il pattern sigleton:
* - Il costruttore e' privato
* - C'e' un metodo static public getInstance() che restistuisce un riferimento all'unica istanza dell'oggetto.
*
*
*/

class DBManager {
	private DBManager() throws SQLException {
	}

	private static DBManager instance = null;

	public static DBManager getInstance() throws SQLException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost/poseidon_db", "poseidon", "project");
		}
		return connection;
	}

	public void closeConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}

	protected Connection connection;
	
}
