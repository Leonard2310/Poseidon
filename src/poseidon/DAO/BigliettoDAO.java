package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poseidon.entity.Biglietto;
import poseidon.entity.BigliettoPasseggero;
import poseidon.entity.BigliettoVeicolo;

public class BigliettoDAO {

	public static void creaBiglietto(Biglietto b) throws SQLException{
		// PRECONDITIONS: il parametro in input è un riferimento ad un oggetto valido 
		// della classe Biglietto
		// POSTCONDITIONS: il biglietto è stato correttamente inserito all'interno della tabella BIGLIETTO
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO BIGLIETTO VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			statement.setInt(1, b.getCodiceBiglietto());
			statement.setDate(2, java.sql.Date.valueOf(b.getData()));
			statement.setTime(3, java.sql.Time.valueOf(b.getOra()));
			statement.setInt(4, b.getCodiceCorsa());
			statement.setInt(5, b.getCodiceImpiegato());
			if (b instanceof BigliettoVeicolo) {
				BigliettoVeicolo v = (BigliettoVeicolo)b;
				statement.setString(6, "VEICOLO");
				statement.setString(7, v.getTarga());
			} else if (b instanceof BigliettoPasseggero) {
				statement.setString(6, "PASSEGGERO");
				statement.setNull(7, java.sql.Types.VARCHAR);
			}
			statement.executeUpdate();
			
			
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
		
	}
	
	public static List<Biglietto> readallBiglietto() throws SQLException {
		// PRECONDITIONS: -
		// POSTCONDITIONS: viene restituita una lista contenente tutte le istanze della tabella BIGLIETTO
		
		List<Biglietto> lista = new ArrayList<Biglietto>();
		Biglietto b = null;
		Connection connection = null;
		Statement s = null;
		ResultSet r = null;
		
		connection = DBManager.getInstance().getConnection();
		s = connection.createStatement();
		
		r = s.executeQuery("SELECT * FROM BIGLIETTO");
	
		while (r.next()) {
			int codiceBiglietto = r.getInt("codicebiglietto");
			LocalDate data = null;
			if (r.getDate("data") != null) 
				data = r.getDate("data").toLocalDate();
			LocalTime ora = null;
			if (r.getTime("ora") != null) 
				ora = r.getTime("ora").toLocalTime();				
			int codiceCorsa = r.getInt("codicecorsa");
			int codiceImpiegato = r.getInt("codiceimpiegato");		
			
			if (r.getString("tipo").equals("veicolo")) {
				String targa = r.getString("targa");
				b = new BigliettoVeicolo(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato, targa);
			}
			else {
				b = new BigliettoPasseggero(codiceBiglietto, data, ora, codiceCorsa, codiceImpiegato);
			}
			
			lista.add(b);
		}
		
		s.close();
		DBManager.getInstance().closeConnection();
		
		return lista;
	}
	
	public static void deleteBiglietto(int codiceBiglietto, int codiceCorsa) throws SQLException{
		// PRECONDITIONS: i parametri in input sono > 0
		// POSTCONDITIONS: se trovato, il biglietto � stato correttamente eliminato dalla tabella BIGLIETTO;
		// altrimenti, il DB non � stato modificato
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("DELETE FROM BIGLIETTO WHERE "
													+ "codicebiglietto = ? AND codicecorsa = ?");
			
			statement.setInt(1, codiceBiglietto);
			statement.setInt(2, codiceCorsa);
			statement.executeUpdate();
			
			
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
		
	}
}
