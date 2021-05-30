package APP;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Location.Location;
import Resource.Railway;
import Resource.Type;
import applications.TrainSchedule;
public class TrainScheduelApp {
	JFrame frame  = new JFrame("TrainSchedule");
	private TrainSchedule schedule = new TrainSchedule();//���༯
	JPanel p1 = new JPanel();//չʾĳ��λ�õ���Ϣ��
	JPanel p2 = new JPanel();//���������ӻ��͵�ǰλ����ص����и�������
	JPanel p3 = new JPanel();//����һ�и������β��Ҹ���������λ�ú�ʱ���
	JPanel p4 = new JPanel();//��ĳ���������η��䳵��
	JPanel p5 = new JPanel();//��ѯĳ��������״̬
	JPanel p6 = new JPanel();//�趨������״̬(������ȡ��������������)
	JPanel p7 = new JPanel();//�鿴��ĳ��������ص����и�������
	JPanel p8 = new JPanel();//�Գ�����в���(�鿴�����ӡ�ɾ��)
	JPanel p9 = new JPanel();//��λ�ý��в���(�鿴�����ӡ�ɾ��)
	JPanel p10 = new JPanel();//չʾ�Ƿ������Դ��ͻ
	JPanel p11 = new JPanel();//�г�ĳ��������ǰ�����
	private Panel cardPannel;//�ڶ�������
	
