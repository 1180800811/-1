package logRecord;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class logKeeperTest {

	/*
	 * 测试logKeeper构造器方法
	 */
	@Test
	public void KeeperTest() throws IOException, ParseException {
		logKeeper  l = new logKeeper("test/logRecord/logger") ;
		assertTrue(l.getRecords().size() == 2  );
		l.showRecordsType("null");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		l.showRecordsTime(sdf.parse("2020-05-25 12:30:20"), sdf.parse("2020-05-27 12:30:20"));
	}
}
