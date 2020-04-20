package demo;


import Connections.BigChainDBDriver;
import Connections.MongoDBDriver;
import models.Weather;
import org.bson.Document;
import utils.WeatherFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        // QLDB 365 inserts: 22063110000
//        BigChainDBDriver demo = new BigChainDBDriver();
        MongoDBDriver mongo = new MongoDBDriver();
        mongo.run();

        // Time for 365 inserts in Nano seconds: 2409386068
        // Weather file
        List<Weather> weatherList = new ArrayList<Weather>();
        WeatherFileReader weatherFileReader = new WeatherFileReader();

        weatherList = weatherFileReader.ReadFile("src/main/java/resources/01002099999.csv");


        // BigChain Asset
//        List<Map<String, String>> weatherAsset = new ArrayList<Map<String, String>>();

        // MongoDB Document
        List<Document> weatherDocument = new ArrayList<Document>();

        for (Weather weather : weatherList) {
//            weatherAsset.add(weather.transformIntoAsset());
            weatherDocument.add(weather.transformIntoDocument());
        }

        long start = System.nanoTime();

        // Mongo time: 43530736541
        // Mongo connecting to local docker: 942546721
        for (Document weatherDoc : weatherDocument) {
            mongo.insert(weatherDoc);
        }

        long end = System.nanoTime();

        long total = end - start;
        System.out.println("Mongo Docker: " + total);

        // Mongo Insert many
        start = System.nanoTime();
        mongo.insertMany(weatherDocument);
        end = System.nanoTime();
        total = end - start;
        System.out.println("Many:" + total);
//        demo.run(weatherList);
    }
}
