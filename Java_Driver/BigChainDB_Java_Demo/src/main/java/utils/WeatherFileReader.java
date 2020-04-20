package utils;


import models.Weather;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.exceptions.CsvValidationException;

public class WeatherFileReader {
    public List<Weather> ReadFile(String fileName) throws IOException, CsvValidationException {
        FileReader fileReader = new FileReader(fileName);

        CSVReader csvReader = new CSVReader(fileReader);
        csvReader.skip(1);
        String[] nextRecord;


        List<Weather> weatherList = new ArrayList<Weather>();
        while ((nextRecord = csvReader.readNext()) != null) {
            Weather weather = new Weather(nextRecord);
            weatherList.add(weather);
        }
        return weatherList;
    }

    public List<Weather> ReadDir(File dirName) throws IOException, CsvValidationException {
//        File[]  files = dirName.listFiles();
        File[] files = dirName.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });

        List<List<Weather>> listOfWeatherLists = new ArrayList<List<Weather>>();
        List<Weather> weatherList = new ArrayList<Weather>();
        for (File file : files) {
            System.out.println(file.toString());
            listOfWeatherLists.add(ReadFile(file.toString()));
        }

        for (List<Weather> lists : listOfWeatherLists) {
            for (Weather weather : lists) {
                weatherList.add(weather);
            }
        }

        return weatherList;
    }


}
