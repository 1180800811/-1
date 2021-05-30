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
	private TrainSchedule schedule = new TrainSchedule();//航班集
	JPanel p1 = new JPanel();//展示某个位置的信息板
	JPanel p2 = new JPanel();//遍历并可视化和当前位置相关的所有高铁车次
	JPanel p3 = new JPanel();//增加一列高铁车次并且给高铁分配位置和时间对
	JPanel p4 = new JPanel();//给某个高铁车次分配车厢
	JPanel p5 = new JPanel();//查询某个高铁的状态
	JPanel p6 = new JPanel();//设定高铁的状态(启动、取消、结束、阻塞)
	JPanel p7 = new JPanel();//查看和某个车厢相关的所有高铁车次
	JPanel p8 = new JPanel();//对车厢进行操作(查看、增加、删除)
	JPanel p9 = new JPanel();//对位置进行操作(查看、增加、删除)
	JPanel p10 = new JPanel();//展示是否存在资源冲突
	JPanel p11 = new JPanel();//列出某个高铁的前序高铁
	private Panel cardPannel;//第二层容器
	
	public void init() {//初始化
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置正常关闭
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//顶层容器
		Choice choice = new Choice();
		choice.add("展示某个位置的信息板");
		choice.add("遍历并可视化和当前位置相关的所有高铁车次");
		choice.add("增加一列高铁车次并且给高铁分配位置和时间对");
		choice.add("给某个高铁车次分配车厢");
		choice.add("查询某个高铁的状态");
		choice.add("设定高铁的状态(启动、取消、结束、阻塞)");
		choice.add("查看和某个车厢相关的所有高铁车次");
		choice.add("对车厢进行操作(查看、增加、删除)");
		choice.add("对位置进行操作(查看、增加、删除)");
		choice.add("展示是否存在资源冲突");
		choice.add("列出某个高铁的前序高铁");
		top.add(choice);//顶层容器添加下拉菜单
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("展示某个位置的信息板", p1);
		cardPannel.add("遍历并可视化和当前位置相关的所有高铁车次", p2);
		cardPannel.add("增加一列高铁车次并且给高铁分配位置和时间对", p3);
		cardPannel.add("给某个高铁车次分配车厢", p4);
		cardPannel.add("查询某个高铁的状态", p5);
		cardPannel.add("设定高铁的状态(启动、取消、结束、阻塞)", p6);
		cardPannel.add("查看和某个车厢相关的所有高铁车次", p7);
		cardPannel.add("对车厢进行操作(查看、增加、删除)", p8);
		cardPannel.add("对位置进行操作(查看、增加、删除)", p9);
		cardPannel.add("展示是否存在资源冲突", p10);
		cardPannel.add("列出某个高铁的前序高铁", p11);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//为下拉菜单增加事件监听器
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("给某个高铁车次分配车厢")) {
					schedule.showResource();
				}
				((CardLayout)cardPannel.getLayout()).show(cardPannel, (String) e.getItem());
			}
		});
		initFunc1();//展示某个位置的信息板
		initFunc2();//遍历并可视化和当前位置相关的所有高铁车次
		initFunc3();//增加一列高铁车次
		initFunc4();//给某个高铁车次分配车厢
		initFunc5();//查询某个高铁的状态
		initFunc6();//设定高铁的状态(启动、取消、结束、阻塞)
		initFunc7();//查看和某个车厢相关的所有高铁车次
		initFunc8();//对车厢进行操作(查看、增加、删除)
		initFunc9();//对位置进行操作(查看、增加、删除)
		initFunc10();//展示是否存在资源冲突
		initFunc11();//列出某个高铁的前序高铁
		frame.add(top);
	}
	public void initFunc1() {//展示某个位置的信息板
		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p1.add(label1);//将标签添加p1中
		p1.add(test1);//将文本框添加p1中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(16);
		p1.add(label2);//将标签添加p1中
		p1.add(test2);//将文本框添加p1中
		JLabel label3 = new JLabel("请选择查看抵达车次/出发出发车次(0/1)");
		JTextField test3 = new JTextField(1);
		p1.add(label3);//将标签添加p1中
		p1.add(test3);//将文本框添加p1中
		JButton bu = new JButton("确定");
		p1.add(bu);//将按钮添加p1中
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
				JOptionPane.showMessageDialog(bu, "操作失败:时间输入格式错误");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc2() {//遍历并可视化和当前位置相关的所有高铁车次
		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//将标签添加p2中
		p2.add(test1);//将文本框添加p2中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(16);
		p2.add(label2);//将标签添加p2中
		p2.add(test2);//将文本框添加p2中
		JButton bu = new JButton("确定");
		p2.add(bu);//将按钮添加p2中
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
				JOptionPane.showMessageDialog(bu, "操作失败:时间输入错误");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc3() {//增加一列高铁车次并且给高铁分配位置和时间对
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("增加一列高铁车次");
		box.addItem("给高铁车次分配时间对和位置");
		p3.add(box);
		JLabel label1 = new JLabel("请输入高铁编号");
		JTextField test1 = new JTextField(6);
		p3.add(label1);
		p3.add(test1);
		JLabel label3 = new JLabel("输入高铁出发位置");
		JTextField test3 = new JTextField(6);
		p3.add(label3);
		p3.add(test3);
		JLabel label4 = new JLabel("输入高铁到达位置");
		JTextField test4 = new JTextField(6);
		p3.add(label4);
		p3.add(test4);
		JLabel label5 = new JLabel("输入高铁出发时间");
		JTextField test5 = new JTextField(12);
		p3.add(label5);
		p3.add(test5);
		JLabel label6 = new JLabel("输入高铁到达时间");
		JTextField test6 = new JTextField(12);
		p3.add(label6);
		p3.add(test6);
		JButton bu1 = new JButton("确定");
		p3.add(bu1);//将按钮添加p3中
		bu1.addActionListener((e)->{//事件监听器
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
				JOptionPane.showMessageDialog(bu1, "高铁出发时间输入格式错误,请重新输入");
				e1.printStackTrace();
			}
			try {
				time3.setTime(sdf.parse(s6));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu1, "高铁抵达时间输入格式错误，请重新输入");
				e1.printStackTrace();
			}
			if(schedule.addplanningEntry(s1, new Location(s3), new Location(s4), time2, time3))
				JOptionPane.showMessageDialog(bu1, "操作成功");
			else
				JOptionPane.showMessageDialog(bu1, "操作失败");
				
		});	
		
	}
	public void initFunc4() {//给某个高铁车次分配车厢
		JLabel label1 = new JLabel("请输入高铁车次编号");
		JTextField test1 = new JTextField(6);
		p4.add(label1);
		p4.add(test1);
		JLabel label6 = new JLabel("请输入高铁出发时间(yyyy-MM-dd HH:mm)");
		JTextField test6 = new JTextField(10);
		p4.add(label6);
		p4.add(test6);
		JLabel label2 = new JLabel("请输入分配待的车厢编号");
		JTextField test2 = new JTextField(6);
		p4.add(label2);
		p4.add(test2);
		JLabel label3 = new JLabel("请输入车厢类型(商务/一等/二等/软卧/硬卧/硬座/行李车/餐车)");
		JTextField test3 = new JTextField(3);
		p4.add(label3);
		p4.add(test3);
		JLabel label4 = new JLabel("请输入车厢人数");
		JTextField test4 = new JTextField(3);
		p4.add(label4);
		p4.add(test4);
		JLabel label5 = new JLabel("请输入车厢厂年份");
		JTextField test5 = new JTextField(4);
		p4.add(label5);
		p4.add(test5);
		JButton bu = new JButton("确定");
		p4.add(bu);//将按钮添加p2中
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
				JOptionPane.showMessageDialog(bu, "高铁出发时间输入格式错误");
				e1.printStackTrace();
			}
			Type s ;//车厢类型
			if(s3.equals("商务")) {
				s = Type.BUSINESS;
			}else if(s3.equals("一等")) {
				s = Type.FIRSTCLASS ;
			}else if(s3.equals("二等")) {
				s = Type.SECONDCLASS;
			}else if(s3.equals("软卧")) {
				s = Type.SOFTSLEPPER ;
			}else if(s3.equals("硬卧")) {
				s = Type.TOURISTCOACH;
			}else if(s3.equals("硬座")) {
				s = Type.HARDSEATS ;
			}else if(s3.equals("行李车")) {
				s = Type.BUGGAGECAR ;
			}else {
				s = Type.RESTAURANTCAR ;
			}
			Railway ra = new Railway(s2,s,Integer.valueOf(s4),Integer.valueOf(s5));
			int t = schedule.FeiPeiResource(s1 ,time1,ra );
				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "想要分配的车厢不在可用车厢内");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "想要分配车厢的高铁已经包含了该车厢");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "分配资源存在冲突");
				}else if(t==4) {
					JOptionPane.showMessageDialog(bu, "想要分配车厢的高铁不存在");
				}else {
					JOptionPane.showMessageDialog(bu, "分配成功");
				}
			
		});
			
	}
	public void initFunc5() {//查询某个高铁的状态
		JLabel label1 = new JLabel("请输入高铁车次");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//将标签添加p5中
		p5.add(test1);//将文本框添加p5中
		JLabel label2 = new JLabel("请输入高铁出发时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//将标签添加p5中
		p5.add(test2);//将文本框添加p5中
		JLabel label3 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test3 = new JTextField(16);
		p5.add(label3);//将标签添加p5中
		p5.add(test3);//将文本框添加p5中
		JButton bu = new JButton("查看状态");
		p5.add(bu);//将按钮添加p5中
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
				JOptionPane.showMessageDialog(bu, "高铁出发时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "当前时间输入格式错误");
				e1.printStackTrace();
			}
			if(schedule.WatchState(s1, time1, time2) == null) {
				JOptionPane.showMessageDialog(bu, "找不到指定的高铁车次");
			}else {
				JOptionPane.showMessageDialog(bu, "高铁当前的状态为:" + schedule.WatchState(s1, time1, time2).toString());
			}
		});
	}
	public void initFunc6() {//设定高铁的状态(启动、取消、结束、阻塞)
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("启动某个高铁");
		box.addItem("取消某个高铁");
		box.addItem("结束某个高铁");
		box.addItem("阻塞某个高铁");
		p6.add(box);
		JLabel label1 = new JLabel("请输入高铁车次号");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//将标签添加p6中
		p6.add(test1);//将文本框添加p6中
		JLabel label2 = new JLabel("请输入高铁出发时间");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//将标签添加p6中
		p6.add(test2);//将文本框添加p6中
		JButton bu = new JButton("确定");
		p6.add(bu);
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
	public void initFunc7() {//查看和某个车厢相关的所有高铁车次
		JLabel label1 = new JLabel("请输入待查询的车厢编号");
		JTextField test1 = new JTextField(10);
		p7.add(label1);//将标签添加p7中
		p7.add(test1);//将文本框添加p7中
		JLabel label2 = new JLabel("请输入待查询的车厢类型(商务/一等/二等/软卧/硬卧/硬座/行李车/餐车)");
		JTextField test2 = new JTextField(3);
		p7.add(label2);//将标签添加p7中
		p7.add(test2);//将文本框添加p7中
		JLabel label3 = new JLabel("请输入待查询的车厢定员数");
		JTextField test3 = new JTextField(2);
		p7.add(label3);//将标签添加p7中
		p7.add(test3);//将文本框添加p7中
		
		JLabel label4 = new JLabel("请输入待查询的车厢出厂年份(xxxx)");
		JTextField test4 = new JTextField(4);
		p7.add(label4);//将标签添加p7中
		p7.add(test4);//将文本框添加p7中
		JLabel label5 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test5 = new JTextField(16);
		p7.add(label5);//将标签添加p7中
		p7.add(test5);//将文本框添加p7中
		
		JButton bu = new JButton("确定") ;
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
				JOptionPane.showMessageDialog(bu, "当前时间输入格式错误");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, s2, Integer.valueOf(s3), Integer.valueOf(s4), time1);
			
		});
	}
	public void initFunc8() {//对车厢进行操作(查看、增加、删除)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的车厢");
		box.addItem("增加可用的车厢");
		box.addItem("删除可用的车厢)");
		p8.add(box);
		JLabel label1 = new JLabel("请输入待查询的车厢编号");
		JTextField test1 = new JTextField(10);
		p8.add(label1);//将标签添加p8中
		p8.add(test1);//将文本框添加p8中
		JLabel label2 = new JLabel("请输入待查询的车厢类型(商务/一等/二等/软卧/硬卧/硬座/行李车/餐车)");
		JTextField test2 = new JTextField(3);
		p8.add(label2);//将标签添加p8中
		p8.add(test2);//将文本框添加p8中
		JLabel label3 = new JLabel("请输入待查询的车厢定员数");
		JTextField test3 = new JTextField(2);
		p8.add(label3);//将标签添加p8中
		p8.add(test3);//将文本框添加p8中
		
		JLabel label4 = new JLabel("请输入待查询的车厢出厂年份(xxxx)");
		JTextField test4 = new JTextField(4);
		p8.add(label4);//将标签添加p8中
		p8.add(test4);//将文本框添加p8中
		JButton bu = new JButton("确定") ;
		p8.add(bu);
		bu.addActionListener((e)->{
			if(box.getSelectedIndex()==0) {
				schedule.showResource();
				JOptionPane.showMessageDialog(bu, "操作成功");
			}else if(box.getSelectedIndex() == 1 ) {
				if(schedule.addResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText())))
					JOptionPane.showMessageDialog(bu, "操作成功");
				else
					JOptionPane.showMessageDialog(bu, "操作失败:待添加的资源已经存在");
			}else {
				if(schedule.deleteResource(test1.getText(), test2.getText(), Integer.valueOf(test3.getText()), Integer.valueOf(test4.getText())))
					JOptionPane.showMessageDialog(bu, "操作成功");
				else
					JOptionPane.showMessageDialog(bu, "操作失败:待删除的资源不存在");
			}
			});
	}
	public void initFunc9() {//对位置进行操作(查看、增加、删除)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的位置");
		box.addItem("增加可用的位置");
		box.addItem("删除可用的位置");
		p9.add(box);
		JLabel label2 = new JLabel("请输入位置");
		JTextField test2 = new JTextField(16);
		p9.add(label2);//将标签添加p9中
		p9.add(test2);//将文本框添加p9中
		JButton bu = new JButton("确定");
		p9.add(bu);
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
	public void initFunc10() {//展示是否存在资源冲突
		JButton bu = new JButton("确定") ;
		p10.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "不存在资源冲突");
			}else {
				JOptionPane.showMessageDialog(bu, "存在资源冲突");
			}

		});
	}
	public void initFunc11() {//列出某个高铁的前序高铁
		JLabel label1 = new JLabel("请输入车厢编号");
		JTextField test1 = new JTextField(10);
		p11.add(label1);//将标签添加p10中
		p11.add(test1);//将文本框添加p10中
		JLabel label2 = new JLabel("请输入车厢类型(商务/一等/二等/软卧/硬卧/硬座/行李车/餐车)");
		JTextField test2 = new JTextField(3);
		p11.add(label2);//将标签添加p10中
		p11.add(test2);//将文本框添加p10中
		JLabel label3 = new JLabel("请输入车厢定员数");
		JTextField test3 = new JTextField(2);
		p11.add(label3);//将标签添加p10中
		p11.add(test3);//将文本框添加p10中
		JLabel label4 = new JLabel("请输入车厢出厂年份(xxxx)");
		JTextField test4 = new JTextField(4);
		p11.add(label4);//将标签添加p10中
		p11.add(test4);//将文本框添加p10中
		JLabel label51 = new JLabel("请输入高铁车次号");
		JTextField test5 = new JTextField(14);
		p11.add(label51);//将标签添加p10中
		p11.add(test5);//将文本框添加p10中
		JLabel label6 = new JLabel("请输入高铁出发时间");
		JTextField test6 = new JTextField(16);
		p11.add(label6);//将标签添加p10中
		p11.add(test6);//将文本框添加p10中
		JButton bu = new JButton("确定") ;
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
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				e1.printStackTrace();
			}
			if(schedule.getPrePlanningEntry(s1, s2, Integer.valueOf(s3), Integer.valueOf(s4),s5,time1)) {
				JOptionPane.showMessageDialog(bu, "操作成功,存在前序计划项");
			}else {
				JOptionPane.showMessageDialog(bu, "没有前序计划项");
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
	

