package softwareproblems.week3.parsingweatherdata;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.util.Scanner;

public class ParsingWeatherReport {

    public CSVRecord getLeastOfTwo(CSVRecord currentRecord,CSVRecord leastSoFar,String recordName){

        if(leastSoFar == null)
            leastSoFar = currentRecord;
        else{
            if(!currentRecord.get(recordName).equals("N/A")|| !currentRecord.get(recordName).equals("-9999")){
                double currentValue = Double.parseDouble(currentRecord.get(recordName));
                double leastValue = Double.parseDouble(leastSoFar.get(recordName));
                if(currentValue < leastValue){
                    leastSoFar = currentRecord;
                }
            }
        }
        return leastSoFar;
    }

    public CSVRecord coldestHourInFile(CSVParser parser){

        CSVRecord leastTempRecord = null;

        for(CSVRecord currentRecord : parser)
            leastTempRecord = getLeastOfTwo(currentRecord,leastTempRecord,"TemperatureF");

        return leastTempRecord;
    }

    public void testColdestHourInFile(){
        FileResource fileResource = new FileResource();
        CSVRecord printRecord = coldestHourInFile(fileResource.getCSVParser());
        System.out.println("Coldest hour in the file is = "+printRecord.get("TimeEST")+" Temperature = "+printRecord.get("TemperatureF"));
    }

    public String fileWithColdestTemperature(){

        String fileName = "";
        CSVRecord leastTempRecord = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file : directoryResource.selectedFiles())
        {
            FileResource fileResource = new FileResource(file);
            CSVRecord currentRecord = coldestHourInFile(fileResource.getCSVParser());
            if(leastTempRecord == null)
                leastTempRecord = currentRecord;
            else{
                double coldestTemp = Double.parseDouble(leastTempRecord.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                if(currentTemp < coldestTemp){
                    leastTempRecord = currentRecord;
                    fileName = file.getName();
                }
            }
        }
        return fileName;
    }

    public void testFileWithColdestTemperature(){

        String fileName = fileWithColdestTemperature();
        System.out.println(fileName);
        FileResource fileResource = new FileResource(fileName);
        CSVRecord printRecord = coldestHourInFile(fileResource.getCSVParser());
        System.out.println("Coldest day was in file "+fileName+"\n");
        System.out.println("Coldest temperature on that day was "+printRecord.get("TemperatureF")+"\n");
        System.out.println("All the Temperatures on the coldest day were: "+"\n");

        for(CSVRecord record : fileResource.getCSVParser())
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
    }


    public CSVRecord lowestHumidityInFile(CSVParser parser){

        CSVRecord lowestHumidRecord = null;

        for(CSVRecord currentRecord : parser)
            lowestHumidRecord = getLeastOfTwo(currentRecord,lowestHumidRecord,"Humidity");
        return lowestHumidRecord;
    }

    public void testLowestHumidityInFile(){

        FileResource fileResource = new FileResource();
        CSVRecord lowestHumidRecord = lowestHumidityInFile(fileResource.getCSVParser());

        System.out.println("Lowest Humidity was "+lowestHumidRecord.get("Humidity")+" at "+lowestHumidRecord.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidRecord = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file : directoryResource.selectedFiles())
        {
            FileResource fileResource = new FileResource(file);
            CSVRecord currentRecord = lowestHumidityInFile(fileResource.getCSVParser());
            lowestHumidRecord = getLeastOfTwo(currentRecord,lowestHumidRecord,"Humidity");
        }
        return lowestHumidRecord;
    }
    public void testLowestHumidityInManyFiles(){

        CSVRecord lowestHumidRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowestHumidRecord.get("Humidity")+" at "+lowestHumidRecord.get("DateUTC"));
    }

    public double averageTemperatureInFile(CSVParser parser){
        return averageTemperatureWithHighHumidityInFile(parser,-459.67);
    }

    public void testAverageTemperatureInFile(){
        FileResource fileResource = new FileResource();
        System.out.println("Average temperature in file is "+averageTemperatureInFile(fileResource.getCSVParser()));

    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, double value){

        double averageTemp = 0.0;
        int count = 0;
        for(CSVRecord record : parser){
            if(Double.parseDouble(record.get("Humidity")) >= value ){
                averageTemp += Double.parseDouble(record.get("TemperatureF"));
                count++;
            }
        }
        if(count == 0){
            return count;
        }
        return averageTemp/count;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){

        Scanner scan = new Scanner(System.in);
        FileResource fileResource = new FileResource();
        System.out.println("Enter Humidity value to check ");
        double value = scan.nextDouble();
        double averageTemperature = averageTemperatureWithHighHumidityInFile(fileResource.getCSVParser(),value);
        if(averageTemperature == 0)
            System.out.println("No temperatures with that humidity");
        else
            System.out.println("Average Temp when high Humidity is "+averageTemperature);
    }

}