	public void init() {//��ʼ��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���������ر�
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//��������
		Choice choice = new Choice();
		choice.add("չʾĳ��λ�õ���Ϣ��");
		choice.add("���������ӻ��͵�ǰλ����ص����и�������");
		choice.add("����һ�и������β��Ҹ���������λ�ú�ʱ���");
		choice.add("��ĳ���������η��䳵��");
		choice.add("��ѯĳ��������״̬");
		choice.add("�趨������״̬(������ȡ��������������)");
		choice.add("�鿴��ĳ��������ص����и�������");
		choice.add("�Գ�����в���(�鿴�����ӡ�ɾ��)");
		choice.add("��λ�ý��в���(�鿴�����ӡ�ɾ��)");
		choice.add("չʾ�Ƿ������Դ��ͻ");
		choice.add("�г�ĳ��������ǰ�����");
		top.add(choice);//����������������˵�
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("չʾĳ��λ�õ���Ϣ��", p1);
		cardPannel.add("���������ӻ��͵�ǰλ����ص����и�������", p2);
		cardPannel.add("����һ�и������β��Ҹ���������λ�ú�ʱ���", p3);
		cardPannel.add("��ĳ���������η��䳵��", p4);
		cardPannel.add("��ѯĳ��������״̬", p5);
		cardPannel.add("�趨������״̬(������ȡ��������������)", p6);
		cardPannel.add("�鿴��ĳ��������ص����и�������", p7);
		cardPannel.add("�Գ�����в���(�鿴�����ӡ�ɾ��)", p8);
		cardPannel.add("��λ�ý��в���(�鿴�����ӡ�ɾ��)", p9);
		cardPannel.add("չʾ�Ƿ������Դ��ͻ", p10);
		cardPannel.add("�г�ĳ��������ǰ�����", p11);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//Ϊ�����˵������¼�������
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("��ĳ���������η��䳵��")) {
					schedule.showResource();
				}
				((CardLayout)cardPannel.getLayout()).show(cardPannel, (String) e.getItem());
			}
		});
		initFunc1();//չʾĳ��λ�õ���Ϣ��
		initFunc2();//���������ӻ��͵�ǰλ����ص����и�������
		initFunc3();//����һ�и�������
		initFunc4();//��ĳ���������η��䳵��
		initFunc5();//��ѯĳ��������״̬
		initFunc6();//�趨������״̬(������ȡ��������������)
		initFunc7();//�鿴��ĳ��������ص����и�������
		initFunc8();//�Գ�����в���(�鿴�����ӡ�ɾ��)
		initFunc9();//��λ�ý��в���(�鿴�����ӡ�ɾ��)
		initFunc10();//չʾ�Ƿ������Դ��ͻ
		initFunc11();//�г�ĳ��������ǰ�����
		frame.add(top);
	}
	public void initFunc1() {//չʾĳ��λ�õ���Ϣ��
		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p1.add(label1);//����ǩ���p1��
		p1.add(test1);//���ı������p1��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(16);
		p1.add(label2);//����ǩ���p1��
		p1.add(test2);//���ı������p1��
		JLabel label3 = new JLabel("��ѡ��鿴�ִﳵ��/������������(0/1)");
		JTextField test3 = new JTextField(1);
		p1.add(label3);//����ǩ���p1��
		p1.add(test3);//���ı������p1��
		JButton bu = new JButton("ȷ��");
		p1.add(bu);//����ť���p1��
		bu.addActionListener((e)->{//�¼�������
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
				schedule.board(new Location(s2), time, Integer.valueOf(s3));
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "����ʧ��:ʱ�������ʽ����");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc2() {//���������ӻ��͵�ǰλ����ص����и�������
		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//����ǩ���p2��
		p2.add(test1);//���ı������p2��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(16);
		p2.add(label2);//����ǩ���p2��
		p2.add(test2);//���ı������p2��
		JButton bu = new JButton("ȷ��");
		p2.add(bu);//����ť���p2��
		bu.addActionListener((e)->{//�¼�������
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
				schedule.show(new Location(s2),time);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "����ʧ��:ʱ���������");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc3() {//����һ�и������β��Ҹ���������λ�ú�ʱ���
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("����һ�и�������");
		box.addItem("���������η���ʱ��Ժ�λ��");
		p3.add(box);
		JLabel label1 = new JLabel("������������");
		JTextField test1 = new JTextField(6);
		p3.add(label1);
		p3.add(test1);
		JLabel label3 = new JLabel("�����������λ��");
		JTextField test3 = new JTextField(6);
		p3.add(label3);
		p3.add(test3);
		JLabel label4 = new JLabel("�����������λ��");
		JTextField test4 = new JTextField(6);
		p3.add(label4);
		p3.add(test4);
		JLabel label5 = new JLabel("�����������ʱ��");
		JTextField test5 = new JTextField(12);
		p3.add(label5);
		p3.add(test5);
		JLabel label6 = new JLabel("�����������ʱ��");
		JTextField test6 = new JTextField(12);
		p3.add(label6);
		p3.add(test6);
		JButton bu1 = new JButton("ȷ��");
		p3.add(bu1);//����ť���p3��
		bu1.addActionListener((e)->{//�¼�������
			String s1 = test1.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			String s6 = test6.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time2 = Calendar.getInstance();
			Calendar time3 = Calendar.getInstance();
			try {
				time2.setTime(sdf.parse(s5));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu1, "��������ʱ�������ʽ����,����������");
				e1.printStackTrace();
			}
			try {
				time3.setTime(sdf.parse(s6));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu1, "�����ִ�ʱ�������ʽ��������������");
				e1.printStackTrace();
			}
			if(schedule.addplanningEntry(s1, new Location(s3), new Location(s4), time2, time3))
				JOptionPane.showMessageDialog(bu1, "�����ɹ�");
			else
				JOptionPane.showMessageDialog(bu1, "����ʧ��");
				
		});	
		
	}
	public void initFunc4() {//��ĳ���������η��䳵��
		JLabel label1 = new JLabel("������������α��");
		JTextField test1 = new JTextField(6);
		p4.add(label1);
		p4.add(test1);
		JLabel label6 = new JLabel("�������������ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test6 = new JTextField(10);
		p4.add(label6);
		p4.add(test6);
		JLabel label2 = new JLabel("�����������ĳ�����");
		JTextField test2 = new JTextField(6);
		p4.add(label2);
		p4.add(test2);
		JLabel label3 = new JLabel("�����복������(����/һ��/����/����/Ӳ��/Ӳ��/���/�ͳ�)");
		JTextField test3 = new JTextField(3);
		p4.add(label3);
		p4.add(test3);
		JLabel label4 = new JLabel("�����복������");
		JTextField test4 = new JTextField(3);
		p4.add(label4);
		p4.add(test4);
		JLabel label5 = new JLabel("�����복�᳧���");
		JTextField test5 = new JTextField(4);
		p4.add(label5);
		p4.add(test5);
		JButton bu = new JButton("ȷ��");
		p4.add(bu);//����ť���p2��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			String s6 = test6.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s6));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��������ʱ�������ʽ����");
				e1.printStackTrace();
			}
			Type s ;//��������
			if(s3.equals("����")) {
				s = Type.BUSINESS;
			}else if(s3.equals("һ��")) {
				s = Type.FIRSTCLASS ;
			}else if(s3.equals("����")) {
				s = Type.SECONDCLASS;
			}else if(s3.equals("����")) {
				s = Type.SOFTSLEPPER ;
			}else if(s3.equals("Ӳ��")) {
				s = Type.TOURISTCOACH;
			}else if(s3.equals("Ӳ��")) {
				s = Type.HARDSEATS ;
			}else if(s3.equals("���")) {
				s = Type.BUGGAGECAR ;
			}else {
				s = Type.RESTAURANTCAR ;
			}
			Railway ra = new Railway(s2,s,Integer.valueOf(s4),Integer.valueOf(s5));
			int t = schedule.FeiPeiResource(s1 ,time1,ra );
				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "��Ҫ����ĳ��᲻�ڿ��ó�����");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "��Ҫ���䳵��ĸ����Ѿ������˸ó���");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "������Դ���ڳ�ͻ");
				}else if(t==4) {
					JOptionPane.showMessageDialog(bu, "��Ҫ���䳵��ĸ���������");
				}else {
					JOptionPane.showMessageDialog(bu, "����ɹ�");
				}
			
		});
			
	}
	public void initFunc5() {//��ѯĳ��������״̬
		JLabel label1 = new JLabel("�������������");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//����ǩ���p5��
		p5.add(test1);//���ı������p5��
		JLabel label2 = new JLabel("�������������ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//����ǩ���p5��
		p5.add(test2);//���ı������p5��
		JLabel label3 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test3 = new JTextField(16);
		p5.add(label3);//����ǩ���p5��
		p5.add(test3);//���ı������p5��
		JButton bu = new JButton("�鿴״̬");
		p5.add(bu);//����ť���p5��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��������ʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ǰʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.WatchState(s1, time1, time2) == null) {
				JOptionPane.showMessageDialog(bu, "�Ҳ���ָ���ĸ�������");
			}else {
				JOptionPane.showMessageDialog(bu, "������ǰ��״̬Ϊ:" + schedule.WatchState(s1, time1, time2).toString());
			}
		});
	}
	public void initFunc6() {//�趨������״̬(������ȡ��������������)
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("����ĳ������");
		box.addItem("ȡ��ĳ������");
		box.addItem("����ĳ������");
		box.addItem("����ĳ������");
		p6.add(box);
		JLabel label1 = new JLabel("������������κ�");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//����ǩ���p6��
		p6.add(test1);//���ı������p6��
		JLabel label2 = new JLabel("�������������ʱ��");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//����ǩ���p6��
		p6.add(test2);//���ı������p6��
		JButton bu = new JButton("ȷ��");
		p6.add(bu);
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(bu,schedule.BeginPlanningEntry(s1, time1));
			}else if(box.getSelectedIndex() == 1) {
				JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s1, time1));
			}else if(box.getSelectedIndex() == 3) {
				JOptionPane.showMessageDialog(bu,schedule.EndPlanningEntry(s1, time1));
			}else {
				JOptionPane.showMessageDialog(bu,schedule.BlockPlanningEntry(s1, time1));
			}
		});
	}
	public void initFunc7() {//�鿴��ĳ��������ص����и�������
		JLabel label1 = new JLabel("���������ѯ�ĳ�����");
		JTextField test1 = new JTextField(10);
		p7.add(label1);//����ǩ���p7��
		p7.add(test1);//���ı������p7��
		JLabel label2 = new JLabel("���������ѯ�ĳ�������(����/һ��/����/����/Ӳ��/Ӳ��/���/�ͳ�)");
		JTextField test2 = new JTextField(3);
		p7.add(label2);//����ǩ���p7��
		p7.add(test2);//���ı������p7��
		JLabel label3 = new JLabel("���������ѯ�ĳ��ᶨԱ��");
		JTextField test3 = new JTextField(2);
		p7.add(label3);//����ǩ���p7��
		p7.add(test3);//���ı������p7��
		
		JLabel label4 = new JLabel("���������ѯ�ĳ���������(xxxx)");
		JTextField test4 = new JTextField(4);
		p7.add(label4);//����ǩ���p7��
		p7.add(test4);//���ı������p7��
		JLabel label5 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test5 = new JTextField(16);
		p7.add(label5);//����ǩ���p7��
		p7.add(test5);//���ı������p7��
		
		JButton bu = new JButton("ȷ��") ;
		p7.add(bu);
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s5));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ǰʱ�������ʽ����");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, s2, Integer.valueOf(s3), Integer.valueOf(s4), time1);
			
		});
	}
	public void initFunc8() {//�Գ�����в���(�鿴�����ӡ�ɾ��)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õĳ���");
		box.addItem("���ӿ��õĳ���");
		box.addItem("ɾ�����õĳ���)");
		p8.add(box);
		JLabel label1 = new JLabel("���������ѯ�ĳ�����");
		JTextField test1 = new JTextField(10);
		p8.add(label1);//����ǩ���p8��
		p8.add(test1);//���ı������p8��
		JLabel label2 = new JLabel("���������ѯ�ĳ�������(����/һ��/����/����/Ӳ��/Ӳ��/���/�ͳ�)");
		JTextField test2 = new JTextField(3);
		p8.add(label2);//����ǩ���p8��
		p8.add(test2);//���ı������p8��
		JLabel label3 = new JLabel("���������ѯ�ĳ��ᶨԱ��");
		JTextField test3 = new JTextField(2);
		p8.add(label3);//����ǩ���p8��
		p8.add(test3);//���ı������p8��
		
		JLabel label4 = new JLabel("���������ѯ�ĳ���������(xxxx)");
		JTextField test4 = new JTextField(4);
		p8.add(label4);//����ǩ���p8��
		p8.add(test4);//���ı������p8��
		JButton bu = new JButton("ȷ��") ;
		p8.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex()==0) {
				schedule.showResource();
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else if(box.getSelectedIndex() == 1 ) {
				if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText())))
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				else
					JOptionPane.showMessageDialog(bu, "����ʧ��:����ӵ���Դ�Ѿ�����");
			}else {
				if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText())))
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				else
					JOptionPane.showMessageDialog(bu, "����ʧ��:��ɾ������Դ������");
			}
			});
	}
	public void initFunc9() {//��λ�ý��в���(�鿴�����ӡ�ɾ��)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õ�λ��");
		box.addItem("���ӿ��õ�λ��");
		box.addItem("ɾ�����õ�λ��");
		p9.add(box);
		JLabel label2 = new JLabel("������λ��");
		JTextField test2 = new JTextField(16);
		p9.add(label2);//����ǩ���p9��
		p9.add(test2);//���ı������p9��
		JButton bu = new JButton("ȷ��");
		p9.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex()==0) {
				schedule.showLocation();
				JOptionPane.showMessageDialog(bu, "�����ɹ�");

			}else if(box.getSelectedIndex() == 1) {

				String text2 = test2.getText();
				if(schedule.addLocation(new Location(text2))) {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else {
					JOptionPane.showMessageDialog(bu, "���ʧ��:����ӵ�λ���Ѿ�����");
				}
			}else {
				String text3 = test2.getText();
				if(schedule.deleteLocation(new Location(text3))) {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else {
					JOptionPane.showMessageDialog(bu, "ɾ��ʧ��:��ɾ����λ�ò�����");
				}
		}
	});	
	}
	public void initFunc10() {//չʾ�Ƿ������Դ��ͻ
		JButton bu = new JButton("ȷ��") ;
		p10.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "��������Դ��ͻ");
			}else {
				JOptionPane.showMessageDialog(bu, "������Դ��ͻ");
			}

		});
	}
	public void initFunc11() {//�г�ĳ��������ǰ�����
		JLabel label1 = new JLabel("�����복����");
		JTextField test1 = new JTextField(10);
		p11.add(label1);//����ǩ���p10��
		p11.add(test1);//���ı������p10��
		JLabel label2 = new JLabel("�����복������(����/һ��/����/����/Ӳ��/Ӳ��/���/�ͳ�)");
		JTextField test2 = new JTextField(3);
		p11.add(label2);//����ǩ���p10��
		p11.add(test2);//���ı������p10��
		JLabel label3 = new JLabel("�����복�ᶨԱ��");
		JTextField test3 = new JTextField(2);
		p11.add(label3);//����ǩ���p10��
		p11.add(test3);//���ı������p10��
		JLabel label4 = new JLabel("�����복��������(xxxx)");
		JTextField test4 = new JTextField(4);
		p11.add(label4);//����ǩ���p10��
		p11.add(test4);//���ı������p10��
		JLabel label51 = new JLabel("������������κ�");
		JTextField test5 = new JTextField(14);
		p11.add(label51);//����ǩ���p10��
		p11.add(test5);//���ı������p10��
		JLabel label6 = new JLabel("�������������ʱ��");
		JTextField test6 = new JTextField(16);
		p11.add(label6);//����ǩ���p10��
		p11.add(test6);//���ı������p10��
		JButton bu = new JButton("ȷ��") ;
		p11.add(bu);
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			String s6 = test6.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s6));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(s1, s2, Integer.valueOf(s3), Integer.valueOf(s4),s5,time1)) {
				JOptionPane.showMessageDialog(bu, "�����ɹ�,����ǰ��ƻ���");
			}else {
				JOptionPane.showMessageDialog(bu, "û��ǰ��ƻ���");
			}
		});
	}
		public static void main(String[] args)	{
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						 new TrainScheduelApp().init();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	

