package uk.ac.ucl.model;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DataLoader {

    private DataFrame dataFrame;

    public DataLoader() {
        this.dataFrame = new DataFrame();
    }

    public DataFrame loadCSVData(String fileName) {
        Reader reader = null;
        CSVParser csvParser = null;

        try {
            reader = new FileReader(fileName);
            // Used to ensure that the order of headers is maintained
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            Map<String, Integer> headerMap = csvParser.getHeaderMap();

            // The LinkedHashMap is used in order to maintain the order the data is added in
            LinkedHashMap<String, Integer> orderedHeaderMap = headerMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

            // This initialises the DataFrame columns with header names
            for (String columnName : orderedHeaderMap.keySet()) {
                this.dataFrame.addColumn(new Column(columnName));
            }

            // this iterates over all records and fills the DataFrame
            for (CSVRecord csvRecord : csvParser) {
                for (String columnName : orderedHeaderMap.keySet()) {
                    String value = csvRecord.get(columnName);
                    this.dataFrame.addValue(columnName, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (csvParser != null) csvParser.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.dataFrame;
    }
}