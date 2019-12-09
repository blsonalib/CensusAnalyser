package censusanalyser;

public class IndiaCensusDAO {

    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;

    public IndiaCensusDAO(IndiaCensusCSV indiaCensusCSV){
        state=indiaCensusCSV.state;
        population=indiaCensusCSV.population;
        densityPerSqKm=indiaCensusCSV.densityPerSqKm;
        areaInSqKm=indiaCensusCSV.areaInSqKm;
    }
}
