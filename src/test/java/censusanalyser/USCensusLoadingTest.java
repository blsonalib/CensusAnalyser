package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class USCensusLoadingTest {

    private static final String US_CENSUS_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusData.csv";
    private static final String US_CENSUS_PATH_FOR_WRONG_DELIMETER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusDataForWrongDelimeter.csv";
    private static final String US_CENSUS_PATH_WITHOUT_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusDataForWithoutHeader.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecordsOfUSCensusData() throws CensusAnalyserException {
        CensusAdapter censusAdapter = new USCensusAdapter();
        Map<String, CensusDAO> records = censusAdapter.loadCensusData(US_CENSUS_PATH);
        Assert.assertEquals(51, records.size());
    }

    @Test
    public void givenUSCensusData_WhenWrongDelimeter_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(US_CENSUS_PATH_FOR_WRONG_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenUSCensusData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            indiaCensusAdapter.loadCensusData(US_CENSUS_PATH_WITHOUT_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenUSCensusCSVFile_WhenPassingWrongFilePath_ShouldReturnThrowException() {
        try {
            CensusAdapter indiaCensusAdapter = new IndiaCensusAdapter();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            Map<String, CensusDAO> records = indiaCensusAdapter.loadCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
}