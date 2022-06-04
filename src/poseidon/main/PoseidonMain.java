package poseidon.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import poseidon.boundary.*;

public class PoseidonMain {

	public static void main(String[] args) {
						
		inputReader = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(System.in);
		int option = 0;
		int flag = 0;
		do {
			System.out.println("Seleziona operazione: \n" +
					"\t1) Login\n" +
					"\t2) Logout\n" +
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
				case 1: { ApplicationConsoleBoundary.login(flag); break; }
				case 2: { ApplicationConsoleBoundary.logout(); break; }
				case 3: { 
					ApplicationConsoleBoundary.ricercaCorsa(); 
					System.out.println("Sei un cliente e vuoi procedere con l'acquisto di una delle corse presenti [y/n]:");
					char choice = input.next().charAt(0);
					if(choice == 'y') {
						flag = 1;
						ApplicationConsoleBoundary.login(flag);
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
				case 4: {
					System.out.println("Arrivederci!\n");
					break; 
				}
				default: {
					System.out.println("Carattere inserito non riconosciuto!\n");
				}
			}
		} while (option != 4);
	}
	

	protected static java.io.BufferedReader inputReader;
}

