package be.mvpee.menu.tools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.ChatColor;

public class ToolsMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(player, 9, ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu!");

            inv.setItem(0, Tools.CREATIVE.getItem());
            inv.setItem(1, Tools.SURVIVAL.getItem());
            inv.setItem(2, Tools.ADVENTURE.getItem());
            inv.setItem(8, Tools.CLOSE.getItem());

            player.openInventory(inv);
        }
        return false;
    }

}
