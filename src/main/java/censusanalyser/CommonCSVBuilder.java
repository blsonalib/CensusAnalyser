package censusanalyser;




import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class CommonCSVBuilder<E> implements ICSVBuilder {
    @Override
    public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        return (Iterator<E>) this.CommonCSVBuilder(reader, csvClass).iterator();
    }

    @Override
    public List<E>getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException {

        try {
            return (List) this.CommonCSVBuilder(reader, csvClass).getRecords().listIterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private CSVParser CommonCSVBuilder(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
           CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
           return csvParser;
        } catch (IllegalStateException | IOException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}