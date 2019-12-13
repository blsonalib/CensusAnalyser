package censusanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class USCensusLoadingTest {

    private static final String US_CENSUS_PATH = "/home/admin1/IdeaProjects/CensusAnalyser/src/test/resources/USCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_ReturnsCorrectRecordsOfUSCensusData()  throws CensusAnalyserException {
        CensusAdapter censusAdapter=new USCensusAdapter();
        Map<String,CensusDAO> records=censusAdapter.loadCensusData(US_CENSUS_PATH);
        Assert.assertEquals(51,records.size());
    }
}
