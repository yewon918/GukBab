package pos2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class POSPanel extends JPanel {
	JButton[] MBtn = new JButton[9];
	String[] menu = {"순 대 국 밥","수 육 국 밥","뼈 해 장 국","돼 지 국 밥","콜 라","사 이 다","환 타","소 주","맥 주","판 매 관 리"
	         };
	
	int[] price = {100, 200, 300, 400, 500, 600 ,700, 800, 900		
	};
	JTextField tf = new JTextField(20);
	
	JButton[] MGRtn = new JButton[1];
	String[] MGR = {"�뙋留ㅺ�由�"};
	
	JTextField t = new JTextField(10);

	JButton[] SBtn = new JButton[4];
	String[] Str = {"荑좏룿","遺�遺꾩랬�냼","�쟾泥댁랬�냼","寃곗젣"};
	
	String [] ColName = {"硫붾돱","�닔�웾","媛�寃�"};
	
	String [][] Data ;
	int count =1;
	DefaultTableModel model = new DefaultTableModel(Data,ColName);
	JTable table = new JTable(model);
	
	class Screen extends JPanel{
		Screen(){
			setBackground(Color.RED);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(50);
			table.getTableHeader().setFont(new Font("궁서", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	} //踰꾪듉�깮�꽦, 諛곌꼍�깋 �꽕�젙
	
	class MenuBtn extends JPanel{
		MenuBtn(){
			setLayout(new GridLayout(3,3,3,3));
			setBackground(Color.YELLOW);
			for(int i=0;i<MBtn.length;i++) {
				MBtn[i]= new JButton(menu[i]);
				add(MBtn[i]);
			}
		}
	} //硫붾돱紐� 踰꾪듉 �젅�씠�븘�썐�꽕�젙, �끂��遺�遺�
	
	
	class MGRBtn extends JPanel{
		MGRBtn(){
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 1, 1, 1));
			
			for(int i=0;i<MGR.length;i++) {
				MGRtn[i]= new JButton(MGR[i]);
				add(MGRtn[i]);
			}
		}
	}//�뙋留ㅺ�由щ쾭�듉 �젅�씠�븘�썐 �꽕�젙
	
	class StrBtn extends JPanel{
		StrBtn(){
			setBackground(Color.ORANGE);
			setLayout(new GridLayout(1,4,3,3));
			
			for(int i=0;i<Str.length;i++) {
				SBtn[i]= new JButton(Str[i]);
				add(SBtn[i]);
			}
		}
	} //荑좏룿~寃곗젣 踰꾪듉 �젅�씠�븘�썐�꽕�젙, 諛곌꼍 二쇳솴遺�遺�
	
	public POSPanel() {
		setLayout(null);
		setBackground(Color.GREEN);
		MenuBtn mbtn = new MenuBtn();
		MGRBtn mgrtn = new MGRBtn();
		StrBtn sbtn = new StrBtn(); //踰꾪듉諛곗뿴
		
		Screen sc = new Screen();
		
		//�쟾泥댄솕硫� �뀒�몢由� �젅�씠�븘�썐, 珥덈줉�깋�
		tf.setSize(450, 70);
		tf.setLocation(50, 480);
		add(tf);
		
		sc.setSize(500, 500); 
		sc.setLocation(25, 20);
		add(sc);
		
		mbtn.setSize(400, 350); //硫붾돱踰꾪듉 �궗�씠利� (媛�濡�, �꽭濡�)
		mbtn.setLocation(530, 23); //硫붾돱踰꾪듉 �쐞移� 
		add(mbtn);
		
		mgrtn.setSize(400, 70); //愿�由щ쾭�듉
		mgrtn.setLocation(530, 400);
		add(mgrtn);
		
		sbtn.setSize(400, 70); //寃곗젣踰꾪듉
		sbtn.setLocation(530, 480);
		add(sbtn);
		

		for(int i=0;i<MBtn.length;i++) {
			final int index =i;
			MBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton MBtn = (JButton)e.getSource();
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{menu[index],count,price[index]});
				}
			});
		}
		
		SBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton)e.getSource();
				table.setValueAt(0, table.getSelectedRow(), 2);
			}
		});
		
			
		SBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.removeRow(table.getSelectedRow());
			}
		});
		
		
		SBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.setRowCount(0);
				tf.setText(String.valueOf(""));
			}
		});
		
		
		SBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton)e.getSource();
				int rowCont = table.getRowCount();
				int sum =0;
				for(int i=0;i<rowCont;i++) {
					sum += (int)table.getValueAt(i, 2);
				}
				tf.setText(String.valueOf(" 총금액 : "+sum + "냥"));
				tf.setFont(new Font("궁서", Font.BOLD, 40));
			}
		});
	}
}

