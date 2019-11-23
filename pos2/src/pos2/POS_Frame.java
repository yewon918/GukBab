package pos2;

import javax.swing.JFrame;
//awt는 실행환경에 따라 컴포넌트 모습이 달라져 일관된 화면 제공이 힘듦- swing권장
public class POS_Frame extends JFrame {
	public POS_Frame() {
		setTitle("뜨거워 죽어도 국밥");
		//setLocationRelativeTo(null); //-가운데오도록함 default와 따라다님
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Exit_on_close : 윈도우 창 종료시 프로세스까지 닫음
		//이거 안하면 실제로는 프로세스가 계속 돌아가고, 메모리 잡아먹음
		setContentPane(new POSPanel());		
		//(이렇게도 가능하다)POSPanel pos = new POSPanel();
		//setContentPane(pos);
		
		setSize(985,660);
		setVisible(true);
		//보이게 함
	}
}
