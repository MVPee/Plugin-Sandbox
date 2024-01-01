package be.mvpee.manager;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PermissionsManager {

    private DbManager db;

    public PermissionsManager(DbManager db) {
        this.db = db;
    }

    public String getPerms(UUID playerUUID) {
        Document query = new Document("uuid", playerUUID.toString());

        FindIterable<Document> result = db.getPlayers().find(query);

        if (result != null && result.first() != null) {
            Document playerData = result.first();

            Object permissions = playerData.get("rank");
            if (permissions instanceof String)
                return (String) permissions;
        }
        return "Guest";
    }

    public int getPermsLevel(String rank) {
        switch(rank) {
            case "Guest":
                return 0;
            case "Assistant":
                return 1;
            case "Moderator":
                return 2;
            case "Admin":
                return 3;
            case "Owner":
                return 4;
        }
        return 0;
    }

    public ChatColor getPermsColor(Player player) {
        int code = getPermsLevel(getPerms(player.getUniqueId()));
        switch(code) {
            case 0:
                return ChatColor.DARK_GREEN;
            case 1:
                return ChatColor.DARK_PURPLE;
            case 2:
                return ChatColor.GOLD;
            case 3:
                return ChatColor.RED;
            case 4:
                return ChatColor.DARK_RED;
        }
        return ChatColor.DARK_GREEN;
    }

    public void sendNoPermission(Player player) {
        player.sendMessage(ChatColor.RED + "You don't have the permission.");
    }

    public boolean hasPerm(Player player, int level) {
        if (getPermsLevel(getPerms(player.getUniqueId())) >= level)
            return true;
        sendNoPermission(player);
        return false;
    }

}
