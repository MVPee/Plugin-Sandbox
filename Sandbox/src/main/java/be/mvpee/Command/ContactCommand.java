package be.mvpee.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class ContactCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(
                    ChatColor.RED.toString() + ChatColor.BOLD + "\nDiscord: " + ChatColor.WHITE + ".mvpee\n" +
                    ChatColor.RED + ChatColor.BOLD + "Mail: " + ChatColor.WHITE + "me.mvpee.be\n"
            );
        }
        return false;
    }

}