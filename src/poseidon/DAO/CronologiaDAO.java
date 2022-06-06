package poseidon.DAO;

import java.sql.*;
import java.util.*;
import poseidon.entity.*;
import java.time.*;

public class CronologiaDAO {

	public static void creaCronologia(CronologiaAcquisti c) throws SQLException{
		// PRECONDITIONS: il parametro in input � un riferimento ad un oggetto valido 
		// della classe CronologiaAcquisti
		// POSTCONDITIONS: la cronologia � stato correttamente inserita all'interno della tabella CRONOLOGIAACQUISTI
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO CRONOLOGIAACQUISTI VALUES"
													+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			statement.setInt(1,  c.getCodiceCliente());
			statement.setInt(2, c.getCorsa().getCodiceCorsa());
			statement.setTime(3, java.sql.Time.valueOf(c.getCorsa().getOrarioPartenza()));
			statement.setTime(4, java.sql.Time.valueOf(c.getCorsa().getOrarioArrivo()));
			statement.setString(5, c.getCorsa().getPortoPartenza());
			statement.setString(6, c.getCorsa().getPortoArrivo());
			statement.setDouble(7, c.getCorsa().getPrezzo());
			statement.setInt(8,  c.getBiglietto().getCodiceBiglietto());
			statement.setDate(9, java.sql.Date.valueOf(c.getBiglietto().getData()));
			statement.setTime(10, java.sql.Time.valueOf(c.getBiglietto().getOra()));
			statement.setInt(11, c.getBiglietto().getCodiceImpiegato());
			if(c.getBiglietto() instanceof BigliettoVeicolo) {
				BigliettoVeicolo v = (BigliettoVeicolo)c.getBiglietto();
				statement.setString(12, "veicolo");
				statement.setString(13, v.getTarga());				
			}
			else {
				statement.setString(12, "passeggero");
				statement.setNull(13, java.sql.Types.VARCHAR);	
			}
			statement.setString(12, c.getRicevuta());
			
			statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();	
	}
	
