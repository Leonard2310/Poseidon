package poseidon.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MyFrame extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	JButton button;

	MyFrame() {
		
		ImageIcon icon = new ImageIcon("icon.jpeg");
		Border border = BorderFactory.createLineBorder(Color.BLUE,4);
			
		// BACKGROUND
		setLayout(new BorderLayout());
		JLabel background =new JLabel(new ImageIcon("background.jpeg"));
		this.add(background);
		background.setLayout(new FlowLayout());	    
		
		// LABEL
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
		
		// BOTTON
		button = new JButton();
		button.addActionListener(this);
		button.setText("START");
		button.setFocusable(false);
		button.setFont(new Font("MV Boli", Font.ITALIC, 15));
		button.setForeground(Color.BLUE);
		button.setForeground(Color.BLUE);
		button.setBounds(0,0,100,50);			//TODO: Leonardo
		button.setEnabled(true);
		
		// FRAME
		this.setTitle("POSEIDON");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//this.setSize(800,800);
		this.setVisible(true);
		this.setIconImage(icon.getImage());

		background.add(label);
		background.add(button);
		this.pack();
		
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			LaunchMenu launchMenu = new LaunchMenu();
			button.setEnabled(false);
		}
	}
}
