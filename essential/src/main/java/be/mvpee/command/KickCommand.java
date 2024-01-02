package be.mvpee.command;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

    private PermissionsManager perm;

    public KickCommand(PermissionsManager perm) {
        this.perm = perm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (perm.hasPerm(player, 1, false)) {
                if (args.length >= 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            message.append(args[i]);
                            if (i < args.length - 1) {
                                message.append(" ");
                            }
                        }
                        target.kickPlayer(ChatColor.GRAY + "You have been kick by " + perm.getPermsColor(player) + player.getName() + " \n \n" + ChatColor.RED + message);
                    } else {
                        player.sendMessage(ChatColor.RED + "This player is not online.");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /kick <pseudo>.");
                }
            }
        } else {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    StringBuilder message = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        message.append(args[i]);
                        if (i < args.length - 1) {
                            message.append(" ");
                        }
                    }
                    target.kickPlayer(ChatColor.GRAY + "You have been kick by " + ChatColor.RED + "The Console." + " \n \n" + ChatColor.RED + message);
                } else {
                    System.out.println("This player is not online.");
                }
            } else {
                System.out.println("Usage: /kick <pseudo>.");
            }
        }
        return false;
    }

}
