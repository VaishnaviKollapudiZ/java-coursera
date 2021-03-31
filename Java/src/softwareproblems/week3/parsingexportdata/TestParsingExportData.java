package softwareproblems.week3.parsingexportdata;

import org.apache.commons.csv.CSVParser;

public class TestParsingExportData {

    public static void main(String[] args){

        ParsingExportData parsingExportData = new ParsingExportData();
        CSVParser parser = parsingExportData.tester();

        System.out.println(parsingExportData.countryInfo(parser,"Peru"));

        parsingExportData.listExportersTwoProducts(parser,"gold","diamond");

        System.out.println(parsingExportData.numberOfExporters(parser,"sugar"));

        parsingExportData.bigExporters(parser," $999,999,999,999");

    }
}
