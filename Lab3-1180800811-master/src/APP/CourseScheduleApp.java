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
import applications.CourseCalendar;
public class CourseScheduleApp {
	JFrame frame  = new JFrame("CourseSchedule");
	private CourseCalendar schedule = new CourseCalendar();//���༯
	JPanel p1 = new JPanel();//չʾĳ��λ�õ���Ϣ��
	JPanel p2 = new JPanel();//���������ӻ��͵�ǰλ����ص����мƻ���
	JPanel p3 = new JPanel();//����һ�ſγ�
	JPanel p4 = new JPanel();//��ĳ���γ̷����ʦ
	JPanel p5 = new JPanel();//��ѯĳ�ſγ̵ĵ�ǰ״̬
	JPanel p6 = new JPanel();//�趨�γ�״̬(������ȡ��������)
	JPanel p7 = new JPanel();//�鿴��ĳ����ʦ��ص����пγ�
	JPanel p8 = new JPanel();//�Խ�ʦ���в���(�鿴�����ӡ�ɾ��)
	JPanel p9 = new JPanel();//�Խ��ҽ��в���(�鿴�����ӡ�ɾ��)
	JPanel p10 = new JPanel();//չʾ�Ƿ������Դ����λ�ó�ͻ
	JPanel p11 = new JPanel();//�г�ĳ�ſγ̵�ǰ��γ�
	JPanel p12 = new JPanel();//�ı�ĳ�ſγ̵Ľ���
	private Panel cardPannel;//�ڶ�������
	
