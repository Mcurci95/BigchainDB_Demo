package demo;


import Connections.BigChainDBDriver;
import Connections.MongoDBDriver;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.util.Base58;
import models.Weather;
import org.bson.Document;
import utils.WeatherFileReader;

import java.io.File;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        String databaseFlag = "MongoDB"; // Set this to BigChainDB or MongoDB;
        String fileLocation = "/Users/marcocurci/Desktop/BigChainDB/files/";
        long start;
        long end;
        int inserts = 0;

        File fileDir = new File(fileLocation);
        List<Weather> weatherList = new ArrayList<Weather>();
        WeatherFileReader weatherFileReader = new WeatherFileReader();
        weatherList = weatherFileReader.ReadDir(fileDir);

        if (databaseFlag.equals("BigChainDB")) {
            BigChainDBDriver demo = new BigChainDBDriver();
            demo.SetConfig();

            KeyPair keys = demo.getKeys();

            System.out.println(Base58.encode(keys.getPublic().getEncoded()));
            System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

            MetaData metaData = new MetaData();
            metaData.setMetaData("", "");

            List<Map<String, String>> weatherAssets = new ArrayList<Map<String, String>>();


            for (Weather weather : weatherList) {
                weatherAssets.add(weather.transformIntoAsset());
            }

            start = System.nanoTime();
            for (Map<String, String> weatherAsset : weatherAssets) {
                demo.create(weatherAsset, metaData, keys);
                inserts++;
            }
            end = System.nanoTime();
            System.out.println("Inserts: " + inserts + ". Time in NanoSeconds: " + (end - start));
        } else if (databaseFlag.equals("MongoDB")) {
            MongoDBDriver mongo = new MongoDBDriver();
            mongo.connect();

            List<Document> weatherDocument = new ArrayList<Document>();


            for (Weather weather : weatherList) {
                weatherDocument.add(weather.transformIntoDocument());
            }

            start = System.nanoTime();
            for (Document weatherDoc : weatherDocument) {
                mongo.insert(weatherDoc);
                inserts++;
            }
            end = System.nanoTime();
            long firstInsert = (end - start);

            start = System.nanoTime();
            mongo.insertMany(weatherDocument);
            end = System.nanoTime();
            long insertMany = (end - start);


            System.out.println("MongoDB Inserts: " + inserts + ". Time in NanoSeconds: " + firstInsert);
            System.out.println("MongoDB insertMany function Time in NanoSeconds: " + insertMany );


        } else {
            System.out.println("Please change database flag to BigChainDB or MongoDB");
        }
    }

}
