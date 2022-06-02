package poseidon.entity;

import java.util.*;

public class Utente {
	private String cognome;
	private String nome;
	private String password;

	public Utente(String cognome, String nome, String password) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.password = password;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int login(String cognome, String nome, String password) {
		// FUNZIONE NON IMPLEMENTATA
		
		int codiceCliente = -1;
		
		return codiceCliente;
	}
	
	public boolean logout() {
		// FUNZIONE NON IMPLEMENTATA
		
		return true;
	}
	
	public List<Corsa> ricercaCorsa(int codiceCorsa) {
		// FUNZIONE NON IMPLEMENTATA
		
		List<Corsa> listaCorsa = new List<Corsa>;
		Corsa c = CorsaDAO.readCorsa(codiceCorsa);
		
		listaCorsa.add(c);
		
		return listaCorsa;
	}
	
}
