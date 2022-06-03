package poseidon.main;

import java.io.IOException;
import poseidon.boundary.*;

public class PoseidonMain {

	public static void main(String[] args) {
						
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int option = 0;
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
				case 1: { ApplicationConsoleBoundary.login(); break; }
				case 2: { ApplicationConsoleBoundary.logout(); break; }
				case 3: { ApplicationConsoleBoundary.ricercaCorsa(); break; }
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

