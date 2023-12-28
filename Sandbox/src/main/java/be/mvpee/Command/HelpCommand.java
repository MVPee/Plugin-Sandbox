package be.mvpee.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(
                    "" +
                    ChatColor.RED + ChatColor.BOLD +    "MVPee Help\n\n" +
                    ChatColor.GOLD + ChatColor.BOLD +   "Help Menu\n" +
                    ChatColor.GRAY +                    "   usage: /help\n" +
                    ChatColor.DARK_GRAY +               "   This beautiful menu.\n\n" +
                    ChatColor.GOLD + ChatColor.BOLD +   "Tools Menu\n" +
                    ChatColor.GRAY +                    "   usage: /tools\n" +
                    ChatColor.DARK_GRAY +               "   Tools menu for everyone.\n\n"
            );
        }
        return false;
    }

}
