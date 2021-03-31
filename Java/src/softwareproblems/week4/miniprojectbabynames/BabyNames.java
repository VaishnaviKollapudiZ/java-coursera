package softwareproblems.week4.miniprojectbabynames;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {


    public void totalBirths(FileResource fileResource) {

        int maleBirths = 0;
        int femaleBirths = 0;
        int maleNames = 0;
        int femaleNames = 0;
        for (CSVRecord record : fileResource.getCSVParser(false)) {

            int numBorn = Integer.parseInt(record.get(2));
            if (record.get(1).equals("M")) {
                maleBirths += numBorn;
                maleNames++;
            } else if (record.get(1).equals("F")) {
                femaleBirths += numBorn;
                femaleNames++;
            }
        }
        int totalBirths = femaleBirths+maleBirths;
        System.out.println("Total Births = " + totalBirths);
        System.out.println("Female Births = " + femaleBirths);
        System.out.println("Male Births = " + maleBirths);

        int totalNames = femaleNames+maleNames;
        System.out.println("Total Names = " + totalNames);
        System.out.println("Female Names = " + femaleNames);
        System.out.println("Male Names = " + maleNames);

    }

    public void testTotalBirths(){
        FileResource fileResource = new FileResource();
        totalBirths(fileResource);
    }

    public int getRank(FileResource fileResource, String name, String gender) {

        int rank = -1;
        int countGenderRank = 0;
        for (CSVRecord record : fileResource.getCSVParser(false)) {

            if (record.get(1).equals(gender)) {
                countGenderRank++;
                if (record.get(0).equals(name)) {
                    rank = countGenderRank;
                    return rank;
                }
            }
        }
        return rank;
    }

    public void testGetRank(){
        FileResource fileResource = new FileResource();
        System.out.println(getRank(fileResource,"Mason","M"));
    }

    public String getName(FileResource fileResource, int rank, String gender) {
        int countGenderRank = 0;

        for (CSVRecord record : fileResource.getCSVParser(false)) {

            if (record.get(1).equals(gender)) {
                countGenderRank++;
                if (rank == countGenderRank) {
                    return record.get(0);
                }
            }
        }
        return "No NAME";
    }

    public void testGetName(){
        FileResource fileResource = new FileResource();
        System.out.println(getName(fileResource,2,"M"));
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {

        FileResource fileResourceYear = new FileResource();
        int rank = getRank(fileResourceYear, name, gender);
        FileResource fileResourceNewYear = new FileResource();
        String newName = getName(fileResourceNewYear, rank, gender);
        System.out.println(name + "born in " + year + " would be " + newName + " if she was born in " + newYear);

    }

    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella",2012,2014,"F");
    }

    public double getAverageRank(String name, String gender) {

        double sumOfRank = 0;

        int count = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            double rank = getRank(fileResource, name, gender);
            if (rank == -1) {
                return -1.0;
            }
            sumOfRank += rank;
            count++;
        }
        if(count == 0) return 0.0;
        return sumOfRank / count;
    }

    public void testGetAverageRank(){
        System.out.println(getAverageRank("Jacob","M"));
    }


    public String extractNumbers(String text) {

        text = text.replaceAll("[^\\d]", "");
        return text;
    }

    public int yearOfHighestRank(String name, String gender) {

        int result = -1;
        int year;
        int rank;
        int maxRank = Integer.MAX_VALUE;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            String fileName = file.getName();
            year = Integer.parseInt(extractNumbers(fileName));
            FileResource fileResource = new FileResource(file);
            rank = getRank(fileResource, name, gender);
            if (rank == -1) {
                return -1;
            }

            if (rank < maxRank) {
                maxRank = rank;
                result = year;
            }

        }
        return result;

    }
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mason","M"));
    }

    public int getBirthByName(CSVParser parser, String name, String gender) {

        for (CSVRecord record : parser) {
            if (record.get(0).equals(name) && record.get(1).equals(gender))
                    return Integer.parseInt(record.get(2));
        }
        return -1;
    }

    public int getTotalBirthsRankedHigher(String name, String gender){

        int result = 0;
        FileResource fileResource = new FileResource();
        int level = getBirthByName(fileResource.getCSVParser(false),name,gender);
        for(CSVRecord record : fileResource.getCSVParser(false)){
            int countOtherBirths;
            if(record.get(1).equals(gender) && !record.get(0).equals(name)){
                    countOtherBirths = getBirthByName(fileResource.getCSVParser(false),record.get(0),gender);
                    if( countOtherBirths >= level)
                        result += countOtherBirths;
            }
        }
        return result;
    }
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher("Ethan","M"));
    }


}
