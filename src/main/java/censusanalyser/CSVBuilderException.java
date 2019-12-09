package censusanalyser;

public class CSVBuilderException extends Exception {

    public CSVBuilderException(String message, ExceptionType type) {
        super(message);
        this.type=type;
    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE;
    }

    ExceptionType type;
}
