package logRecord;

// a immutable class
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logRecord {
	// RI:
	//all field is not null
	//time is the form of yyyy-MM-dd hh:mm:ss
	// the logType is SEVERE or INFO or WARNNING
	
	//AF��
	// Representing a log that consists of className��methodName��time��logType��message��exception
	
	//Safety from rep exposure
	//
	// String is immutable and there is no exposure of the fields

	
    String className; //����
    String methodName; //������
    String time; //ʱ��
    String logType; //log����
    String message;//�쳣/����/�����������Ϣ
    String exception ; //�쳣����
    public logRecord(String line) {
        String regex = "<record>\n\\<class\\>(.*?)\n\\<method\\>(.*?)\n\\<time\\>(.*?)\n\\<level\\>(.*?)\n\\<message\\>(.*?)\n\\<Exception\\>(.*?)\n";
        Matcher matcher = Pattern.compile(regex).matcher(line);
        if(matcher.find()) {
            className = matcher.group(1);
            methodName = matcher.group(2);
            time = matcher.group(3);
            logType = matcher.group(4);
            message = matcher.group(5);
            exception = matcher.group(6); //�쳣����
        }
    }

    public String getLogType() {
        return logType;
    }

    public String getReason() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getTime() {
        return time;
    }
    public String getException() {
    	return exception ;
    }
    public Date gettime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		return sdf.parse(time) ;
    }   
}