package censusanalyser;

public class CSVBuilderFactory {
    public static ICSVBuilder createCsvbuilder() {
       return new OpenCsvBuilder();
       // return new CommonCSVBuilder();
    }
}