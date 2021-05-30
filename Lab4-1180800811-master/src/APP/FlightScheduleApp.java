package APP;


import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import Location.Location;
import MyException.cancelPlanningEntryException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import applications.FlightSchedule;
import logRecord.logKeeper;

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
	JPanel p13 = new JPanel();//��־��ѯ
	private static Logger logger = Logger.getLogger(FlightScheduleApp.class.getName());//��¼��־
	private Panel cardPannel;//�ڶ�������
	public void init() {//��ʼ��	
		 FileHandler fileHandler;
	        try {
	            fileHandler = new FileHandler("src/text/logger");
	            fileHandler.setFormatter(new Formatter() {

	                @Override public String format(LogRecord record) {
	                    String string = "<record>\n";
	                    string += "<class>" + record.getSourceClassName() + "\n";
	                    string += "<method>" + record.getSourceMethodName() + "\n";
	                    string += "<time>" + new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "\n";
	                    string += "<level>" + record.getLevel() + "\n";
	                    string += "<message>" + record.getMessage() + "\n";
	                    string +="<Exception>" + record.getThrown() + "\n";
	                    return string;
	                }
	            });
	            logger.addHandler(fileHandler);
	            logger.setLevel(Level.INFO);
		}catch (Exception e) {
	        e.printStackTrace();
	        System.exit(-1);
	    }
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
		choice.add("��־��ѯ");
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
		cardPannel.add("��־��ѯ",p13);
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
		initFunc13();
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
			logger.log(Level.INFO, "�����ļ�������ʼ���༯");
			if(box.getSelectedIndex() == 0) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_1");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
					logger.log(Level.INFO, "ʹ���ļ�1���ɺ��༯,�����ɹ�!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					logger.log(Level.SEVERE, "ʹ���ļ�1���ɺ��༯,����ʧ��!" ,e1);
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 1) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_2");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
					logger.log(Level.INFO, "ʹ���ļ�2���ɺ��༯,�����ɹ�!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					logger.log(Level.SEVERE, "ʹ���ļ�2���ɺ��༯,����ʧ��!",e1);
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 2) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_3");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
					logger.log(Level.INFO, "ʹ���ļ�3���ɺ��༯,�����ɹ�!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					logger.log(Level.SEVERE, "ʹ���ļ�3���ɺ��༯,����ʧ��!",e1);
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 3) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_4");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
					logger.log(Level.INFO, "ʹ���ļ�4���ɺ��༯,�����ɹ�!");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					logger.log(Level.SEVERE, "ʹ���ļ�4���ɺ��༯,����ʧ��!",e1);
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 4) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_5");
					logger.log(Level.INFO, "ʹ���ļ�5���ɺ��༯,�����ɹ�!");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "����ʧ��,��ѡ���������ļ����ж�ȡ");
					logger.log(Level.SEVERE, "ʹ���ļ�5���ɺ��༯,����ʧ��!" ,e1);
					e1.printStackTrace();
				}
			}
		});
	}
	public void initFunc2() {//չʾĳ��λ�õ���Ϣ��
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
			logger.log(Level.INFO, "չʾĳ��λ�õ���Ϣ��");
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				logger.log(Level.INFO, "��ѯλ��: "+s2 + "����Ϣ��,��ѯ�ɹ�!");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
				schedule.board(new Location(s2), time, Integer.valueOf(s3));
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				logger.log(Level.SEVERE, "��ѯλ��: "+s2  + "����Ϣ��,ʱ�������ʽ����!" , e2);
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
			logger.log(Level.INFO, "��ѯ��ĳ��λ����ص����мƻ���");
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				logger.log(Level.INFO, "��ѯλ��: "+s2 + "��ص����мƻ���,��ѯ�ɹ�!");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
				schedule.show(new Location(s2),time);
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "��ѯλ��: "+s2 + "��ص����мƻ���,ʱ���ʽ�������!" ,e1);
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
			logger.log(Level.INFO, "����һ������");
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
				logger.log(Level.SEVERE, "���Ӻ���: "+s5 + "��ʼʱ���ʽ�������!" ,e1);
				e1.printStackTrace();
				t= 1 ;
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				logger.log(Level.SEVERE, "���Ӻ���: "+s5 + "��ֹʱ���ʽ�������!" ,e1);
				e1.printStackTrace();
				t=1 ;
			}
			if(t==0) {
				schedule.addPlanningEntry(new Location(s3), new Location(s4),time1 , time2, s5);
				logger.log(Level.INFO, "���Ӻ���: "+s5 + "�����ɹ�!");
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
			logger.log(Level.INFO, "��ĳ���������ɻ�");
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
				logger.log(Level.SEVERE, "������: "+s3 + "����ɻ� ,��ʼʱ�������ʽ����!" ,e1);
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "������: "+s3 + "����ɻ� ,��ֹʱ�������ʽ����!" ,e1);
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();

			}
			try {
				t = schedule.FeiPeiResource(s4, time1, time2, s3);
			} catch (feipeiResourceException e1) {
				logger.log(Level.SEVERE, "������: "+s3 + "����ɻ� ,������Դ�������Դ��ռ��ͻ!" ,e1);
				JOptionPane.showMessageDialog(bu, "����ʧ��:" + e1.getMessage());
			}

				if(t == 0) {
					logger.log(Level.WARNING, "������: "+s3 + "����ɻ� ,��Ҫ����ķɻ����ڿ��÷ɻ���!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ����ķɻ����ڿ��÷ɻ���");
				}else if(t==1) {
					logger.log(Level.WARNING, "������: "+s3 + "����ɻ� ,��Ҫ������Դ�ĺ����Ѿ�������Դ!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ������Դ�ĺ����Ѿ����亽��");
				}else if(t==4) {
					logger.log(Level.WARNING, "������: "+s3 + "����ɻ� ,��Ҫ����ɻ��ĺ��಻����!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ����ɻ��ĺ��಻����");
				}else {
					logger.log(Level.INFO, "������: "+s3 + "����ɻ� ,����ɹ�!" );
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
		JButton bu = new JButton("�鿴״̬");
		p6.add(bu);//����ť����������p2��
		bu.addActionListener((e)->{
			logger.log(Level.INFO, "��ѯĳ�������״̬");
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "��ѯ����: "+s3 + "��״̬ ,��ʼʱ�������ʽ����!",e1 );
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "��ѯ����: "+s3 + "��״̬ ,��ֹʱ�������ʽ����!",e1 );
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.WatchState(s3, time1, time2) == null) {
				logger.log(Level.SEVERE, "��ѯ����: "+s3 + "��״̬ ,��ֹʱ�������ʽ����!");
				JOptionPane.showMessageDialog(bu, "�Ҳ���ָ���ĺ���");
			}else {
				logger.log(Level.INFO, "��ѯ����: "+s3 + "��״̬ ,��ѯ�ɹ�!");
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
			logger.log(Level.INFO, "�趨�����״̬");
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s1));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "�趨����:"  +s3 +  "״̬����ʼʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "�趨����:"  +s3 +  "״̬��ֹʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				String t = schedule.BeginPlanningEntry(s3, time1, time2) ;
				logger.log(Level.INFO, "��������:" + s3 + t);
				JOptionPane.showMessageDialog(bu,t);
			}else if(box.getSelectedIndex() == 1) {
				try {
					JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s3, time1, time2));
				} catch (cancelPlanningEntryException e1) {
					logger.log(Level.SEVERE, "ȡ������:"  +s3 +  "��ǰ�ƻ����״̬������ȡ��",e1);
					JOptionPane.showMessageDialog(bu,"����ʧ��" + e1.getMessage());
				}
			}else {
				String t = schedule.EndPlanningEntry(s3, time1, time2) ;
				logger.log(Level.INFO, "��������:" + s3 + t);
				JOptionPane.showMessageDialog(bu,t);
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
			logger.log(Level.INFO, "�鿴ʹ�÷ɻ�:" + s1 + "�����мƻ���");	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "�鿴ʹ�÷ɻ�:" + s1 + "�����мƻ��� ��ʱ�������ʽ���� ");	
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, time1);
			logger.log(Level.INFO, "�鿴ʹ�÷ɻ�:" + s1 + "�����мƻ���,�����ɹ� ");	
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
			logger.log(Level.INFO, "��λ�ý��в���");
			if(box.getSelectedIndex()==0) {
				schedule.showLocation();
				logger.log(Level.INFO, "�鿴���п��õ�λ�ã������ɹ�");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");

			}else if(box.getSelectedIndex() == 1) {

				String text2 = test2.getText();
				if(schedule.addLocation(new Location(text2))) {
					logger.log(Level.INFO, "���ӿ��õ�λ�ã������ɹ�");
					JOptionPane.showMessageDialog(bu, "�����ɹ�");
				}else {
					logger.log(Level.WARNING, "��ӿ��õ�λ�ã����ʧ��:����ӵ�λ���Ѿ�����");
					JOptionPane.showMessageDialog(bu, "���ʧ��:����ӵ�λ���Ѿ�����");
				}
			}else {
				String text3 = test2.getText();
				try {
					if(schedule.deleteLocation(new Location(text3))) {
						logger.log(Level.INFO , "ɾ�����õ�λ�ã������ɹ�");
						JOptionPane.showMessageDialog(bu, "�����ɹ�");
					}else {
						logger.log(Level.WARNING, "ɾ�����õ�λ�ã�ɾ��ʧ��:��ɾ����λ�ò�����");
						JOptionPane.showMessageDialog(bu, "ɾ��ʧ��:��ɾ����λ�ò�����");
					}
				} catch (deleteLocationException e1) {
						logger.log(Level.SEVERE, "ɾ�����õ�λ�ã�ɾ��ʧ��:����δ�����ļƻ�����ڸ�λ��ִ��",e1);
						JOptionPane.showMessageDialog(bu, "ɾ��λ��ʧ��:" + e1.getMessage());
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
			logger.log(Level.INFO, "����Դ���в���");
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			logger.log(Level.INFO, "�鿴���п��õ���Դ�������ɹ�");
			JOptionPane.showMessageDialog(bu, "�����ɹ�");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText()))) {
				logger.log(Level.INFO, "���ӿ��õ���Դ�������ɹ�");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}
			else {
				logger.log(Level.WARNING, "����ʧ��:����ӵ���Դ�Ѿ�����");
				JOptionPane.showMessageDialog(bu, "����ʧ��:����ӵ���Դ�Ѿ�����");
			}

		}else {
			try {
				if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText()))) {
						JOptionPane.showMessageDialog(bu, "�����ɹ�");
						logger.log(Level.WARNING, "ɾ�����õ���Դ�������ɹ�");
				}

				else {
					logger.log(Level.WARNING, "ɾ�����õ���Դ������ʧ��:��ɾ������Դ������");
					JOptionPane.showMessageDialog(bu, "����ʧ��:��ɾ������Դ������");
				}

			} catch (deleteResourceException e1) {
				logger.log(Level.SEVERE, "ɾ�����õ���Դ������ʧ��:����δ�����ļƻ�������ռ�ø���Դ",e1);
				JOptionPane.showMessageDialog(bu, "����ʧ��:" + e1.getMessage());
			} 
		}
		});
		
	}
	public void initFunc11() {//��ѯ��ͻ
		JButton bu = new JButton("ȷ��") ;
		p11.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				logger.log(Level.INFO, "��ѯ��ͻ����������Դ��ͻ");
				JOptionPane.showMessageDialog(bu, "��������Դ��ͻ");
			}else {
				logger.log(Level.INFO, "��ѯ��ͻ��������Դ��ͻ");
				JOptionPane.showMessageDialog(bu, "������Դ��ͻ");
			}

		});
	}
	public void initFunc12() {//�г�ĳ���ƻ����ǰ��ƻ���
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
			logger.log(Level.INFO, "�г�ĳ���ƻ����ǰ��ƻ���");
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
				logger.log(Level.SEVERE, "�г��ƻ���:" + s3 + "��ǰ��ƻ���:��ʼʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "��ʼʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "�г��ƻ���:" + s3 + "��ǰ��ƻ���:��ֹʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "��ֹʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(time1, time2, s3, s4)) {
				logger.log(Level.INFO, "�г��ƻ���:" + s3 + "��ǰ��ƻ���:�����ɹ�����ǰ��ƻ���");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else {
				logger.log(Level.INFO, "�г��ƻ���:" + s3 + "��ǰ��ƻ���:û��ǰ��ƻ���");
				JOptionPane.showMessageDialog(bu, "����ʧ�ܣ�û��ǰ��ƻ���");
			}
		});
	}
	public void initFunc13() {//��ѯ��־
		JLabel label1 = new JLabel("������ʼʱ��(yyyy-MM-dd HH:mm:ss)");
		JTextField test1 = new JTextField(13);
		p13.add(label1);//����ǩ���p13��
		p13.add(test1);//���ı������p13��
		JLabel label2 = new JLabel("������ֹʱ��(��ʽͬǰ)");
		JTextField test2 = new JTextField(13);
		p13.add(label2);//����ǩ���p13��
		p13.add(test2);//���ı������p13��
		JButton bu1 = new JButton("��ѯ");
		p13.add(bu1);
		JLabel label3 = new JLabel("�����ѯ��־����(INFO/WARNING/SEVERE)");
		JTextField test3 = new JTextField(10);
		p13.add(label3);//����ǩ���p13��
		p13.add(test3);//���ı������p13��
		JButton bu2 = new JButton("��ѯ");
		p13.add(bu2);
		bu1.addActionListener((e)->{
			String s1 = test1.getText() ;
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date time1 = new Date();
			Date time2 = new Date();
			try {
				time1 = sdf.parse(s1);
				time2= sdf.parse(s2);
				new logKeeper("src/text/logger").showRecordsTime(time1, time2);
				JOptionPane.showMessageDialog(bu1, "��ѯ��־:�����ɹ�");
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "��ѯ��־: ����ʧ�ܣ���ѯ����ʼʱ���ʽ����");
				JOptionPane.showMessageDialog(bu1, "����ʧ�ܣ���ʼʱ���ʽ����");
				e1.printStackTrace();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "��ѯ��־: ����ʧ�ܣ���ѯ����ֹʱ���ʽ����");
				JOptionPane.showMessageDialog(bu1, "����ʧ�ܣ���ʼʱ���ʽ����");
				e1.printStackTrace();
			}
		});
		bu2.addActionListener((e)->{
			String s3 = test3.getText() ;
			System.out.println(s3);
			if((s3.equals("INFO") || s3.equals("WARNING") || s3.equals("SEVERE"))) {
				try {
					new logKeeper("src/text/logger").showRecordsType(s3);
					logger.log(Level.INFO, "��ѯ��־:�����ɹ�");
					JOptionPane.showMessageDialog(bu1, "�����ɹ�");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}		
			}else {
				logger.log(Level.INFO, "��ѯ��־:����ʧ�ܣ���־�����������");
				JOptionPane.showMessageDialog(bu1, "����ʧ�ܣ���־�����������");
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




