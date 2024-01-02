package be.mvpee.command;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {

    private PermissionsManager perm;

    public BanCommand(PermissionsManager perm) {
        this.perm = perm;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (perm.hasPerm(player, 2, false)) {
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
                        String reason = ChatColor.GRAY + "You have been ban by " + perm.getPermsColor(player) + player.getName() + " \n \n" + ChatColor.RED + message;
                        target.kickPlayer(reason);
                        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, null);
                    } else {
                        player.sendMessage(ChatColor.RED + "This player is not online.");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /ban <pseudo>.");
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
                    String reason = ChatColor.GRAY + "You have been ban by " + ChatColor.RED + "The Console." + " \n \n" + ChatColor.RED + message;
                    target.kickPlayer(reason);
                    Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), reason, null, null);
                } else {
                    System.out.println("This player is not online.");
                }
            } else {
                System.out.println("Usage: /ban <pseudo>.");
            }
        }
        return false;
    }
}
