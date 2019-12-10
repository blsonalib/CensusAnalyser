package censusanalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String INDIA_StateCode_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER="/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForDelimeter.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER="/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusDataForHeader.csv";

    @Test
    public void givenIndianCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
         } catch (CensusAnalyserException e) {
                Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIndianStateCsv_ShouldReturnExactCount(){
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        int numOfStateCode= 0;
        try {
            numOfStateCode = censusAnalyser.loadIndiaStateCode(INDIA_StateCode_CSV_FILE_PATH );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(37,numOfStateCode);
        }
    }

    @Test
    public void givenIndiaCensusData_WhenWrongDelimer_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_DELIMETER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DATA_NOT_FOUND,e.type);
        }
    }

    @Test
    public void givenIndiaCensusData_WithoutHeader_ShouldReturnThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH_FOR_HEADER);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.DATA_NOT_FOUND,e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
         CensusAnalyser censusAnalyser = new CensusAnalyser();
         censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
         String  sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(INDIA_CENSUS_CSV_FILE_PATH);
         IndiaCensusCSV[] censuCSVS=new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
         Assert.assertEquals("Andhra Pradesh",censuCSVS[0].state);
    }
}
