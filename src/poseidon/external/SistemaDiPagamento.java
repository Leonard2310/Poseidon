package poseidon.external;

public class SistemaDiPagamento {

	// SISTEMA ESTERNO NON IMPLEMENTATO

	public static String generateRicevuta(int codiceCliente, int codiceCorsa, String tipoBiglietto) {

		String vocabolarioAlfaNumerico;
		StringBuilder builder;
		String ricevuta = null;

		int i = 3; 						// Grandezza stringa alfanumerica randomica
		vocabolarioAlfaNumerico = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789"; // Vocabolario alfanumerico
		builder = new StringBuilder(i); // String buffer

		for (int m = 0; m < i; m++) {
			int myindex = (int) (vocabolarioAlfaNumerico.length() * Math.random());

			builder.append(vocabolarioAlfaNumerico.charAt(myindex));
			builder.toString();
		}

		if (tipoBiglietto.equals("passeggeri")) {
			ricevuta = codiceCliente + "P" + codiceCorsa + builder;
		} else if (tipoBiglietto.equals("veicoli")){
			ricevuta = codiceCliente + "V" + codiceCorsa + builder;
		}
		
		return ricevuta;
	}

	public static boolean elaborazioneAcquisto(String tipologiaPagamento) {
		return true;
	}

}
