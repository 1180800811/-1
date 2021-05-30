package APP;


import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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
import applications.FlightSchedule;

public class FlightScheduleApp {
	JFrame frame  = new JFrame("FlightSchedule");
	private FlightSchedule schedule = new FlightSchedule();//���༯
	JPanel p1 = new JPanel();//�����ļ�������ʼ���༯
	JPanel p2 = new JPanel();//չʾĳ��λ�õ���Ϣ��
	JPanel p3 = new JPanel();//���������ӻ��͵�ǰλ����ص����мƻ���
	JPanel p4 = new JPanel();//����һ������
	JPanel p5 = new JPanel();//��ĳ���������ɻ�
	JPanel p6 = new JPanel();//��ѯĳ������ĵ�ǰ״̬
	JPanel p7 = new JPanel();//�趨����״̬(������ȡ��������)
	JPanel p8 = new JPanel();//�鿴ʹ��ĳ���ɻ������мƻ���
	JPanel p9 = new JPanel();//����Դ���в���(�鿴�����ӡ�ɾ��)
	JPanel p10 = new JPanel();//��λ�ý��в���(�鿴�����ӡ�ɾ��)
	JPanel p11 = new JPanel();//չʾ�Ƿ������Դ����λ�ó�ͻ
	JPanel p12 = new JPanel();//�г�ĳ���ƻ����ǰ��ƻ���
	private Panel cardPannel;//�ڶ�������
	public void init() {//��ʼ��		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���������ر�
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//��������
		Choice choice = new Choice();
		choice.add("�����ļ�");
		choice.add("չʾ��Ϣ��");
		choice.add("�������к���");
		choice.add("���Ӻ���");
		choice.add("��ĳ�����������Դ");
		choice.add("��ѯ״̬");
		choice.add("�趨����״̬(������ȡ��������)");
		choice.add("�鿴ʹ��ĳ���ɻ������к���");
		choice.add("����Դ���в���(�鿴�����ӡ�ɾ��)");
		choice.add("��λ�ý��в���(�鿴�����ӡ�ɾ��)");
		choice.add("��ѯ��ͻ");
		choice.add("�г�ĳ���ƻ����ǰ��ƻ���");
		
		top.add(choice);//����������������˵�
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("�����ļ�", p1);
		cardPannel.add("չʾ��Ϣ��", p2);
		cardPannel.add("�������к���", p3);
		cardPannel.add("���Ӻ���", p4);
		cardPannel.add("��ĳ�����������Դ", p5);
		cardPannel.add("��ѯ״̬", p6);
		cardPannel.add("�趨����״̬(������ȡ��������)", p7);
		cardPannel.add("�鿴ʹ��ĳ���ɻ������к���", p8);
		cardPannel.add("����Դ���в���(�鿴�����ӡ�ɾ��)", p9);
		cardPannel.add("��λ�ý��в���(�鿴�����ӡ�ɾ��)", p10);
		cardPannel.add("��ѯ��ͻ", p11);
		cardPannel.add("�г�ĳ���ƻ����ǰ��ƻ���",p12);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//Ϊ�����˵������¼�������
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("��ĳ�����������Դ")) {
					schedule.showResource();
				}
				((CardLayout)cardPannel.getLayout()).show(cardPannel, (String) e.getItem());
			}
		});

		initFunc1();
		initFunc2();
		initFunc3();
		initFunc4();
		initFunc5();
		initFunc6();
		initFunc7();
		initFunc8();
		initFunc9();
		initFunc10();
		initFunc11();
		initFunc12();
		frame.add(top);
	}
	public void initFunc1() {//�����ļ�������ʼ���༯
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("ʹ���ļ�1���ɺ��༯");
		box.addItem("ʹ���ļ�2���ɺ��༯");
		box.addItem("ʹ���ļ�3���ɺ��༯");
		box.addItem("ʹ���ļ�4���ɺ��༯");
		box.addItem("ʹ���ļ�5���ɺ��༯");
		p1.add(box);
		JButton bu = new JButton("ȷ��");//��Ӱ�ť
		p1.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex() == 0) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_1");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 1) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_2");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 2) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_3");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 3) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_4");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 4) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_5");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					e1.printStackTrace();
				}
			}
		});
	}
	public void initFunc2() {//չʾĳ��λ�õ���Ϣ��
//		p2.setLayout(new GridLayout());
		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//����ǩ���p2��
		p2.add(test1);//���ı������p2��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(10);
		p2.add(label2);//����ǩ���p2��
		p2.add(test2);//���ı������p2��
		JLabel label3 = new JLabel("��ѡ��鿴�ִﺽ��/��������(0/1)");
		JTextField test3 = new JTextField(1);
		p2.add(label3);//����ǩ���p2��
		p2.add(test3);//���ı������p2��
		JButton bu = new JButton("ȷ��");
		p2.add(bu);//����ť���p2��
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
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc3() {//���������ӻ��͵�ǰλ����ص����мƻ���

		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p3.add(label1);//����ǩ���p3��
		p3.add(test1);//���ı������p3��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(10);
		p3.add(label2);//����ǩ���p3��
		p3.add(test2);//���ı������p3��
		JButton bu = new JButton("ȷ��");
		p3.add(bu);//����ť���p3��
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
				JOptionPane.showMessageDialog(bu, "ʱ���������");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc4() {//����һ������
		JLabel label1 = new JLabel("���뺽����ʼʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p4.add(label1);//����ǩ���p4��
		p4.add(test1);//���ı������p4��
		JLabel label4 = new JLabel("���뺽����ֹʱ��(��ʽͬǰ)");
		JTextField test2 = new JTextField(16);
		p4.add(label4);//����ǩ���p4��
		p4.add(test2);//���ı������p4��
		JLabel label2 = new JLabel("���뺽����ʼλ��");
		JTextField test3 = new JTextField(10);
		p4.add(label2);//����ǩ���p4��
		p4.add(test3);//���ı������p4��
		
		JLabel label5 = new JLabel("���뺽����ֹλ��");
		JTextField test4 = new JTextField(10);
		p4.add(label5);//����ǩ���p4��
		p4.add(test4);//���ı������p4��
		
		JLabel label3 = new JLabel("���뺽������");
		JTextField test5 = new JTextField(6);
		p4.add(label3);//����ǩ���p4��
		p4.add(test5);//���ı������p4��
		JButton bu = new JButton("ȷ��");
		p4.add(bu);//����ť���p4��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			int t = 0 ;
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
				t= 1 ;
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
				t=1 ;
			}
			if(t==0) {
				schedule.addPlanningEntry(new Location(s3), new Location(s4),time1 , time2, s5);
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}


		});
		
		
	}
	public void initFunc5() {//��ĳ���������ɻ�
		JLabel label1 = new JLabel("�����뺽�����ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//����ǩ���p5��
		p5.add(test1);//���ı������p5��
		JLabel label2 = new JLabel("�����뺽�ൽ��ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//����ǩ���p5��
		p5.add(test2);//���ı������p5��
		JLabel label3 = new JLabel("�����뺽���");
		JTextField test3 = new JTextField(16);
		p5.add(label3);//����ǩ���p5��
		p5.add(test3);//���ı������p5��
		JLabel label4 = new JLabel("�������ķɻ����");
		JTextField test4 = new JTextField(10);
		p5.add(label4);//����ǩ���p5��
		p5.add(test4);//���ı������p5��
		JButton bu = new JButton("ȷ��");
		p5.add(bu);//����ť����������p5��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			int t = 0 ;

			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();

			}
			t = schedule.FeiPeiResource(s4, time1, time2, s3);

				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "��Ҫ����ķɻ����ڿ��÷ɻ���");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "��Ҫ������Դ�ĺ����Ѿ����亽��");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "�����ƻ�����Դ���ڳ�ͻ");
				}else if(t==4) {
					JOptionPane.showMessageDialog(bu, "��Ҫ����ɻ��ĺ��಻����");
				}else {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}
	
		});	
	}
	public void initFunc6() {//��ѯĳ�������״̬
		JLabel label1 = new JLabel("�����뺽�����ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//����ǩ���p6��
		p6.add(test1);//���ı������p6��
		JLabel label2 = new JLabel("�����뺽�ൽ��ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//����ǩ���p6��
		p6.add(test2);//���ı������p6��
		JLabel label3 = new JLabel("�����뺽���");
		JTextField test3 = new JTextField(16);
		p6.add(label3);//����ǩ���p6��
		p6.add(test3);//���ı������p6��
//		JLabel label4 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
//		JTextField test4 = new JTextField(16);
//		p6.add(label4);//����ǩ���p6��
//		p6.add(test4);//���ı������p6��
		
		JButton bu = new JButton("�鿴״̬");
		p6.add(bu);//����ť����������p2��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
//			Calendar time3 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
//			try {
//				time3.setTime(sdf.parse(s2));
//			} catch (ParseException e1) {
//				JOptionPane.showMessageDialog(bu, "��ǰʱ�������ʽ����");
//				e1.printStackTrace();
//			}
			if(schedule.WatchState(s3, time1, time2) == null) {
				JOptionPane.showMessageDialog(bu, "�Ҳ���ָ���ĺ���");
			}else {
				JOptionPane.showMessageDialog(bu, "���൱ǰ��״̬Ϊ:" + schedule.WatchState(s3, time1, time2).toString());
			}
		});
	}
	public void initFunc7() {//�趨����״̬(������ȡ��������)
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("����ĳ������");
		box.addItem("ȡ��ĳ������");
		box.addItem("����ĳ������");
		p7.add(box);
		JLabel label1 = new JLabel("�����뺽�����ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p7.add(label1);//����ǩ���p7��
		p7.add(test1);//���ı������p7��
		JLabel label2 = new JLabel("�����뺽�ൽ��ʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p7.add(label2);//����ǩ���p7��
		p7.add(test2);//���ı������p7��
		JLabel label3 = new JLabel("�����뺽���");
		JTextField test3 = new JTextField(16);
		p7.add(label3);//����ǩ���p7��
		p7.add(test3);//���ı������p7��
		JButton bu = new JButton("ȷ��");
		p7.add(bu);
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(bu,schedule.BeginPlanningEntry(s3, time1, time2));
			}else if(box.getSelectedIndex() == 1) {
				JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s3, time1, time2));
			}else {
				JOptionPane.showMessageDialog(bu,schedule.EndPlanningEntry(s3, time1, time2));
			}
		});
	}
	public void initFunc8() {//�鿴ʹ��ĳ���ɻ������мƻ���
		JLabel label1 = new JLabel("���������ѯ�ķɻ����");
		JTextField test1 = new JTextField(6);
		p8.add(label1);//����ǩ���p8��
		p8.add(test1);//���ı������p8��
		JLabel label2 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p8.add(label2);//����ǩ���p8��
		p8.add(test2);//���ı������p8��
		JButton bu = new JButton("ȷ��") ;
		p8.add(bu);
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
			schedule.getResourcePlanningEntry(s1, time1);
			JOptionPane.showMessageDialog(bu, "�����ɹ�");
			
		});
	}
	public void initFunc10() {//��λ�ý��в���
		
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õ�λ��");
		box.addItem("���ӿ��õ�λ��");
		box.addItem("ɾ�����õ�λ��");
		p10.add(box);
		JLabel label2 = new JLabel("������λ��");
		JTextField test2 = new JTextField(16);
		p10.add(label2);//����ǩ���p10��
		p10.add(test2);//���ı������p10��
		JButton bu = new JButton("ȷ��");
		p10.add(bu);
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
	public void initFunc9() {//����Դ���в���
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õ���Դ");
		box.addItem("���ӿ��õ���Դ");
		box.addItem("ɾ�����õ���Դ");
		p9.add(box);
		JLabel label1 = new JLabel("������ɻ����(planeNumber)");
		JTextField test1 = new JTextField(16);
		p9.add(label1);//����ǩ���p9��
		p9.add(test1);//���ı������p9��
		JLabel label2 = new JLabel("��������ͺ�(machineNumber)");
		JTextField test2 = new JTextField(16);
		p9.add(label2);//����ǩ���p9��
		p9.add(test2);//���ı������p9��
		JLabel label3 = new JLabel("�������������");
		JTextField test3 = new JTextField(16);
		p9.add(label3);//����ǩ���p9��
		p9.add(test3);//���ı������p9��
		JLabel label4 = new JLabel("���������");
		JTextField test4 = new JTextField(16);
		p9.add(label4);//����ǩ���p9��
		p9.add(test4);//���ı������p9��
		JButton bu = new JButton("ȷ��") ;
		p9.add(bu);
		bu.addActionListener((e)->{
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			JOptionPane.showMessageDialog(bu, "�����ɹ�");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText())))
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			else
				JOptionPane.showMessageDialog(bu, "����ʧ��:����ӵ���Դ�Ѿ�����");
		}else {
			if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText())))
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			else
				JOptionPane.showMessageDialog(bu, "����ʧ��:��ɾ������Դ������");
		}
		});
		
	}
	public void initFunc11() {//��ѯ��ͻ
		JButton bu = new JButton("ȷ��") ;
		p11.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "��������Դ��ͻ");
			}else {
				JOptionPane.showMessageDialog(bu, "������Դ��ͻ");
			}

		});
	}
	public void initFunc12() {//�г�ĳ���ƻ����ǰ��ƻ���
		p12.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("���뺽����ʼʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p12.add(label1);//����ǩ���p12��
		p12.add(test1);//���ı������p12��
		JLabel label2 = new JLabel("���뺽����ֹʱ��(��ʽͬǰ)");
		JTextField test2 = new JTextField(16);
		p12.add(label2);//����ǩ���p12��
		p12.add(test2);//���ı������p12��
		JLabel label3 = new JLabel("���뺽������");
		JTextField test3 = new JTextField(6);
		p12.add(label3);//����ǩ���p12��
		p12.add(test3);//���ı������p12��
		JLabel label4 = new JLabel("����ɻ����(planeNumber)");
		JTextField test4 = new JTextField(6);
		p12.add(label4);//����ǩ���p12��
		p12.add(test4);//���ı������p12��
		JButton bu = new JButton("ȷ��");
		p12.add(bu);//����ť����������p4��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(time1, time2, s3, s4)) {
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else {
				JOptionPane.showMessageDialog(bu, "����ʧ�ܣ�û��ǰ��ƻ���");
			}
		});
	}
	public static void main(String[] args)	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new FlightScheduleApp().init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}




