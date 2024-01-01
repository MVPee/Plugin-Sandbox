package be.mvpee.manager;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.bson.Document;

import java.time.LocalDate;

public class DbManager implements Listener {

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection players;

    public DbManager(String urlConnexion, String dbName, String collectionName) {
        this.client = MongoClients.create(new ConnectionString(urlConnexion));
        this.database = this.client.getDatabase(dbName);
        this.players = this.database.getCollection(collectionName);
    }

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        ProxiedPlayer player = e.getPlayer();
        String playerUUID = player.getUniqueId().toString();

        Document query = new Document("uuid", playerUUID);
        Document existingPlayer = (Document) players.find(query).first();
        if (existingPlayer == null) {

            String currentDate = LocalDate.now().toString();

            Document document = new Document();
            document.put("uuid", playerUUID.toString());
            document.put("pseudo", player.getName());
            document.put("rank", "Guest");
            document.put("money", 500);
            document.put("level", 0);
            document.put("jump", 1);
            document.put("join", currentDate);
            players.insertOne(document);
        }
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
