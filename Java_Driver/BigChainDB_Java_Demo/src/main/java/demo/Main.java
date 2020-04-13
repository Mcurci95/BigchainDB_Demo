package demo;


import models.Weather;
import utils.WeatherFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        DemoDriver demo = new DemoDriver();
////
////        demo.run();;

        // Time for 365 inserts in Nano seconds: 2409386068
        // Weather file
        List<Map<String, String>> weatherList = new ArrayList<Map<String, String>>();
        WeatherFileReader weatherFileReader = new WeatherFileReader();

        weatherList = weatherFileReader.ReadFile("src/main/java/resources/01002099999.csv");
        demo.run(weatherList);
    }
}
