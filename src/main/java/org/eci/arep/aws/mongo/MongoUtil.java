package org.eci.arep.aws.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String CONNECTION_STRING = "mongodb://ec2-174-129-94-199.compute-1.amazonaws.com:27017";
    private static final String DATABASE_NAME = "msgDB";

    /**
     * Método que retorna la dirección a la base de datos
     * @return dirección a la base de datos
     */
    public static MongoDatabase getDB() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        return client.getDatabase(DATABASE_NAME);
    }
}
