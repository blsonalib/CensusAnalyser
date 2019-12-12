package censusanalyser;

class CensusDAO {

    public String state;
    public String stateCode;
    public int population;
    public double populationDensity;
    public double totalArea;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        population = indiaCensusCSV.population;
        populationDensity = indiaCensusCSV.populationDencity;
        totalArea = indiaCensusCSV.totalArea;
    }

    public CensusDAO(USCensusCSV censusCSV) {

        state = censusCSV.state;
        stateCode = censusCSV.stateId;
        population = censusCSV.population;
        populationDensity = censusCSV.populationDencity;
        totalArea = censusCSV.totalArea;
    }
}
