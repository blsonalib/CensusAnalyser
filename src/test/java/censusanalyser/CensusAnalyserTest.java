package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH_EMPTY = " ";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForDelimeter.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForHeader.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH_FOR_DELIMETER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCodeForDelimeter.csv";
    private static final String INDIA_STATECODE_CSV_FILE_PATH_FOR_HEADER = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCodeForHeader.csv";
    private static final String US_CENSUS_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenUSCensusData_ReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATECODE_CSV_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndianStateCsv_ShouldReturnExactCount() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfStateCode = 0;
        try {
            numOfStateCode = censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATECODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(29, numOfStateCode);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenWrongDelimeter_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithEmptyFile_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH_EMPTY);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIndiaStateCode_WhenWrongDelimeter_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_STATECODE_CSV_FILE_PATH_FOR_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER,INDIA_STATECODE_CSV_FILE_PATH_FOR_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ISSUE_IN_FILE, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(SortedField.Field.STATE);
            IndiaCensusCSV[] censuCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censuCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenAddStateCode_ShouldReturnStateCode() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH,INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(SortedField.Field.STATE);
            IndiaCensusCSV[] censuCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censuCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedByPopulation_ShouldReturnSortedPopulation() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(SortedField.Field.POPULATION);
            IndiaCensusCSV[] censuCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censuCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedByAreaDencity_ShouldReturnSortedAreaDencity() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(SortedField.Field.AREA);
            IndiaCensusCSV[] censuCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censuCSVS[0].state);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUsCensusData_ShouldReturnCorrectRecords() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int count = censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_PATH);
        Assert.assertEquals(51, count);
    }
}