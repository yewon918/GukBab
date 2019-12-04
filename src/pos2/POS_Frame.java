package pos2;

import java.awt.Color;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument.Content;

public class POS_Frame extends JFrame {
	//JScrollPane scroll;
	//JPanel panel;
	
	public POS_Frame() {
		setTitle("¶ß°Å¿ö Á×¾îµµ ±¹¹ä");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new POSPanel());
		
		setSize(1300,700);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//scroll = new JScrollPane();
		//POSPanel.add(scroll);
		setVisible(true);
	}
}
