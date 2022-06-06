package poseidon.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


import poseidon.boundary.*;

public class PoseidonMain {

	public static void main(String[] args) {
		
		//new MyFrame();
		
		inputReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(System.in);
		int option = 0;
		char answer = 'n';
		int codice = -1;
		
		do {
			System.out.println("Seleziona operazione: \n" +
					"\t1) Login\n" +
					"\t2) Registrati\n" +
					"\t3) Ricerca Corsa\n" +
					"\t4) Esci");
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				option = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (option) {
				case 1: { 
					System.out.println("Sei un dipendente [y/n]?");
					try {
						answer = inputReader.readLine().charAt(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (answer == 'y') {
						codice = ApplicationConsoleBoundary.login(1);
						if (codice > 0) { 
							DipendenteConsoleBoundary.showDipendenteConsoleBoundary(codice);
						}
					}
					else {
						codice = ApplicationConsoleBoundary.login(0);
						if (codice > 0) { 
							ClienteConsoleBoundary.showClienteConsoleBoundary(codice);
						}
					}
					break; 
				}
				
				case 2: { 
					System.out.println("Vuoi registrarti come dipendente [y/n]?");
					try {
						answer = inputReader.readLine().charAt(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (answer == 'y' || answer == 'n') {
						ApplicationConsoleBoundary.registra(answer);
					}
					else {
						System.out.println("Carattere inserito non valido.");
					}
					break; 
				}
				
				case 3: { 
					ApplicationConsoleBoundary.ricercaCorsa(); 
					System.out.println("Sei un cliente e vuoi procedere con l'acquisto di una delle corse [y/n]:");
					char choice = input.next().charAt(0);
					if(choice == 'y') {
						do {
							codice = ApplicationConsoleBoundary.login(0);
						} while(codice == -1);

						System.out.println("Inserisci il codice della corsa scelta: \t");
						
						try {
							int codiceCorsa = Integer.parseInt(inputReader.readLine());
							ClienteConsoleBoundary.acquistaBiglietto(codiceCorsa, codice);
						} catch (NumberFormatException e) {
							option = 0;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						break;
					}
				}
				
				case 4: {
					System.out.println("Arrivederci!\n");
					break; 
				}
				default: {
					System.out.println("Carattere inserito non riconosciuto!\n");
				}
			}
		} while (option != 4);
		
		input.close();
	}
	

	protected static java.io.BufferedReader inputReader;
}

