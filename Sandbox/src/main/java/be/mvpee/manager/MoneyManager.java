package be.mvpee.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class MoneyManager {

    private final JavaPlugin plugin;
    private FileConfiguration playersConfig;
    private File playersFile;
    private PlayerManager playerManager;

    public MoneyManager(JavaPlugin plugin, PlayerManager playerManager) {
        this.plugin = plugin;
        this.playerManager = playerManager;
        playersFile = new File(plugin.getDataFolder(), "players.yml");

        if (!playersFile.exists()) {
            plugin.saveResource("players.yml", false);
        }

        playersConfig = YamlConfiguration.loadConfiguration(playersFile);
    }

    public int getMoney(String pseudo) {
        for (String key : playersConfig.getConfigurationSection("players").getKeys(false)) {
            String playerName = playersConfig.getString("players." + key + ".pseudo");
            if (playerName != null && playerName.equalsIgnoreCase(pseudo)) {
                return playersConfig.getInt("players." + key + ".money", 0);
            }
        }
        return -1;
    }

    public void setMoney(String pseudo, int amount) {
        for (String key : playersConfig.getConfigurationSection("players").getKeys(false)) {
            String playerName = playersConfig.getString("players." + key + ".pseudo");
            if (playerName != null && playerName.equalsIgnoreCase(pseudo)) {
                int currentMoney = playersConfig.getInt("players." + key + ".money");
                playersConfig.set("players." + key + ".money", currentMoney + amount);
                try {
                    playersConfig.save(playersFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

}
