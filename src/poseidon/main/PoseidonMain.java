package poseidon.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


import poseidon.boundary.*;

public class PoseidonMain {

	public static void main(String[] args) {
		
		new MyFrame();
		
		inputReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(System.in);
		int option = 0;
		char answer = 'n';
		do {
			System.out.println("Seleziona operazione: \n" +
					"\t1) Login\n" +
					"\t2) Ricerca Corsa\n" +
					"\t3) Esci");
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
					System.out.println("Sei un dipendente (y/n)?");
					try {
						answer = inputReader.readLine().charAt(0);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					if (answer == 'y') {
						if (ApplicationConsoleBoundary.login(1) == 0) { 
							DipendenteConsoleBoundary.showDipendenteConsoleBoundary();
						}
					}
					else {
						if (ApplicationConsoleBoundary.login(0) == 0) { 
							ClienteConsoleBoundary.showClienteConsoleBoundary();
						}
					}
					break; 
				}
				
				case 2: { 
					ApplicationConsoleBoundary.ricercaCorsa(); 
					System.out.println("Sei un cliente e vuoi procedere con l'acquisto di una delle corse presenti [y/n]:");
					char choice = input.next().charAt(0);
					if(choice == 'y') {
						ApplicationConsoleBoundary.login(0);
						System.out.println("Inserisci il codice della corsa scelta: \t");
						try {
							int codiceCorsa = Integer.parseInt(inputReader.readLine());
							ClienteConsoleBoundary.acquistaBiglietto(codiceCorsa);
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
				
				case 3: {
					System.out.println("Arrivederci!\n");
					break; 
				}
				default: {
					System.out.println("Carattere inserito non riconosciuto!\n");
				}
			}
		} while (option != 3);
		
		input.close();
	}
	

	protected static java.io.BufferedReader inputReader;
}

