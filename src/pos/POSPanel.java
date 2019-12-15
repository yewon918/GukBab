package pos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class POSPanel extends JPanel {
   JButton[] MBtn = new JButton[9];
   String[] menu = { "순 대 국 밥", "수 육 국 밥", "뼈 해 장 국", "돼 지 국 밥", "콜 라", "사 이 다", "환 타", "소 주", "맥 주" };
   int[] price = { 7000, 6500, 8000, 7000, 1000, 1000, 2000, 4500, 4000 };
   JTextField tf = new JTextField(20);

   JButton[] MGRtn = new JButton[1];
   String[] MGR = { "판매 관리" };

   JTextField t = new JTextField(10);

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

   // 구현
   class Screen extends JPanel {
      Screen() {
         setBackground(new Color(255, 255, 204));
         DefaultTableModel m = (DefaultTableModel) table.getModel();
         table.setRowHeight(40);
         table.getTableHeader().setFont(new Font("휴먼편지체", Font.BOLD, 15));
         table.getTableHeader().setBackground(Color.white);
         table.setBackground(Color.orange);
         table.setGridColor(Color.white);
         table.setSelectionBackground(Color.YELLOW);

         add(new JScrollPane(table));
      }
   } 
   class MenuBtn extends JPanel {
      MenuBtn() {
         setLayout(new GridLayout(3, 3, 3, 3));
         setBackground(Color.WHITE);
         ImageIcon[] img = new ImageIcon[9];
         img[0] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\sun.jpg");
         img[1] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\soo.jpg");                                                                                 
         img[2] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\bone.jpg");
         img[3] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\pig.jpg");
         img[4] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\coke.jpg");
         img[5] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\cider.jpg");
         img[6] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\fanta.jpg");
         img[7] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\soju.jpg");
         img[8] = new ImageIcon("C:\\Users\\김예원\\Desktop\\img\\beer.jpg");

         for (int i = 0; i < img.length; i++) {
            icon[i] = img[i].getImage();
            newimg[i] = icon[i].getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            newicon[i] = new ImageIcon(newimg[i]);
         }
         for (int i = 0; i < MBtn.length; i++) {
            MBtn[i] = new JButton(newicon[i]);
            MBtn[i].setBorderPainted(true);
            MBtn[i].setContentAreaFilled(false);
            add(MBtn[i]);
         }
      }
   }

   class MGRBtn extends JPanel {
      MGRBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(1, 1, 1, 1));

         for (int i = 0; i < MGR.length; i++) {
            MGRtn[i] = new JButton(MGR[i]);
            MGRtn[i].setBackground(new Color(230, 230, 0));
            MGRtn[i].setFont(new Font("휴먼편지체", Font.BOLD, 30));
            add(MGRtn[i]);
         }
      }
   }

   class StrBtn extends JPanel {
      StrBtn() {
         setBackground(Color.WHITE);
         setLayout(new GridLayout(1, 4, 3, 3));

         for (int i = 0; i < Str.length; i++) {
            SBtn[i] = new JButton(Str[i]);
            SBtn[i].setBackground(new Color(230, 230, 0));
            SBtn[i].setFont(new Font("휴먼편지체", Font.BOLD, 20));
            add(SBtn[i]);
         }
      }
   }

   class LOGIN extends JFrame {
      private JPanel loginPanel = new JPanel(new GridLayout(5, 1));
      private JLabel idLabel = new JLabel("아이디");
      private JLabel pwLabel = new JLabel("비밀번호 ");
      private JTextField idText = new JTextField();
      private JPasswordField pwText = new JPasswordField();
      private JButton loginBtn = new JButton("로그인");

      public LOGIN() {
         super("로그인");

         this.setContentPane(loginPanel);
         loginPanel.add(idLabel);
         loginPanel.add(idText);
         loginPanel.add(pwLabel);
         loginPanel.add(pwText);
         loginPanel.add(loginBtn);

         idLabel.setHorizontalAlignment(NORMAL);
         pwLabel.setHorizontalAlignment(NORMAL);

         setSize(300, 200);
         this.setLocationRelativeTo(null);
         this.setVisible(true);
      }
   }

   public POSPanel() {
      setLayout(null);
      setBackground(new Color(255, 255, 204));
      MenuBtn mbtn = new MenuBtn();
      MGRBtn mgrtn = new MGRBtn();
      StrBtn sbtn = new StrBtn(); // 버튼배열
      Screen sc = new Screen();

      tf.setSize(500, 100);
      tf.setLocation(60, 480);
      add(tf);

      sc.setSize(600, 600);
      sc.setLocation(25, 20);
      add(sc);

      mbtn.setSize(500, 400);
      mbtn.setLocation(700, 23);
      add(mbtn);

      mgrtn.setSize(500, 70);
      mgrtn.setLocation(700, 450);
      add(mgrtn);

      sbtn.setSize(500, 70); // 결제버튼
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

      SBtn[1].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JButton MBtn = (JButton) e.getSource();
            DefaultTableModel m = (DefaultTableModel) table.getModel();

            m.removeRow(table.getSelectedRow());
         }
      });

      SBtn[2].addActionListener(new ActionListener() {
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
            int coke = 0;
            int fanta = 0;
            int cider = 0;
            int soju = 0;
            int beer = 0;
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
               else if (table.getValueAt(i, 0).equals("콜 라"))
                  coke += (int) table.getValueAt(i, 1);
               else if (table.getValueAt(i, 0).equals("환 타"))
                  fanta += (int) table.getValueAt(i, 1);
               else if (table.getValueAt(i, 0).equals("사 이 다"))
                  cider += (int) table.getValueAt(i, 1);
               else if (table.getValueAt(i, 0).equals("소 주"))
                  soju += (int) table.getValueAt(i, 1);
               else if (table.getValueAt(i, 0).equals("맥 주"))
                  beer += (int) table.getValueAt(i, 1);
            }

            for (int i = 0; i < rowCont; i++)
               sum += (int) table.getValueAt(i, 2);

            tf.setText(String.valueOf(" 총 금액 : " + sum + " 냥 "));
            tf.setFont(new Font("굴림체", Font.BOLD, 40));

            Connection conn = null;
            Statement stmt = null;
            try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost/db?serverTimezone=UTC";
               conn = DriverManager.getConnection(url, "root", "dPdnjs0918!");

               stmt = conn.createStatement();
               stmt.executeUpdate("update sundae set count= count +" + sundae);
               stmt.executeUpdate("update suyuk set count= count +" + suyuk);
               stmt.executeUpdate("update bone set count= count +" + bone);
               stmt.executeUpdate("update pig set count= count +" + pig);
               stmt.executeUpdate("update coke set count= count +" + coke);
               stmt.executeUpdate("update cider set count= count +" + cider);
               stmt.executeUpdate("update fanta set count= count +" + fanta);
               stmt.executeUpdate("update soju set count= count +" + soju);
               stmt.executeUpdate("update beer  set count= count +" + beer);
               stmt.executeUpdate("update sales set profit= profit +" + sum);

               JOptionPane.showMessageDialog(null, "결제되었습니다.", "결제", JOptionPane.DEFAULT_OPTION); 
                                                                              
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
      MGRtn[0].addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Connection conn = null;
            Statement stmt = null;
            try {
               Class.forName("com.mysql.cj.jdbc.Driver");
               String url = "jdbc:mysql://localhost/db?serverTimezone=UTC";

               conn = DriverManager.getConnection(url, "root", "dPdnjs0918!");
               stmt = conn.createStatement();

               LOGIN lg = new LOGIN();
               Dimension dim = new Dimension(600, 300);
               JFrame Frame = new JFrame("판매관리");
               Frame.setSize(800, 600);
               Frame.setPreferredSize(dim);

               String name[] = { "품목명", "판매횟수" };
               String data[][] = { { "                      순대국밥", "" }, { "                      뼈해장국", "" },
                     { "                      수육국밥", "" }, { "                      돼지국밥", "" },{ "                      콜라", "" },
                     { "                      환타", "" },                     { "                      사이다", "" },{ "                      소주", "" },
                     { "                      맥주", "" },                     { "                      누적매출", "" } };

               DefaultTableModel model = new DefaultTableModel(data, name);

               JTable table = new JTable(model);
               JScrollPane scrollpane = new JScrollPane(table);
               Frame.add(scrollpane);
               Frame.pack();

               lg.loginBtn.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent e) {
                     String id = lg.idText.getText().trim();
                     String pw = lg.pwText.getText().trim();

                     if (id.equals("test") && pw.equals("test1")) {
                        JOptionPane.showMessageDialog(null, "Success", "로그인성공!", JOptionPane.DEFAULT_OPTION);
                        Frame.setVisible(true);
                     } else {
                        JOptionPane.showMessageDialog(null, "Failed", "로그인실패!", JOptionPane.DEFAULT_OPTION);
                        Frame.setVisible(false);
                     }
                  }
               });

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

               ResultSet srs5 = stmt.executeQuery("select * from coke");
               InsertData(srs5, "coke_count", model);
               srs5.close();

               ResultSet srs6 = stmt.executeQuery("select * from fanta");
               InsertData(srs6, "fanta_count", model);
               srs6.close();

               ResultSet srs7 = stmt.executeQuery("select * from cider");
               InsertData(srs7, "cider_count", model);
               srs7.close();

               ResultSet srs8 = stmt.executeQuery("select * from soju");
               InsertData(srs8, "soju_count", model);
               srs8.close();

               ResultSet srs9 = stmt.executeQuery("select * from beer");
               InsertData(srs9, "beer_count", model);
               srs9.close();

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
      while (srs.next()) {
         if (col1.equals("sundae_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 0, 1);
            break;
         } else if (col1.equals("suyuk_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 1, 1);
            break;
         } else if (col1.equals("bone_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 2, 1);
            break;
         } else if (col1.equals("pig_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 3, 1);
            break;
         } else if (col1.equals("coke_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 4, 1);
            break;
         } else if (col1.equals("fanta_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 5, 1);
            break;
         } else if (col1.equals("cider_count")) {
            model.setValueAt(srs.getInt("count") + " 개", 6, 1);
            break;
         } else if (col1.equals("soju_count")) {
            model.setValueAt(srs.getInt("count") + " 병 ", 7, 1);
            break;
         } else if (col1.equals("beer_count")) {
            model.setValueAt(srs.getInt("count") + " 병 ", 8, 1);
            break;
         } else if (col1.equals("profit")) {
            model.setValueAt(srs.getInt("profit") + " 원 ", 9, 1);
            break;
         } else
            System.out.println();
      }
   }
}