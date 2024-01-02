package be.mvpee.command;

import be.mvpee.manager.PermissionsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermCommand implements CommandExecutor{

    private PermissionsManager perm;

    public PermCommand(PermissionsManager perm) {
        this.perm = perm;
    }

    /*
            /perm set <pseudo> <rank>
     */

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (perm.hasPerm(player, 4, false)) {
                if (args.length == 3) {
                    if (args[0].equalsIgnoreCase("set")) {
                        Player target = Bukkit.getPlayer(args[1]);
                        if (target != null) {
                            boolean result = perm.setPerm(target.getUniqueId(), args[2]);
                            if (result)
                                player.sendMessage(
                                        ChatColor.GREEN + "You have set " + ChatColor.WHITE + ChatColor.BOLD + target.getName()
                                        + ChatColor.GREEN + " to " + perm.getPermsColor(target) + ChatColor.BOLD + args[2] + "."
                                );
                            else
                                player.sendMessage(ChatColor.RED + "This rank doesn't exist.");
                        }
                        else {
                            player.sendMessage(ChatColor.RED + "This player is not online.");
                        }
                    } else {
                        player.sendMessage(ChatColor.GRAY + "Usage: /perm set <pseudo> <rank>.");
                    }
                } else {
                    player.sendMessage(ChatColor.GRAY + "Usage: /perm set <pseudo> <rank>.");
                }
            }
        } else {
            if (args.length == 3) {
                if (args[0].equalsIgnoreCase("set")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target != null) {
                        boolean result = perm.setPerm(target.getUniqueId(), args[2]);
                        if (result)
                            System.out.println("You have set " + target.getName() + " to " + args[2] + ".");
                        else
                            System.out.println("This rank doesn't exist.");
                    }
                    else {
                        System.out.println("This player is not online.");
                    }
                } else {
                    System.out.println("Usage: /perm set <pseudo> <rank>.");
                }
            } else {
                System.out.println("Usage: /perm set <pseudo> <rank>.");
            }
        }
        return true;
    }



}
