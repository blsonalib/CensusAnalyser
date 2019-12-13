package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

public class IndiaCensusLoadingTest {

    private static final String INDIA_STATECODE_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCode.csv";
    private static final String INDIA_CENSUS_CSV_FILE_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecords()  throws CensusAnalyserException {
        CensusAdapter censusAdapter=new IndiaCensusAdapter();
        Map <String,CensusDAO> records=censusAdapter.loadCensusData( INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATECODE_CSV_FILE_PATH);
        Assert.assertEquals(29,records.size());
    }

    @Test
    public void givenIndianCensusCSVFile_PassedSingleCSVFile_throwsException() {
        try {
            CensusAdapter indiaCensusAdapter=new IndiaCensusAdapter();
            Map<String, CensusDAO> records = indiaCensusAdapter.loadCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,records.size());
        } catch (CensusAnalyserException e) { }
    }
}
