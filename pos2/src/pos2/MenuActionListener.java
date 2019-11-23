package pos2;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class MenuActionListener implements ActionListener {
	POSPanel mp;
	
	public MenuActionListener(POSPanel mp) {
		this.mp =mp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//JButton MBtn = (JButton)e.getSource(); //서브메뉴?
		DefaultTableModel m = (DefaultTableModel)mp.table.getModel();
		for(int i=0;i<2;i++)
			m.addRow(new Object[]{mp.menu[i],mp.count,mp.price[i]});
	}
}
