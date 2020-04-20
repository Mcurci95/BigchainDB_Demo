package Connections;

import com.mongodb.DBCollection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import config.Constants;
import models.Weather;
import org.bson.Document;

import java.util.List;

public class MongoDBDriver  {

    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection collection;
    MongoCollection manyCollection;

    public void run() {
        connect();
        database.getCollection("weather");
    }

    public void connect() {
//        this.mongoClient = MongoClients.create(
//                "mongodb+srv://" + Constants.MONGO_USER + ":" + Constants.MONGO_PASS + "@devconnector-deczi.mongodb.net/test?retryWrites=true&w=majority");
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("cecs574");
        this.collection =  database.getCollection("weather");
        this.manyCollection = database.getCollection("weather_many");
    }


    public void insert(Document weatherDoc) {
        collection.insertOne(weatherDoc);
    }

    public void insertMany(List<Document> weatherDocList) {
        this.manyCollection.insertMany(weatherDocList);
    }

}
