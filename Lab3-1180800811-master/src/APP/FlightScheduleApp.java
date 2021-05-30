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
	private FlightSchedule schedule = new FlightSchedule();//航班集
	JPanel p1 = new JPanel();//读入文件创建初始航班集
	JPanel p2 = new JPanel();//展示某个位置的信息板
	JPanel p3 = new JPanel();//遍历并可视化和当前位置相关的所有计划项
	JPanel p4 = new JPanel();//增加一个航班
	JPanel p5 = new JPanel();//给某个航班分配飞机
	JPanel p6 = new JPanel();//查询某个航班的当前状态
	JPanel p7 = new JPanel();//设定航班状态(启动、取消、结束)
	JPanel p8 = new JPanel();//查看使用某个飞机的所有计划项
	JPanel p9 = new JPanel();//对资源进行操作(查看、增加、删除)
	JPanel p10 = new JPanel();//对位置进行操作(查看、增加、删除)
	JPanel p11 = new JPanel();//展示是否存在资源或者位置冲突
	JPanel p12 = new JPanel();//列出某个计划项的前序计划项
	private Panel cardPannel;//第二层容器
	public void init() {//初始化		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置正常关闭
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//顶层容器
		Choice choice = new Choice();
		choice.add("读入文件");
		choice.add("展示信息板");
		choice.add("遍历所有航班");
		choice.add("增加航班");
		choice.add("给某个航班分配资源");
		choice.add("查询状态");
		choice.add("设定航班状态(启动、取消、结束)");
		choice.add("查看使用某个飞机的所有航班");
		choice.add("对资源进行操作(查看、增加、删除)");
		choice.add("对位置进行操作(查看、增加、删除)");
		choice.add("查询冲突");
		choice.add("列出某个计划项的前序计划项");
		
		top.add(choice);//顶层容器添加下拉菜单
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("读入文件", p1);
		cardPannel.add("展示信息板", p2);
		cardPannel.add("遍历所有航班", p3);
		cardPannel.add("增加航班", p4);
		cardPannel.add("给某个航班分配资源", p5);
		cardPannel.add("查询状态", p6);
		cardPannel.add("设定航班状态(启动、取消、结束)", p7);
		cardPannel.add("查看使用某个飞机的所有航班", p8);
		cardPannel.add("对资源进行操作(查看、增加、删除)", p9);
		cardPannel.add("对位置进行操作(查看、增加、删除)", p10);
		cardPannel.add("查询冲突", p11);
		cardPannel.add("列出某个计划项的前序计划项",p12);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//为下拉菜单增加事件监听器
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("给某个航班分配资源")) {
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
	public void initFunc1() {//读入文件创建初始航班集
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("使用文件1生成航班集");
		box.addItem("使用文件2生成航班集");
		box.addItem("使用文件3生成航班集");
		box.addItem("使用文件4生成航班集");
		box.addItem("使用文件5生成航班集");
		p1.add(box);
		JButton bu = new JButton("确定");//添加按钮
		p1.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex() == 0) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_1");
					JOptionPane.showMessageDialog(bu, "创建成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "创建失败,请选择其他的文件进行读取");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 1) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_2");
					JOptionPane.showMessageDialog(bu, "创建成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "创建失败,请选择其他的文件进行读取");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 2) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_3");
					JOptionPane.showMessageDialog(bu, "创建成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "创建失败,请选择其他的文件进行读取");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 3) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_4");
					JOptionPane.showMessageDialog(bu, "创建成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "创建失败,请选择其他的文件进行读取");
					e1.printStackTrace();
				}
			}else if(box.getSelectedIndex() == 4) {
				try {
					schedule = new FlightSchedule("src/text/FlightScheduel_5");
					JOptionPane.showMessageDialog(bu, "创建成功");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(bu, "创建失败,请选择其他的文件进行读取");
					e1.printStackTrace();
				}
			}
		});
	}
	public void initFunc2() {//展示某个位置的信息板
//		p2.setLayout(new GridLayout());
		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//将标签添加p2中
		p2.add(test1);//将文本框添加p2中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(10);
		p2.add(label2);//将标签添加p2中
		p2.add(test2);//将文本框添加p2中
		JLabel label3 = new JLabel("请选择查看抵达航班/出发航班(0/1)");
		JTextField test3 = new JTextField(1);
		p2.add(label3);//将标签添加p2中
		p2.add(test3);//将文本框添加p2中
		JButton bu = new JButton("确定");
		p2.add(bu);//将按钮添加p2中
		bu.addActionListener((e)->{//事件监听器
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "操作成功");
				schedule.board(new Location(s2), time, Integer.valueOf(s3));
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc3() {//遍历并可视化和当前位置相关的所有计划项

		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p3.add(label1);//将标签添加p3中
		p3.add(test1);//将文本框添加p3中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(10);
		p3.add(label2);//将标签添加p3中
		p3.add(test2);//将文本框添加p3中
		JButton bu = new JButton("确定");
		p3.add(bu);//将按钮添加p3中
		bu.addActionListener((e)->{//事件监听器
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "操作成功");
				schedule.show(new Location(s2),time);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "时间输入错误");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc4() {//增加一个航班
		JLabel label1 = new JLabel("输入航班起始时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p4.add(label1);//将标签添加p4中
		p4.add(test1);//将文本框添加p4中
		JLabel label4 = new JLabel("输入航班终止时间(格式同前)");
		JTextField test2 = new JTextField(16);
		p4.add(label4);//将标签添加p4中
		p4.add(test2);//将文本框添加p4中
		JLabel label2 = new JLabel("输入航班起始位置");
		JTextField test3 = new JTextField(10);
		p4.add(label2);//将标签添加p4中
		p4.add(test3);//将文本框添加p4中
		
		JLabel label5 = new JLabel("输入航班终止位置");
		JTextField test4 = new JTextField(10);
		p4.add(label5);//将标签添加p4中
		p4.add(test4);//将文本框添加p4中
		
		JLabel label3 = new JLabel("输入航班名称");
		JTextField test5 = new JTextField(6);
		p4.add(label3);//将标签添加p4中
		p4.add(test5);//将文本框添加p4中
		JButton bu = new JButton("确定");
		p4.add(bu);//将按钮添加p4中
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
				t= 1 ;
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
				t=1 ;
			}
			if(t==0) {
				schedule.addPlanningEntry(new Location(s3), new Location(s4),time1 , time2, s5);
				JOptionPane.showMessageDialog(bu, "操作成功");
			}


		});
		
		
	}
	public void initFunc5() {//给某个航班分配飞机
		JLabel label1 = new JLabel("请输入航班出发时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//将标签添加p5中
		p5.add(test1);//将文本框添加p5中
		JLabel label2 = new JLabel("请输入航班到达时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//将标签添加p5中
		p5.add(test2);//将文本框添加p5中
		JLabel label3 = new JLabel("请输入航班号");
		JTextField test3 = new JTextField(16);
		p5.add(label3);//将标签添加p5中
		p5.add(test3);//将文本框添加p5中
		JLabel label4 = new JLabel("输入分配的飞机编号");
		JTextField test4 = new JTextField(10);
		p5.add(label4);//将标签添加p5中
		p5.add(test4);//将文本框添加p5中
		JButton bu = new JButton("确定");
		p5.add(bu);//将按钮添加内容面板p5中
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();

			}
			t = schedule.FeiPeiResource(s4, time1, time2, s3);

				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "想要分配的飞机不在可用飞机内");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "想要分配资源的航班已经分配航班");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "分配后计划项资源存在冲突");
				}else if(t==4) {
					JOptionPane.showMessageDialog(bu, "想要分配飞机的航班不存在");
				}else {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}
	
		});	
	}
	public void initFunc6() {//查询某个航班的状态
		JLabel label1 = new JLabel("请输入航班出发时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//将标签添加p6中
		p6.add(test1);//将文本框添加p6中
		JLabel label2 = new JLabel("请输入航班到达时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//将标签添加p6中
		p6.add(test2);//将文本框添加p6中
		JLabel label3 = new JLabel("请输入航班号");
		JTextField test3 = new JTextField(16);
		p6.add(label3);//将标签添加p6中
		p6.add(test3);//将文本框添加p6中
//		JLabel label4 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
//		JTextField test4 = new JTextField(16);
//		p6.add(label4);//将标签添加p6中
//		p6.add(test4);//将文本框添加p6中
		
		JButton bu = new JButton("查看状态");
		p6.add(bu);//将按钮添加内容面板p2中
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
			}
//			try {
//				time3.setTime(sdf.parse(s2));
//			} catch (ParseException e1) {
//				JOptionPane.showMessageDialog(bu, "当前时间输入格式错误");
//				e1.printStackTrace();
//			}
			if(schedule.WatchState(s3, time1, time2) == null) {
				JOptionPane.showMessageDialog(bu, "找不到指定的航班");
			}else {
				JOptionPane.showMessageDialog(bu, "航班当前的状态为:" + schedule.WatchState(s3, time1, time2).toString());
			}
		});
	}
	public void initFunc7() {//设定航班状态(启动、取消、结束)
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("启动某个航班");
		box.addItem("取消某个航班");
		box.addItem("结束某个航班");
		p7.add(box);
		JLabel label1 = new JLabel("请输入航班出发时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p7.add(label1);//将标签添加p7中
		p7.add(test1);//将文本框添加p7中
		JLabel label2 = new JLabel("请输入航班到达时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p7.add(label2);//将标签添加p7中
		p7.add(test2);//将文本框添加p7中
		JLabel label3 = new JLabel("请输入航班号");
		JTextField test3 = new JTextField(16);
		p7.add(label3);//将标签添加p7中
		p7.add(test3);//将文本框添加p7中
		JButton bu = new JButton("确定");
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
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
	public void initFunc8() {//查看使用某个飞机的所有计划项
		JLabel label1 = new JLabel("请输入待查询的飞机编号");
		JTextField test1 = new JTextField(6);
		p8.add(label1);//将标签添加p8中
		p8.add(test1);//将文本框添加p8中
		JLabel label2 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p8.add(label2);//将标签添加p8中
		p8.add(test2);//将文本框添加p8中
		JButton bu = new JButton("确定") ;
		p8.add(bu);
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time1 = Calendar.getInstance();
			try {
				time1.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, time1);
			JOptionPane.showMessageDialog(bu, "操作成功");
			
		});
	}
	public void initFunc10() {//对位置进行操作
		
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的位置");
		box.addItem("增加可用的位置");
		box.addItem("删除可用的位置");
		p10.add(box);
		JLabel label2 = new JLabel("请输入位置");
		JTextField test2 = new JTextField(16);
		p10.add(label2);//将标签添加p10中
		p10.add(test2);//将文本框添加p10中
		JButton bu = new JButton("确定");
		p10.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex()==0) {
				schedule.showLocation();
				JOptionPane.showMessageDialog(bu, "操作成功");

			}else if(box.getSelectedIndex() == 1) {

				String text2 = test2.getText();
				if(schedule.addLocation(new Location(text2))) {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					JOptionPane.showMessageDialog(bu, "添加失败:待添加的位置已经存在");
				}
			}else {
				String text3 = test2.getText();
				if(schedule.deleteLocation(new Location(text3))) {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					JOptionPane.showMessageDialog(bu, "删除失败:待删除的位置不存在");
				}
		}
		

	});

	}
	public void initFunc9() {//对资源进行操作
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的资源");
		box.addItem("增加可用的资源");
		box.addItem("删除可用的资源");
		p9.add(box);
		JLabel label1 = new JLabel("请输入飞机编号(planeNumber)");
		JTextField test1 = new JTextField(16);
		p9.add(label1);//将标签添加p9中
		p9.add(test1);//将文本框添加p9中
		JLabel label2 = new JLabel("请输入机型号(machineNumber)");
		JTextField test2 = new JTextField(16);
		p9.add(label2);//将标签添加p9中
		p9.add(test2);//将文本框添加p9中
		JLabel label3 = new JLabel("请输入承载人数");
		JTextField test3 = new JTextField(16);
		p9.add(label3);//将标签添加p9中
		p9.add(test3);//将文本框添加p9中
		JLabel label4 = new JLabel("请输入机龄");
		JTextField test4 = new JTextField(16);
		p9.add(label4);//将标签添加p9中
		p9.add(test4);//将文本框添加p9中
		JButton bu = new JButton("确定") ;
		p9.add(bu);
		bu.addActionListener((e)->{
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			JOptionPane.showMessageDialog(bu, "操作成功");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText())))
				JOptionPane.showMessageDialog(bu, "操作成功");
			else
				JOptionPane.showMessageDialog(bu, "操作失败:待添加的资源已经存在");
		}else {
			if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Double.valueOf(test4.getText())))
				JOptionPane.showMessageDialog(bu, "操作成功");
			else
				JOptionPane.showMessageDialog(bu, "操作失败:待删除的资源不存在");
		}
		});
		
	}
	public void initFunc11() {//查询冲突
		JButton bu = new JButton("确定") ;
		p11.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "不存在资源冲突");
			}else {
				JOptionPane.showMessageDialog(bu, "存在资源冲突");
			}

		});
	}
	public void initFunc12() {//列出某个计划项的前序计划项
		p12.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("输入航班起始时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p12.add(label1);//将标签添加p12中
		p12.add(test1);//将文本框添加p12中
		JLabel label2 = new JLabel("输入航班终止时间(格式同前)");
		JTextField test2 = new JTextField(16);
		p12.add(label2);//将标签添加p12中
		p12.add(test2);//将文本框添加p12中
		JLabel label3 = new JLabel("输入航班名称");
		JTextField test3 = new JTextField(6);
		p12.add(label3);//将标签添加p12中
		p12.add(test3);//将文本框添加p12中
		JLabel label4 = new JLabel("输入飞机编号(planeNumber)");
		JTextField test4 = new JTextField(6);
		p12.add(label4);//将标签添加p12中
		p12.add(test4);//将文本框添加p12中
		JButton bu = new JButton("确定");
		p12.add(bu);//将按钮添加内容面板p4中
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(time1, time2, s3, s4)) {
				JOptionPane.showMessageDialog(bu, "操作成功");
			}else {
				JOptionPane.showMessageDialog(bu, "操作失败，没有前序计划项");
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




