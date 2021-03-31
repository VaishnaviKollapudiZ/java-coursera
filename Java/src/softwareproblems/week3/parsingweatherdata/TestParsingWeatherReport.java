package softwareproblems.week3.parsingweatherdata;

public class TestParsingWeatherReport {

    public static void main(String[] args){

        ParsingWeatherReport test = new ParsingWeatherReport();

        test.testColdestHourInFile();

        test.testFileWithColdestTemperature();

        test.testLowestHumidityInFile();

        test.testLowestHumidityInManyFiles();

        test.testAverageTemperatureInFile();

        test.testAverageTemperatureWithHighHumidityInFile();
    }
}
