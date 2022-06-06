package poseidon.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import poseidon.entity.*;

public class PortoDAO {

	public static void creaPorto(Porto p) throws SQLException {
		// PRECONDITION: il parametro di input e' un riferimento ad un oggetto valido della classe Porto
		// POSTCONDITION: il porto viene inserito nella tabella PORTO
			
		Connection connection = null;
		PreparedStatement statement = null;
		
		connection = DBManager.getInstance().getConnection();

		try { 
			statement = connection.prepareStatement("INSERT INTO PORTO VALUES (?)");
			
			statement.setString(1, p.getCitta());
 			
			statement.executeUpdate();
		
		} finally {
			if (statement != null) {
				statement.close();
			}
		}		
		
		DBManager.getInstance().closeConnection();
	}
	
}
