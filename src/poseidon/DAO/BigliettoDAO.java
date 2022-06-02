package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import poseidon.entity.Biglietto;
import poseidon.entity.BigliettoVeicolo;

public class BigliettoDAO {

	public static void creaBiglietto(Biglietto b) throws SQLException{
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		/* Apertura della connessione */
		connection = DBManager.getInstance().getConnection();

		/* Inserimento del biglietto nel DataBase */
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
			} else if (b instanceof BigliettoVeicolo) {
				statement.setString(6, "PASSEGGERO");
				statement.setNull(7, java.sql.Types.VARCHAR);
			}
			statement.executeUpdate();
			
			
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		/* Chiusura della connessione */
		DBManager.getInstance().closeConnection();
		
	}
	
}
