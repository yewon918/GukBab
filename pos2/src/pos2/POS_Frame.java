package pos2;

import javax.swing.JFrame;

public class POS_Frame extends JFrame {
	public POS_Frame() {
		setTitle("¶ß°Å¿ö Á×¾îµµ ±¹¹ä");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new POSPanel());

		setSize(985,660);
		setVisible(true);
	}
}
