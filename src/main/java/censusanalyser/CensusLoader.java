package censusanalyser;

import com.bridgelabz.censusanalyser.CSVBuilderException;
import com.bridgelabz.censusanalyser.CSVBuilderFactory;
import com.bridgelabz.censusanalyser.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {

    Map<String, CensusDAO> censusStateMap = null;

    public <E> Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country,String...csvFilePath) throws CensusAnalyserException {
        if(country.equals(CensusAnalyser.Country.INDIA))
            return this.loadCensusData(IndiaCensusCSV.class,csvFilePath);
       else if(country.equals(CensusAnalyser.Country.US))
            return this.loadCensusData(USCensusCSV.class, csvFilePath);
       else
           throw new CensusAnalyserException("Incorrect Country",CensusAnalyserException.ExceptionType.IN_WRONG_COUNTRY);
    }


    @Override
    public Map<String, CensusDAO> loadCensusData(String... csvFilePath) {
        return null;
    }

    public <E> Map<String, CensusDAO> loadCensusData(Class<E> censusCSVClass, String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDAO> censusStateMap = new HashMap<>();
       /* try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))) {
            ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
            Iterator<E> csvIterator = csvbuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (censusCSVClass.getName().equals("censusanalyser.IndiaCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCsv -> censusStateMap.put(censusCsv.state, new CensusDAO(censusCsv)));
            } else if (censusCSVClass.getName().equals("censusanalyser.USCensusCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCsv -> censusStateMap.put(censusCsv.state, new CensusDAO(censusCsv)));
            }*/

            if (csvFilePath.length == 1) return censusStateMap;
            this.loadIndiaStateCode(censusStateMap, csvFilePath[1]);
            return censusStateMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }


        private Map<String, CensusDAO> loadIndiaStateCode (Map < String, CensusDAO > censusStateMap, String s) throws CensusAnalyserException {
            try (Reader reader = Files.newBufferedReader(Paths.get(s));) {
                ICSVBuilder csvbuilder = CSVBuilderFactory.createCsvbuilder();
                Iterator<IndiaStateCodeCSV> stateCSVIterator = csvbuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
                Iterable<IndiaStateCodeCSV> csvIterable = () -> stateCSVIterator;
                StreamSupport.stream(csvIterable.spliterator(), false).
                        filter(csvState -> censusStateMap.get(csvState.stateName) != null).
                        forEach(csvState -> censusStateMap.get(csvState.stateName).stateCode = csvState.stateName);
                return censusStateMap;
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            } catch (CSVBuilderException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
            } catch (RuntimeException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.ISSUE_IN_FILE);
            }
        }
    }


