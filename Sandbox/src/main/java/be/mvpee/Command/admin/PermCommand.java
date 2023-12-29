package be.mvpee.Command.admin;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor {

    private final PermissionsManager permissionsManager;

    public PermCommand(PermissionsManager permissionsManager) {

        this.permissionsManager = permissionsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || permissionsManager.getPermissions(player.getName()) == 4) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.GRAY + "Usage: /perm <info> " + ChatColor.WHITE + ChatColor.BOLD + "|" + ChatColor.GRAY +" <set> <pseudo>");
                    return false;
                }
                if (args[0].equalsIgnoreCase("set")) {
                    if (args.length == 3) {
                        permissionsManager.setPermissions(args[1], Integer.parseInt(args[2]));
                    }
                    else {
                        player.sendMessage(ChatColor.GRAY + "Usage: /perm set <pseudo> <code>");
                    }
                }
                else if (args[0].equalsIgnoreCase("info")) {
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.WHITE + args[1] + ChatColor.GOLD + " has the perm: " + ChatColor.RED + "0" + permissionsManager.getPermissions(args[1]));
                    }
                    else {
                        player.sendMessage(ChatColor.GRAY + "Usage: /perm info <pseudo>");
                    }
                }
                else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /perm <info> " + ChatColor.WHITE + ChatColor.BOLD + "|" + ChatColor.GRAY +" <set> <pseudo>");
                }
            }
            else {
                player.sendMessage(ChatColor.RED + "You don't have the permissions for this command.");
            }
        }
        return true;
    }
}
