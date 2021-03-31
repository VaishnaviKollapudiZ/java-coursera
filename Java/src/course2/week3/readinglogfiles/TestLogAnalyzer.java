package course2.week3.readinglogfiles;


import java.util.Map;

public class TestLogAnalyzer {
    public static void main(String[] args){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile();
        System.out.println("\nAll log entries - ");
        logAnalyzer.printAllLogEntries();
        System.out.println("\nNo. of logged people = "+logAnalyzer.countUniqueIPs());
        System.out.println("\nAll log entries having a status code greater than 400 = ");
        logAnalyzer.printAllHigherThanNum(200);

        System.out.println("\nUnique Logs on Mar 24  - ");
        for(String ipAddress : logAnalyzer.uniqueIPVisitsOnDay("Mar 24"))
            System.out.println(ipAddress);

        System.out.println("\nNo. of logs having status code in range of [200,299] = "+logAnalyzer.countUniqueIPsInRange(200,299));
        Map<String,Integer> countVisits = logAnalyzer.countVisitsPerIP();
        System.out.println(logAnalyzer.iPsMostVisits(countVisits));


    }
}
