package pos2;

import javax.swing.JFrame;
//awt�� ����ȯ�濡 ���� ������Ʈ ����� �޶��� �ϰ��� ȭ�� ������ ����- swing����
public class POS_Frame extends JFrame {
	public POS_Frame() {
		setTitle("�߰ſ� �׾ ����");
		//setLocationRelativeTo(null); //-����������� default�� ����ٴ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Exit_on_close : ������ â ����� ���μ������� ����
		//�̰� ���ϸ� �����δ� ���μ����� ��� ���ư���, �޸� ��Ƹ���
		setContentPane(new POSPanel());		
		//(�̷��Ե� �����ϴ�)POSPanel pos = new POSPanel();
		//setContentPane(pos);
		
		setSize(985,660);
		setVisible(true);
		//���̰� ��
	}
}
