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
import Resource.Railway;
import Resource.Type;
import applications.TrainSchedule;
import logRecord.logKeeper;
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
	JPanel p12 = new JPanel(); //��־��ѯ
	private Panel cardPannel;//�ڶ�������
	private Logger logger = Logger.getLogger(TrainScheduelApp.class.getName());
	
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
		choice.add("��ѯ��־");
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
		cardPannel.add("��ѯ��־",p12);
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
		initFunc12();
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
				logger.log(Level.SEVERE, "��ѯλ��: "+s2  + "����Ϣ��,ʱ�������ʽ����!" , e2);
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
				JOptionPane.showMessageDialog(bu, "����ʧ��:ʱ���������");
				logger.log(Level.SEVERE, "��ѯλ��: "+s2 + "��ص����мƻ���,ʱ���ʽ�������!" ,e1);
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
			logger.log(Level.INFO, "����һ�и������β��Ҹ���������λ�ú�ʱ���");
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
				logger.log(Level.SEVERE, "������:" + s1 + "����λ�ú�ʱ���,��ʼʱ���ʽ�������!" ,e1);
				JOptionPane.showMessageDialog(bu1, "��������ʱ�������ʽ����,����������");
				e1.printStackTrace();
			}
			try {
				time3.setTime(sdf.parse(s6));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu1, "�����ִ�ʱ�������ʽ��������������");
				logger.log(Level.SEVERE, "������:" + s1 + "����λ�ú�ʱ���,�ִ�ʱ���ʽ�������!" ,e1);
				e1.printStackTrace();
			}
			if(schedule.addplanningEntry(s1, new Location(s3), new Location(s4), time2, time3)) {
					JOptionPane.showMessageDialog(bu1, "�����ɹ�");
					logger.log(Level.INFO, "������:" + s1 + "����λ�ú�ʱ���,�����ɹ�");
			}

			else {
				logger.log(Level.WARNING, "������:" + s1 + "����λ�ú�ʱ���,����ʧ��");
					JOptionPane.showMessageDialog(bu1, "����ʧ��");
			}

				
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
			logger.log(Level.INFO, "��ĳ���������η��䳵��");
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
				logger.log(Level.SEVERE, "����������: "+s1 + "���䳵�� ,��ʼʱ�������ʽ����!" ,e1);
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
			int t;
			try {
				t = schedule.FeiPeiResource(s1 ,time1,ra );
				if(t == 0) {
					logger.log(Level.WARNING, "����������: "+s1 + "���䳵�� ,��Ҫ����ĳ��᲻�ڿ��ó�����!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ����ĳ��᲻�ڿ��ó�����");
				}else if(t==1) {
					logger.log(Level.WARNING, "����������: "+s1 + "���䳵�� ,��Ҫ���䳵��ĸ����Ѿ������˸ó���!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ���䳵��ĸ����Ѿ������˸ó���");
				}else if(t==4) {
					logger.log(Level.WARNING, "����������: "+s1 + "��Ҫ���䳵��ĸ���������!" );
					JOptionPane.showMessageDialog(bu, "��Ҫ���䳵��ĸ���������");
				}else {
					logger.log(Level.WARNING, "����������: "+s1 + "�����ɹ�!" );
					JOptionPane.showMessageDialog(bu, "����ɹ�");
				}
			} catch (feipeiResourceException e1) {
					logger.log(Level.SEVERE, "����������: "+s1 + "����ʧ��,������Դ���ڳ�ͻ!" ,e1);
					JOptionPane.showMessageDialog(bu, "������Դ���ڳ�ͻ");
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
			logger.log(Level.INFO, "��ѯĳ���������ε�״̬");
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			Calendar time2 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.WARNING, "��ѯ����: "+s1 + "��״̬ ,��ʼʱ�������ʽ����!",e1 );
				JOptionPane.showMessageDialog(bu, "��������ʱ�������ʽ����");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				logger.log(Level.WARNING, "��ѯ����: "+s1 + "��״̬ ,��ǰʱ�������ʽ����!",e1 );
				JOptionPane.showMessageDialog(bu, "��ǰʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.WatchState(s1, time1, time2) == null) {
				logger.log(Level.WARNING, "��ѯ����: "+s1 + "��״̬ ,�Ҳ���ָ���ĸ�������" );
				JOptionPane.showMessageDialog(bu, "�Ҳ���ָ���ĸ�������");
			}else {
				logger.log(Level.INFO, "��ѯ����: "+s1 + "��״̬ ,�����ɹ�" );
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
			logger.log(Level.INFO, "�趨�����״̬");
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "�趨����:"  +s1 +  "״̬����ʼʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				String t =schedule.BeginPlanningEntry(s1, time1);
				logger.log(Level.INFO, "��������:" + s1 + t);
				JOptionPane.showMessageDialog(bu,t);
			}else if(box.getSelectedIndex() == 1) {
				try {
					JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s1, time1));
				}catch (cancelPlanningEntryException e1) {
					logger.log(Level.SEVERE, "ȡ������:"  +s1 +  "״̬���üƻ���ĵ�ǰ״̬������ȡ��",e1);
					JOptionPane.showMessageDialog(bu, "ȡ��ĳ�ƻ����ʱ�򣬸üƻ���ĵ�ǰ״̬������ȡ��");
				}
			}else if(box.getSelectedIndex() == 3) {
				String t =schedule.EndPlanningEntry(s1, time1);
				logger.log(Level.INFO, "��������:" + s1 + t);
				JOptionPane.showMessageDialog(bu,t);
			}else {
				String t =schedule.BlockPlanningEntry(s1, time1);
				logger.log(Level.INFO, "��������:" + s1 + t);
				JOptionPane.showMessageDialog(bu,t);
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
			logger.log(Level.INFO, "�鿴ʹ�ó���:" + s1 + "�����мƻ���");	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s5));
			} catch (ParseException e1) {
				logger.log(Level.WARNING, "�鿴ʹ�ó���:" + s1 + "�����мƻ��� ��ʱ�������ʽ���� ");	
				JOptionPane.showMessageDialog(bu, "��ǰʱ�������ʽ����");
				e1.printStackTrace();
			}
			logger.log(Level.INFO, "�鿴ʹ�ó���:" + s1 + "�����мƻ���,�����ɹ� ");	
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
			logger.log(Level.INFO, "�Գ�����в���");
			if(box.getSelectedIndex()==0) {
				schedule.showResource();
				logger.log(Level.INFO, "�鿴���п��õ���Դ�������ɹ�");
				JOptionPane.showMessageDialog(bu, "�����ɹ�");
			}else if(box.getSelectedIndex() == 1 ) {
				if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText()))) {
						JOptionPane.showMessageDialog(bu, "�����ɹ�");
						logger.log(Level.INFO, "���ӿ��õ���Դ�������ɹ�");
				}
				else {
						JOptionPane.showMessageDialog(bu, "����ʧ��:����ӵ���Դ�Ѿ�����");
						logger.log(Level.WARNING, "����ʧ��:����ӵ���Դ�Ѿ�����");
				}
			}else {
				try {
					if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText()))) {
						JOptionPane.showMessageDialog(bu, "�����ɹ�");
						logger.log(Level.INFO, "ɾ�����õ���Դ�������ɹ�");
					}
					else {
							JOptionPane.showMessageDialog(bu, "����ʧ��:��ɾ������Դ������");	
							logger.log(Level.WARNING, "ɾ�����õ���Դ������ʧ��:��ɾ������Դ������");
					}
				}catch (deleteResourceException e1) {
						logger.log(Level.SEVERE, "ɾ�����õ���Դ������ʧ��:����δ�����ļƻ�������ռ�ø���Դ",e1);
						JOptionPane.showMessageDialog(bu, "����ʧ��:ɾ������Դ��ʱ������δ�����ļƻ�������ռ�ø���Դ");
				}
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
						logger.log(Level.WARNING , "ɾ�����õ�λ�ã�ɾ��ʧ��:��ɾ����λ�ò�����");
						JOptionPane.showMessageDialog(bu, "ɾ��ʧ��:��ɾ����λ�ò�����");
					}
				}catch (deleteLocationException e1) {
					logger.log(Level.SEVERE, "ɾ�����õ�λ�ã�ɾ��ʧ��:����δ�����ļƻ�����ڸ�λ��ִ��",e1);
					JOptionPane.showMessageDialog(bu, "ɾ����λ�õ�ʱ������δ�����ļƻ�����ڸ�λ��ִ��");
				}
		}
	});	
	}
	public void initFunc10() {//չʾ�Ƿ������Դ��ͻ
		JButton bu = new JButton("ȷ��") ;
		p10.add(bu);
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
			logger.log(Level.INFO, "�г�ĳ���ƻ����ǰ��ƻ���");
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
				logger.log(Level.SEVERE, "�г��ƻ���:" + s5 + "��ǰ��ƻ���:ʱ�������ʽ����",e1);
				JOptionPane.showMessageDialog(bu, "ʱ�������ʽ����");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(s1, s2, Integer.valueOf(s3), Integer.valueOf(s4),s5,time1)) {
				logger.log(Level.INFO, "�г��ƻ���:" + s5 + "��ǰ��ƻ���:�����ɹ�����ǰ��ƻ���");
				JOptionPane.showMessageDialog(bu, "�����ɹ�,����ǰ��ƻ���");
			}else {
				logger.log(Level.INFO, "�г��ƻ���:" + s5 + "��ǰ��ƻ���:û��ǰ��ƻ���");
				JOptionPane.showMessageDialog(bu, "û��ǰ��ƻ���");
			}
		});
	}
	public void initFunc12() {//��ѯ��־
		JLabel label1 = new JLabel("������ʼʱ��(yyyy-MM-dd HH:mm:ss)");
		JTextField test1 = new JTextField(13);
		p12.add(label1);//����ǩ���p12��
		p12.add(test1);//���ı������p12��
		JLabel label2 = new JLabel("������ֹʱ��(��ʽͬǰ)");
		JTextField test2 = new JTextField(13);
		p12.add(label2);//����ǩ���p12��
		p12.add(test2);//���ı������p12��
		JButton bu1 = new JButton("��ѯ");
		p12.add(bu1);
		JLabel label3 = new JLabel("�����ѯ��־����(INFO/WARNING/SEVERE)");
		JTextField test3 = new JTextField(10);
		p12.add(label3);//����ǩ���p12��
		p12.add(test3);//���ı������p12��
		JButton bu2 = new JButton("��ѯ");
		p12.add(bu2);
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
				logger.log(Level.INFO , "��ѯ��־:�����ɹ�");
				JOptionPane.showMessageDialog(bu1, "��ѯ��־:�����ɹ�");
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "��ѯ��־: ����ʧ�ܣ���ѯ����ʼʱ���ʽ����",e1);
				JOptionPane.showMessageDialog(bu1, "����ʧ�ܣ���ʼʱ���ʽ����");
				e1.printStackTrace();
			} catch (IOException e1) {
				logger.log(Level.SEVERE, "��ѯ��־: ����ʧ�ܣ���ѯ����ֹʱ���ʽ����",e1);
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
				logger.log(Level.WARNING, "��ѯ��־:����ʧ�ܣ���־�����������");
				JOptionPane.showMessageDialog(bu1, "����ʧ�ܣ���־�����������");
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
	

