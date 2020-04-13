package demo;


import models.Weather;
import utils.WeatherFileReader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        DemoDriver demo = new DemoDriver();
////
////        demo.run();;


        // Weather file
        List<Weather> weatherList = new ArrayList<Weather>();
        WeatherFileReader weatherFileReader = new WeatherFileReader();
        weatherList = weatherFileReader.ReadFile("src/main/java/resources/01002099999.csv");
        for (Weather weather: weatherList) {
            System.out.println(weather);
        }

    }
}
