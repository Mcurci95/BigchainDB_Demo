package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WeatherFileReader {
    public void ReadFile(String fileName) throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader(fileName);

        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;

        while ((nextRecord = csvReader.readNext()) != null) {
            for (String cell : nextRecord) {
                System.out.println(cell + " \t");
            }
        }
    }
}
