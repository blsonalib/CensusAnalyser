package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class IndiaCensusLoadingTest {

    private static final String INDIA_STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForDelimeter.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForHeader.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH_FOR_DELIMETER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCodeForDelimeter.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH_FOR_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCodeForHeader.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords() throws CensusAnalyserException {
        CensusAdapter censusAdapter = new IndiaCensusAdapter();
        Map<String, CensusDAO> records = censusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        Assert.assertEquals(29, records.size());
    }

    @Test
    public void givenIndianCensusCSVFile_PassedSingleCSVFile_throwsException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            Map<String, CensusDAO> records = indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, records.size());
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenPassingWrongFile_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            Map<String, CensusDAO> records = indiaCensusAdapter.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenWrongDelimeter_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenWrongDelimeter_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData( INDIA_STATECODE_CSV_FILE_PATH_FOR_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(INDIA_STATECODE_CSV_FILE_PATH_FOR_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }
}
