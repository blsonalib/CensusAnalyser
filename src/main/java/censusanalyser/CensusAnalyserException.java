package censusanalyser;

public class CensusAnalyserException extends Exception {
    public CensusAnalyserException(String incorrect_country) {
    }

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, NO_CENCUS_DATA, DATA_NOT_FOUND, ISSUE_IN_FILE, IN_WRONG_COUNTRY,No_CENSUS_DATA;
    }
    ExceptionType type;

    public CensusAnalyserException(String message, String name) {
        super(message);
        this.type = ExceptionType.valueOf(name);
    }

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
