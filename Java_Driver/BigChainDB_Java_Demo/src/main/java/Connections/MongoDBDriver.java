package Connections;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import config.Constants;

public class MongoDBDriver {

    MongoClient mongoClient;
    MongoDatabase database;

    public void run() {
        connect();
        database.getCollection("weather");

    }

    public void connect() {
        this.mongoClient = MongoClients.create(
                "mongodb+srv://mongo:" + Constants.MONGO_PASS + "@devconnector-deczi.mongodb.net/test?retryWrites=true&w=majority");
        this.database = mongoClient.getDatabase("cecs574");
    }




}
