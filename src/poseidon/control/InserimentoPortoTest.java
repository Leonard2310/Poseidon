package poseidon.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import poseidon.DAO.*;
import poseidon.entity.*;
import poseidon.control.*;

class InserimentoPortoTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		PortoDAO.deleteallPorto();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		PortoDAO.deleteallPorto();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void InserimentoPortoTest_1() throws SQLException {
		
		/* Output attesi */
		String citta = "Pozzuoli";
		
		/* Funzione */
		Porto p = gestisciCorsa.inserimentoPorto("Pozzuoli");
		
		/* Controllo Output */
		assertEquals(citta, p.getCitta());		
		
	}

	@Test
	void InserimentoPortoTest_2() throws SQLException {
		
		/* Output attesi */
		Porto porto = null;
		List<Porto> lista_porto = new ArrayList<Porto>(0);
		
		/* Funzione */
		final int max_string_size = 50;
		Porto p = gestisciCorsa.inserimentoPorto("Poooooooooooooooooozzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzuuuuuuuuuuuuooooooooooooollllllllllllllllllllliiiiiiiiiiiiiiiiiiiii");
		List<Porto> lista_p = PortoDAO.readallPorto();

		/* Controllo Output */
		assertEquals(max_string_size, p.getCitta().length());
		assertEquals(lista_porto, lista_p);
		assertEquals(porto, p);
		
	}
	
	@Test
	void InserimentoPortoTest_3() throws SQLException {

		/* Output attesi */
		Porto porto = null;
		List<Porto> lista_porto = new ArrayList<Porto>(0);
		
		/* Funzione */
		Porto p = gestisciCorsa.inserimentoPorto("Pozzuoli");
		List<Porto> lista_p = PortoDAO.readallPorto();
		
		/* Controllo Output */
		assertEquals(lista_porto, lista_p);
		assertEquals(porto, p);
		
	}

}
