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

class InserimentoCorsaTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CorsaDAO.deleteallCorsa();
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		CorsaDAO.deleteallCorsa();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testInserimentoCorsa_1() throws SQLException {
		
		/* Output attesi */
		int codiceCorsa = 101;
		String portoPartenza = "Napoli";
		String portoArrivo = "Ischia";
		LocalTime orarioPartenza = LocalTime.of(10,15);
		LocalTime orarioArrivo = LocalTime.of(13,40);
		double prezzo = 20.00; 
		String nome = "Estate";
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Ischia", 20.00, "Estate");
		Nave n = NaveDAO.readNave(nome);

		/* Controllo Output */
		assertEquals(codiceCorsa, c.getCodiceCorsa());
		assertEquals(portoPartenza, c.getPortoPartenza());
		assertEquals(portoArrivo, c.getPortoArrivo());
		assertEquals(orarioPartenza, c.getOrarioPartenza());
		assertEquals(orarioArrivo, c.getOrarioArrivo());
		assertEquals(prezzo, c.getPrezzo());
		assertEquals(nome, n.getNome());		
				
	}

	@Test
	void testInserimentoCorsa_2() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		final int max_string_size = 50;
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Naaaaaaaaaappppppppppoooooooooolllllllllliiiiiiiiiiiiii", "Ischia", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(max_string_size, c.getPortoPartenza().length());
		assertEquals(lista_corse, lista_c);	
	}

	@Test
	void testInserimentoCorsa_3() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Milano", "Ischia", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);	
	}

	@Test
	void testInserimentoCorsa_4() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		final int max_string_size = 50;
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Isssssssssscccccccccchhhhhhhhhhiiiiiiiiiiiaaaaaaaaaaaaaaa", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertFalse(max_string_size < c.getPortoArrivo().length());
		assertEquals(lista_corse, lista_c);		
	}
	
	@Test
	void testInserimentoCorsa_5() throws SQLException {
		
		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Milano", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);		
		assertNull(c.getCodiceCorsa());

	}
	
	@Test
	void testInserimentoCorsa_6() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(15,15), LocalTime.of(13,40), "Napoli", "Ischia", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);		

	}
	
	@Test
	void testInserimentoCorsa_7() throws SQLException {
		
		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(40,40), "Napoli", "Ischia", 20.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);		

	}
	
	@Test
	void testInserimentoCorsa_8() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Ischia", -1.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);		

	}
	
	@Test
	void testInserimentoCorsa_9() throws SQLException {
	
		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Ischia", 250.00, "Estate");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);		

	}
	
	@Test
	void testInserimentoCorsa_10() throws SQLException {

		/* Output attesi */
		Corsa corsa = null;
		List<Corsa> lista_corse = new ArrayList<Corsa>(0);
		
		/* Funzione */
		Corsa c = gestisciCorsa.inserimentoCorsa(LocalTime.of(10,15), LocalTime.of(13,40), "Napoli", "Ischia", 20.00, "Coooooooooossssssssstttttttttttttttaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		List<Corsa> lista_c = CorsaDAO.readallCorsa();

		/* Controllo Output */
		assertEquals(corsa, c);
		assertEquals(lista_corse, lista_c);	
	}
		
}
