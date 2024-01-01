package be.mvpee.manager;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import org.bukkit.event.Listener;

public class DbManager implements Listener {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection players;

    public DbManager(String urlConnexion, String dbName, String collectionName) {
        this.client = MongoClients.create(new ConnectionString(urlConnexion));
        this.database = this.client.getDatabase(dbName);
        this.players = this.database.getCollection(collectionName);
    }

    public MongoClient getClient() {
        return this.client;
    }

    public MongoCollection getPlayers() {
        return this.players;
    }

    public MongoDatabase getDatabase() {
        return this.database;
    }

}