	public void init() {//��ʼ��		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���������ر�
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//��������
		Choice choice = new Choice();
		choice.add("չʾĳ��λ�õ���Ϣ��");
		choice.add("���������ӻ��͵�ǰλ����ص����мƻ���");
		choice.add("����һ�ſγ�");
		choice.add("��ĳ���γ̷����ʦ");
		choice.add("��ѯĳ�ſγ̵ĵ�ǰ״̬");
		choice.add("�趨�γ�״̬(������ȡ��������)");
		choice.add("�鿴��ĳ����ʦ��ص����пγ�");
		choice.add("�Խ�ʦ���в���(�鿴�����ӡ�ɾ��)");
		choice.add("�Խ��ҽ��в���(�鿴�����ӡ�ɾ��)");
		choice.add("չʾ�Ƿ������Դ����λ�ó�ͻ");
		choice.add("�г�ĳ�ſγ̵�ǰ��γ�");
		choice.add("�ı�ĳ�ſγ̵Ľ���");
		
		top.add(choice);//����������������˵�
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("չʾĳ��λ�õ���Ϣ��", p1);
		cardPannel.add("���������ӻ��͵�ǰλ����ص����мƻ���", p2);
		cardPannel.add("����һ�ſγ�", p3);
		cardPannel.add("��ĳ���γ̷����ʦ", p4);
		cardPannel.add("��ѯĳ�ſγ̵ĵ�ǰ״̬", p5);
		cardPannel.add("�趨�γ�״̬(������ȡ��������)", p6);
		cardPannel.add("�鿴��ĳ����ʦ��ص����пγ�", p7);
		cardPannel.add("�Խ�ʦ���в���(�鿴�����ӡ�ɾ��)", p8);
		cardPannel.add("�Խ��ҽ��в���(�鿴�����ӡ�ɾ��)", p9);
		cardPannel.add("չʾ�Ƿ������Դ����λ�ó�ͻ", p10);
		cardPannel.add("�г�ĳ�ſγ̵�ǰ��γ�", p11);
		cardPannel.add("�ı�ĳ�ſγ̵Ľ���", p12);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//Ϊ�����˵������¼�������
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("��ĳ���γ̷����ʦ")) {
					schedule.showResource();
				}
				((CardLayout)cardPannel.getLayout()).show(cardPannel, (String) e.getItem());
			}
		});
		initFunc1();//չʾĳ��λ�õ���Ϣ��
		initFunc2();//���������ӻ��͵�ǰλ����ص����мƻ���
		initFunc3();//����һ�ſγ�
		initFunc4();//��ĳ���γ̷����ʦ
		initFunc5();//��ѯĳ�ſγ̵ĵ�ǰ״̬
		initFunc6();//�趨�γ�״̬(������ȡ��������)
		initFunc7();//�鿴��ĳ����ʦ��ص����пγ�
		initFunc8();//�Խ�ʦ���в���(�鿴�����ӡ�ɾ��)
		initFunc9();//�Խ��ҽ��в���(�鿴�����ӡ�ɾ��)
		initFunc10();//չʾ�Ƿ������Դ����λ�ó�ͻ
		initFunc11();//�г�ĳ�ſγ̵�ǰ��γ�
		initFunc12();//�ı�ĳ�ſγ̵Ľ���
		frame.add(top);
	}

	public void initFunc1() {
		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p1.add(label1);//����ǩ���p1��
		p1.add(test1);//���ı������p1��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(10);
		p1.add(label2);//����ǩ���p1��
		p1.add(test2);//���ı������p1��
		JButton bu = new JButton("ȷ��");
		p1.add(bu);//����ť����������p1��
		bu.addActionListener((e)->{//�¼�������
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //ʱ���ʽ
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "�ɹ�");
				schedule.board(new Location(s2), time);
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc2() {
		JLabel label1 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//����ǩ���p2��
		p2.add(test1);//���ı������p2��
		JLabel label2 = new JLabel("�����뵱ǰλ��");
		JTextField test2 = new JTextField(10);
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
				schedule.show(new Location(s2),time);
				JOptionPane.showMessageDialog(bu, "�ɹ�");
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "ʱ���������");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc3() {
		p3.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("����γ�����");
		JTextField test1 = new JTextField(10);
		p3.add(label1);//����ǩ���p3��
		p3.add(test1);//���ı������p3��
		JLabel label2 = new JLabel("����γ̿�ʼʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p3.add(label2);//����ǩ���p3��
		p3.add(test2);//���ı������p3��
		JLabel label3 = new JLabel("����γ���ֹʱ��(��ʽͬǰ)");
		JTextField test3 = new JTextField(16);
		p3.add(label3);//����ǩ���p3��
		p3.add(test3);//���ı������p3��
		JLabel label4 = new JLabel("�����Ͽν���");
		JTextField test4 = new JTextField(10);
		p3.add(label4);//����ǩ���p3��
		p3.add(test4);//���ı������p3��
		JButton bu = new JButton("ȷ��");
		p3.add(bu);//����ť����������p4��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				if(schedule.addPlanningEntry(s1, new Location(s4),time1 , time2)) {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else {
					JOptionPane.showMessageDialog(bu, "����ʧ��,�γ̴���λ�ó�ͻ");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
	}
	public void initFunc4() {

		JLabel label1 = new JLabel("������������ʦ�Ŀγ�����");
		JTextField test1 = new JTextField(10);
		p4.add(label1);//����ǩ���p4��
		p4.add(test1);//���ı������p4��
		JLabel label2 = new JLabel("�������ʦ���֤��");
		JTextField test2 = new JTextField(18);
		p4.add(label2);//����ǩ���p4��
		p4.add(test2);//���ı������p4��
		JLabel label3 = new JLabel("�������ʦ����");
		JTextField test3 = new JTextField(10);
		p4.add(label3);//����ǩ���p4��
		p4.add(test3);//���ı������p4��
		JLabel label4 = new JLabel("�����ʦ�Ա�(ture(��)/false(Ů)");
		JTextField test4 = new JTextField(5);
		p4.add(label4);//����ǩ���p4��
		p4.add(test4);//���ı������p4��
		JLabel label5 = new JLabel("�����ʦְ��");
		JTextField test5 = new JTextField(5);
		p4.add(label5);//����ǩ���p4��
		p4.add(test5);//���ı������p4��
		JButton bu = new JButton("ȷ��");
		p4.add(bu);//����ť���p4��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			int t = schedule.FeiPeiResource(s1, s2,s3,Boolean.valueOf(s4),s5);
				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��:ָ���Ŀγ��Ѿ������ʦ");
				}else if(t==2) {
					JOptionPane.showMessageDialog(bu, "����ʧ��:��ָ���Ŀγ��Ѿ������ʦ");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "����ʧ��:�γ̼������Դ��ͻ");
				}else {
					JOptionPane.showMessageDialog(bu, "����ʧ��:������Ľ�ʦ���ڿ��õĽ�ʦ֮��");
				}
					
		});	
	}
	public void initFunc5() {
		JLabel label1 = new JLabel("������γ�����");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//����ǩ���p5��
		p5.add(test1);//���ı������p5��
		JLabel label2 = new JLabel("�����뵱ǰʱ��(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//����ǩ���p5��
		p5.add(test2);//���ı������p5��
		JButton bu = new JButton("ȷ��");
		p5.add(bu);//����ť���p5��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "ʱ���ʽ�������");
				e1.printStackTrace();
			}
			schedule.WatchState(s1, time);
			JOptionPane.showMessageDialog(bu, "�����ɹ�");
			
		});

		
	}
	public void initFunc6() {
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("����ĳ�ſγ�");
		box.addItem("ȡ��ĳ�ſγ�");
		box.addItem("����ĳ�ſγ�");
		p6.add(box);
		JLabel label1 = new JLabel("������γ�����");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//����ǩ���p6��
		p6.add(test1);//���ı������p6��
		JLabel label2 = new JLabel("������γ̿�ʼʱ��");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//����ǩ���p6��
		p6.add(test2);//���ı������p6��
		JLabel label3 = new JLabel("������γ̽���ʱ��");
		JTextField test3 = new JTextField(16);
		p6.add(label3);//����ǩ���p6��
		p6.add(test3);//���ı������p6��
		JLabel label4 = new JLabel("���������");
		JTextField test4 = new JTextField(16);
		p6.add(label4);//����ǩ���p6��
		p6.add(test4);//���ı������p6��
		JButton bu = new JButton("ȷ��");
		p6.add(bu);	
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(bu,schedule.BeginPlanningEntry(s1,time1,time2,new Location(s4)));
			}else if(box.getSelectedIndex() == 1) {
				JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s1,time1,time2,new Location(s4)));
			}else {
				JOptionPane.showMessageDialog(bu,schedule.EndPlanningEntry(s1,time1,time2,new Location(s4)));
			}
		});
		
	}
	public void initFunc7() {//�鿴��ĳ����ʦ��ص����пγ�
		JLabel label1 = new JLabel("�������ʦ����");
		JTextField test1 = new JTextField(16);
		p7.add(label1);//����ǩ���p7��
		p7.add(test1);//���ı������p7��
		JLabel label2 = new JLabel("�������ʦ���֤��");
		JTextField test2 = new JTextField(18);
		p7.add(label2);//����ǩ���p7��
		p7.add(test2);//���ı������p7��
		JLabel label3 = new JLabel("�����ʦ�Ա�(ture(��)/false(Ů)");
		JTextField test3 = new JTextField(5);
		p7.add(label3);//����ǩ���p7��
		p7.add(test3);//���ı������p7��
		JLabel label4 = new JLabel("�������ʦְ��");
		JTextField test4 = new JTextField(16);
		p7.add(label4);//����ǩ���p7��
		p7.add(test4);//���ı������p7��
		JLabel label5 = new JLabel("�����뵱ǰʱ��");
		JTextField test5 = new JTextField(16);
		p7.add(label5);//����ǩ���p7��
		p7.add(test5);//���ı������p7��
		JButton bu = new JButton("ȷ��");
		p7.add(bu);//����ť���p7��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s5));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, s2, Boolean.valueOf(s3),s4, time);
			
		
	});
}
	public void initFunc8() {//�Խ�ʦ���в���(�鿴�����ӡ�ɾ��)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õĽ�ʦ");
		box.addItem("���ӿ��õĽ�ʦ");
		box.addItem("ɾ�����õĽ�ʦ");
		p8.add(box);
		JLabel label1 = new JLabel("�������ʦ����");
		JTextField test1 = new JTextField(16);
		p8.add(label1);//����ǩ���p8��
		p8.add(test1);//���ı������p8��
		JLabel label2 = new JLabel("�������ʦ���֤��");
		JTextField test2 = new JTextField(18);
		p8.add(label2);//����ǩ���p8��
		p8.add(test2);//���ı������p8��
		JLabel label3 = new JLabel("�����ʦ�Ա�(ture(��)/false(Ů)");
		JTextField test3 = new JTextField(5);
		p8.add(label3);//����ǩ���p8��
		p8.add(test3);//���ı������p8��
		JLabel label4 = new JLabel("�������ʦְ��");
		JTextField test4 = new JTextField(16);
		p8.add(label4);//����ǩ���p8��
		p8.add(test4);//���ı������p8��
		JButton bu = new JButton("ȷ��") ;
		p8.add(bu);
		bu.addActionListener((e)->{
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			JOptionPane.showMessageDialog(bu, "�����ɹ�");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText()))
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			else
				JOptionPane.showMessageDialog(bu, "����ʧ��:����ӵĽ�ʦ�Ѿ�����");
		}else {
			if(schedule.deleteResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText()))
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			else
				JOptionPane.showMessageDialog(bu, "����ʧ��:��ɾ���Ľ�ʦ������");
		}
		});
	}
	public void initFunc9() {//�Խ��ҽ��в���(�鿴�����ӡ�ɾ��)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("�鿴���п��õĽ���");
		box.addItem("���ӿ��õĽ���");
		box.addItem("ɾ�����õĽ���");
		p9.add(box);
		JLabel label2 = new JLabel("�������ʦ");
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
					JOptionPane.showMessageDialog(bu, "���ʧ��:����ӵĽ����Ѿ�����");
				}
			}else {
				String text3 = test2.getText();
				if(schedule.deleteLocation(new Location(text3))) {
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else {
					JOptionPane.showMessageDialog(bu, "ɾ��ʧ��:��ɾ���Ľ��Ҳ�����");
				}
		}
		

	});
	}
	public void initFunc10() {//չʾ�Ƿ������Դ����λ�ó�ͻ
		JButton bu = new JButton("ȷ��") ;
		p10.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "��������Դ/λ�ó�ͻ");
			}else {
				JOptionPane.showMessageDialog(bu, "������Դ/λ�ó�ͻ");
			}

		});
	}
	public void initFunc11() {//�г�ĳ�ſγ̵�ǰ��γ�
		p11.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("������γ�����");
		JTextField test1 = new JTextField(16);
		p11.add(label1);//����ǩ���p11��
		p11.add(test1);//���ı������p11��
		JLabel label2 = new JLabel("������γ̵Ľ���");
		JTextField test2 = new JTextField(16);
		p11.add(label2);//����ǩ���p11��
		p11.add(test2);//���ı������p11��
		JLabel label3 = new JLabel("������ÿγ̽�ʦ������");
		JTextField test3 = new JTextField(10);
		p11.add(label3);//����ǩ���p11��
		p11.add(test3);//���ı������p11��
		JLabel label4 = new JLabel("������ÿγ̽�ʦ�����֤��");
		JTextField test4 = new JTextField(18);
		p11.add(label4);//����ǩ���p11��
		p11.add(test4);//���ı������p11��
		JButton bu = new JButton("ȷ��");
		p11.add(bu);//����ť����������p11��
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			if(schedule.getPrePlanningEntry(s1, new Location(s2), s3, s4)) {
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else {
				JOptionPane.showMessageDialog(bu, "����ʧ�ܣ�û��ǰ��ƻ���");
			}
		});
	}
	public void initFunc12() {//�ı�ĳ�ſγ̵Ľ���
		p12.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("������γ�����");
		JTextField test1 = new JTextField(16);
		p12.add(label1);//����ǩ���p12��
		p12.add(test1);//���ı������p12��
		JLabel label2 = new JLabel("������γ̵ĳ�ʼ����");
		JTextField test2 = new JTextField(16);
		p12.add(label2);//����ǩ���p12��
		p12.add(test2);//���ı������p12��
		JLabel label3 = new JLabel("������γ̸��ĺ�Ľ���");
		JTextField test3 = new JTextField(10);
		p12.add(label3);//����ǩ���p12��
		p12.add(test3);//���ı������p12��
		JButton bu = new JButton("ȷ��");
		p12.add(bu);//����ť����������p12��
		bu.addActionListener((e)->{
			if(schedule.changeLocationEntry(test1.getText(), new Location(test2.getText()), new Location(test3.getText()))) {
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else {
				JOptionPane.showMessageDialog(bu, "û���ҵ�ָ���Ŀγ�");
			}
		});
	}
	public static void main(String[] args)	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new CourseScheduleApp().init();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
