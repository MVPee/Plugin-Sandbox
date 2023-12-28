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
                    " \n" +
                    ChatColor.RED + ChatColor.BOLD +    "MVPee Help\n" +
                    " \n"
            );
            for (CustomCommand command : CustomCommand.values()) {
                player.sendMessage(
                    ChatColor.GOLD.toString() + ChatColor.BOLD + command.getTitle() + " \n" +
                    ChatColor.GRAY + "   " + command.getUsage() + " \n" +
                    ChatColor.DARK_GRAY + "   " + command.getDescription() + " \n"
                );
            player.sendMessage(" \n");

            }
        }
        return false;
    }

}
