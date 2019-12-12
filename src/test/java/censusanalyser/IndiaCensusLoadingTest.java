package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IndiaCensusLoadingTest {

    private static final String INDIA_STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";
    private static final String US_CENSUS_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords()  throws CensusAnalyserException {
        CensusAdapter censusAdapter=new IndiaCensusAdapter();
        Map <String,CensusDAO> records=censusAdapter.loadCensusData( INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        Assert.assertEquals(29,records.size());
    }

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecordsOfUSCensusData()  throws CensusAnalyserException {
        CensusAdapter censusAdapter=new USCensusAdapter();
        Map <String,CensusDAO> records=censusAdapter.loadCensusData(US_CENSUS_PATH);
        Assert.assertEquals(51,records.size());
    }
}