	public static CronologiaAcquisti readCronologia(int codiceCliente, int codiceCorsa, String ricevuta) throws SQLException {
		// PRECONDITIONS: il codiceCliente, il codiceCorsa e la ricevuta sono numeri > 0
		// POSTCONDITIONS: viene restituito un riferimento ad un oggetto della classe CronologiaAcquisti
		// corrispondente alla Cronologia avente il codiceCliente, il codiceCorsa e la ricevuta passati in ingresso;
		// se la Cronologia non � stata trovata, viene restituito un riferimento null
		
		CronologiaAcquisti c_a = null;
		Connection connection = null;
		PreparedStatement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("SELECT * FROM CRONOLOGIAACQUISTI WHERE CODICECLIENTE = ?," 
										+ " CODICECORSA = ?, RICEVUTA = ?");
		s.setInt(1,  codiceCliente);
		s.setInt(2, codiceCorsa);
		s.setString(3,  ricevuta);
		
		r = s.executeQuery();
		
		if (r.next()) {		
			LocalTime orarioPartenza = null;
			if (r.getTime("orarioPartenza") != null) 
				orarioPartenza = r.getTime("orarioPartenza").toLocalTime();
			LocalTime orarioArrivo = null;
			if (r.getTime("orarioArrivo") != null) 
				orarioArrivo = r.getTime("orarioArrivo").toLocalTime();				
			String portoPartenza = r.getString("portoPartenza");
			String portoArrivo = r.getString("portoArrivo");
			double prezzo = r.getDouble("prezzo");
			Corsa corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			
			int codiceBiglietto = r.getInt("codiceBiglietto");
			LocalDate data = null;
			if (r.getDate("Data") != null)
				data = r.getDate("Data").toLocalDate();
			LocalTime ora = null;
			if (r.getDate("Ora") != null)
				ora = r.getTime("Ora").toLocalTime();
			int codiceImpiegato = r.getInt("codiceImpiegato");	
			Biglietto biglietto = null;
			if (r.getString("tipo").equals("veicolo")) {
				String targa = r.getString("targa");
				biglietto = new BigliettoVeicolo(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato, targa);
			}
			else {
				biglietto = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
			}
			
			c_a = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
		}
		
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
			double prezzo = r.getDouble("prezzo");
			Corsa corsa = new Corsa(codiceCorsa, orarioPartenza, orarioArrivo, portoPartenza, portoArrivo, prezzo);
			
			int codiceBiglietto = r.getInt("codiceBiglietto");
			LocalDate data = null;
			if (r.getDate("Data") != null)
				data = r.getDate("Data").toLocalDate();
			LocalTime ora = null;
			if (r.getDate("Ora") != null)
				ora = r.getTime("Ora").toLocalTime();
			int codiceImpiegato = r.getInt("codiceImpiegato");	
			Biglietto biglietto = null;
			if (r.getString("tipo").equals("veicolo")) {
				String targa = r.getString("targa");
				biglietto = new BigliettoVeicolo(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato, targa);
			}
			else {
				biglietto = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
			}
			
			String ricevuta = r.getString("ricevuta");
			
			c_a = new CronologiaAcquisti(codiceCliente, corsa, biglietto, ricevuta);
			lista.add(c_a);
		}
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return lista;
	}
	
	public static void updateCronologia(CronologiaAcquisti cronologia) throws SQLException {
		// PRECONDITIONS: il parametro in input � un riferimento ad un oggetto valido 
		// della classe CronologiaAcquisti
		// POSTCONDITIONS: l'istanza della tabella CRONOLOGIAACQUISTI � stata correttamente modificata
		
		Connection connection = null;
		PreparedStatement s = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.prepareStatement("UPDATE CRONOLOGIAACQUISTI SET orariopartenza = ?,"
										+ " orarioarrivo = ?,"
										+ " portopartenza = ?,"
										+ " portoarrivo = ?,"
										+ " prezzo = ?,"
										+ " codicebiglietto = ?,"
										+ " data = ?,"
										+ " ora = ?,"
										+ " codiceimpiegato = ?,"
										+ " tipo = ?,"
										+ " targa = ?"
										+ " WHERE codicecliente = ? AND"
										+ " ricevuta = ? AND"
										+ " codicecorsa = ?");
		
		s.setTime(1, java.sql.Time.valueOf(cronologia.getCorsa().getOrarioPartenza()));
		s.setTime(2, java.sql.Time.valueOf(cronologia.getCorsa().getOrarioArrivo()));
		s.setString(3, cronologia.getCorsa().getPortoPartenza());
		s.setString(4, cronologia.getCorsa().getPortoArrivo());
		s.setDouble(5, cronologia.getCorsa().getPrezzo());
		s.setInt(6, cronologia.getBiglietto().getCodiceBiglietto());
		s.setDate(7, java.sql.Date.valueOf(cronologia.getBiglietto().getData()));
		s.setTime(8, java.sql.Time.valueOf(cronologia.getBiglietto().getOra()));
		s.setInt(9, cronologia.getBiglietto().getCodiceImpiegato());
		if(cronologia.getBiglietto() instanceof BigliettoVeicolo) {
			BigliettoVeicolo v = (BigliettoVeicolo)cronologia.getBiglietto();
			s.setString(10, "veicolo");
			s.setString(11, v.getTarga());				
		}
		else {
			s.setString(10, "passeggero");
			s.setNull(11, java.sql.Types.VARCHAR);	
		}
		s.setInt(12, cronologia.getCodiceCliente());
		s.setString(13, cronologia.getRicevuta());
		s.setInt(14, cronologia.getCorsa().getCodiceCorsa());
		
		s.executeUpdate();
		
		s.close();
		DBManager.getInstance().closeConnection();
	}
}
