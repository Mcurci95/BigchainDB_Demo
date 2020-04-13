package utils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.Weather;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherFileReader {
    public List<Weather> ReadFile(String fileName) throws  IOException, CsvValidationException {
        FileReader fileReader = new FileReader(fileName);

        CSVReader csvReader = new CSVReader(fileReader);
        String[] nextRecord;


        List<Weather> weatherList = new ArrayList<Weather>();
        while ((nextRecord = csvReader.readNext()) != null) {
            Weather weather = new Weather(nextRecord);
            weatherList.add(weather);
        }
        return weatherList;
    }
}
