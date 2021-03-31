package course2.week3.readinglogfiles;

import edu.duke.*;

import java.util.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> logRecord;

    public LogAnalyzer(){logRecord = new ArrayList<>();}

    public void readFile(){
        FileResource fileResource = new FileResource();
        for(String line : fileResource.lines()){
            LogEntry logEntry = WebLogParser.parseEntry(line);
            logRecord.add(logEntry);
        }
    }
    public void printAllLogEntries(){
        for(LogEntry logEntry : logRecord){
            System.out.println(logEntry);
        }
    }
    public int countUniqueIPs(){
        ArrayList<String> ipAddress = new ArrayList<>();
        for(LogEntry logEntry : logRecord){
            if(!ipAddress.contains(logEntry.getIpAddress())){
                ipAddress.add(logEntry.getIpAddress());
            }
        }
        return ipAddress.size();
    }
    public void printAllHigherThanNum(int statusCode){
        for(LogEntry logEntry: logRecord){
            if(logEntry.getStatusCode()>statusCode)
                System.out.println(logEntry);
        }
    }
    public List<String> uniqueIPVisitsOnDay(String someDay){
        ArrayList<String > uniqueLogsInDay = new ArrayList<>();
        for(LogEntry logEntry : logRecord){
            String ipAddress = logEntry.getIpAddress();
            String monthDay = logEntry.getAccessTime().toString().substring(4,10);
            if(monthDay.equals(someDay) && !uniqueLogsInDay.contains(ipAddress))
                uniqueLogsInDay.add(ipAddress);
        }
        return uniqueLogsInDay;
    }
    public int countUniqueIPsInRange(int low,int high){
        int countUniqueIpsInRange = 0;
        for(LogEntry logEntry:logRecord){
            if(logEntry.getStatusCode() >=low && logEntry.getStatusCode()<=high)
                 countUniqueIpsInRange++;
        }
        return countUniqueIpsInRange;
    }

    public Map<String,Integer> countVisitsPerIP(){
        HashMap<String,Integer> countIpVisits = new HashMap<>();
        for(LogEntry logEntry:logRecord){
            String ipAddress = logEntry.getIpAddress();
            if(!countIpVisits.containsKey(ipAddress)) countIpVisits.put(ipAddress,1);
            else countIpVisits.put(ipAddress,countIpVisits.get(ipAddress)+1);
        }
        return countIpVisits;
    }
    public List<String> iPsMostVisits(Map<String,Integer> countIpVisits){
        ArrayList<String> ipAddresses = new ArrayList<>();
        int maxCount = mostNumberVisitsByIP(countIpVisits);
        for(Map.Entry<String,Integer> entry : countIpVisits.entrySet()){
            if(entry.getValue() == maxCount)
                ipAddresses.add(entry.getKey());
        }
        return  ipAddresses;
    }
    public int mostNumberVisitsByIP(Map<String,Integer> countIpVisits){
        return Collections.max(countIpVisits.values());
    }



}
