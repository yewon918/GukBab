package pos2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class POSPanel extends JPanel {
	JButton[] MBtn = new JButton[9]; //버튼 생성
	String[] menu = {"순 대 국 밥","수 육 국 밥","뼈 해 장 국",
			"돼 지 국 밥","콜 라","사 이 다",
			"환 타","소 주","맥 주",
			 };
	
	int[] price = {100, 200, 300, 
			400, 500, 600,
			700, 800, 900};
	
	JTextField tf = new JTextField(20); //인풋박스tf- 20
	
	JButton[] MGRtn = new JButton[1];
	//String[] MGR = {"판매관리"};
	
	JTextField t = new JTextField(10); //인풋박스t- 입력창 열 10

	JButton[] SBtn = new JButton[4];
	String[] Str = {"쿠폰","부분취소","전체취소","결제"};
	
	String [] ColName = {"메뉴","수량","가격"};
	
	String [][] Data;
	int count =1;
	DefaultTableModel model = new DefaultTableModel(Data,ColName);
	//Data와 Col에 연결된 상태
	JTable table = new JTable(model); //DefaultTableModel로 테이블 관리
	
	class Screen extends JPanel{ //메뉴, 수량, 가격 
		Screen(){
			setBackground(Color.RED);
			DefaultTableModel m = (DefaultTableModel)table.getModel();
			table.setRowHeight(50);
			table.getTableHeader().setFont(new Font("궁서", Font.BOLD, 15));
			add(new JScrollPane(table));
		}
	} 
	
	//메뉴버튼을 생성함
	class MenuBtn extends JPanel{
		//여기 음료랑 먹을거 분리해서 할 수 있지 않을까
		MenuBtn(){
			setLayout(new GridLayout(3,3,3,3));
			//setLayout - 배치관리자 Grid는 격자모양 배치
			//gridlayout(row,col,hgap,vgap) //gap-여백조절
			setBackground(Color.YELLOW); //버튼 칸의 배경 설정
			for(int i=0;i<MBtn.length;i++) {
				MBtn[i]= new JButton(menu[i]);
				add(MBtn[i]); //최종적으로 버튼을 생성
			}
		}
	}
	
	//판매관리 버튼 만듦
	class MGRBtn extends JPanel{
		MGRBtn(){
			setBackground(Color.WHITE);
			setLayout(new GridLayout(1, 1, 1, 1));	
			/*for(int i=0;i<MGR.length;i++) {
				MGRtn[i]= new JButton(MGR[i]);
				add(MGRtn[i]);
			}*/
			add(new JButton("판매관리")); //이렇게도 바꿀 수 있을것 같아요
		}
	}
	
	//쿠폰 ~ 결제 창 버튼
	class StrBtn extends JPanel{
		StrBtn(){
			setBackground(Color.ORANGE);
			setLayout(new GridLayout(1,4,3,3));
			
			for(int i=0;i<Str.length;i++) {
				SBtn[i]= new JButton(Str[i]);
				add(SBtn[i]);
				//add(new JButton(Str[i])); - 똑같은건데 이거 사용하면 예외처리 뜸
			}
		}
	}
	
	//기본패널
	public POSPanel() {
		setLayout(null);
		setBackground(Color.GREEN); 
		MenuBtn mbtn = new MenuBtn();
		MGRBtn mgrtn = new MGRBtn();
		StrBtn sbtn = new StrBtn(); //踰꾪듉諛곗뿴
		
		Screen sc = new Screen(); //사용자의 화면을 나타냄
		
		//�쟾泥댄솕硫� �뀒�몢由� �젅�씠�븘�썐, 珥덈줉�깋�
		tf.setSize(450, 70); //가격 표시 창
		tf.setLocation(50, 480); //가로 위치, 세로 위치
		add(tf);
		
		sc.setSize(500, 500); //메뉴 선택시 선택 메뉴 뜨는 창
		sc.setLocation(25, 20);
		add(sc);
		
		mbtn.setSize(400, 350); //메뉴버튼 사이즈(가로, 세로)
		mbtn.setLocation(530, 23);
		add(mbtn);
		
		mgrtn.setSize(400, 70); //관리 버튼
		mgrtn.setLocation(530, 400);
		add(mgrtn);
		
		sbtn.setSize(400, 70); //쿠폰 등 버튼
		sbtn.setLocation(530, 480);
		add(sbtn);
		
		//Table 관리
		//메뉴버튼-추가
		for(int i=0;i<MBtn.length;i++) { //0~8까지 
			final int index =i;
			MBtn[i].addActionListener(new ActionListener() {
				//ActionListener은 버튼이 클릭될때마다 발생하는 이벤트를 만듦
				//버튼에 액션 리스너를 추가한다
				@Override
				public void actionPerformed(ActionEvent e) {
					//JButton MBtn = (JButton)e.getSource();
					//왜있는건지 이해 안됨
					DefaultTableModel m = (DefaultTableModel)table.getModel();
					m.addRow(new Object[]{menu[index],count,price[index]});
					//선택된 메뉴 추가(추가되는 데이터 내용) -count 건드리면 저번에 말한 내용 가능
				}
			});
		}
		
		//쿠폰 버튼-가격 0으로
		SBtn[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JButton MBtn = (JButton)e.getSource();
				table.setValueAt(0, table.getSelectedRow(), 2);
				//col2의 값을 0으로
			}
		});
		
		//부분취소 버튼
		SBtn[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JButton MBtn = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.removeRow(table.getSelectedRow());
				//선택된 row를 없앰 
			}
		});
		
		//전체취소
		SBtn[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JButton MBtn = (JButton)e.getSource();
				DefaultTableModel m = (DefaultTableModel)table.getModel();
				
				m.setRowCount(0); //모델 내의 행수 설정
				tf.setText(String.valueOf(""));
			}
		});
		
		//결제
		SBtn[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JButton MBtn = (JButton)e.getSource();
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

