package softwareproblems.week3.parsingexportdata;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ParsingExportData {

    public CSVParser tester(){
        FileResource fileResource = new FileResource();
        return fileResource.getCSVParser();

    }

    public String countryInfo(CSVParser parser, String country){

        for(CSVRecord record : parser){
            String countryRecord = record.get("Country");
            if(countryRecord.contains(country))
                return country + ": "+record.get("Exports")+": "+record.get("Value (dollars)");
        }
        return country+"NOT FOUND";
    }

    public  void listExportersTwoProducts(CSVParser parser,String exportItem1, String exportItem2){

        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem1) && export.contains(exportItem2))
                System.out.println(record.get("Country"));
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){

        int countCountry = 0;
        for(CSVRecord record : parser){
            String export = record.get("Exports");
            if(export.contains(exportItem))
                countCountry++;
        }
        return countCountry;
    }
    public void bigExporters(CSVParser parser, String amount){

        int amountLength = amount.length();
        for(CSVRecord record : parser){
            String valueOfExport = record.get("Value (dollars)");
            if(valueOfExport.length() > amountLength)
                System.out.println(record.get("Country")+" "+valueOfExport);
        }
    }

}
