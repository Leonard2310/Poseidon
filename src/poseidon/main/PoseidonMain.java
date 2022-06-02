package poseidon.main;

import java.io.IOException;
import java.sql.SQLException;

import poseidon.DAO.*;
import poseidon.entity.*;
import poseidon.boundary.*;
import poseidon.control.*;

public class PoseidonMain {

	public static void main(String[] args) {
						
		inputReader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		int option = 0;
		do {
			System.out.println("Seleziona operazione: \n" +
					"\t1) \n" +
					"\t2) \n" +
					"\t3) \n" +
					"\t4) \n" +
					"\t5) \n" +
					"\t6) \n" +
					"\t7) \n" +
					"\t9) Esci");
			System.out.flush();
			
			try {
				option = Integer.parseInt(inputReader.readLine());
			} catch (NumberFormatException e) {
				option = 0;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			switch (option) {
				case 1: { ; break; }
				case 2: { ; break; }
				case 3: { ; break; }
				case 4: { ; break; }
				case 5: { ; break; }
				case 6: { ; break; }
				case 7: { ; break; }
				case 9: {
					System.out.println("Goodbye!\n");
					break; 
				}
				default: {
					System.out.println("Carattere inserito non riconosciuto!\n");
				}
			}
		} while (option != 9);
	}
	

	protected static java.io.BufferedReader inputReader;
}

