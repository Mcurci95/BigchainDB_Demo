package utils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import models.Weather;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherFileReader {
    public List<Map<String, String>> ReadFile(String fileName) throws  IOException, CsvValidationException {
        FileReader fileReader = new FileReader(fileName);

        CSVReader csvReader = new CSVReader(fileReader);
        csvReader.skip(1);
        String[] nextRecord;


        List<Map<String, String>> weatherList = new ArrayList<Map<String, String>>();
        while ((nextRecord = csvReader.readNext()) != null) {
            Weather weather = new Weather(nextRecord);
            weatherList.add(weather.transformIntoAsset());
        }
        return weatherList;
    }
}
