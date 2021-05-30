package APP;
import java.awt.CardLayout;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
import MyException.changeLocationException;
import MyException.deleteLocationException;
import MyException.deleteResourceException;
import MyException.feipeiResourceException;
import applications.CourseCalendar;
import logRecord.logKeeper;
// a immutable class
public class CourseScheduleApp {
	
	//AF:
	//表示一个课程管理的GUI，课程管理有指定的一些操作
	//
	//RI:
	//all fields are not null 
	//
	//Rep:
	// all fields are private and there is no exposure of the fields

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
	JPanel p13 = new JPanel() ;//日志查询
	
	private Logger logger = Logger.getLogger(CourseScheduleApp.class.getName());
	private Panel cardPannel;//第二层容器
	
	public void init() {//初始化	
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
		choice.add("查询日志");
		
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
		cardPannel.add("查询日志",p13);
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
		initFunc13() ; //日志查询
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
			logger.log(Level.INFO, "展示某个位置的信息板");
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //时间格式
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				logger.log(Level.INFO, "查询位置: "+s2 + "的信息板,查询成功!");
				JOptionPane.showMessageDialog(bu, "成功");
				schedule.board(new Location(s2), time);
			} catch (ParseException e2) {
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				logger.log(Level.SEVERE, "查询位置: "+s2  + "的信息板,时间输入格式错误!" , e2);
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
			logger.log(Level.INFO, "查询和某个位置相关的所有计划项");
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s1));
				schedule.show(new Location(s2),time);
				logger.log(Level.INFO, "查询位置: "+s2 + "相关的所有计划项,查询成功!");
				JOptionPane.showMessageDialog(bu, "成功");
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "查询位置: "+s2 + "相关的所有计划项,时间格式输入错误!" ,e1);
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
			logger.log(Level.INFO, "增加一门课程");
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
				logger.log(Level.SEVERE, "增加课程: "+s1 + "起始时间格式输入错误!" ,e1);
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "增加课程: "+s1 + "终止时间格式输入错误!" ,e1);
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				if(schedule.addPlanningEntry(s1, new Location(s4),time1 , time2)) {
					logger.log(Level.INFO, "增加课程: "+s1 + "操作成功!");
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					logger.log(Level.WARNING, "增加课程: "+s1 + "操作失败,课程存在位置冲突!");
					JOptionPane.showMessageDialog(bu, "操作失败,课程存在位置冲突");
				}
			} catch (Exception e1) {
				logger.log(Level.WARNING, "增加课程: "+s1 + "操作失败,时间格式错误!",e1);
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
			logger.log(Level.INFO, "给某门课程分配教师");
			String s1 = test1.getText();
			String s2 = test2.getText();
			String s3 = test3.getText();
			String s4 = test4.getText();
			String s5 = test5.getText();
			int t;
			try {
				t = schedule.FeiPeiResource(s1, s2,s3,Boolean.valueOf(s4),s5);
				if(t == 0) {
					logger.log(Level.INFO , "给课程: "+s1 + "分配教师,操作成功!");
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else if(t==1) {
					logger.log(Level.WARNING , "给课程: "+s1 + "分配教师,操作失败:指定的课程已经分配教师!");
					JOptionPane.showMessageDialog(bu, "操作失败:指定的课程已经分配教师");
				}else if(t==2) {
					logger.log(Level.WARNING , "给课程: "+s1 + "分配教师,操作失败:找不到指定的课程!");
					JOptionPane.showMessageDialog(bu, "操作失败:找不到指定的课程");
				}else {
					logger.log(Level.WARNING , "给课程: "+s1 + "分配教师,操作失败:待分配的教师不在可用的教师之内!");
					JOptionPane.showMessageDialog(bu, "操作失败:待分配的教师不在可用的教师之内");
				}
			} catch (feipeiResourceException e1) {
				logger.log(Level.SEVERE , "操作失败:" +e1.getMessage(),e1);
				JOptionPane.showMessageDialog(bu, "操作失败:" +e1.getMessage());
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
			logger.log(Level.INFO, "查询某门课程的状态");
			String s1 = test1.getText();
			String s2 = test2.getText();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s2));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "查询课程: "+s1 + "的状态 ,当前时间输入格式错误!",e1 );
				JOptionPane.showMessageDialog(bu, "时间格式输入错误");
				e1.printStackTrace();
			}
			schedule.WatchState(s1, time);
			logger.log(Level.INFO, "查询课程: "+s1 + "的状态 ,查询成功!");
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
			logger.log(Level.INFO, "设课程的状态");
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
				logger.log(Level.SEVERE, "设定课程:"  +s1 +  "状态,起始时间输入格式错误",e1);
				JOptionPane.showMessageDialog(bu, "起始时间输入格式错误");
				e1.printStackTrace();
			}
			try {
				time2.setTime(sdf.parse(s3));
			} catch (ParseException e1) {
				logger.log(Level.SEVERE, "设定课程:"  +s1 +  "状态,终止时间输入格式错误",e1);
				JOptionPane.showMessageDialog(bu, "终止时间输入格式错误");
				e1.printStackTrace();
			}
			if(box.getSelectedIndex() == 0) {
				String t = schedule.BeginPlanningEntry(s1,time1,time2,new Location(s4)) ;
				logger.log(Level.INFO, "启动课程课程:"  +s1 +  t);
				JOptionPane.showMessageDialog(bu,t);
			}else if(box.getSelectedIndex() == 1) {
				try {
					JOptionPane.showMessageDialog(bu,schedule.cancelPlanningEntry(s1,time1,time2,new Location(s4)));
				} catch (cancelPlanningEntryException e1) {
					logger.log(Level.SEVERE, "取消课程:"  +s1 +  "当前课程的状态不允许取消",e1);
					JOptionPane.showMessageDialog(bu, "操作失败:计划项当前的状态不可取消");
				}
			}else {
				String t = schedule.EndPlanningEntry(s1,time1,time2,new Location(s4)) ;
				logger.log(Level.INFO, "结束课程:"  +s1 +  t);
				JOptionPane.showMessageDialog(bu,t);
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
			logger.log(Level.INFO, "查看使用教师:" + s1 + "的所有计划项");	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
			Calendar time = Calendar.getInstance();
			try {
				time.setTime(sdf.parse(s5));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(bu, "时间输入格式错误");
				logger.log(Level.SEVERE, "查看使用教师:" + s1 + "的所有计划项 ，时间输入格式错误 ",e1);	
				e1.printStackTrace();
			}
			logger.log(Level.INFO, "查看使用教师:" + s1 + "的所有计划项,操作成功 ");	
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
			logger.log(Level.INFO, "对资源进行操作");
		if(box.getSelectedIndex()==0) {
			schedule.showResource();
			logger.log(Level.INFO, "查看所有可用的教师，操作成功");
			JOptionPane.showMessageDialog(bu, "操作成功");
		}else if(box.getSelectedIndex() == 1 ) {
			if(schedule.addResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText())) {
					JOptionPane.showMessageDialog(bu, "操作成功");
					logger.log(Level.INFO, "增加可用的教师，操作成功");
			}

			else {
					JOptionPane.showMessageDialog(bu, "操作失败:待添加的教师已经存在");
					logger.log(Level.WARNING, "操作失败:待添加的教师已经存在");
			}

		}else {
			try {
				if(schedule.deleteResource(test1.getText(), test2.getText(), Boolean.valueOf(test3.getText()), test4.getText())) {
					JOptionPane.showMessageDialog(bu, "操作成功");
					logger.log(Level.INFO, "删除可用的教师，操作成功");
				}

				else {
					JOptionPane.showMessageDialog(bu, "操作失败:待删除的教师不存在");
					logger.log(Level.WARNING, "删除可用的资源，操作失败:待删除的教师不存在");
				}

			} catch (deleteResourceException e1) {
				logger.log(Level.SEVERE, "删除可用的资源，操作失败:有尚未结束的计划项正在占用该资源",e1);
					JOptionPane.showMessageDialog(bu, "操作失败:有尚未结束的课程正在占用该教师");
			}
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
			logger.log(Level.INFO, "对位置进行操作");
			if(box.getSelectedIndex()==0) {
				schedule.showLocation();
				logger.log(Level.INFO, "查看所有可用的位置，操作成功");
				JOptionPane.showMessageDialog(bu, "操作成功");

			}else if(box.getSelectedIndex() == 1) {
				String text2 = test2.getText();
				if(schedule.addLocation(new Location(text2))) {
					logger.log(Level.INFO, "增加可用的位置，操作成功");
					JOptionPane.showMessageDialog(bu, "操作成功");
				}else {
					logger.log(Level.WARNING, "添加可用的位置，添加失败:待添加的位置已经存在");
					JOptionPane.showMessageDialog(bu, "添加失败:待添加的教室已经存在");
				}
			}else {
				String text3 = test2.getText();
				try {
					if(schedule.deleteLocation(new Location(text3))) {
						logger.log(Level.INFO , "删除可用的位置，操作成功");
						JOptionPane.showMessageDialog(bu, "操作成功");
					}else {
						logger.log(Level.INFO , "删除可用的位置，删除失败:待删除的位置不存在");
						JOptionPane.showMessageDialog(bu, "删除失败:待删除的教室不存在");
					}
				} catch (deleteLocationException e1) {
					logger.log(Level.SEVERE, "删除可用的位置，删除失败:有尚未结束的计划项会在该位置执行",e1);
					JOptionPane.showMessageDialog(bu, "删除失败:有尚未结束的课程会在该教室执行");
				}
		}
		

	});
	}
	public void initFunc10() {//展示是否存在资源或者位置冲突
		JButton bu = new JButton("确定") ;
		p10.add(bu);
		bu.addActionListener((e)->{
			if(schedule.check()) {
				logger.log(Level.INFO, "查询冲突，不存在资源/位置冲突");
				JOptionPane.showMessageDialog(bu, "不存在资源/位置冲突");
			}else {
				logger.log(Level.INFO, "查询冲突，存在资源/位置冲突");
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
			logger.log(Level.INFO, "列出计划项:" + s1 + "的前序计划项");
			if(schedule.getPrePlanningEntry(s1, new Location(s2), s3, s4)) {
				JOptionPane.showMessageDialog(bu, "操作成功");
				logger.log(Level.INFO, "列出计划项:" + s1 + "的前序计划项:操作成功，有前序计划项");
			}else {
				JOptionPane.showMessageDialog(bu, "操作成功，没有前序计划项");
				logger.log(Level.INFO, "列出计划项:" + s1 + "的前序计划项:操作成功，没有前序计划项");
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
			logger.log(Level.INFO, "更改某门课程的位置");
			try {
				if(schedule.changeLocationEntry(test1.getText(), new Location(test2.getText()), new Location(test3.getText()))) {
					JOptionPane.showMessageDialog(bu, "操作成功");
					logger.log(Level.INFO, "更改某门课程的位置，操作成功");
				}else {
					logger.log(Level.WARNING, "更改某门课程的位置:操作失败，没有找到指定的课程");
					JOptionPane.showMessageDialog(bu, "操作失败：没有找到指定的课程");
				}
			} catch (changeLocationException e1) {
				logger.log(Level.SEVERE, "更改某门课程的失败：改变位置后存在位置冲突");
				JOptionPane.showMessageDialog(bu, "操作失败:改变位置后存在位置冲突");
			} 
		});
	}
	
	public void initFunc13() {//查询日志
		JLabel label1 = new JLabel("输入起始时间(yyyy-MM-dd HH:mm:ss)");
		JTextField test1 = new JTextField(13);
		p13.add(label1);//将标签添加p13中
		p13.add(test1);//将文本框添加p13中
		JLabel label2 = new JLabel("输入终止时间(格式同前)");
		JTextField test2 = new JTextField(13);
		p13.add(label2);//将标签添加p13中
		p13.add(test2);//将文本框添加p13中
		JButton bu1 = new JButton("查询");
		p13.add(bu1);
		JLabel label3 = new JLabel("输入查询日志类型(INFO/WARNING/SEVERE)");
		JTextField test3 = new JTextField(10);
		p13.add(label3);//将标签添加p13中
		p13.add(test3);//将文本框添加p13中
		JButton bu2 = new JButton("查询");
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
				JOptionPane.showMessageDialog(bu1, "查询日志:操作成功");
			} catch (ParseException e1) {
				logger.log(Level.INFO, "查询日志: 操作失败，查询的起始时间格式错误");
				JOptionPane.showMessageDialog(bu1, "操作失败，起始时间格式错误");
				e1.printStackTrace();
			} catch (IOException e1) {
				logger.log(Level.INFO, "查询日志: 操作失败，查询的终止时间格式错误");
				JOptionPane.showMessageDialog(bu1, "操作失败，起始时间格式错误");
				e1.printStackTrace();
			}
		});
		bu2.addActionListener((e)->{
			String s3 = test3.getText() ;
			System.out.println(s3);
			if((s3.equals("INFO") || s3.equals("WARNING") || s3.equals("SEVERE"))) {
				try {
					new logKeeper("src/text/logger").showRecordsType(s3);
					logger.log(Level.INFO, "查询日志:操作成功");
					JOptionPane.showMessageDialog(bu1, "操作成功");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}		
			}else {
				logger.log(Level.WARNING, "查询日志:操作失败，日志类型输入错误");
				JOptionPane.showMessageDialog(bu1, "操作失败，日志类型输入错误");
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
