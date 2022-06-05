package poseidon.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import poseidon.entity.Corsa;

public class CorsaDAO {

	public static List<Corsa> readallCorsa() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della
		// tabella CORSA

		List<Corsa> lista_corsa = new ArrayList<Corsa>();
		Corsa corsa = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM CORSA");

		while (r.next()) {
			int codiceCorsa = r.getInt("codiceCorsa");
			LocalTime orarioPartenza = null;
			if (r.getTime("orarioPartenza") != null)
				orarioPartenza = r.getTime("orarioPartenza").toLocalTime();
			LocalTime orarioArrivo = null;
			if (r.getTime("orarioArrivo") != null)
				orarioArrivo = r.getTime("orarioArrivo").toLocalTime();
			String portoPartenza = r.getString("portoArrivo");
			String portoArrivo = r.getString("portoArrivo");

			corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo);
			lista_corsa.add(corsa);
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return lista_corsa;
	}

	public static List<Corsa> readCorsaFiltri(LocalTime orarioPartenza, LocalTime orarioArrivo, String portoPartenza, String portoArrivo) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della tabella CORSA

		List<Corsa> lista_corsa = new ArrayList<Corsa>();
		Corsa corsa = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM CORSA");

		while (r.next()) {
			if (portoPartenza == r.getString("portoPartenza") && portoArrivo == r.getString("portoArrivo") 
					&& orarioPartenza == r.getTime("orarioPartenza").toLocalTime() 
					&& orarioArrivo == r.getTime("orarioArrivo").toLocalTime()) {
				int codiceCorsa = r.getInt("codiceCorsa");
				if (r.getTime("orarioArrivo") != null)
					orarioArrivo = r.getTime("orarioArrivo").toLocalTime();
				portoPartenza = r.getString("portoArrivo");
				portoArrivo = r.getString("portoArrivo");
				
				corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo);
				
				lista_corsa.add(corsa);
			}
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return lista_corsa;
	}

	public static Corsa readCorsa(int codiceCorsa) throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita un'instanza specifica della tabella CORSA

		Corsa corsa = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;

		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();

		r = s.executeQuery("SELECT * FROM CORSA");

		while (r.next()) {
			if (codiceCorsa == r.getInt("codiceCorsa")) {
				LocalTime orarioPartenza = null;
				if (r.getTime("orarioPartenza") != null)
					orarioPartenza = r.getTime("orarioPartenza").toLocalTime();
				LocalTime orarioArrivo = null;
				if (r.getTime("orarioArrivo") != null)
					orarioArrivo = r.getTime("orarioArrivo").toLocalTime();
				String portoPartenza = r.getString("portoArrivo");
				String portoArrivo = r.getString("portoArrivo");

				corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo);
			}
		}

		s.close();
		DBManager.getInstance().closeConnection();

		return corsa;
	}

}
