package poseidon.main;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class LaunchMenu {
	
	JFrame frame = new JFrame();
	JLabel label = new JLabel();
	
	@SuppressWarnings("unused") //TODO
	LaunchMenu() {
		
		ImageIcon icon = new ImageIcon("icon.jpeg");
		Border border = BorderFactory.createLineBorder(Color.BLUE,4);  
		
		// LABEL
		JLabel label = new JLabel();
		label.setText("POSEIDON");
		label.setBounds(0,0,150,150);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(new Color(0,60,80));
		label.setFont(new Font("MV Boli", Font.BOLD, 20));
		label.setIconTextGap(-25);
		label.setBackground(Color.CYAN);
		label.setOpaque(true);
		label.setBorder(border);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		// FRAME
		frame.setTitle("POSEIDON");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(440,380);
		//frame.setLayout(null);
		frame.setVisible(true);
		frame.setIconImage(icon.getImage());

		frame.add(label);
		//frame.pack();
		
		// NOTIFICA DI ERRORE
		JOptionPane.showMessageDialog(null,  "Carattere inserito non riconosciuto!", "ERRORE - POSEIDON", JOptionPane.WARNING_MESSAGE);
		
		// NOTIFICA DI INPUT 
		String[] responses = {"No, sono un utente", "No, sono un cliente",  "Si"};
		JOptionPane.showOptionDialog(null, "Sei un dipendente?", "POSEIDON", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon, responses, 0);
		int dip_answer = JOptionPane.showConfirmDialog(null,  "Sei un dipendente?", "POSEIDON", JOptionPane.YES_NO_OPTION);
		String nome= JOptionPane.showInputDialog("Inserisci il tuo nome: ");
		String cognome= JOptionPane.showInputDialog("Inserisci il tuo cognome: ");
		String password= JOptionPane.showInputDialog("Inserisci la password: ");
		
		// NOTIFICA DI CONFERMA
		int answer = JOptionPane.showConfirmDialog(null,  "Sei sicuro di voler uscire?", "POSEIDON", JOptionPane.YES_NO_OPTION);
		
	}
	

}
