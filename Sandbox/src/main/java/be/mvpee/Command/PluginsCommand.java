package be.mvpee.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            player.sendMessage(
                    ChatColor.RED.toString() + ChatColor.BOLD + "Github: " + ChatColor.WHITE + "https://github.com/MVPee\n" +
                    ChatColor.RED + ChatColor.BOLD + "Project: " + ChatColor.WHITE + "https://github.com/MVPee/Plugin-Sandbox\n"
            );
        }
        return false;
    }

}
