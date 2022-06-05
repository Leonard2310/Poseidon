package poseidon.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import poseidon.entity.Nave;

public class NaveDAO {
	
	public static Nave readNave(String nome) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella NAVE

		Nave nave = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM CORSA");

		while (r.next()) {
			if (nome == r.getString("nome")) {
				int capienzaAutoveicoli = r.getInt("capienzaAutoveicoli");
				int capienzaPasseggeri = r.getInt("capienzaPasseggeri");
				double prezzo = r.getDouble("prezzo");
				int codiceCorsa = r.getInt("codiceCorsa");
				String categoria = r.getString("categoria");

				nave = new Nave(nome, capienzaAutoveicoli, capienzaPasseggeri, prezzo, categoria, codiceCorsa);
			}
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return nave;
	}

}
