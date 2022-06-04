package poseidon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	MyFrame() {
		
		ImageIcon icon = new ImageIcon("icon.jpeg");
		Border border = BorderFactory.createLineBorder(Color.BLUE,4);
			
		setLayout(new BorderLayout());
		JLabel background =new JLabel(new ImageIcon("background.jpeg"));
		this.add(background);
		background.setLayout(new FlowLayout());	    
		
		JLabel label = new JLabel();
		label.setText("POSEIDON");
		label.setIcon(icon);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setVerticalTextPosition(JLabel.TOP);
		label.setForeground(new Color(0,60,80));
		label.setFont(new Font("MV Boli", Font.BOLD, 20));
		label.setIconTextGap(-25);
		//label.setBackground(Color.CYAN);
		//label.setOpaque(true);
		label.setBorder(border);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.setTitle("POSEIDON");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.setSize(800,800);

		this.setVisible(true);
		this.setIconImage(icon.getImage());

		background.add(label);
	    
		this.pack();
		
	}

}
