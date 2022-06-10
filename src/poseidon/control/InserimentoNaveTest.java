package poseidon.control;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import poseidon.DAO.*;
import poseidon.entity.*;

class InserimentoNaveTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		NaveDAO.deleteallNave();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		NaveDAO.deleteallNave();
	}

	@BeforeEach
	void setUp() throws Exception {
		NaveDAO.deleteallNave();
	}

	@AfterEach
	void tearDown() throws Exception {
		NaveDAO.deleteallNave();
	}

	@Test
	void InserimentoNaveTest_1() throws SQLException {
		
		/* Output attesi */
		String nome = "Estate";
		int capienzaAutoveicoli = 30;
		int capienzaPasseggeri = 270;
		String categoria = "traghetto";
		int codiceCorsa = 101;
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", 270, 30, 101);

		/* Controllo Output */
		assertEquals(nome, n.getNome());
		assertEquals(capienzaAutoveicoli, n.getCapienzaAutoveicoli());
		assertEquals(capienzaPasseggeri, n.getCapienzaPasseggeri());
		assertEquals(categoria, n.getCategoria());
		assertEquals(codiceCorsa, n.getCodiceCorsa());
				
	}

	@Test
	void InserimentoNaveTest_2() throws SQLException {

		/* Output attesi */
		String nome = "IN";
		int capienzaAutoveicoli = 0;
		int capienzaPasseggeri = 270;
		String categoria = "aliscafo";
		int codiceCorsa = 102;
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("IN", "aliscafo", 270, 0, 102);

		/* Controllo Output */
		assertEquals(nome, n.getNome());
		assertEquals(capienzaAutoveicoli, n.getCapienzaAutoveicoli());
		assertEquals(capienzaPasseggeri, n.getCapienzaPasseggeri());
		assertEquals(categoria, n.getCategoria());
		assertEquals(codiceCorsa, n.getCodiceCorsa());
				
	}
	
	@Test
	void InserimentoNaveTest_3() throws SQLException {
		
		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Coooooooooossssssssstttttttttttttttaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "traghetto", 270, 30, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);		

	}
	
	@Test
	void InserimentoNaveTest_4() throws SQLException {
		
		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "Battello", 270, 30, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);		

	}

	@Test
	void InserimentoNaveTest_5() throws SQLException {
		
		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", 500, 30, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);		

	}

	@Test
	void InserimentoNaveTest_6() throws SQLException {

		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", -1, 30, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);		

	}

	@Test
	void InserimentoNaveTest_7() throws SQLException {

		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", 270, 150, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);		

	}

	@Test
	void InserimentoNaveTest_8() throws SQLException {

		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", 270, -1, 101);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);	

	}

	@Test
	void InserimentoNaveTest_9() throws SQLException {

		/* Output attesi */
		Nave nave = null;
		List<Nave> lista_nave = new ArrayList<Nave>(0);
		
		/* Funzione */
		Nave n = gestisciCorsa.inserimentoNave("Estate", "traghetto", 270, 30, -1);
		List<Nave> lista_n = NaveDAO.readallNave();
		
		/* Controllo Output */
		assertEquals(lista_nave, lista_n);
		assertEquals(nave, n);	

	}

}
