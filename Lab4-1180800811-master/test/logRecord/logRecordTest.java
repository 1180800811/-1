package logRecord ;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class logRecordTest {
	
	/*
	 * 测试构造方法
	 */
	@Test
	public void RecordTest() throws IOException, ParseException {
		BufferedReader in = new BufferedReader(new FileReader(new File("test/logRecord/logger")));
		String fileline;
		StringBuffer s = new StringBuffer("") ;
		while ((fileline = in.readLine()) != null) {
			s.append(fileline + "\n") ;
		}
		logRecord l = new logRecord(s.toString());
		in.close();
		assertTrue(l.getClassName().equals("APP.FlightScheduleApp"));
		assertTrue(l.getException().equals("null"));
		assertTrue(l.getLogType().equals("INFO"));
		assertTrue(l.getMethodName().equals("lambda$13"));
		assertTrue(l.getReason().equals("操作成功"));
		assertTrue(l.getTime().equals("2020-05-27 01:31:52"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss") ;
		assertTrue(sdf.format(l.gettime()).equals(l.getTime()));
	}
}
