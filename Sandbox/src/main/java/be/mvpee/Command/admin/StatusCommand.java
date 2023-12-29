package be.mvpee.Command.admin;

import be.mvpee.manager.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.Set;

public class StatusCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final PlayerManager playerManager;

    public StatusCommand(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerManager = new PlayerManager(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || playerManager.getPermissions(player.getName()) >= 3) {
                File playersFile = new File(plugin.getDataFolder(), "players.yml");
                FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);

                ConfigurationSection playersSection = playersConfig.getConfigurationSection("players");

                if (playersSection == null) {
                    player.sendMessage(ChatColor.RED + "No players found.");
                    return true;
                }

                Set<String> playerUUIDs = playersSection.getKeys(false);

                for (String uuid : playerUUIDs) {
                    ConfigurationSection playerSection = playersSection.getConfigurationSection(uuid);

                    if (playerSection != null) {
                        String playerName = playerSection.getString("pseudo");
                        String permissions = playerSection.getString("permissions.name");
                        String firstJoin = playerSection.getString("firstJoin");
                        String lastJoin = playerSection.getString("lastJoin");
                        String money = playerSection.getString("money");

                        player.sendMessage(" ");
                        player.sendMessage("Player: " + playerName);
                        player.sendMessage("Permissions: " + permissions);
                        player.sendMessage("First joined: " + firstJoin);
                        player.sendMessage("Last joined: " + lastJoin);
                        player.sendMessage("Money: " + money);
                        player.sendMessage(" ");
                    }
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You don't have the permission for this command.");
            }
        }
        return true;
    }
}
