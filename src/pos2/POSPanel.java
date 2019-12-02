package pos2;

import java.sql.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class POSPanel extends JPanel {
	JButton[] MBtn = new JButton[9];
	String[] menu = { "순 대 국 밥", "수 육 국 밥", "뼈 해 장 국", "돼 지 국 밥", "콜 라", "사 이 다", "환 타", "소 주", "맥 주" };
	int[] price = {7000, 6500, 8000, 7000, 1000, 1000, 2000, 4500, 4000};
	JTextField tf = new JTextField(20);
	
	JButton[] MGRtn = new JButton[1];
	String[] MGR = { "판매 관리" };

	//JTextField t = new JTextField(10);

	JButton[] SBtn = new JButton[4];
	String[] Str = { "쿠폰", "부분취소", "전체취소", "결제" };

	String[] ColName = { "메뉴", "수량", "가격" };
	String[][] Data;
	
	int count = 1;
	DefaultTableModel model = new DefaultTableModel(Data, ColName);
	JTable table = new JTable(model);

	Image[] icon = new Image[9];
	Image[] newimg = new Image[9];
	ImageIcon[] newicon = new ImageIcon[9];
	
	//구현
	class Screen extends JPanel {
		Screen() {
			setBackground(Color.RED);
			DefaultTableModel m = (DefaultTableModel) table.getModel();
			table.setRowHeight(60);
			table.getTableHeader().setFont(new Font("궁서", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	} // 버튼생성, 배경색 설정

	class MenuBtn extends JPanel {
		MenuBtn() {
			setLayout(new GridLayout(3, 3, 3, 3));
			setBackground(Color.YELLOW);
			
			ImageIcon[] img = new ImageIcon[9];
			img[0] = new ImageIcon("C:\\Users\\김예원\\Desktop\\sun.jpg"); 
			img[1] = new ImageIcon("C:\\Users\\김예원\\Desktop\\soo.jpg"); //경로 설정해야함 
			img[2] = new ImageIcon("C:\\Users\\김예원\\Desktop\\bone.jpg"); 
			img[3] = new ImageIcon("C:\\Users\\김예원\\Desktop\\pig.jpg"); 
			img[4] = new ImageIcon("C:\\Users\\김예원\\Desktop\\coke.jpg"); 
			img[5] = new ImageIcon("C:\\Users\\김예원\\Desktop\\cider.jpg"); 
			img[6] = new ImageIcon("C:\\Users\\김예원\\Desktop\\fanta.jpg"); 
			img[7] = new ImageIcon("C:\\Users\\김예원\\Desktop\\soju.jpg"); 
			img[8] = new ImageIcon("C:\\Users\\김예원\\Desktop\\beer.jpg");

			
			for(int i=0;i<img.length;i++) {
				icon[i] = img[i].getImage();
				newimg[i] = icon[i].getScaledInstance(140,140,Image.SCALE_SMOOTH);
				newicon[i] = new ImageIcon(newimg[i]);
			}
	        // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
	        // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
	        // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
	        // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
			for (int i = 0; i < MBtn.length; i++) {
				MBtn[i] = new JButton(newicon[i]);
				MBtn[i].setBorderPainted(false);
				MBtn[i].setContentAreaFilled(false);
				add(MBtn[i]);
			}
			
		}
	} // 메뉴명 버튼 레이아웃설정, 노란부분

	class MGRBtn extends JPanel {
		MGRBtn() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 1, 1, 1));

			for (int i = 0; i < MGR.length; i++) {
				MGRtn[i] = new JButton(MGR[i]);
				add(MGRtn[i]);
			}
		}
	}// 판매관리버튼 레이아웃 설정

	class StrBtn extends JPanel {
		StrBtn() {
			setBackground(Color.ORANGE);
			setLayout(new GridLayout(1, 4, 3, 3));

			for (int i = 0; i < Str.length; i++) {
				SBtn[i] = new JButton(Str[i]);
				add(SBtn[i]);
			}
		}
	} // 쿠폰~결제 버튼 레이아웃설정, 배경 주황부분
		
	public POSPanel() {
		setLayout(null);
		setBackground(Color.GREEN);
		MenuBtn mbtn = new MenuBtn();
		MGRBtn mgrtn = new MGRBtn();
		StrBtn sbtn = new StrBtn(); // 버튼배열
		Screen sc = new Screen();

		// 가격표시란
		//tf.setSize(450, 70);
		tf.setSize(500, 100);
		//tf.setLocation(50, 480);
		tf.setLocation(60, 480);
		add(tf);
		
		//선택 메뉴 표시란
		//sc.setSize(500, 500);
		sc.setSize(600, 600);
		//sc.setLocation(70, 20);
		sc.setLocation(25, 20);
		add(sc);

		//mbtn.setSize(400, 350); // 메뉴버튼 사이즈 (가로, 세로)
		mbtn.setSize(500, 400); // 메뉴버튼 사이즈 (가로, 세로)
		//mbtn.setLocation(530, 23); // 메뉴버튼 위치
		mbtn.setLocation(700, 23); // 메뉴버튼 위치
		add(mbtn);

		//mgrtn.setSize(400, 70); // 관리버튼
		mgrtn.setSize(500, 70); // 관리버튼
		//mgrtn.setLocation(530, 400);
		mgrtn.setLocation(700, 450);
		add(mgrtn);

		//sbtn.setSize(400, 70); // 결제버튼
		sbtn.setSize(500, 70); // 결제버튼
		//sbtn.setLocation(530, 480);
		sbtn.setLocation(700, 550);
		add(sbtn);

		for (int i = 0; i < MBtn.length; i++) {
			final int index = i;
			MBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton MBtn = (JButton) e.getSource();
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					m.addRow(new Object[] { menu[index], count, price[index] });
				}
			});
		}
		SBtn[0].addActionListener(new ActionListener() { // 쿠폰 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton) e.getSource();
				table.setValueAt(0, table.getSelectedRow(), 2);
			}
		});

		SBtn[1].addActionListener(new ActionListener() { // 부분취소 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton) e.getSource();
				DefaultTableModel m = (DefaultTableModel) table.getModel();

				m.removeRow(table.getSelectedRow());
			}
		});

		SBtn[2].addActionListener(new ActionListener() { // 전체취소 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton) e.getSource();
				DefaultTableModel m = (DefaultTableModel) table.getModel();

				m.setRowCount(0);
				tf.setText(String.valueOf(""));
			}
		});

		SBtn[3].addActionListener(new ActionListener() { // 결제 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton MBtn = (JButton) e.getSource();
				int rowCont = table.getRowCount();
				
				int sundae = 0;
				int suyuk = 0;
				int bone = 0;
				int pig = 0;
				int sum = 0;

				for (int i = 0; i < rowCont; i++) {
					if (table.getValueAt(i, 0).equals("순 대 국 밥"))
						sundae += (int) table.getValueAt(i, 1);
					else if (table.getValueAt(i, 0).equals("수 육 국 밥"))
						suyuk += (int) table.getValueAt(i, 1);
					else if (table.getValueAt(i, 0).equals("뼈 해 장 국"))
						bone += (int) table.getValueAt(i, 1);
					else if (table.getValueAt(i, 0).equals("돼 지 국 밥"))
						pig += (int) table.getValueAt(i, 1);
				}

				for (int i = 0; i < rowCont; i++)
					sum += (int) table.getValueAt(i, 2);

				tf.setText(String.valueOf(" 총 금액 : " + sum + "냥"));
				tf.setFont(new Font("굴림체", Font.BOLD, 40));

				Connection conn = null;
				Statement stmt = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/db?serverTimezone=UTC";
					conn = DriverManager.getConnection(url, "root", "****");	//비밀번호 설정

					stmt = conn.createStatement();
					stmt.executeUpdate("update sundae set count= count +" + sundae);
					stmt.executeUpdate("update suyuk set count= count +" + suyuk);
					stmt.executeUpdate("update bone set count= count +" + bone);
					stmt.executeUpdate("update pig set count= count +" + pig);
					stmt.executeUpdate("update sales set profit= profit +" + sum);

					JOptionPane.showMessageDialog(null, "결제되었습니다.", "결제", JOptionPane.DEFAULT_OPTION); // DB에 오류가 없을때
					DefaultTableModel m = (DefaultTableModel) table.getModel();
					
					m.setRowCount(0);
					tf.setText(String.valueOf(""));
				} catch (ClassNotFoundException e1) {
					System.out.println("DB연동 실패");
				} catch (SQLException e1) {
					System.out.println("error : " + e1);
				} finally {
					try {
						if (conn != null && !conn.isClosed())
							conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		MGRtn[0].addActionListener(new ActionListener() {// 판매관리 버튼을 눌렀을 때
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement stmt = null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost/db?serverTimezone=UTC";

					conn = DriverManager.getConnection(url, "root", "****");	//비밀번호 설정
					stmt = conn.createStatement();
					
					Dimension dim = new Dimension(400, 150);

					JFrame Frame = new JFrame("판매관리"); // GUI의 기본 창 만들기
					Frame.setSize(800, 600); // 크기를 지정해 주지 않으면, 창이 너무 작게 나옴
					Frame.setVisible(true); // 창이 보이게 하기. 기본적으로는 보이지 않음
					Frame.setPreferredSize(dim);

					String name[] = { "메뉴", "판매횟수" };
					String data[][] = { { "                      순대국밥", "" }, { "                      뼈해장국", "" },
							{ "                      수육국밥", "" }, { "                      돼지국밥", "" },
							{ "                      누적매출", "" } };
					DefaultTableModel model = new DefaultTableModel(data, name);

					JTable table = new JTable(model);
					JScrollPane scrollpane = new JScrollPane(table);
					Frame.add(scrollpane);
					Frame.pack();
					Frame.setVisible(true);
					
					ResultSet srs1 = stmt.executeQuery("select * from sundae");
					InsertData(srs1, "sundae_count", model);
					srs1.close();
					
					ResultSet srs2 = stmt.executeQuery("select * from suyuk");
					InsertData(srs2, "suyuk_count", model);
					srs2.close();
					
					ResultSet srs3 = stmt.executeQuery("select * from bone");
					InsertData(srs3, "bone_count", model);
					srs3.close();
					
					ResultSet srs4 = stmt.executeQuery("select * from pig");
					InsertData(srs4, "pig_count", model);
					srs4.close();
					
					ResultSet srs = stmt.executeQuery("select * from sales");
					InsertData(srs, "profit", model);
					srs.close();
					
				} catch (ClassNotFoundException e1) {
					System.out.println("DB연동 실패");
				} catch (SQLException e1) {
					System.out.println("error : " + e1);
				} finally {
					try {
						if (conn != null && !conn.isClosed()) {
							conn.close();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		;
	}
	
	private static void InsertData(ResultSet srs, String col1, DefaultTableModel model) throws SQLException { 
	      // 각각의   db데이터 출력함수
	      while (srs.next()) {
	         if (col1.equals("sundae_count")) {
	            model.setValueAt(srs.getInt("count") + "개", 0, 1);
	            break;
	         } else if (col1.equals("suyuk_count")) {
	            model.setValueAt(srs.getInt("count") + "개", 1, 1);
	            break;
	         } else if (col1.equals("bone_count")) {
	            model.setValueAt(srs.getInt("count") + "개", 2, 1);
	            break;
	         } else if (col1.equals("pig_count")) {
	            model.setValueAt(srs.getInt("count") + "개", 3, 1);
	            break;
	         } else if (col1.equals("profit")) {
	            model.setValueAt(srs.getInt("profit") + "원", 4, 1);
	            break;
	         } else
	            System.out.println();
	      }
	   }
	
}