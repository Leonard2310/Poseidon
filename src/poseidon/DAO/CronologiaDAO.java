package poseidon.DAO;

import java.sql.*;
import java.util.*;
import poseidon.entity.*;
import java.time.*;

public class CronologiaDAO {

	
	public static CronologiaAcquisti readCronologia(int codiceCliente) throws SQLException {
		// PRECONDITIONS: il codiceCliente è un numero > 0
		// POSTCONDITIONS: viene restituito un riferimento ad un oggetto della classe CronologiaAcquisti
		// corrispondente alla Cronologia avente il codiceCliente passato in ingresso
		
		CronologiaAcquisti c_a = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();
		
		r = s.executeQuery("SELECT * FROM CRONOLOGIAACQUISTI WHERE CODICECLIENTE = " + codiceCliente);
		r.next();
		
		int codiceCorsa = r.getInt("codiceCorsa");
		LocalTime orarioPartenza = null;
		if (r.getTime("orarioPartenza") != null) 
			orarioPartenza = r.getTime("orarioPartenza").toLocalTime();
		LocalTime orarioArrivo = null;
		if (r.getTime("orarioArrivo") != null) 
			orarioArrivo = r.getTime("orarioArrivo").toLocalTime();				
		String portoPartenza = r.getString("portoPartenza");
		String portoArrivo = r.getString("portoArrivo");
		Corsa corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo);
		
		int codiceBiglietto = r.getInt("codiceBiglietto");
		LocalDate data = null;
		if (r.getDate("Data") != null)
			data = r.getDate("Data").toLocalDate();
		LocalTime ora = null;
		if (r.getDate("Ora") != null)
			ora = r.getTime("Ora").toLocalTime();
		int codiceImpiegato = r.getInt("codiceImpiegato");			
		Biglietto biglietto = new Biglietto(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
		
		int ricevuta = r.getInt("ricevuta");
		
		c_a = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
		c_a = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return c_a;
	}
	
	public static List<CronologiaAcquisti> readallCronologia() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della tabella
		// CRONOLOGIAACQUISTI
		
		List<CronologiaAcquisti> lista = new ArrayList<CronologiaAcquisti>();
		CronologiaAcquisti c_a = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();
		
		r = s.executeQuery("SELECT * FROM CRONOLOGIAACQUISTI");
	
		while (r.next()) {
			int codiceCliente = r.getInt("codiceCliente");
			
			int codiceCorsa = r.getInt("codiceCorsa");
			LocalTime orarioPartenza = null;
			if (r.getTime("orarioPartenza") != null) 
				orarioPartenza = r.getTime("orarioPartenza").toLocalTime();
			LocalTime orarioArrivo = null;
			if (r.getTime("orarioArrivo") != null) 
				orarioArrivo = r.getTime("orarioArrivo").toLocalTime();				
			String portoPartenza = r.getString("portoPartenza");
			String portoArrivo = r.getString("portoArrivo");
			Corsa corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo);
			
			int codiceBiglietto = r.getInt("codiceBiglietto");
			LocalDate data = null;
			if (r.getDate("Data") != null)
				data = r.getDate("Data").toLocalDate();
			LocalTime ora = null;
			if (r.getDate("Ora") != null)
				ora = r.getTime("Ora").toLocalTime();
			int codiceImpiegato = r.getInt("codiceImpiegato");			
			Biglietto biglietto = new Biglietto(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
			
			int ricevuta = r.getInt("ricevuta");
			
			c_a = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
			lista.add(c_a);
		}
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return lista;
	}
	
	public static void updateCronologia(CronologiaAcquisti cronologia) throws SQLException {
		// PRECONDITIONS: il parametro in input è un riferimento ad un oggetto valido 
		// della classe CronologiaAcquisti
		// POSTCONDITIONS: l'istanza della tabella CRONOLOGIAACQUISTI è stata correttamente modificata
		
		Connection connection = null;
		PreparedStatement s = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("UPDATE CRONOLOGIAACQUISTI SET codicecorsa = ?,"
										+ " orariopartenza = ?,"
										+ " orarioarrivo = ?,"
										+ " portopartenza = ?,"
										+ " portoarrivo = ?,"
										+ " codicebiglietto = ?,"
										+ " data = ?,"
										+ " ora = ?,"
										+ " codiceimpiegato = ?,"
										+ " ricevuta = ?"
										+ " WHERE codicecliente = ?");
		
		s.setInt(1, cronologia.getCorsa().getCodiceCorsa());
		s.setTime(2, java.sql.Time.valueOf(cronologia.getCorsa().getOrarioPartenza()));
		s.setTime(3, java.sql.Time.valueOf(cronologia.getCorsa().getOrarioArrivo()));
		s.setString(4, cronologia.getCorsa().getPortoPartenza());
		s.setString(5, cronologia.getCorsa().getPortoArrivo());
		s.setInt(6, cronologia.getBiglietto().getCodiceBiglietto());
		s.setDate(7, java.sql.Date.valueOf(cronologia.getBiglietto().getData()));
		s.setTime(8, java.sql.Time.valueOf(cronologia.getBiglietto().getOra()));
		s.setInt(9, cronologia.getBiglietto().getCodiceImpiegato());
		s.setInt(10, cronologia.getRicevuta());
		s.setInt(11, cronologia.getCodiceCliente());
		
		s.executeUpdate();
		
		s.close();
		DBManager.getInstance().closeConnection();
	}
}
