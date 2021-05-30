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
	private CourseCalendar schedule = new CourseCalendar();//航班集
	JPanel p1 = new JPanel();//展示某个位置的信息板
	JPanel p2 = new JPanel();//遍历并可视化和当前位置相关的所有计划项
	JPanel p3 = new JPanel();//增加一门课程
	JPanel p4 = new JPanel();//给某个课程分配教师
	JPanel p5 = new JPanel();//查询某门课程的当前状态
	JPanel p6 = new JPanel();//设定课程状态(启动、取消、结束)
	JPanel p7 = new JPanel();//查看和某个教师相关的所有课程
	JPanel p8 = new JPanel();//对教师进行操作(查看、增加、删除)
	JPanel p9 = new JPanel();//对教室进行操作(查看、增加、删除)
	JPanel p10 = new JPanel();//展示是否存在资源或者位置冲突
	JPanel p11 = new JPanel();//列出某门课程的前序课程
	JPanel p12 = new JPanel();//改变某门课程的教室
	private Panel cardPannel;//第二层容器
	
	public void init() {//初始化		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置正常关闭
		frame.setVisible(true);
		frame.setBounds(40, 40, 1500, 500);
		JPanel top = new JPanel();//顶层容器
		Choice choice = new Choice();
		choice.add("展示某个位置的信息板");
		choice.add("遍历并可视化和当前位置相关的所有计划项");
		choice.add("增加一门课程");
		choice.add("给某个课程分配教师");
		choice.add("查询某门课程的当前状态");
		choice.add("设定课程状态(启动、取消、结束)");
		choice.add("查看和某个教师相关的所有课程");
		choice.add("对教师进行操作(查看、增加、删除)");
		choice.add("对教室进行操作(查看、增加、删除)");
		choice.add("展示是否存在资源或者位置冲突");
		choice.add("列出某门课程的前序课程");
		choice.add("改变某门课程的教室");
		
		top.add(choice);//顶层容器添加下拉菜单
		CardLayout layout = new CardLayout();
		cardPannel = new Panel(layout);	
		cardPannel.add("展示某个位置的信息板", p1);
		cardPannel.add("遍历并可视化和当前位置相关的所有计划项", p2);
		cardPannel.add("增加一门课程", p3);
		cardPannel.add("给某个课程分配教师", p4);
		cardPannel.add("查询某门课程的当前状态", p5);
		cardPannel.add("设定课程状态(启动、取消、结束)", p6);
		cardPannel.add("查看和某个教师相关的所有课程", p7);
		cardPannel.add("对教师进行操作(查看、增加、删除)", p8);
		cardPannel.add("对教室进行操作(查看、增加、删除)", p9);
		cardPannel.add("展示是否存在资源或者位置冲突", p10);
		cardPannel.add("列出某门课程的前序课程", p11);
		cardPannel.add("改变某门课程的教室", p12);
		top.add(cardPannel);
		choice.addItemListener(new ItemListener() {//为下拉菜单增加事件监听器
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(((String) e.getItem()).equals("给某个课程分配教师")) {
					schedule.showResource();
				}
				((CardLayout)cardPannel.getLayout()).show(cardPannel, (String) e.getItem());
			}
		});
		initFunc1();//展示某个位置的信息板
		initFunc2();//遍历并可视化和当前位置相关的所有计划项
		initFunc3();//增加一门课程
		initFunc4();//给某个课程分配教师
		initFunc5();//查询某门课程的当前状态
		initFunc6();//设定课程状态(启动、取消、结束)
		initFunc7();//查看和某个教师相关的所有课程
		initFunc8();//对教师进行操作(查看、增加、删除)
		initFunc9();//对教室进行操作(查看、增加、删除)
		initFunc10();//展示是否存在资源或者位置冲突
		initFunc11();//列出某门课程的前序课程
		initFunc12();//改变某门课程的教室
		frame.add(top);
	}

	public void initFunc1() {
		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p1.add(label1);//将标签添加p1中
		p1.add(test1);//将文本框添加p1中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(10);
		p1.add(label2);//将标签添加p1中
		p1.add(test2);//将文本框添加p1中
		JButton bu = new JButton("确定");
		p1.add(bu);//将按钮添加内容面板p1中
		bu.addActionListener((e)->{//事件监听器
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //时间格式
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				JOptionPane.showMessageDialog(bu, "成功");
				schedule.board(new Location(s2), time);
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				e2.printStackTrace();
			}
		});
	}
	public void initFunc2() {
		JLabel label1 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test1 = new JTextField(16);
		p2.add(label1);//将标签添加p2中
		p2.add(test1);//将文本框添加p2中
		JLabel label2 = new JLabel("请输入当前位置");
		JTextField test2 = new JTextField(10);
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
				schedule.show(new Location(s2),time);
				JOptionPane.showMessageDialog(bu, "成功");
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "时间输入错误");
				e1.printStackTrace();
			}
		});
	}
	public void initFunc3() {
		p3.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("输入课程名称");
		JTextField test1 = new JTextField(10);
		p3.add(label1);//将标签添加p3中
		p3.add(test1);//将文本框添加p3中
		JLabel label2 = new JLabel("输入课程开始时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p3.add(label2);//将标签添加p3中
		p3.add(test2);//将文本框添加p3中
		JLabel label3 = new JLabel("输入课程终止时间(格式同前)");
		JTextField test3 = new JTextField(16);
		p3.add(label3);//将标签添加p3中
		p3.add(test3);//将文本框添加p3中
		JLabel label4 = new JLabel("输入上课教室");
		JTextField test4 = new JTextField(10);
		p3.add(label4);//将标签添加p3中
		p3.add(test4);//将文本框添加p3中
		JButton bu = new JButton("确定");
		p3.add(bu);//将按钮添加内容面板p4中
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				if(schedule.addPlanningEntry(s1, new Location(s4),time1 , time2)) {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					JOptionPane.showMessageDialog(bu, "操作失败,课程存在位置冲突");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		
	}
	public void initFunc4() {

		JLabel label1 = new JLabel("请输入待分配教师的课程名称");
		JTextField test1 = new JTextField(10);
		p4.add(label1);//将标签添加p4中
		p4.add(test1);//将文本框添加p4中
		JLabel label2 = new JLabel("请输入教师身份证号");
		JTextField test2 = new JTextField(18);
		p4.add(label2);//将标签添加p4中
		p4.add(test2);//将文本框添加p4中
		JLabel label3 = new JLabel("请输入教师姓名");
		JTextField test3 = new JTextField(10);
		p4.add(label3);//将标签添加p4中
		p4.add(test3);//将文本框添加p4中
		JLabel label4 = new JLabel("输入教师性别(ture(男)/false(女)");
		JTextField test4 = new JTextField(5);
		p4.add(label4);//将标签添加p4中
		p4.add(test4);//将文本框添加p4中
		JLabel label5 = new JLabel("输入教师职称");
		JTextField test5 = new JTextField(5);
		p4.add(label5);//将标签添加p4中
		p4.add(test5);//将文本框添加p4中
		JButton bu = new JButton("确定");
		p4.add(bu);//将按钮添加p4中
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			int t = schedule.FeiPeiResource(s1, s2,s3,Boolean.valueOf(s4),s5);
				if(t == 0) {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else if(t==1) {
					JOptionPane.showMessageDialog(bu, "操作失败:指定的课程已经分配教师");
				}else if(t==2) {
					JOptionPane.showMessageDialog(bu, "操作失败:分指定的课程已经分配教师");
				}else if(t==3) {
					JOptionPane.showMessageDialog(bu, "操作失败:课程间存在资源冲突");
				}else {
					JOptionPane.showMessageDialog(bu, "操作失败:待分配的教师不在可用的教师之内");
				}
					
		});	
	}
	public void initFunc5() {
		JLabel label1 = new JLabel("请输入课程名称");
		JTextField test1 = new JTextField(16);
		p5.add(label1);//将标签添加p5中
		p5.add(test1);//将文本框添加p5中
		JLabel label2 = new JLabel("请输入当前时间(yyyy-MM-dd HH:mm)");
		JTextField test2 = new JTextField(16);
		p5.add(label2);//将标签添加p5中
		p5.add(test2);//将文本框添加p5中
		JButton bu = new JButton("确定");
		p5.add(bu);//将按钮添加p5中
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "时间格式输入错误");
				e1.printStackTrace();
			}
			schedule.WatchState(s1, time);
			JOptionPane.showMessageDialog(bu, "操作成功");
			
		});

		
	}
	public void initFunc6() {
		JComboBox<String>  box = new JComboBox<String>();
		box.addItem("启动某门课程");
		box.addItem("取消某门课程");
		box.addItem("结束某门课程");
		p6.add(box);
		JLabel label1 = new JLabel("请输入课程名称");
		JTextField test1 = new JTextField(16);
		p6.add(label1);//将标签添加p6中
		p6.add(test1);//将文本框添加p6中
		JLabel label2 = new JLabel("请输入课程开始时间");
		JTextField test2 = new JTextField(16);
		p6.add(label2);//将标签添加p6中
		p6.add(test2);//将文本框添加p6中
		JLabel label3 = new JLabel("请输入课程结束时间");
		JTextField test3 = new JTextField(16);
		p6.add(label3);//将标签添加p6中
		p6.add(test3);//将文本框添加p6中
		JLabel label4 = new JLabel("请输入教室");
		JTextField test4 = new JTextField(16);
		p6.add(label4);//将标签添加p6中
		p6.add(test4);//将文本框添加p6中
		JButton bu = new JButton("确定");
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
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
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
	public void initFunc7() {//查看和某个教师相关的所有课程
		JLabel label1 = new JLabel("请输入教师名字");
		JTextField test1 = new JTextField(16);
		p7.add(label1);//将标签添加p7中
		p7.add(test1);//将文本框添加p7中
		JLabel label2 = new JLabel("请输入教师身份证号");
		JTextField test2 = new JTextField(18);
		p7.add(label2);//将标签添加p7中
		p7.add(test2);//将文本框添加p7中
		JLabel label3 = new JLabel("输入教师性别(ture(男)/false(女)");
		JTextField test3 = new JTextField(5);
		p7.add(label3);//将标签添加p7中
		p7.add(test3);//将文本框添加p7中
		JLabel label4 = new JLabel("请输入教师职称");
		JTextField test4 = new JTextField(16);
		p7.add(label4);//将标签添加p7中
		p7.add(test4);//将文本框添加p7中
		JLabel label5 = new JLabel("请输入当前时间");
		JTextField test5 = new JTextField(16);
		p7.add(label5);//将标签添加p7中
		p7.add(test5);//将文本框添加p7中
		JButton bu = new JButton("确定");
		p7.add(bu);//将按钮添加p7中
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
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				e1.printStackTrace();
			}
			schedule.getResourcePlanningEntry(s1, s2, Boolean.valueOf(s3),s4, time);
			
		
	});
}
	public void initFunc8() {//对教师进行操作(查看、增加、删除)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的教师");
		box.addItem("增加可用的教师");
		box.addItem("删除可用的教师");
		p8.add(box);
		JLabel label1 = new JLabel("请输入教师名字");
		JTextField test1 = new JTextField(16);
		p8.add(label1);//将标签添加p8中
		p8.add(test1);//将文本框添加p8中
		JLabel label2 = new JLabel("请输入教师身份证号");
		JTextField test2 = new JTextField(18);
		p8.add(label2);//将标签添加p8中
		p8.add(test2);//将文本框添加p8中
		JLabel label3 = new JLabel("输入教师性别(ture(男)/false(女)");
		JTextField test3 = new JTextField(5);
		p8.add(label3);//将标签添加p8中
		p8.add(test3);//将文本框添加p8中
		JLabel label4 = new JLabel("请输入教师职称");
		JTextField test4 = new JTextField(16);
		p8.add(label4);//将标签添加p8中
		p8.add(test4);//将文本框添加p8中
		JButton bu = new JButton("确定") ;
		p8.add(bu);
		bu.addActionListener((e)->{
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			JOptionPane.showMessageDialog(bu, "操作成功");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText()))
				JOptionPane.showMessageDialog(bu, "操作成功");
			else
				JOptionPane.showMessageDialog(bu, "操作失败:待添加的教师已经存在");
		}else {
			if(schedule.deleteResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText()))
				JOptionPane.showMessageDialog(bu, "操作成功");
			else
				JOptionPane.showMessageDialog(bu, "操作失败:待删除的教师不存在");
		}
		});
	}
	public void initFunc9() {//对教室进行操作(查看、增加、删除)
		JComboBox<String> box = new JComboBox<String>()	;
		box.addItem("查看所有可用的教室");
		box.addItem("增加可用的教室");
		box.addItem("删除可用的教室");
		p9.add(box);
		JLabel label2 = new JLabel("请输入教师");
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
					JOptionPane.showMessageDialog(bu, "添加失败:待添加的教室已经存在");
				}
			}else {
				String text3 = test2.getText();
				if(schedule.deleteLocation(new Location(text3))) {
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					JOptionPane.showMessageDialog(bu, "删除失败:待删除的教室不存在");
				}
		}
		

	});
	}
	public void initFunc10() {//展示是否存在资源或者位置冲突
		JButton bu = new JButton("确定") ;
		p10.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				JOptionPane.showMessageDialog(bu, "不存在资源/位置冲突");
			}else {
				JOptionPane.showMessageDialog(bu, "存在资源/位置冲突");
			}

		});
	}
	public void initFunc11() {//列出某门课程的前序课程
		p11.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("请输入课程名称");
		JTextField test1 = new JTextField(16);
		p11.add(label1);//将标签添加p11中
		p11.add(test1);//将文本框添加p11中
		JLabel label2 = new JLabel("请输入课程的教室");
		JTextField test2 = new JTextField(16);
		p11.add(label2);//将标签添加p11中
		p11.add(test2);//将文本框添加p11中
		JLabel label3 = new JLabel("请输入该课程教师的姓名");
		JTextField test3 = new JTextField(10);
		p11.add(label3);//将标签添加p11中
		p11.add(test3);//将文本框添加p11中
		JLabel label4 = new JLabel("请输入该课程教师的身份证号");
		JTextField test4 = new JTextField(18);
		p11.add(label4);//将标签添加p11中
		p11.add(test4);//将文本框添加p11中
		JButton bu = new JButton("确定");
		p11.add(bu);//将按钮添加内容面板p11中
		bu.addActionListener((e)->{
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			if(schedule.getPrePlanningEntry(s1, new Location(s2), s3, s4)) {
				JOptionPane.showMessageDialog(bu, "操作成功");
			}else {
				JOptionPane.showMessageDialog(bu, "操作失败，没有前序计划项");
			}
		});
	}
	public void initFunc12() {//改变某门课程的教室
		p12.setLayout(new FlowLayout());
		JLabel label1 = new JLabel("请输入课程名称");
		JTextField test1 = new JTextField(16);
		p12.add(label1);//将标签添加p12中
		p12.add(test1);//将文本框添加p12中
		JLabel label2 = new JLabel("请输入课程的初始教室");
		JTextField test2 = new JTextField(16);
		p12.add(label2);//将标签添加p12中
		p12.add(test2);//将文本框添加p12中
		JLabel label3 = new JLabel("请输入课程更改后的教室");
		JTextField test3 = new JTextField(10);
		p12.add(label3);//将标签添加p12中
		p12.add(test3);//将文本框添加p12中
		JButton bu = new JButton("确定");
		p12.add(bu);//将按钮添加内容面板p12中
		bu.addActionListener((e)->{
			if(schedule.changeLocationEntry(test1.getText(), new Location(test2.getText()), new Location(test3.getText()))) {
				JOptionPane.showMessageDialog(bu, "操作成功");
			}else {
				JOptionPane.showMessageDialog(bu, "没有找到指定的课程");
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
