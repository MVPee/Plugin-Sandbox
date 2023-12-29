package be.mvpee.manager;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class PlayerManager implements Listener {

    private final JavaPlugin plugin;
    private FileConfiguration playersConfig;
    private File playersFile;

    public PlayerManager(JavaPlugin plugin) {
        this.plugin = plugin;
        playersFile = new File(plugin.getDataFolder(), "players.yml");

        if (!playersFile.exists()) {
            plugin.saveResource("players.yml", false);
        }

        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        if (!playersConfig.contains("players." + uuid)) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            playersConfig.set("players." + uuid + ".pseudo", player.getName());
            playersConfig.set("players." + uuid + ".permissions" + ".name", "Member");
            playersConfig.set("players." + uuid + ".permissions" + ".code", 0);
            playersConfig.set("players." + uuid + ".money", 500);
            playersConfig.set("players." + uuid + ".firstJoin", formattedDateTime);
            playersConfig.set("players." + uuid + ".lastJoin", formattedDateTime);

            try {
                playersConfig.save(playersFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);

            playersConfig.set("players." + uuid + ".lastJoin", formattedDateTime);

            try {
                playersConfig.save(playersFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getNameWithCode(int code) {
        switch (code) {
            case 0:
                return "Member";
            case 1:
                return "Assistant";
            case 2:
                return "Moderator";
            case 3:
                return "Admin";
            case 4:
                return "Owner";
        }
        return "undefind";
    }

    public ChatColor getColorWithCode(int code) {
        switch (code) {
            case 0:
                return ChatColor.WHITE;
            case 1:
                return ChatColor.LIGHT_PURPLE;
            case 2:
                return ChatColor.GOLD;
            case 3:
                return ChatColor.RED;
            case 4:
                return ChatColor.DARK_RED;
        }
        return ChatColor.RED;
    }

}