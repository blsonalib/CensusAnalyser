package censusanalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class SortedField {

    static Map<Field, Comparator> sortFieldComparator = new HashMap<>();

    enum Field {
        STATE, POPULATION, AREA, DENSITY;
    }

    SortedField() {

    }

    public static Comparator getField(SortedField.Field field) {

        Comparator<CensusDAO> stateComparator = Comparator.comparing(census -> census.state);
        Comparator<CensusDAO> areaComparator = Comparator.comparing(census -> census.totalArea, Comparator.reverseOrder());
        Comparator<CensusDAO> populationComparator = Comparator.comparing(census -> census.population, Comparator.reverseOrder());
        Comparator<CensusDAO> densityComparator = Comparator.comparing(census -> census.populationDensity, Comparator.reverseOrder());

        sortFieldComparator.put(Field.STATE, stateComparator);
        sortFieldComparator.put(Field.POPULATION, populationComparator);
        sortFieldComparator.put(Field.AREA, areaComparator);
        sortFieldComparator.put(Field.DENSITY, densityComparator);

        Comparator<CensusDAO> daoComparator = sortFieldComparator.get(field);
        return daoComparator;
    }
}
