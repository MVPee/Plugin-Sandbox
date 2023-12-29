package be.mvpee.Command.admin;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Set;

public class StatusCommand implements CommandExecutor {

    private final JavaPlugin plugin;
    private final PermissionsManager permissionsManager;

    public StatusCommand(JavaPlugin plugin, PermissionsManager permissionsManager) {
        this.plugin = plugin;
        this.permissionsManager = permissionsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || permissionsManager.getPermissions(player.getName()) >= 3) {
                File playersFile = new File(plugin.getDataFolder(), "players.yml");
                FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(playersFile);

                ConfigurationSection playersSection = playersConfig.getConfigurationSection("players");

                if (playersSection == null) {
                    player.sendMessage(ChatColor.RED + "No players found.");
                    return true;
                }

                Set<String> playerUUIDs = playersSection.getKeys(false);

                player.sendMessage(" \n" + ChatColor.RED + ChatColor.BOLD + "STATUS\n" + " ");
                for (String uuid : playerUUIDs) {
                    ConfigurationSection playerSection = playersSection.getConfigurationSection(uuid);

                    if (playerSection != null) {
                        String playerName = playerSection.getString("pseudo");
                        String permissions = playerSection.getString("permissions.name");
                        String firstJoin = playerSection.getString("firstJoin");
                        String lastJoin = playerSection.getString("lastJoin");
                        String money = playerSection.getString("money");

                        player.sendMessage(" ");
                        player.sendMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + "Player: " + playerName);
                        player.sendMessage(ChatColor.DARK_RED + "Permissions: " + ChatColor.BOLD +  permissions);
                        player.sendMessage(ChatColor.GREEN + "First joined: " + ChatColor.WHITE + firstJoin);
                        player.sendMessage(ChatColor.DARK_GREEN + "Last joined: " + ChatColor.WHITE + lastJoin);
                        player.sendMessage(ChatColor.YELLOW + "Money: " + money);
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
