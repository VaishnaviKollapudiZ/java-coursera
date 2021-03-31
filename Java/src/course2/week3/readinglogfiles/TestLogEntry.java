package course2.week3.readinglogfiles;

import java.util.Date;

public class TestLogEntry {
    public static void main(String[] args){
        LogEntry logEntry = new LogEntry("1.2.100.4",new Date(),"example request",200,500);
        System.out.println(logEntry);
    }

}
