package logRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import Board.Show;
// a immutable class
public class logKeeper {

	// RI:
	// records is not null
	// 
	//AF:
	//表示一个日志类集合，由很多条日志组成
	//
	//Safety from rep exposure:
	// the field is private
	// because List is mutable so make defensive copy for getRecords()
	private List<logRecord> records = new ArrayList<>();

	public logKeeper(String ss) throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new FileReader(new File(ss)));
		String fileline;
		StringBuffer s = new StringBuffer("") ;
		int cnt = 0 ;
		while ((fileline = in.readLine()) != null) {
			s.append(fileline + "\n") ;
			cnt ++ ;
			if(cnt % 7 == 0) {
				logRecord record = new logRecord(s.toString());	
				records.add(record);
				s = new StringBuffer("") ;
			}

		}
		in.close();
		Collections.sort(records, new Comparator<logRecord>() {
			@Override
			public int compare(logRecord o1, logRecord o2) {
				int t = 0 ;
				try {
					if(o1.gettime().before(o2.gettime()))	{
						t = 1 ;
					}else if(o1.gettime().after(o2.gettime())) {
						t =  -1 ;
					}else {
						t = 0 ;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return t ;	
			}
			
		});
	}
	
	
	public List<logRecord> getRecords(){
		List<logRecord> l = new ArrayList<logRecord>();
		l.addAll(records);
		return l ;
	}
	
	/**
	 * 查询在某个时间内的日志
	 * @param time1 起始时间
	 * @param time2 终止时间
	 * @throws ParseException 
	 */
	public void showRecordsTime(Date time1 , Date time2) throws ParseException {
		String[] columNames = { "序号" , "类名" , "方法名" ,"时间" , "日志等级" , "日志信息" , "异常类型"} ;//表格第一行信息
		List<logRecord> l = new ArrayList<logRecord>() ;//所有在指定时间内的日志
		for(int i = 0 ; i < records.size() ; i ++) {
			if(records.get(i).gettime().after(time1) && records.get(i).gettime().before(time2)) {
				l.add(records.get(i)) ;
			}
		}
		Object[][] rowData = new String[l.size()][] ;
		int i = 0 ;
		for(logRecord e : l) {
			rowData[i] = new String[] {String.valueOf(i+1) ,e.getClassName() , e.getMethodName() ,e.getTime() , e.getLogType() , e.getReason() , e.getException()};
			i ++ ;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		JFrame jf =new JFrame("在时间" + sdf.format(time1) + " - " + sdf.format(time2) + "之间的日志");
		Show.show(columNames, rowData, jf);
	}
	
	/**
	 * 根据日志类型来查询日志
	 * @param type 日志类型
	 */
	public void showRecordsType(String type) {
		String[] columNames = { "序号" , "类名" , "方法名" ,"时间" , "日志等级" , "日志信息" , "异常类型"} ;//表格第一行信息
		List<logRecord> l = new ArrayList<logRecord>() ;//所有的指定日志类型的日志
		for(int i = 0 ; i < records.size() ; i ++) {
			if(records.get(i).getLogType().equals(type)) {
				l.add(records.get(i)) ;
			}
		}
		Object[][] rowData = new String[l.size()][] ;
		int i = 0 ;
		for(logRecord e : l) {
			rowData[i] = new String[] {String.valueOf(i+1) ,e.getClassName() , e.getMethodName() ,e.getTime() , e.getLogType() , e.getReason() , e.getException()};
			i ++ ;
		}
		JFrame jf =new JFrame("所有的日志类型为" + type +  "的日志");
		Show.show(columNames, rowData, jf);
	}
}
